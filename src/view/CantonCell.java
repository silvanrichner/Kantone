package view;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Canton;

/**
 * @author Yannik Inniger
 * on 17.05.2016
 */
public class CantonCell extends ListCell<Canton>{

    @Override
    public void updateItem(Canton canton, boolean empty){
        super.updateItem(canton, empty);
        setGraphic(null);
        setText(null);
        if(canton != null){
            setText(canton.getNameProperty().getValue());

            ImageView flagView = new ImageView();
            flagView.fitHeightProperty().bind(getListView().fixedCellSizeProperty().subtract(8));
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
