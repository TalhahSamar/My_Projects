package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application{
    @Override
    public void start(Stage s){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root,590,790);
            scene.getStylesheets().add(getClass().getResource("sample.css").toExternalForm());
            s.setScene(scene);
            s.setTitle("LUDO MASTER - HIPPO LAB");
            Image i = new Image(Controller.class.getResource("/Images/LudoBoard.png").toExternalForm(), false);
            s.getIcons().add(i);
            s.setResizable(false);
            s.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}