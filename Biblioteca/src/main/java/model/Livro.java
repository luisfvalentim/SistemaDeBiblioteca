package main.java.model;

public class Livro {
    private boolean disponivel;
    private boolean exemplarBiblioteca;
    private int codigo;
    private Titulo titulo;
    private Autor autor; // Novo atributo

    // Construtor atualizado
    public Livro(int codigo, Titulo titulo, Autor autor, boolean disponivel, boolean exemplarBiblioteca) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.exemplarBiblioteca = exemplarBiblioteca;
    }

    // Getters e Setters
    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isExemplarBiblioteca() {
        return exemplarBiblioteca;
    }

    public void setExemplarBiblioteca(boolean exemplarBiblioteca) {
        this.exemplarBiblioteca = exemplarBiblioteca;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    

    public Titulo getTitulo() {
        return titulo;
    }

    public Autor getAutor() { // Novo Getter
        return autor;
    }

    public void setAutor(Autor autor) { // Novo Setter
        this.autor = autor;
    }

    // Métodos
    public boolean verificaLivro(int codigo) {
        return this.codigo == codigo;
    }

    public static class Builder {
        private int codigo;
        private Titulo titulo;
        private Autor autor;
        private boolean disponivel; 
        private boolean exemplarBiblioteca;

        public Builder comCodigo(int codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder comTitulo(Titulo titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder comAutor(Autor autor) {
            this.autor = autor;
            return this;
        }

        public Builder comDisponivel(boolean disponivel) {
            this.disponivel = disponivel;
            return this;
        }

        public Builder comExemplarBiblioteca(boolean exemplarBiblioteca) {
            this.exemplarBiblioteca = exemplarBiblioteca;
            return this;
        }

        public Livro build() {
            return new Livro(codigo, titulo, autor, disponivel, exemplarBiblioteca);
        }
    }

    // Métodos Factory para simplificar a criação de objetos
    public static Livro criarLivroBasico(int codigo, Titulo titulo) {
        return new Livro.Builder()
                .comCodigo(codigo)
                .comTitulo(titulo)
                .build();
    }

    public static Livro criarLivroCompleto(int codigo, Titulo titulo, Autor autor, boolean disponivel, boolean exemplarBiblioteca) {
        return new Livro.Builder()
                .comCodigo(codigo)
                .comTitulo(titulo)
                .comAutor(autor)
                .comDisponivel(disponivel)
                .comExemplarBiblioteca(exemplarBiblioteca)
                .build();
    }
}
