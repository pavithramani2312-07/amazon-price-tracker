package service;

import util.Constants;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TelegramService {

    public void sendTelegramMessage(String message) {

        try {

            String encodedMessage =
                    URLEncoder.encode(message, StandardCharsets.UTF_8);

            String url =
                    "https://api.telegram.org/bot"
                            + Constants.TELEGRAM_BOT_TOKEN
                            + "/sendMessage?chat_id="
                            + Constants.TELEGRAM_CHAT_ID
                            + "&text="
                            + encodedMessage;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

            client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Telegram message sent");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}