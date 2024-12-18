package main.java.dao;

import main.java.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DevolucaoDAO {

    // Buscar livros pendentes
    public List<String> buscarLivrosPendentes() throws SQLException {
        List<String> livrosPendentes = new ArrayList<>();
        String sql = "SELECT i.id AS id_item, l.codigo " +
                     "FROM itememprestimo i " +
                     "JOIN livro l ON i.id_livro = l.codigo " +
                     "WHERE i.status = 'pendente'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idItem = rs.getInt("id_item");
                int codigoLivro = rs.getInt("codigo");
                livrosPendentes.add(idItem + " - " + codigoLivro);
            }
        }
        return livrosPendentes;
    }

    // Atualizar status e disponibilidade após devolução
    public void registrarDevolucao(int idItemEmprestimo, int codigoLivro) throws SQLException {
        String sqlItem = "UPDATE itememprestimo SET status = 'devolvido' WHERE id = ?";
        String sqlLivro = "UPDATE livro SET disponivel = true WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Inicia a transação

            try (PreparedStatement statetItem = conn.prepareStatement(sqlItem);
                 PreparedStatement statetLivro = conn.prepareStatement(sqlLivro)) {

                statetItem.setInt(1, idItemEmprestimo);
                statetItem.executeUpdate();

                statetLivro.setInt(1, codigoLivro);
                statetLivro.executeUpdate();

                conn.commit(); // Confirma a transação
            } catch (SQLException e) {
                conn.rollback(); // Desfaz em caso de erro
                throw e;
            }
        }
    }

    public double calculaMulta( Date dataEmprestimo, Date dataEntrega) {
        
        double multaPadrao = 1.5;
    
        // Verifica se as datas são válidas
        if (dataEmprestimo == null || dataEntrega == null) {
            throw new IllegalArgumentException("Datas de empréstimo e entrega não podem ser nulas.");
        }
    
        // Converte Date para LocalDate para facilitar o cálculo
        LocalDate dataEmprestimoLocal = dataEmprestimo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataEntregaLocal = dataEntrega.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
        // Calcula a data prevista (7 dias após o empréstimo)
        LocalDate dataPrevista = dataEmprestimoLocal.plusDays(7);
    
        // Calcula os dias de atraso
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataEntregaLocal);
    
        // Se houver atraso, calcula a multa
        double multa = diasAtraso > 0 ? diasAtraso * multaPadrao : 0.0;
    
        // Retorna o valor total somado com a multa
        return multa;
    }

    public Date buscarDataEmprestimo(int idItemEmprestimo) throws Exception {
        String sql = "SELECT e.data_emprestimo FROM emprestimo e " +
                     "JOIN itememprestimo i ON e.id = i.id_emprestimo " +
                     "WHERE i.id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, idItemEmprestimo);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getDate("data_emprestimo");
            }
        }
        return null;
    }

    public Date buscarDataDevolucao(int idItemEmprestimo) {
        String sql = "SELECT data_devolucao FROM devolucao WHERE id_itememprestimo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idItemEmprestimo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDate("data_devolucao"); // Retorna a data de devolução
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null; // Retorna null caso não encontre
    }
    
}
