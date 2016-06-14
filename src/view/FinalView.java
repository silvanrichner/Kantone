package view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.CantonList;

/**
 * @author Yannik Inniger
 * on 17.05.2016
 */
public class FinalView extends VBox{

    private HBox navigationBar;
    private SplitPane content;
    private CantonList model;

    public FinalView(CantonList model){
        this.model = model;
        initializeControls();
        layoutControls();
        addEventHandlers();
    }

    private void initializeControls(){
        navigationBar = new NavigationBar(model);
        content = new SplitPane(model);
    }

    private void layoutControls(){
        setVgrow(content, Priority.ALWAYS);
        getChildren().addAll(navigationBar, content);
    }

    private void addEventHandlers(){

    }

}
