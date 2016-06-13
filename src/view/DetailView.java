package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.Canton;

import java.util.*;

/**
 * @author Yannik Inniger
 * on 22.05.2016
 */
public class DetailView extends GridPane {

    private Label where;
    private Label name;
    private Label abbreviation;
    private Label capital;
    private Label area;
    private List<Label> inhabitantLabels;

    private TextField nameField;
    private TextField abbreviationField;
    private TextField capitalField;
    private TextField areaField;
    private List<TextField> inhabitantTextFields;
    private PopulationChart populationChart;
    private Map<Integer, IntegerProperty> chartValues;

    private ObjectProperty<Canton> model;

    public DetailView(ObjectProperty<Canton> model){
        this.model = model;
        initializeControls();
        layoutControls();
        addEventHandlers();
        addBindings();
    }

    private void initializeControls(){
        where = createLabel("Wo ist der Kanton?");
        where.setFont(new Font(where.getFont().getName(), 18));
        name = createLabel("Name");
        abbreviation = createLabel("Abkürzung");
        capital = createLabel("Hauptstadt");
        area = createLabel("Fläche");
        inhabitantLabels = new ArrayList<>();
        model.get().getPopulation().keySet().forEach(i -> inhabitantLabels.add(createLabel("Einwohner " + i)));

        nameField = createTextField(model.getName());
        abbreviationField = createTextField(model.get().getAbbreviationProperty().getValue());
        capitalField = createTextField(model.get().getCapitalProperty().getValue());
        areaField = createTextField(model.get().getAreaProperty().getValue()+"");

        inhabitantTextFields = new ArrayList<>();
        model.get().getPopulation().values().forEach(i -> inhabitantTextFields.add(createTextField(i.getValue() + "")));

        chartValues = new HashMap<>();
        model.get().getPopulation().forEach((k, v) -> chartValues.put(k, new SimpleIntegerProperty(Integer.parseInt(v.getValue()))));

        populationChart = new PopulationChart(chartValues);
    }

    private void layoutControls(){
        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        for(int i = 0; i != 11; ++i){
            getRowConstraints().add(rc);
        }
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setHgrow(Priority.ALWAYS);
        cc1.setPercentWidth(40);
        getColumnConstraints().add(cc1);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setHgrow(Priority.ALWAYS);
        cc2.setPercentWidth(60);
        getColumnConstraints().add(cc2);

        add(where, 0, 0);
        add(name, 0, 1);
        add(abbreviation, 0, 2);
        add(capital, 0, 3);
        add(area, 0, 4);
        int index = 5;
        for(Label l : inhabitantLabels){
            add(l, 0, index++);
        }

        add(nameField, 1, 1);
        add(abbreviationField, 1, 2);
        add(capitalField, 1, 3);
        add(areaField, 1, 4);
        index = 5;
        for(TextField t : inhabitantTextFields){
            add(t, 1, index++);
        }
        populationChart.setPadding(new Insets(5));
        add(populationChart, 0, 10, 2, 1);

    }

    private void addEventHandlers(){
    }

    private void addBindings(){
        model.addListener(((observable, oldValue, newValue) -> {
            nameField.textProperty().unbindBidirectional(oldValue.getNameProperty());
            nameField.clear();
            nameField.textProperty().bindBidirectional(newValue.getNameProperty());
            abbreviationField.textProperty().unbindBidirectional(oldValue.getAbbreviationProperty());
            abbreviationField.clear();
            abbreviationField.textProperty().bindBidirectional(newValue.getAbbreviationProperty());
            capitalField.textProperty().unbindBidirectional(oldValue.getCapitalProperty());
            capitalField.clear();
            capitalField.textProperty().bindBidirectional(newValue.getCapitalProperty());
            areaField.textProperty().unbindBidirectional(oldValue.getAreaProperty());
            areaField.textProperty().bindBidirectional(newValue.getAreaProperty());

            model.get().getPopulation().forEach((k, v) -> {
                if(v.getValue() != "") {
                    chartValues.put(k, new SimpleIntegerProperty(Integer.parseInt(v.getValue())));
                }
            });

            getChildren().remove(populationChart);
            populationChart = new PopulationChart(chartValues);
            add(populationChart, 0, 10, 2, 1);

            for(int i = 0; i != inhabitantTextFields.size(); ++i){
                inhabitantTextFields.get(i).textProperty().unbindBidirectional(oldValue.getPopulation().get(model.get().getYearList().get(i)));
                inhabitantTextFields.get(i).clear();
                inhabitantTextFields.get(i).textProperty().bindBidirectional(newValue.getPopulation().get(model.get().getYearList().get(i)));
            }
        }));
        nameField.textProperty().bindBidirectional(model.get().getNameProperty());
        populationChart.maxWidthProperty().bind(widthProperty().subtract(50));
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        label.setPadding(new Insets(0, 0, 0, 10));
        return label;
    }

    private TextField createTextField(String text){
        TextField textField = new TextField(text);
        textField.maxWidthProperty().bind(widthProperty().divide(2));
        return textField;
    }

}
