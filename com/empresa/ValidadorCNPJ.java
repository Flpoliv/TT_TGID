package com.empresa;

public class ValidadorCNPJ {

    public static boolean validarCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 00000000000000)
        if (cnpj.chars().distinct().count() == 1) {
            return false;
        }

        // Calcula os dois dígitos verificadores
        String cnpjBase = cnpj.substring(0, 12);
        String digitos = cnpj.substring(12);

        int primeiroDigito = calcularDigitoVerificador(cnpjBase, 5);
        int segundoDigito = calcularDigitoVerificador(cnpjBase + primeiroDigito, 6);

        // Verifica se os dígitos verificadores calculados correspondem aos dígitos informados
        return digitos.equals(primeiroDigito + "" + segundoDigito);
    }

    private static int calcularDigitoVerificador(String cnpjBase, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (int i = 0; i < cnpjBase.length(); i++) {
            soma += Character.getNumericValue(cnpjBase.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
