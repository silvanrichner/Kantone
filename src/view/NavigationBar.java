package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;

/**
 * Created by yanni on 17.05.2016.
 */
public class NavigationBar extends HBox{

    private Button save;
    private Button redo;
    private Button undo;
    private Button home;

    public NavigationBar(){
        initializeControls();
        layoutControls();
    }

    private void initializeControls(){
        save = initializeButton("save");
        redo = initializeButton("redo");
        undo = new Button("UnDo");
        home = new Button("Home");
    }

    private void layoutControls(){
        setMaxHeight(30);
        setPadding(new Insets(5));
        getChildren().addAll(save, undo, redo, home);
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

}
