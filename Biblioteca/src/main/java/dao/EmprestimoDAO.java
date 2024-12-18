package main.java.dao;

import main.java.model.Emprestimo;
import main.java.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmprestimoDAO {

    // Inserir empréstimo utilizando matrícula do aluno
    public int inserirEmprestimo(Emprestimo emprestimo, int matriculaAluno) {
        String sql = "INSERT INTO emprestimo (id_aluno, data_emprestimo, data_prevista, multa) VALUES (?, ?, ?, ?) RETURNING id";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, matriculaAluno);
            stmt.setDate(2, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataPrevista().getTime()));
            stmt.setFloat(4, emprestimo.getMulta());
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir empréstimo: " + e.getMessage());
        }
        throw new RuntimeException("Falha ao obter ID do empréstimo.");
    }
    
    
    

    // Inserir item de empréstimo (livro vinculado ao empréstimo)
    public void inserirItemEmprestimo(int idEmprestimo, int codigoLivro) {
        String sql = "INSERT INTO itemEmprestimo (id_emprestimo, id_livro) VALUES (?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, idEmprestimo);
            stmt.setInt(2, codigoLivro); // codigoLivro agora é a chave primária
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item de empréstimo: " + e.getMessage());
        }
    }
    
}

