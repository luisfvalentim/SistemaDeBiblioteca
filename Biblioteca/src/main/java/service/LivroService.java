package main.java.service;

import main.java.dao.LivroDAO;
import main.java.model.Livro;

import java.sql.SQLException;
import java.util.List;

public class LivroService {
    private LivroDAO livroDAO = new LivroDAO();

    // Cadastrar Livro
    public void cadastrarLivro(Livro livro) throws Exception {
        if (livroDAO.codigoExiste(livro.getCodigo())) {
            throw new Exception("Código já existe no banco de dados. Escolha outro código.");
        }
        livroDAO.inserir(livro);
    }
    

    // Buscar Livro por Código
    public Livro buscarLivroPorCodigo(int codigo) {
        try {
            return livroDAO.buscarPorCodigo(codigo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o livro: " + e.getMessage());
        }
    }

    // Listar Todos os Livros
    public List<Livro> listarLivros() {
        try {
            return livroDAO.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os livros: " + e.getMessage());
        }
    }

    // Atualizar Livro
    public void atualizarLivro(Livro livro) {
        try {
            livroDAO.atualizar(livro);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o livro: " + e.getMessage());
        }
    }

    // Excluir Livro
    public void excluirLivro(int codigo) {
        try {
            livroDAO.excluir(codigo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o livro: " + e.getMessage());
        }
    }
}

