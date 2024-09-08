package com.empresa;

public class TesteSistema {

    public static void main(String[] args) {
        // Cria taxa de exemplo
        Taxa taxa = new Taxa(2.5);

        // Cria uma empresa com CNPJ válido e taxa definida
        Empresa empresa = new Empresa("Empresa Teste", "contato@empresa.com", "12.345.678/0001-95", taxa);

        // Cria um cliente com CPF válido
        Cliente cliente = new Cliente("Cliente Teste", "cliente@cliente.com", "123.456.789-09");

        // Testa validação de CPF e CNPJ
        System.out.println("Validação CPF: " + cliente.validarDocumento());
        System.out.println("Validação CNPJ: " + empresa.validarDocumento());

        // Realiza uma transação de depósito
        Transacao.realizarTransacao(cliente, empresa, "depósito", 1000);
        System.out.println("Saldo do cliente após depósito: " + cliente.getSaldo());
        System.out.println("Saldo da empresa após depósito: " + empresa.getSaldo());

        // Realiza uma transação de saque
        Transacao.realizarTransacao(cliente, empresa, "saque", 200);
        System.out.println("Saldo do cliente após saque: " + cliente.getSaldo());
        System.out.println("Saldo da empresa após saque: " + empresa.getSaldo());
    }
}
