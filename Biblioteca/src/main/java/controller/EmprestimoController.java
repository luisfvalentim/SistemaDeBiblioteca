package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.java.service.EmprestimoService;
import main.java.dao.LivroDAO;
import main.java.dao.AlunoDAO;
import main.java.model.Emprestimo;
import main.java.model.Livro;
import main.java.model.Aluno;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmprestimoController {

    @FXML private ComboBox<String> comboAluno;   // ComboBox para alunos
    @FXML private ComboBox<String> comboLivro;   // ComboBox para livros
    @FXML private DatePicker dataEmprestimo;     // DatePicker para data de empréstimo

    private EmprestimoService emprestimoService = new EmprestimoService();
    private AlunoDAO alunoDAO = new AlunoDAO();
    private LivroDAO livroDAO = new LivroDAO();

    @FXML
    public void initialize() {
        carregarAlunos();
        carregarLivros();
    }

    @FXML
private void salvarEmprestimo(ActionEvent event) {
    try {
        String alunoSelecionado = comboAluno.getValue();
        String livroSelecionado = comboLivro.getValue();
        LocalDate dataEmp = dataEmprestimo.getValue();

        if (alunoSelecionado == null || livroSelecionado == null || dataEmp == null) {
            mostrarMensagem("Todos os campos são obrigatórios!");
            return;
        }

        // Extrai matrícula do aluno
        int matriculaAluno = Integer.parseInt(alunoSelecionado.split(" - ")[1]);
        Aluno aluno = alunoDAO.buscarPorMatricula(matriculaAluno);

        if (aluno == null) {
            mostrarMensagem("Aluno não encontrado!");
            return;
        }

        // Extrai código do livro
        int codigoLivro = Integer.parseInt(livroSelecionado.split(" - ")[1]);

        // Cria o objeto Emprestimo
        Emprestimo emprestimo = new Emprestimo(
            Date.valueOf(dataEmp),
            Date.valueOf(dataEmp.plusDays(7)), // Data prevista
            0.0f
        );

        // Insere empréstimo e o item
        int idEmprestimo = emprestimoService.registrarEmprestimo(emprestimo, aluno.getMatricula());
        emprestimoService.inserirItemEmprestimo(idEmprestimo, codigoLivro);

        mostrarMensagem("Empréstimo registrado com sucesso!");
        limparCampos();

    } catch (Exception e) {
        mostrarMensagem("Erro ao registrar empréstimo: " + e.getMessage());
    }
}

    





    private void carregarAlunos() {
        try {
            List<Aluno> alunos = alunoDAO.listarTodos();
            for (Aluno aluno : alunos) {
                comboAluno.getItems().add(aluno.getNome() + " - " + aluno.getMatricula());
            }
        } catch (Exception e) {
            mostrarMensagem("Erro ao carregar alunos: " + e.getMessage());
        }
    }

    private void carregarLivros() {
        try {
            List<Livro> livros = livroDAO.listarTodos();
            for (Livro livro : livros) {
                comboLivro.getItems().add(livro.getTitulo().getIsbn() + " - " + livro.getCodigo());
            }
        } catch (Exception e) {
            mostrarMensagem("Erro ao carregar livros: " + e.getMessage());
        }
    }

    private void limparCampos() {
        comboAluno.setValue(null);
        comboLivro.setValue(null);
        dataEmprestimo.setValue(null);
    }

    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
