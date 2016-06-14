package view;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.CantonList;
import model.XmlIO;

import java.io.File;

/**
 * @author Yannik Inniger
 * on 17.05.2016
 */
public class NavigationBar extends HBox{

    private Button save;

    private CantonList model;

    public NavigationBar(CantonList model){
        this.model = model;
        initializeControls();
        layoutControls();
        addEventHandlers();
    }

    private void initializeControls(){
        this.setId("toolbar");
        save = initializeButton("save");
    }

    private void layoutControls(){
        setMaxHeight(30);
        setPadding(new Insets(5));
        getChildren().add(save);
    }

    private Button initializeButton(String name){
        final File IMAGE = new File("src" + File.separator + "resources" + File.separator + "ui_elements" + File.separator + name.toLowerCase() + ".png");
        Button button = new Button();
        if(IMAGE.canRead()){
            ImageView iv = new ImageView(IMAGE.toURI().toString());
            iv.fitHeightProperty().bind(this.maxHeightProperty());
            iv.setPreserveRatio(true);
            button.setGraphic(iv);
            button.setPadding(new Insets(5));
            return button;
        } else {
            return null;
        }
    }

    private void addEventHandlers(){
        save.setOnAction(e -> {
            XmlIO.writeXml(model.getCantonListProperty().getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Daten wruden gespeichert");
            alert.show();
        });
    }

    public Button getSaveButtoon(){
        return save;
    }

}
