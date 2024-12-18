package main.java.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import main.java.model.Autor;
import main.java.model.Livro;
import main.java.model.Titulo;
import main.java.service.LivroService;

import javafx.stage.Stage;

import java.util.List;

public class LivroController {

    @FXML private TextField campoCodigo, campoTitulo, campoAutor, campoIsbn, campoEdicao, campoAno;
    @FXML private CheckBox checkboxDisponivel, checkboxExemplarBiblioteca;

    @FXML private ComboBox<String> comboLivro; // Para seleção no excluir e editar
    @FXML private TableView<Livro> tabelaLivros;
    @FXML private TableColumn<Livro, Integer> colunaCodigo;
    @FXML private TableColumn<Livro, String> colunaTitulo, colunaAutor, colunaDisponivel, colunaExemplarBiblioteca;

    private LivroService livroService = new LivroService();
    private Livro livroSelecionado;

    @FXML
    public void initialize() {
    }

    @FXML
private void cadastrarLivro(ActionEvent event) {
    try {
        validarCampos();

        // Criação dos objetos
        Titulo titulo = new Titulo(
            1, // ID fictício
            campoTitulo.getText(),
            campoIsbn.getText(),
            Integer.parseInt(campoEdicao.getText()),
            Integer.parseInt(campoAno.getText())
        );

        Autor autor = new Autor(1, campoAutor.getText());
        Livro livro = Livro.criarLivroCompleto(
            Integer.parseInt(campoCodigo.getText()),
            titulo,
            autor,
            checkboxDisponivel.isSelected(),
            checkboxExemplarBiblioteca.isSelected()
        );

        // Chama o serviço para cadastro
        livroService.cadastrarLivro(livro);

        exibirMensagem("Livro cadastrado com sucesso!");
        limparCampos();

       

    } catch (NumberFormatException e) {
        exibirMensagem("Os campos Código, Edição e Ano devem ser números válidos.");
    } catch (Exception e) {
        exibirMensagem("Erro ao cadastrar o livro: " + e.getMessage());
    }
}






private void validarCampos() throws Exception {
    if (campoCodigo.getText().isEmpty() || campoTitulo.getText().isEmpty() ||
    campoIsbn.getText().isEmpty() || campoEdicao.getText().isEmpty() ||
    campoAno.getText().isEmpty() || campoAutor.getText().isEmpty()) {
    throw new Exception("Todos os campos devem ser preenchidos.");
}

}

    

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    

    private void atualizarComboBox() {
        List<Livro> livros = livroService.listarLivros();
        comboLivro.getItems().clear();
        for (Livro livro : livros) {
            comboLivro.getItems().add(livro.getCodigo() + " - " + livro.getTitulo());
        }
    }

    private void limparCampos() {
        campoCodigo.clear();
        campoTitulo.clear();
        campoAutor.clear();
        campoIsbn.clear();
        campoEdicao.clear();
        campoAno.clear();
        checkboxDisponivel.setSelected(false);
        checkboxExemplarBiblioteca.setSelected(false);
        comboLivro.setValue(null);
    }

