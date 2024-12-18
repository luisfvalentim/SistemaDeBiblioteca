package main.java.model;

import java.util.Date;

public class ItemEmprestimo {
    private Date dataDevolucao;
    private Date dataPrevista;

    // Construtor
    public ItemEmprestimo(Date dataDevolucao, Date dataPrevista) {
        this.dataDevolucao = dataDevolucao;
        this.dataPrevista = dataPrevista;
    }

    // Getters e Setters
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }
}

