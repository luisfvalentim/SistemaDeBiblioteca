package main.java.dao;

import main.java.model.Autor;
import main.java.model.Livro;
import main.java.model.Titulo;
import main.java.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // Inserir Livro
    public void inserir(Livro livro) throws SQLException {
    String sql = "INSERT INTO livro (codigo, id_titulo, id_autor, disponivel, exemplar_biblioteca) " +
                 "VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, livro.getCodigo());
        stmt.setInt(2, livro.getTitulo().getId()); // FK para a tabela Titulo
        stmt.setInt(3, livro.getAutor().getId());  // FK para a tabela Autor
        stmt.setBoolean(4, livro.isDisponivel());
        stmt.setBoolean(5, livro.isExemplarBiblioteca());

        stmt.executeUpdate();
    }
}


public Livro buscarPorCodigo(int codigo) throws SQLException {
    String sql = "SELECT l.codigo, t.id AS id_titulo, t.nome AS titulo_nome, t.isbn, t.edicao, t.ano, " +
                 "a.id AS id_autor, a.nome AS autor_nome, l.disponivel, l.exemplar_biblioteca " +
                 "FROM livro l " +
                 "JOIN Titulo t ON l.id_titulo = t.id " +
                 "JOIN Autor a ON l.id_autor = a.id " +
                 "WHERE l.codigo = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Titulo titulo = new Titulo(
                rs.getInt("id_titulo"),
                rs.getString("titulo_nome"),
                rs.getString("isbn"),
                rs.getInt("edicao"),
                rs.getInt("ano")
            );

            Autor autor = new Autor(
                rs.getInt("id_autor"),
                rs.getString("autor_nome")
            );

            return new Livro(
                rs.getInt("codigo"),
                titulo,
                autor,
                rs.getBoolean("disponivel"),
                rs.getBoolean("exemplar_biblioteca")
            );
        }
    }
    return null;
}


    // Listar Todos os Livros
    public List<Livro> listarTodos() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT l.codigo, l.disponivel, l.exemplar_biblioteca, " +
                     "t.id AS id_titulo, t.nome AS titulo_nome, t.isbn, t.edicao, t.ano, " +
                     "a.id AS id_autor, a.nome AS autor_nome " +
                     "FROM livro l " +
                     "JOIN Titulo t ON l.id_titulo = t.id " +
                     "JOIN Autor a ON l.id_autor = a.id";
    
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                Titulo titulo = new Titulo(
                    rs.getInt("id_titulo"),    // ID do Titulo
                    rs.getString("titulo_nome"),
                    rs.getString("isbn"),
                    rs.getInt("edicao"),
                    rs.getInt("ano")
                );
    
                Autor autor = new Autor(
                    rs.getInt("id_autor"),    // ID do Autor
                    rs.getString("autor_nome")
                );
    
                livros.add(new Livro(
                    rs.getInt("codigo"),
                    titulo,
                    autor,
                    rs.getBoolean("disponivel"),
                    rs.getBoolean("exemplar_biblioteca")
                ));
            }
        }
        return livros;
    }
    

    // Atualizar Livro
    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, disponivel = ?, exemplarBiblioteca = ?, prazo = ?, isbn = ?, edicao = ?, ano = ? " +
                     "WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo().getIsbn());
            stmt.setString(2, livro.getAutor().getNome());
            stmt.setBoolean(3, livro.isDisponivel());
            stmt.setBoolean(4, livro.isExemplarBiblioteca());
            stmt.setString(6, livro.getTitulo().getIsbn());
            stmt.setInt(7, livro.getTitulo().getEdicao());
            stmt.setInt(8, livro.getTitulo().getAno());
            stmt.setInt(9, livro.getCodigo());

            stmt.executeUpdate();
        }
    }

    public boolean codigoExiste(int codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM livro WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Se o count > 0, o código existe
            }
        }
        return false;
    }
    

    // Excluir Livro
    public void excluir(int codigo) throws SQLException {
        String sql = "DELETE FROM livro WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }
}
