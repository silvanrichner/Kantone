package view;

import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Canton;

/**
 * Created by yanni on 17.05.2016
 */
public class FinalView extends VBox{

    private HBox navigationBar;
    private SplitPane content;

    public FinalView(ObservableList<Canton> cantons){
        initializeControls(cantons);
        layoutControls();
    }

    private void initializeControls(ObservableList<Canton> cantons){
        navigationBar = new NavigationBar();
        content = new SplitPane(cantons);
    }

    private void layoutControls(){
        setVgrow(content, Priority.ALWAYS);
        getChildren().addAll(navigationBar, content);
    }

}
