package view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import model.Canton;
import model.CantonList;

/**
 * Created by yanni on 17.05.2016
 */
public class SplitPane extends javafx.scene.control.SplitPane{

    private ListView<Canton> cantonList;
    private GridPane detailView;

    private ObjectProperty<Canton> selectedCanton = new SimpleObjectProperty<>();
    private CantonList model;

    public SplitPane(CantonList model){
        this.model = model;
        selectedCanton.setValue(model.getCantonListProperty().getValue().get(0));
        initializeControls();
        layoutControls();
        addEventHandlers();
        addBindings();
    }

    private void initializeControls(){
        cantonList = new ListView<>(model.getCantonListProperty().getValue());
        cantonList.setCellFactory(c -> new CantonCell());
        detailView = new DetailView(selectedCanton);
    }

    private void layoutControls(){
        cantonList.fixedCellSizeProperty().setValue(80);
        cantonList.minWidthProperty().setValue(280);
        cantonList.maxWidthProperty().setValue(350);
        detailView.minWidthProperty().setValue(520);
        getItems().addAll(cantonList, detailView);
        setDividerPositions(0.3f, 0.6f);
    }

    private void addEventHandlers(){
    }

    private void addBindings(){
        selectedCanton.bind(cantonList.focusModelProperty().get().focusedItemProperty());
    }



}
