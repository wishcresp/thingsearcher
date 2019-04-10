package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.Controller;
import main.model.Model;

public class IntegradevApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/IntegradevSearcher.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setModel(new Model());
        primaryStage.setTitle("Integradev Interview Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
