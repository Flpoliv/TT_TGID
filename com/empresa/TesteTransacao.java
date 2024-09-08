package com.empresa;

public class TesteTransacao {

    public static void main(String[] args) {
        // Cria taxa de exemplo
        Taxa taxa = new Taxa(2.5);

        // Cria uma empresa com CNPJ válido e taxa definida
        Empresa empresa = new Empresa("Empresa Teste", "contato@empresa.com", "12.345.678/0001-95", taxa);

        // Cria um cliente com CPF válido
        Cliente cliente = new Cliente("Cliente Teste", "cliente@cliente.com", "123.456.789-09");

        // Realiza uma transação de depósito e verifica a comunicação com o webhook e a notificação
        System.out.println("Realizando depósito...");
        Transacao.realizarTransacao(cliente, empresa, "depósito", 500);

        // Realiza uma transação de saque e verifica a comunicação com o webhook e a notificação
        System.out.println("Realizando saque...");
        Transacao.realizarTransacao(cliente, empresa, "saque", 100);
    }
}
