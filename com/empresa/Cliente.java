package com.empresa;

public class Cliente extends Usuario {
    private String cpf;
    private double saldo;

    public Cliente(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = cpf;
        this.saldo = 0.0;
    }

    @Override
    public boolean validarDocumento() {
        return ValidadorCPF.validarCPF(cpf);
    }

    public void depositar(double valor, Empresa empresa) {
        if (empresa != null) {
            double taxa = empresa.calcularTaxa(valor);
            empresa.realizarDeposito(valor - taxa);
            this.saldo += valor;
        }
    }

    public void sacar(double valor, Empresa empresa) {
        if (empresa != null && valor <= this.saldo) {
            double taxa = empresa.calcularTaxa(valor);
            empresa.realizarSaque(valor + taxa);
            this.saldo -= valor;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
