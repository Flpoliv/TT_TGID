package com.empresa;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Notificacao {

    private static final String WEBHOOK_URL = "https://webhook.site/2420acfd-d3b4-42ef-b26a-e1ec10afbcfd";

    @SuppressWarnings("deprecation")
    public static void enviar(String email, String mensagem) throws IOException {
        URL url = new URL(WEBHOOK_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonPayload = "{\"email\":\"" + email + "\",\"mensagem\":\"" + mensagem + "\"}";
        
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Notificação enviada com sucesso.");
        } else {
            throw new IOException("Falha no envio da notificação. Código de resposta: " + responseCode);
        }
    }
}
