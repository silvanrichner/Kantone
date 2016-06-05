package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import model.Canton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannik Inniger
 * on 22.05.2016
 */
public class DetailView extends FlowPane {

    private final static Label WHERE;
    private final static Label NAME;
    private final static Label ABBREVIATION;
    private final static Label CAPITAL;
    private final static Label AREA;

    static {
        Label where = new Label("Wo ist der Kanton?");
        where.setFont(new Font(where.getFont().getName(), 18));
        WHERE = where;
        NAME = createLabel("Name");
        ABBREVIATION = createLabel("Abkürzung");
        CAPITAL = createLabel("Hauptstadt");
        AREA = createLabel("Fläche");
    }

    private List<Label> inhabitantLabels;

    private TextField nameField;
    private TextField abbreviationField;
    private TextField capitalField;
    private TextField areaField;
    private List<TextField> inhabitantTextFields;

    private Canton model;

    public DetailView(Canton model){
        this.model = model;
        initializeControls();
        layoutControls();
        addEventHandlers();
    }

    private void initializeControls(){
        inhabitantLabels = new ArrayList<>();
        for(Integer i : model.getPopulation().keySet()){
            inhabitantLabels.add(createLabel("Einwohner " + i));
        }

        nameField = createTextField(model.getName());
        abbreviationField = createTextField(model.getAbbreviation());
        capitalField = createTextField(model.getCapital());
        areaField = createTextField(model.getArea()+"");

        inhabitantTextFields = new ArrayList<>();
        for(Integer i : model.getPopulation().values()){
            inhabitantLabels.add(createLabel("" + i));
        }
    }

    private void layoutControls(){

    }

    private void addEventHandlers(){

    }

    private static Label createLabel(String text){
        Label label = new Label(text);
        return label;
    }

    private TextField createTextField(String text){
        TextField textField = new TextField(text);
        return textField;
    }

}
