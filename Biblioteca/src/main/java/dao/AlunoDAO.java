package main.java.dao;

import main.java.model.Aluno;
import main.java.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // Inserir Aluno
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, nome, cpf, endereco) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getEndereco());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    // Atualizar Aluno
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, endereco = ? WHERE matricula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEndereco());
            stmt.setInt(4, aluno.getMatricula());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    // Deletar Aluno
    public void deletar(int matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, matricula);
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum aluno encontrado com a matrícula: " + matricula);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno: " + e.getMessage());
        }
    }
    

    // Buscar Aluno por Matrícula
    public Aluno buscarPorMatricula(int matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Aluno.criarAlunoCompleto(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    // Listar todos os Alunos
    public List<Aluno> listarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(Aluno.criarAlunoCompleto(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar aluno: " + e.getMessage());
        }
        return lista;
    }
}
