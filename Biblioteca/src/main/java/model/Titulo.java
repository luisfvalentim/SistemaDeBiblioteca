package main.java.model;

public class Titulo {
    private int id; // ID do título
    private String nome;
    private String isbn;
    private int edicao;
    private int ano;

    public Titulo(int id, String nome, String isbn, int edicao, int ano) {
        this.id = id;
        this.nome = nome;
        this.isbn = isbn;
        this.edicao = edicao;
        this.ano = ano;
    }

    // Construtor sem ID (sobrecarga)
    public Titulo(String nome, String isbn, int edicao, int ano) {
        this(0, nome, isbn, edicao, ano); // ID padrão 0
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getEdicao() {
        return edicao;
    }

    public int getAno() {
        return ano;
    }
}
