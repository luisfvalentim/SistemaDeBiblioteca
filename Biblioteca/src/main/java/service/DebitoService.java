package main.java.service;

import main.java.dao.DebitoDAO;
import main.java.model.Debito;

public class DebitoService {

    private DebitoDAO debitoDAO = new DebitoDAO();

    // Registrar Débito
    public void registrarDebito(Debito debito) {
        debitoDAO.inserirDebito(debito);
    }

    // Excluir Débito
    public void excluirDebito(int id) {
        debitoDAO.excluirDebito(id);
    }
}
