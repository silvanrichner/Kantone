package view;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Canton;

/**
 * Created by yanni on 17.05.2016
 */
public class SplitPane extends javafx.scene.control.SplitPane{

    private ListView<Canton> cantonList;
    private FlowPane detailView;

    private Canton model;

    public SplitPane(){
        //this.model = model;
        initializeControls();
        layoutControls();
    }

    private void initializeControls(){
        cantonList = new ListView<>();
        detailView = new FlowPane();
    }

    private void layoutControls(){
        getItems().addAll(cantonList, detailView);
        setDividerPositions(0.3f, 0.6f);
    }



}