    @FXML
private void editarLivro(ActionEvent event) {
    try {
        validarCampos();

        // Criar objeto Titulo
        Titulo titulo = new Titulo(
            1, // ID fictício
            campoTitulo.getText(),
            campoIsbn.getText(),
            Integer.parseInt(campoEdicao.getText()),
            Integer.parseInt(campoAno.getText())
        );

        // Criar objeto Autor
        Autor autor = new Autor(1, campoAutor.getText());

        // Criar objeto Livro atualizado
        Livro livroAtualizado = new Livro(
            Integer.parseInt(campoCodigo.getText()),
            titulo,
            autor,
            checkboxDisponivel.isSelected(),
            checkboxExemplarBiblioteca.isSelected()
        );

        // Atualizar no serviço
        livroService.atualizarLivro(livroAtualizado);

        exibirMensagem("Livro atualizado com sucesso!");
        limparCampos();
        atualizarInterface();
    } catch (NumberFormatException e) {
        exibirMensagem("Os campos Código, Edição e Ano devem ser números válidos.");
    } catch (Exception e) {
        exibirMensagem("Erro ao editar livro: " + e.getMessage());
    }
}








@FXML
private void excluirLivro(ActionEvent event) {
    String selecao = comboLivro.getValue();
    if (selecao != null && !selecao.isEmpty()) {
        try {
            // Extrai o código da primeira parte da seleção
            String codigoStr = selecao.split(" - ")[0].trim(); // Pega a primeira parte e remove espaços extras
            int codigo = Integer.parseInt(codigoStr); // Converte para inteiro

            // Chama o serviço para excluir o livro
            livroService.excluirLivro(codigo);
            exibirMensagem("Livro excluído com sucesso!");

            // Atualiza o ComboBox após exclusão
            carregarLivrosNoComboBox();
        } catch (NumberFormatException e) {
            exibirMensagem("Erro: Código do livro inválido. Selecione um livro corretamente.");
        } catch (Exception e) {
            exibirMensagem("Erro ao excluir livro: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        exibirMensagem("Por favor, selecione um livro para excluir.");
    }
}


    public void inicializarEdicao() {
        carregarLivrosNoComboBox();
    }

    @FXML
    private void listarLivros(ActionEvent event) {
        atualizarTabela();
    }

    @FXML
    private void selecionarLivro() {
        String selecao = comboLivro.getValue();
        if (selecao != null) {
            int codigo = Integer.parseInt(selecao.split(" - ")[0]);
            livroSelecionado = livroService.buscarLivroPorCodigo(codigo);
            if (livroSelecionado != null) {
                preencherCamposComLivro(livroSelecionado);
            }
        }
    }

    public void configurarColunasTabela() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo().getNome()));
        colunaAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor().getNome()));
        colunaDisponivel.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().isDisponivel() ? "Sim" : "Não"
        ));
        colunaExemplarBiblioteca.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().isExemplarBiblioteca() ? "Sim" : "Não"
        ));
    }
    

    private void atualizarInterface() {
        atualizarTabela();
        carregarLivrosNoComboBox();
    }

    private void atualizarTabela() {
        try {
            List<Livro> livros = livroService.listarLivros();
            tabelaLivros.setItems(FXCollections.observableArrayList(livros));
        } catch (Exception e) {
            exibirMensagem("Erro ao listar livros: " + e.getMessage());
        }
    }

    private void carregarLivrosNoComboBox() {
        if (comboLivro != null) { // Verifica se o ComboBox está presente no FXML carregado
            comboLivro.getItems().clear();
            try {
                List<Livro> livros = livroService.listarLivros();
                for (Livro livro : livros) {
                    comboLivro.getItems().add(livro.getCodigo() + " - " + livro.getTitulo().getNome());
                }
            } catch (Exception e) {
                exibirMensagem("Erro ao carregar livros: " + e.getMessage());
            }
        }
    }
    
    
    

    private void preencherCamposComLivro(Livro livro) {
        campoCodigo.setText(String.valueOf(livro.getCodigo())); 
        campoTitulo.setText(livro.getTitulo().getNome());       
        campoAutor.setText(livro.getAutor().getNome());         
        campoIsbn.setText(livro.getTitulo().getIsbn());
        campoEdicao.setText(String.valueOf(livro.getTitulo().getEdicao()));
        campoAno.setText(String.valueOf(livro.getTitulo().getAno()));
        checkboxDisponivel.setSelected(livro.isDisponivel());
        checkboxExemplarBiblioteca.setSelected(livro.isExemplarBiblioteca());
    }
    
    

    private Livro criarLivroAPartirCampos() {
        Titulo titulo = new Titulo(
            campoTitulo.getText(),
            campoIsbn.getText(),
            Integer.parseInt(campoEdicao.getText()),
            Integer.parseInt(campoAno.getText())
        );
    
        Autor autor = new Autor(1, campoAutor.getText()); // ID fictício e nome
    
        return new Livro(
            Integer.parseInt(campoCodigo.getText()),
            titulo,
            autor,
            checkboxDisponivel.isSelected(),
            checkboxExemplarBiblioteca.isSelected()
        );
    }
    

    public void carregarLivros() {
        try {
            List<Livro> listaLivros = livroService.listarLivros();
            ObservableList<Livro> livros = FXCollections.observableArrayList(listaLivros);
            tabelaLivros.setItems(livros); // Correção aplicada
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }
    
}
