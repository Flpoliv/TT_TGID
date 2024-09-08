package com.empresa;

public class ValidadorCPF {

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        int primeiroDigito = calcularDigitoVerificador(cpf, 10);
        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        int segundoDigito = calcularDigitoVerificador(cpf, 11);
        return segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    private static int calcularDigitoVerificador(String cpf, int peso) {
        int soma = 0;
        for (int i = 0; i < cpf.length() - 2; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
