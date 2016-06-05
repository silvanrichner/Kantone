package view;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import model.Canton;

/**
 * Created by yanni on 17.05.2016
 */
public class SplitPane extends javafx.scene.control.SplitPane{

    private ListView<Canton> cantonList;
    private FlowPane detailView;

    private ObservableList<Canton> cantons;

    public SplitPane(ObservableList<Canton> cantons){
        this.cantons = cantons;
        //this.model = model;
        initializeControls();
        layoutControls();
        addBindings();
    }

    private void initializeControls(){
        cantonList = new ListView<>(cantons);
        cantonList.setCellFactory(c -> new CantonCell());
        detailView = new FlowPane();
    }

    private void layoutControls(){
        cantonList.fixedCellSizeProperty().setValue(80);
        getItems().addAll(cantonList, detailView);
        setDividerPositions(0.3f, 0.6f);
    }

    private void addBindings(){

    }



}
