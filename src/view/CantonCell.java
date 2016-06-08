package view;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Canton;

/**
 * Created by Yannik on 19.05.2016
 */
public class CantonCell extends ListCell<Canton>{

    @Override
    public void updateItem(Canton canton, boolean empty){
        super.updateItem(canton, empty);
        setGraphic(null);
        setText(null);
        if(canton != null){
            setText(canton.getNameProperty().getValue());

            //set up ImageView
            ImageView flagView = new ImageView();
            flagView.fitHeightProperty().bind(getListView().fixedCellSizeProperty().subtract(5));
            flagView.preserveRatioProperty().setValue(true);

            if(canton.getFlag().canRead()){
                flagView.setImage(new Image(canton.getFlag().toURI().toString()));
            } else {
                flagView.setImage(null);
            }
            setGraphic(flagView);
        }

    }

}
