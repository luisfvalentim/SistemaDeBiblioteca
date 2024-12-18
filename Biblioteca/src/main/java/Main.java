package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controller.MainController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SistemaBiblioteca.fxml"));

            Scene scene = new Scene(loader.load());

            // Configurar o controlador
            MainController mainController = loader.getController();
            mainController.setStage(primaryStage);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Sistema Biblioteca");
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar a aplicação.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
