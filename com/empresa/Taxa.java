package com.empresa;

public class Taxa {
    private double percentual;

    public Taxa(double percentual) {
        this.percentual = percentual;
    }

    public double calcularTaxa(double valor) {
        return valor * (percentual / 100.0);
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }
}
