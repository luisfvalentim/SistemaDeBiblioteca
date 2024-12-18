package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainController {

    private Stage stage;

    public MainController() {
        // Construtor vazio, o stage será configurado externamente (ou use FXMLLoader para passar o stage).
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Métodos para o Menu Aluno
    /*@FXML
    private void cadastrarAluno(ActionEvent event) {
        carregarTela("/view/CadastroAluno.fxml", "Cadastrar Aluno");
    }*/

    @FXML
    private void cadastrarAluno(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroAluno.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastrar Aluno");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela EditarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void editarAluno(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditarAluno.fxml"));
            Parent root = loader.load();

            // Obtém o controlador e chama a inicialização específica
            AlunoController controller = loader.getController();
            controller.inicializarEdicao(); // Atualiza o ComboBox apenas para a tela Editar

            // Configura e exibe a tela
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Aluno");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela EditarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void excluirAluno(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExcluirAluno.fxml"));
            Parent root = loader.load();
    
            // Obtém o controlador e chama a inicialização específica
            AlunoController controller = loader.getController();
            controller.inicializarEdicao(); // Atualiza o ComboBox apenas para a tela Editar
    
            // Configura e exibe a tela
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Excluir Aluno");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela ExcluirAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void listarAlunos(ActionEvent event) {
        try {
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListarAluno.fxml"));
            Parent root = loader.load();

            // Obtém o controlador associado ao FXML
            AlunoController controller = loader.getController();
        
            // Inicializa a tabela chamando métodos específicos
            controller.configurarColunas();
            controller.carregarAlunos();

            // Configura e exibe a nova janela sem afetar a principal
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Listar Alunos");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela ListarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Métodos para o Menu Livro
    /*@FXML
    private void cadastrarLivro(ActionEvent event) {
        carregarTela("/view/CadastroLivro.fxml", "Cadastrar Livro");
    }*/

    @FXML
    private void cadastrarLivro(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroLivro.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastrar Livro");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela EditarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void editarLivro(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditarLivro.fxml"));
            Parent root = loader.load();

            // Obtém o controlador e chama a inicialização específica
            LivroController controller = loader.getController();
            controller.inicializarEdicao(); // Atualiza o ComboBox apenas para a tela Editar

            // Configura e exibe a tela
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Livro");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela EditarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
private void excluirLivro(ActionEvent event) {
    try {
        // Carrega o FXML da tela Excluir Livro
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExcluirLivro.fxml"));
        Parent root = loader.load();

        // Obtém o controlador associado e chama a inicialização específica
        LivroController controller = loader.getController();
        controller.inicializarEdicao(); // Carrega os livros no ComboBox para exclusão

        // Configura e abre uma nova janela
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Excluir Livro");
        stage.show();
    } catch (Exception e) {
        System.err.println("Erro ao carregar a tela ExcluirLivro: " + e.getMessage());
        e.printStackTrace();
    }
}



    @FXML
    private void listarLivros(ActionEvent event) {
        try {
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListarLivro.fxml"));
            Parent root = loader.load();

            // Obtém o controlador associado ao FXML
            LivroController controller = loader.getController();
        
            // Inicializa a tabela chamando métodos específicos
            controller.configurarColunasTabela(); // Configura as colunas da tabela
            controller.carregarLivros();          // Carrega a lista de livros


            // Configura e exibe a nova janela sem afetar a principal
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Listar Livro");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela ListarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Métodos para Emprestimo
    /*@FXML
    private void novoEmprestimo(ActionEvent event) {
        carregarTela("/view/Emprestimo.fxml", "Novo Empréstimo");
    }*/

    @FXML
    private void novoEmprestimo(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Emprestimo.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Novo emprestimo");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela EditarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Métodos para Devolução
    /*@FXML
    private void registrarDevolucao(ActionEvent event) {
        carregarTela("/view/Devolucao.fxml", "Devolução");
    }*/

    @FXML
    private void registrarDevolucao(ActionEvent event) {
        try {
        
            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Devolucao.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registrar devolucao");
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela EditarAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void sairSistema(ActionEvent event) {
        System.out.println("Saindo do sistema...");
        System.exit(0); // Fecha o sistema
    }


    // Método auxiliar para carregar telas
    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            // Configurando o palco (Stage) com a nova cena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a tela: " + caminhoFXML);
            e.printStackTrace();
        }
    }
}
