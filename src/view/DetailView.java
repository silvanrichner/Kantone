package view;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.Canton;

import java.util.*;

/**
 * @author Yannik Inniger
 * on 17.05.2016
 */
public class DetailView extends GridPane {

    private Label where;
    private Label name;
    private Label abbreviation;
    private Label capital;
    private Label area;
    private List<Label> inhabitantLabels;

    private ImageView locationView;
    private TextField nameField;
    private TextField abbreviationField;
    private TextField capitalField;
    private TextField areaField;
    private List<TextField> inhabitantTextFields;
    private PopulationChart populationChart;
    private Map<Integer, IntegerProperty> chartValues;

    private ObjectProperty<Canton> model;

    public DetailView(ObjectProperty<Canton> model) {
        this.model = model;
        initializeControls();
        layoutControls();
        addEventHandlers();
        addBindings();
    }

    private void initializeControls() {
        this.setId("detail-view");
        where = createLabel("Wo ist der Kanton?");
        where.setFont(new Font(where.getFont().getName(), 18));
        name = createLabel("Name");
        abbreviation = createLabel("Abkürzung");
        capital = createLabel("Hauptstadt");
        area = createLabel("Fläche");
        inhabitantLabels = new ArrayList<>();
        model.get().getPopulation().keySet().forEach(i -> inhabitantLabels.add(createLabel("Einwohner " + i)));

        locationView = new ImageView(model.getValue().getLocationImageProperty().getValue());
        nameField = createTextField(model.getName());
        abbreviationField = createTextField(model.get().getAbbreviationProperty().getValue());
        capitalField = createTextField(model.get().getCapitalProperty().getValue());
        areaField = createTextField(model.get().getAreaProperty().getValue() + "");

        inhabitantTextFields = new ArrayList<>();
        model.get().getPopulation().values().forEach(i -> inhabitantTextFields.add(createTextField(i.getValue() + "")));

        chartValues = new HashMap<>();
        model.get().getPopulation().forEach((k, v) -> chartValues.put(k, new SimpleIntegerProperty(Integer.parseInt(v.getValue()))));

        populationChart = new PopulationChart(chartValues);
    }

    private void layoutControls() {
        getRowConstraints().add(createColumnConstraint(130));
        for (int i = 0; i != 9; ++i) {
            getRowConstraints().add(createColumnConstraint(30));
        }
        getRowConstraints().add(createColumnConstraint(300));

        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setHgrow(Priority.ALWAYS);
        cc1.percentWidthProperty().setValue(40);
        getColumnConstraints().add(cc1);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setHgrow(Priority.ALWAYS);
        cc2.percentWidthProperty().setValue(60);
        getColumnConstraints().add(cc2);

        add(where, 0, 0);
        add(name, 0, 1);
        add(abbreviation, 0, 2);
        add(capital, 0, 3);
        add(area, 0, 4);
        int index = 5;
        for (Label l : inhabitantLabels) {
            add(l, 0, index++);
        }

        locationView.fitHeightProperty().setValue(120);
        locationView.preserveRatioProperty().setValue(true);
        add(locationView, 1, 0);
        add(nameField, 1, 1);
        add(abbreviationField, 1, 2);
        add(capitalField, 1, 3);
        add(areaField, 1, 4);
        index = 5;
        for (TextField t : inhabitantTextFields) {
            add(t, 1, index++);
        }
        populationChart.setPadding(new Insets(5));
        add(populationChart, 0, 10, 2, 1);
    }

    private void addEventHandlers() {
        for (TextField textField : inhabitantTextFields) {
            textField.setOnAction(e -> {
                model.get().getPopulation().forEach((k, v) -> {
                    if (v.getValue() != "") {
                        chartValues.put(k, new SimpleIntegerProperty(Integer.parseInt(v.getValue())));
                    }
                });
                getChildren().remove(populationChart);
                populationChart = new PopulationChart(chartValues);
                add(populationChart, 0, 10, 2, 1);
            });
        }
    }

    private void addBindings() {
        model.addListener(((observable, oldValue, newValue) -> {
            if(oldValue != null){
                nameField.textProperty().unbindBidirectional(oldValue.getNameProperty());
                abbreviationField.textProperty().unbindBidirectional(oldValue.getAbbreviationProperty());
                capitalField.textProperty().unbindBidirectional(oldValue.getCapitalProperty());
                areaField.textProperty().unbindBidirectional(oldValue.getAreaProperty());
                for (int i = 0; i != inhabitantTextFields.size(); ++i) {
                    inhabitantTextFields.get(i).textProperty().unbindBidirectional(oldValue.getPopulation()
                            .get(oldValue.getYearList().get(i)));
                }
            }

            if(newValue != null){
                locationView.imageProperty().unbind();
                locationView.imageProperty().bind(model.get().getLocationImageProperty());
                nameField.textProperty().bindBidirectional(newValue.getNameProperty());
                abbreviationField.textProperty().bindBidirectional(newValue.getAbbreviationProperty());
                capitalField.textProperty().bindBidirectional(newValue.getCapitalProperty());
                areaField.textProperty().bindBidirectional(newValue.getAreaProperty());

                for (int i = 0; i != inhabitantTextFields.size(); ++i) {
                    inhabitantTextFields.get(i).textProperty().bindBidirectional(newValue.getPopulation()
                            .get(newValue.getYearList().get(i)));
                }

                newValue.getPopulation().forEach((k, v) -> {
                    if (v.getValue() != "") {
                        chartValues.put(k, new SimpleIntegerProperty(Integer.parseInt(v.getValue())));
                    }
                });
                getChildren().remove(populationChart);
                populationChart = new PopulationChart(chartValues);
                populationChart.maxWidthProperty().bind(widthProperty().subtract(50));
                add(populationChart, 0, 10, 2, 1);
            }
        }));
        for (int i = 0; i != inhabitantTextFields.size(); ++i) {
            inhabitantTextFields.get(i).textProperty().bindBidirectional(model.getValue().getPopulation().get(model.get().getYearList().get(i)));
        }
        nameField.textProperty().bindBidirectional(model.get().getNameProperty());
        populationChart.maxWidthProperty().bind(widthProperty().subtract(50));
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setPadding(new Insets(0, 0, 0, 10));
        return label;
    }

    private TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.maxWidthProperty().setValue(200);
        return textField;
    }

    private RowConstraints createColumnConstraint(double maxHeigth) {
        RowConstraints temp = new RowConstraints();
        temp.maxHeightProperty().setValue(maxHeigth);
        temp.setVgrow(Priority.ALWAYS);
        return temp;
    }

}
