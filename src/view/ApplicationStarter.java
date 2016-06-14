package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Canton;
import model.CantonList;
import model.XmlIO;

import java.io.File;

/**
 * @author Yannik Inniger
 * on 17.05.2016
 */
public class ApplicationStarter extends Application{

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

        window.getIcons().add(new Image("resources/app-icon.jpg"));
        window.setTitle("Kantons App");
        window.setScene(scene);
        window.setHeight(800);
        window.setWidth(1000);
        window.setMinWidth(800);
        window.setMinHeight(650);
        window.show();
    }

}