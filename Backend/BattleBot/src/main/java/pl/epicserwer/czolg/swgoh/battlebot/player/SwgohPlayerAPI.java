package pl.epicserwer.czolg.swgoh.battlebot.player;

import com.google.gson.*;
import lombok.NonNull;
import org.commons.AllyCode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class SwgohPlayerAPI {
    public boolean isExists(@NonNull final AllyCode allyCode){
        return this.sendRequest(allyCode) != null;
    }

    public List<SwgohHero> getHeroes(@NonNull final AllyCode allyCode){
        final JsonObject playerJson = this.sendRequest(allyCode);
        if(playerJson == null) return null;


        List<SwgohHero> heroes = new ArrayList<>();
        if(playerJson.has("units") && !playerJson.get("units").isJsonNull() &&
                playerJson.get("units") instanceof JsonArray heroesJsonArray){
            for (JsonElement jsonElement : heroesJsonArray) {
                JsonObject dataJson = jsonElement.getAsJsonObject().get("data").getAsJsonObject();

                String name = dataJson.get("name").getAsString();
                final int level = this.getInt(dataJson,"level");
                final int gear = this.getInt(dataJson,"gear_level");
                final int relic = this.getInt(dataJson,"relic_tier") - 1;
                final long power = this.getLong(dataJson,"power");

                SwgohHero swgohHero = new SwgohHero(name, level, gear, relic,power);
                heroes.add(swgohHero);
            }
        }

        return heroes;
    }

    public JsonObject sendRequest(@NonNull final AllyCode allyCode){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://swgoh.gg/api/player/"+allyCode+"/"))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement playerJson = gson.fromJson(response.body(), JsonElement.class);

            return playerJson.getAsJsonObject();
        } catch (IOException | InterruptedException | JsonSyntaxException ignore) {

        }

        return null;
    }

    private int getInt(@NonNull final JsonObject jsonObject,@NonNull final String key){
        if(!jsonObject.has(key) || jsonObject.get(key).isJsonNull()) return 0;
        return jsonObject.get(key).getAsInt();
    }
    private long getLong(@NonNull final JsonObject jsonObject,@NonNull final String key){
        if(!jsonObject.has(key) || jsonObject.get(key).isJsonNull()) return 0;
        return jsonObject.get(key).getAsLong();
    }
}
