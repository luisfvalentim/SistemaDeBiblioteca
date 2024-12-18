package main.java.controller;

import javafx.scene.control.Alert;
import main.java.factory.DAOFactory;
import main.java.model.ItemEmprestimo;

import java.util.Date;

public class ItemEmprestimoController {

    public void registrarItemEmprestimo(Date dataDevolucao, Date dataPrevista) {
        try {
            if (dataDevolucao == null || dataPrevista == null) {
                mostrarMensagem("As datas de devolução e prevista são obrigatórias!");
                return;
            }

            if (dataDevolucao.before(dataPrevista)) {
                mostrarMensagem("A data de devolução não pode ser anterior à data prevista!");
                return;
            }

            // Criação do ItemEmprestimo
            ItemEmprestimo item = new ItemEmprestimo(dataDevolucao, dataPrevista);
            DAOFactory.getItemEmprestimoDAO().inserirItemEmprestimo(item);

            mostrarMensagem("Item de empréstimo registrado com sucesso!");
        } catch (Exception e) {
            mostrarMensagem("Erro ao registrar item de empréstimo: " + e.getMessage());
        }
    }

    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
