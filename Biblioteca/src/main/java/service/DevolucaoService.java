package main.java.service;

import main.java.dao.DevolucaoDAO;

import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class DevolucaoService {

    private DevolucaoDAO devolucaoDAO = new DevolucaoDAO();

    // Buscar livros pendentes
    public List<String> buscarLivrosPendentes() throws SQLException {
        return devolucaoDAO.buscarLivrosPendentes();
    }

    // Registrar devolução
    public void registrarDevolucao(int idItemEmprestimo, int codigoLivro) throws SQLException {
        devolucaoDAO.registrarDevolucao(idItemEmprestimo, codigoLivro);
    }

    public double calculaMulta(Date dataEmprestimo, Date dataEntrega) throws Exception {
        return devolucaoDAO.calculaMulta(dataEmprestimo, dataEntrega);
    }

    public Date buscarDataEmprestimo(int idItemEmprestimo) throws Exception {
        System.out.println("Buscando data de empréstimo para ID: " + idItemEmprestimo);
        java.util.Date data = devolucaoDAO.buscarDataEmprestimo(idItemEmprestimo);
        System.out.println("Data de empréstimo retornada: " + data.getTime());
        // Conversão de java.util.Date para java.sql.Date
        return new java.sql.Date(data.getTime());
    }

    public double calculaDebito(double multa) {
        return multa > 0 ? multa + 10.0 : 0.0; // Exemplo: adiciona R$ 10,00 se houver multa
    }
    

    
}
