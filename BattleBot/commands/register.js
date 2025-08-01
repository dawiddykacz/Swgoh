const { SlashCommandBuilder } = require('discord.js');
const { sendRegister } = require('../components/connection'); 

module.exports = {
  data: new SlashCommandBuilder()
    .setName('register')
    .setDescription('Odpowiada wiadomością, którą podasz')
    .addStringOption(option =>
      option
        .setName('allycode')
        .setDescription('Rejestruje użytkownika')
        .setRequired(true)
    ),
  async execute(interaction) {
    const userId = interaction.user.id;
    const allyCode = interaction.options.getString('allycode');

    const response = await sendRegister(userId,allyCode);
    await interaction.reply(`<@${userId}>: ${response}`);
  },
};
