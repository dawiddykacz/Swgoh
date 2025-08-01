const fs = require('node:fs');
const path = require('node:path');
const { Client, Collection, GatewayIntentBits, REST, Routes } = require('discord.js');

const clientId = '';
const guildId = '';
const token = '';

const client = new Client({
  intents: [GatewayIntentBits.Guilds]
});

client.commands = new Collection();

const commands = [];
const commandsPath = path.join(__dirname, 'commands');
const commandFiles = fs.readdirSync(commandsPath).filter(file => file.endsWith('.js'));

for (const file of commandFiles) {
  const command = require(`./commands/${file}`);
  client.commands.set(command.data.name, command);
  commands.push(command.data.toJSON());
}

const rest = new REST({ version: '10' }).setToken(token);

async function registerCommands() {
  try {
    console.log('Rejestruję slash komendy...');

    // Jeśli chcesz rejestrować globalnie (na wszystkich serwerach), użyj tej linii:
    // await rest.put(Routes.applicationCommands(clientId), { body: commands });

    // Jeśli chcesz rejestrować lokalnie (szybciej, tylko na jednym serwerze), użyj tej:
    await rest.put(Routes.applicationGuildCommands(clientId, guildId), { body: commands });

    console.log('✅ Komendy zarejestrowane');
  } catch (error) {
    console.error(error);
  }
}

client.once('ready', async () => {
  console.log(`Zalogowano jako ${client.user.tag}`);
  await registerCommands();
});

client.on('interactionCreate', async interaction => {
  if (!interaction.isChatInputCommand()) return;

  const command = client.commands.get(interaction.commandName);
  if (!command) return;

  try {
    await command.execute(interaction);
  } catch (error) {
    console.error(error);
    await interaction.reply({ content: 'Wystąpił błąd!', ephemeral: true });
  }
});

client.login(token);
