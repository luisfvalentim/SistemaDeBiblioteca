package main.java.model;

import java.util.Date;

public class Devolucao {
    private Date dataDevolucao;
    private double valorTotal;
    private double multa;
    private int atraso;

    // Construtor
    public Devolucao(Date dataDevolucao, double valorTotal, double multa, int atraso) {
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.multa = multa;
        this.atraso = atraso;
    }
    
    // Getters e Setters
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public int getAtraso() {
        return atraso;
    }

    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }
}
