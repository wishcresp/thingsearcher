package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.Controller;
import main.model.Model;

public class ThingSearcherApplication extends Application {
    
    private static final Model model = new Model();
    
    @Override
    public void start(Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/layouts/ThingSearcher.fxml"));
        final Parent root = loader.load();
        final Controller controller = loader.getController();
        
        // Set the model and refresh the interface in case data was loaded
        controller.setModel(model, stage);
        controller.refreshInterface();
        
        stage.setTitle("ThingSearcher Application");
        stage.setScene(new Scene(root));
        
        // Save before exit
        stage.setOnCloseRequest(event -> {
            model.saveAttributes();
            model.saveSearchables();
            Platform.exit();
            System.exit(0);
        });
        
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
