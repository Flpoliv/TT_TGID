package com.empresa;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Transacao {

    private static final String WEBHOOK_URL = "https://webhook.site/2420acfd-d3b4-42ef-b26a-e1ec10afbcfd";

    public static void realizarTransacao(Cliente cliente, Empresa empresa, String tipo, double valor) {
        if ("depósito".equalsIgnoreCase(tipo)) {
            cliente.depositar(valor, empresa);
        } else if ("saque".equalsIgnoreCase(tipo)) {
            cliente.sacar(valor, empresa);
        }

        // Envia callback para a empresa
        try {
            enviarCallback(empresa, tipo, valor);
        } catch (IOException e) {
            System.out.println("Erro ao enviar callback para a empresa: " + e.getMessage());
        }

        // Envia notificação para o cliente
        try {
            Notificacao.enviar(cliente.getEmail(), "Transação de " + tipo + " realizada com sucesso no valor de R$ " + valor);
        } catch (IOException e) {
            System.out.println("Erro ao enviar notificação para o cliente: " + e.getMessage());
        }
    }

    @SuppressWarnings("deprecation")
    private static void enviarCallback(Empresa empresa, String tipo, double valor) throws IOException {
        URL url = new URL(WEBHOOK_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonPayload = "{\"empresa\":\"" + empresa.getNome() + "\",\"tipo\":\"" + tipo + "\",\"valor\":" + valor + "}";
        
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Callback enviado com sucesso para a empresa.");
        } else {
            throw new IOException("Falha no envio do callback. Código de resposta: " + responseCode);
        }
    }
}
