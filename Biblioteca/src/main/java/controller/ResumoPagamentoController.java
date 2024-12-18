package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.service.DevolucaoService;

import java.sql.Date;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;


public class ResumoPagamentoController {

    @FXML
    private TextField campoMulta;
    @FXML
    private TextField campoValorTotal;
    @FXML
    private TextField campoDebito;

    private DevolucaoService devolucaoService = new DevolucaoService();

    // Método para calcular os valores e exibi-los
    // Corrigir a conversão de LocalDate para java.sql.Date
    public void calcularValores(int idItemEmprestimo, LocalDate dataDevolucao) {
        try {
            // Busca a data de empréstimo no formato java.sql.Date
            Date dataEmprestimo = devolucaoService.buscarDataEmprestimo(idItemEmprestimo);
    
            // Converte java.sql.Date para LocalDate para cálculos
            LocalDate dataEmprestimoLocal = dataEmprestimo.toLocalDate();
    
            // Calcula a diferença de dias entre a data de empréstimo e devolução
            long diasDiferenca = ChronoUnit.DAYS.between(dataEmprestimoLocal, dataDevolucao);
    
            // Calcula a multa somente se os dias ultrapassarem 7
            double multa = diasDiferenca > 7 ? (diasDiferenca - 7) * 1.5 : 0.0;
    
            // Exemplo de valor fixo adicional
            double valorBase = 5.0;
            double valorTotal = valorBase + multa;
    
            // Atualiza os campos da interface
            campoMulta.setText(String.format("%.2f", multa));
            campoValorTotal.setText(String.format("%.2f", valorTotal));
            campoDebito.setText(String.format("%.2f", 0.0)); // Placeholder para débito, caso precise no futuro
    
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao calcular valores: " + e.getMessage());
        }
    }
    
    
    


    // Método para fechar a tela
    @FXML
    private void fecharTela() {
        Stage stage = (Stage) campoMulta.getScene().getWindow();
        stage.close();
    }
}
