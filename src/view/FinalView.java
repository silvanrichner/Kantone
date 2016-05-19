package view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Created by yanni on 17.05.2016
 */
public class FinalView extends VBox{

    private HBox navigationBar;
    private SplitPane content;

    public FinalView(){
        initializeControls();
        layoutControls();
    }

    private void initializeControls(){
        navigationBar = new NavigationBar();
        content = new SplitPane();
    }

    private void layoutControls(){
        setVgrow(content, Priority.ALWAYS);
        getChildren().addAll(navigationBar, content);
    }

}
