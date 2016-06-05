package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Canton;
import model.XmlIO;

/**
 * Created by yanni on 17.05.2016
 */
public class ViewMain extends Application{

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage window){
        ObservableList<Canton> cantons = FXCollections.observableArrayList(XmlIO.readXml()); // future model
        Parent root = new FinalView(cantons);
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.setTitle("Test");
        window.setHeight(600);
        window.setWidth(1000);
        window.show();
    }

}