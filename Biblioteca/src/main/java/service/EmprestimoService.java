package main.java.service;

import main.java.dao.EmprestimoDAO;
import main.java.model.Emprestimo;

public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    // Registrar empréstimo usando matrícula
    public int registrarEmprestimo(Emprestimo emprestimo, int matriculaAluno) {
        return emprestimoDAO.inserirEmprestimo(emprestimo, matriculaAluno);
    }

    // Registrar item de empréstimo
    public void inserirItemEmprestimo(int idEmprestimo, int idLivro) {
        try {
            emprestimoDAO.inserirItemEmprestimo(idEmprestimo, idLivro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir item de empréstimo: " + e.getMessage());
        }
    }
    
}

