package main.java.dao;

import main.java.model.ItemEmprestimo;
import main.java.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemEmprestimoDAO {

    public void inserirItemEmprestimo(ItemEmprestimo itemEmprestimo) {
        String sql = "INSERT INTO itemEmprestimo (dataDevolucao, dataPrevista) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(itemEmprestimo.getDataDevolucao().getTime()));
            stmt.setDate(2, new java.sql.Date(itemEmprestimo.getDataPrevista().getTime()));

            stmt.executeUpdate();
            System.out.println("Item de empréstimo inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir item de empréstimo: " + e.getMessage());
        }
    }
}

