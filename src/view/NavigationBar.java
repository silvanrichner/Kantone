package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
        save = new Button("Save");
        redo = new Button("ReDo");
        undo = new Button("UnDo");
        home = new Button("Home");
    }

    private void layoutControls(){
        getChildren().addAll(save, undo, redo, home);
    }

}
