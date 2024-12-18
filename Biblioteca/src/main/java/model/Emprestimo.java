package main.java.model;

import java.util.Date;

public class Emprestimo {
    private Date dataEmprestimo;
    private Date dataPrevista;
    private float multa;

    // Construtor
    public Emprestimo(Date dataEmprestimo, Date dataPrevista, float multa) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.multa = multa;
    }

    // Getters e Setters
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        this.multa = multa;
    }
}
