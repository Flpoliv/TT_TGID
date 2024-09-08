package com.empresa;

public class Empresa extends Usuario {
    private String cnpj;
    private double saldo;
    private Taxa taxa;

    public Empresa(String nome, String email, String cnpj, Taxa taxa) {
        super(nome, email);
        this.cnpj = cnpj;
        this.taxa = taxa;
        this.saldo = 0.0;
    }

    @Override
    public boolean validarDocumento() {
        return ValidadorCNPJ.validarCNPJ(cnpj);
    }

    public double calcularTaxa(double valor) {
        return taxa.calcularTaxa(valor);
    }

    public void realizarDeposito(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }

    public void realizarSaque(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Taxa getTaxa() {
        return taxa;
    }

    public void setTaxa(Taxa taxa) {
        this.taxa = taxa;
    }
}
