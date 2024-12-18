package main.java.model;

public class Aluno {
    private int matricula;
    private String nome;
    private String cpf;
    private String endereco;

    // Construtor privado para uso no Builder ou Factory
    private Aluno(int matricula, String nome, String cpf, String endereco) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    // Getters e Setters com validação
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        if (matricula > 0) {
            this.matricula = matricula;
        } else {
            throw new IllegalArgumentException("Matrícula deve ser positiva.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco != null && !endereco.isEmpty()) {
            this.endereco = endereco;
        } else {
            throw new IllegalArgumentException("Endereço não pode ser vazio.");
        }
    }

    // Builder Interno
    public static class Builder {
        private int matricula;
        private String nome;
        private String cpf;
        private String endereco;

        public Builder comMatricula(int matricula) {
            this.matricula = matricula;
            return this;
        }

        public Builder comNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder comCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder comEndereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public Aluno build() {
            return new Aluno(matricula, nome, cpf, endereco);
        }
    }

    // Métodos Factory para simplificar a criação de objetos
    public static Aluno criarAlunoBasico(int matricula, String nome) {
        return new Aluno.Builder()
                .comMatricula(matricula)
                .comNome(nome)
                .build();
    }

    public static Aluno criarAlunoCompleto(int matricula, String nome, String cpf, String endereco) {
        return new Aluno.Builder()
                .comMatricula(matricula)
                .comNome(nome)
                .comCpf(cpf)
                .comEndereco(endereco)
                .build();
    }
}
