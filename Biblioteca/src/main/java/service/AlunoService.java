package main.java.service;

import main.java.dao.AlunoDAO;
import main.java.model.Aluno;

import java.util.List;

public class AlunoService {

    private AlunoDAO alunoDAO = new AlunoDAO();

    public void cadastrarAluno(Aluno aluno) {
        alunoDAO.inserir(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listarTodos();
    }

    public void atualizarAluno(Aluno aluno) {
        alunoDAO.atualizar(aluno);
    }

    public void excluirAluno(int matricula) {
        alunoDAO.deletar(matricula);
    }

    public Aluno buscarAluno(int matricula) {
        return alunoDAO.buscarPorMatricula(matricula);
    }
}
