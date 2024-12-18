package main.java.dao;

import main.java.model.Debito;
import main.java.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DebitoDAO {

    public void inserirDebito(Debito debito) {
        String sql = "INSERT INTO debito (idAluno) VALUES (?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, debito.getIdAluno());
            stmt.executeUpdate();
    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir débito: " + e.getMessage());
        }
    }
    
    public void excluirDebito(int id) {
        String sql = "DELETE FROM debito WHERE idAluno = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, id);
            stmt.executeUpdate();
    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir débito: " + e.getMessage());
        }
    }
    
    
}

