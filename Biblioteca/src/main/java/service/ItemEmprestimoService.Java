package main.java.service;

import main.java.dao.ItemEmprestimoDAO;
import main.java.model.ItemEmprestimo;

import java.sql.SQLException;
import java.util.List;

public class ItemEmprestimoService {

    private ItemEmprestimoDAO itemEmprestimoDAO = new ItemEmprestimoDAO();

    // Registrar Item de Empréstimo
    public void registrarItemEmprestimo(ItemEmprestimo item) {
        try {
            itemEmprestimoDAO.inserirItemEmprestimo(item);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar item de empréstimo: " + e.getMessage());
        }
    }

    // Listar Itens de Empréstimo
    public List<ItemEmprestimo> listarItensEmprestimo() {
        try {
            return itemEmprestimoDAO.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens de empréstimo: " + e.getMessage());
        }
    }

    // Excluir Item de Empréstimo
    public void excluirItemEmprestimo(int id) {
        try {
            itemEmprestimoDAO.excluirItemEmprestimo(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir item de empréstimo: " + e.getMessage());
        }
    }
}
