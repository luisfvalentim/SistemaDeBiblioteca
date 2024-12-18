package main.java.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.model.Aluno;
import main.java.service.AlunoService;
import javafx.collections.ObservableList;


import java.util.List;

public class AlunoController {

    // TextFields para o cadastro
    @FXML private TextField campoNome;
    @FXML private TextField campoMatricula;
    @FXML private TextField campoCpf;
    @FXML private TextField campoEndereco;

    // ComboBox e TableView (para listagem futura)
    @FXML private ComboBox<String> comboAluno;
    @FXML private TableView<Aluno> tabelaAlunos;
    @FXML private TableColumn<Aluno, String> colunaNome, colunaCpf, colunaEndereco;
    @FXML private TableColumn<Aluno, Integer> colunaMatricula;

    private AlunoService alunoService = new AlunoService();
    private Aluno alunoSelecionado = null;

    @FXML
    public void initialize() {
        
    }

    // Método para cadastrar aluno (usando apenas os TextFields)
    @FXML
    private void cadastrarAluno(ActionEvent event) {
        try {
            validarCampos();

            // Cria um novo aluno com os dados fornecidos
            Aluno aluno = Aluno.criarAlunoCompleto(
                Integer.parseInt(campoMatricula.getText()),
                campoNome.getText(),
                campoCpf.getText(),
                campoEndereco.getText()
            );

            // Salva o aluno no banco de dados
            alunoService.cadastrarAluno(aluno);

            exibirMensagem("Aluno cadastrado com sucesso!");

            // Atualiza os componentes necessários
            atualizarInterface();
            limparCampos();

        } catch (NumberFormatException e) {
            exibirMensagem("A matrícula deve ser um número válido.");
        } catch (Exception e) {
            exibirMensagem("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    // Configura a tabela para listagem futura
    private void configurarTabela() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    }

    // Atualiza os dados da tabela
    private void atualizarTabela() {
        List<Aluno> alunos = alunoService.listarAlunos();
        tabelaAlunos.setItems(FXCollections.observableArrayList(alunos));
    }

    // Atualiza o ComboBox com os alunos
    private void atualizarComboBox() {
        List<Aluno> alunos = alunoService.listarAlunos();
        comboAluno.getItems().clear();
        for (Aluno aluno : alunos) {
            comboAluno.getItems().add(aluno.getNome() + " - " + aluno.getMatricula());
        }
    }

    // Limpa os campos do formulário
    private void limparCampos() {
        campoNome.clear();
        campoMatricula.clear();
        campoCpf.clear();
        campoEndereco.clear();
        comboAluno.setValue(null);
    }

    // Valida os campos obrigatórios
    private void validarCampos() throws Exception {
        if (campoNome.getText().isEmpty() || campoCpf.getText().isEmpty() ||
            campoEndereco.getText().isEmpty() || campoMatricula.getText().isEmpty()) {
            throw new Exception("Todos os campos devem ser preenchidos.");
        }
    }

    // Exibe mensagens na tela
    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Atualiza a interface
    private void atualizarInterface() {
        if (tabelaAlunos != null) {
            atualizarTabela();
        }
        atualizarComboBox();
    }

    @FXML
public void editarAluno(ActionEvent event) {
    try {
        carregarAlunosNoComboBox();
        // Validação dos campos
        if (campoNome.getText().isEmpty() || campoMatricula.getText().isEmpty() ||
            campoCpf.getText().isEmpty() || campoEndereco.getText().isEmpty()) {
            exibirMensagem("Todos os campos devem ser preenchidos.");
            return;
        }

        // Capturar os valores dos campos
        String nome = campoNome.getText();
        int matricula = Integer.parseInt(campoMatricula.getText());
        String cpf = campoCpf.getText();
        String endereco = campoEndereco.getText();

        // Criar objeto aluno atualizado
        Aluno alunoAtualizado = Aluno.criarAlunoCompleto(matricula, nome, cpf, endereco);

        // Atualizar aluno no banco
        alunoService.atualizarAluno(alunoAtualizado);

        // Mensagem de sucesso
        exibirMensagem("Aluno atualizado com sucesso!");

        // Limpar os campos e atualizar a interface
        limparCampos();
        atualizarInterface();
    } catch (NumberFormatException e) {
        exibirMensagem("Matrícula deve ser um número válido.");
    } catch (Exception e) {
        exibirMensagem("Erro ao editar aluno: " + e.getMessage());
    }
}

public void inicializarEdicao() {
    carregarAlunosNoComboBox();
}

private void carregarAlunosNoComboBox() {
    try {
        comboAluno.getItems().clear(); // Limpa os itens existentes
        List<Aluno> alunos = alunoService.listarAlunos();

        for (Aluno aluno : alunos) {
            comboAluno.getItems().add(aluno.getNome() + " - " + aluno.getMatricula());
        }
    } catch (Exception e) {
        exibirMensagem("Erro ao carregar alunos: " + e.getMessage());
    }
}

@FXML
private void excluirAluno(ActionEvent event) {
    String selecao = comboAluno.getValue();
    if (selecao != null) {
        try {
            int matricula = Integer.parseInt(selecao.split(" - ")[1]);
            System.out.println("Tentando excluir aluno com matrícula: " + matricula); // LOG
            alunoService.excluirAluno(matricula);
            exibirMensagem("Aluno excluído com sucesso!");
            atualizarInterface();
        } catch (Exception e) {
            exibirMensagem("Erro ao excluir aluno: " + e.getMessage());
        }
    } else {
        exibirMensagem("Selecione um aluno para excluir.");
    }
}

public void configurarColunas() {
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
}

public void carregarAlunos() {
    try {
        List<Aluno> listaAlunos = alunoService.listarAlunos();
        ObservableList<Aluno> alunos = FXCollections.observableArrayList(listaAlunos);
        tabelaAlunos.setItems(alunos);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erro ao carregar alunos: " + e.getMessage());
    }
}




    
}
