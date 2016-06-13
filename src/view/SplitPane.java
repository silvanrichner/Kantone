package view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import model.Canton;
import model.CantonList;

/**
 * Created by yanni on 17.05.2016
 */
public class SplitPane extends javafx.scene.control.SplitPane{

    private ListView<Canton> cantonList;
    private GridPane detailView;
    private BorderPane navigation;

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

        FilteredList<Canton> filteredData = new FilteredList<Canton>(model.getCantonListProperty().getValue(), s -> true);
        SortedList<Canton> sortedData = new SortedList<>(filteredData, (s1, s2) -> s1.getNameProperty().getValue()
                .compareTo(s2.getNameProperty().getValue()));

        TextField filterInput = new TextField();
        filterInput.textProperty().addListener(obs -> {
            String filter = filterInput.getText();
            if (filter == null || filter.length() == 0) {
                filteredData.setPredicate(s -> true);
                sortedData.setComparator((s1, s2) -> s1.getNameProperty().getValue().compareTo(s2.getNameProperty().getValue()));
            } else {
                filteredData.setPredicate(s -> s.levenshteinDistance(filter, s.getNameProperty().getValue()) <= (s.getNameProperty()
                        .getValue().length()));
                sortedData.setComparator((s1, s2) -> (s1.levenshteinDistance(filter, s1.getNameProperty().getValue())) -
                        (s2.levenshteinDistance(filter, s2.getNameProperty().getValue())));
            }
        });

        cantonList = new ListView<Canton>(sortedData);
        cantonList.setCellFactory(c -> new CantonCell());
        detailView = new DetailView(selectedCanton);

        navigation = new BorderPane(cantonList);
        navigation.setTop(filterInput);
    }

    private void layoutControls(){
        cantonList.fixedCellSizeProperty().setValue(80);
        getItems().addAll(navigation, detailView);
        navigation.maxWidthProperty().setValue(350);
        navigation.minWidthProperty().setValue(230);
        detailView.minWidthProperty().setValue(520);
        setDividerPositions(0.3f, 0.6f);
    }

    private void addEventHandlers(){
    }

    private void addBindings(){
        selectedCanton.bind(cantonList.focusModelProperty().get().focusedItemProperty());
    }



}
