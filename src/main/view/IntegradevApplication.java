package main.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.Controller;
import main.model.Model;

public class IntegradevApplication extends Application {
    
    private static Model model;
    
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/IntegradevSearcher.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setModel(model, stage);
        stage.setTitle("Integradev Interview Application");
        stage.setScene(new Scene(root));
        
        stage.setOnCloseRequest(event -> {
            model.saveSearchables();
            Platform.exit();
            System.exit(0);
        });
        
        stage.show();
    }
    
    public static void main(String[] args) {
        model = new Model();
        launch(args);
    }
}
