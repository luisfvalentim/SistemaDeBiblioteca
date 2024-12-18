package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.java.service.DevolucaoService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class DevolucaoController {

    @FXML private ComboBox<String> comboLivro;
    @FXML private DatePicker dataDevolucao;
    @FXML private TextField campoMulta;
    @FXML private TextField campoValorTotal;

    private DevolucaoService devolucaoService = new DevolucaoService();

    @FXML
    public void initialize() {
        carregarLivrosPendentes();
    }


    @FXML
private void salvarDevolucao(ActionEvent event) {
    try {
        // Valida a seleção do livro
        String selecao = comboLivro.getValue();
        if (selecao == null || selecao.isEmpty()) {
            mostrarMensagem("Selecione um livro para devolução!");
            return;
        }

        int idItemEmprestimo = Integer.parseInt(selecao.split(" - ")[0]);
        LocalDate dataDev = dataDevolucao.getValue();

        if (dataDev == null) {
            mostrarMensagem("Selecione a data de devolução!");
            return;
        }

        // Chama a tela de resumo
        
        // Registra a devolução no banco de dados
        devolucaoService.registrarDevolucao(idItemEmprestimo, Integer.parseInt(selecao.split(" - ")[1]));
        
        mostrarMensagem("Devolução registrada com sucesso!");
        carregarLivrosPendentes();
        abrirTelaResumo(idItemEmprestimo, dataDev);
    } catch (Exception e) {
        mostrarMensagem("Erro ao registrar devolução: " + e.getMessage());
    }
}

private void abrirTelaResumo(int idItemEmprestimo, LocalDate dataDevolucao) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ResumoPagamento.fxml"));
    Parent root = loader.load();

    ResumoPagamentoController controller = loader.getController();
    controller.calcularValores(idItemEmprestimo, dataDevolucao);

    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle("Resumo do Pagamento");
    stage.show();
}

    



    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void carregarLivrosPendentes() {
        try {
            // Chama o service para buscar os livros pendentes
            List<String> livrosPendentes = devolucaoService.buscarLivrosPendentes();
    
            // Limpa e atualiza o ComboBox
            comboLivro.getItems().clear();
            comboLivro.getItems().addAll(livrosPendentes);
        } catch (Exception e) {
            mostrarMensagem("Erro ao carregar livros pendentes: " + e.getMessage());
        }
    }
    
}
