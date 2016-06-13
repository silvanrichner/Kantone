package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 * Created by Yannik on 12.06.2016.
 */
public class CantonList {

    private static CantonList instance;

    private ObjectProperty<ObservableList<Canton>> cantonList = new SimpleObjectProperty<>();

    public CantonList(ObservableList<Canton> cantons){
        if(cantonList != null){
            instance = this;
        } else {
            throw new RuntimeException("ONLY ONE INSTANCE OF CANTON LIST ALLOWED");
        }
        cantonList.setValue(cantons);
    }

    public ObjectProperty<ObservableList<Canton>> getCantonListProperty(){
        return cantonList;
    }

    public CantonList getInstance(){
        return instance;
    }

}
