package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.java.service.DebitoService;
import main.java.factory.DAOFactory;
import main.java.model.Debito;

public class DebitoController {

    @FXML
    private TextField campoIdAluno; // Campo para ID do Aluno no formulário

    private DebitoService debitoService = new DebitoService(); // Instância do serviço

    // Excluir Débito
    @FXML
    public void excluirDebito(ActionEvent event) {
        try {
            int idAluno = Integer.parseInt(campoIdAluno.getText());

            if (idAluno <= 0) {
                mostrarMensagem("ID do aluno inválido!");
                return;
            }

            debitoService.excluirDebito(idAluno);
            mostrarMensagem("Débito excluído com sucesso!");

        } catch (NumberFormatException e) {
            mostrarMensagem("Erro: ID deve ser um número válido.");
        } catch (Exception e) {
            mostrarMensagem("Erro ao excluir débito: " + e.getMessage());
        }
    }

    // Registrar Débito
    @FXML
    public void registrarDebito(ActionEvent event) {
        try {
            int idAluno = Integer.parseInt(campoIdAluno.getText());

            if (idAluno <= 0) {
                mostrarMensagem("ID do aluno inválido!");
                return;
            }

            Debito debito = new Debito(idAluno);
            debitoService.registrarDebito(debito);

            mostrarMensagem("Débito registrado com sucesso!");
        } catch (NumberFormatException e) {
            mostrarMensagem("Erro: ID deve ser um número válido.");
        } catch (Exception e) {
            mostrarMensagem("Erro ao registrar débito: " + e.getMessage());
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
