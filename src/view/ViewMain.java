package view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by yanni on 17.05.2016
 */
public class ViewMain extends Application{

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage window){
        Parent root = new FinalView();
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.setTitle("Test");
        window.setHeight(600);
        window.setWidth(1000);
        window.show();
    }

}
