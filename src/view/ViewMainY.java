package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Canton;
import model.CantonList;
import model.XmlIO;

/**
 * Created by yanni on 17.05.2016
 */
public class ViewMainY extends Application{

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage window){
        CantonList model = new CantonList(FXCollections.observableArrayList(XmlIO.readXml()));
        Parent root = new FinalView(model);
        Scene scene = new Scene(root);
        String style = getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(style);

        window.setScene(scene);
        window.setTitle("Test");
        window.setHeight(800);
        window.setWidth(1000);
        window.show();
    }

}