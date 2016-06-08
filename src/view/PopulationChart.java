package view;

import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class PopulationChart extends LineChart<Number, Number> {

	public PopulationChart(Map<Integer, IntegerProperty> populationData) {
		super(new NumberAxis("Jahr",
				5 * (Math.floor(new Double(populationData.entrySet().stream().min((s1, s2) -> s1.getKey() - s2.getKey()).get()
						.getKey().intValue() - 5) / 5)),
				5 * (Math.ceil(new Double(populationData.entrySet().stream().max((s1, s2) -> s1.getKey() - s2.getKey()).get()
						.getKey().intValue() + 5) / 5)),
				5),
			  new NumberAxis("Einwohner",
				50000 * (Math.floor(new Double(populationData.entrySet().stream().min((s1, s2) ->
						s1.getValue().getValue() - s2.getValue().getValue()).get().getValue().intValue() - 50000) / 50000)),
				50000 * (Math.ceil(new Double(populationData.entrySet().stream().max((s1, s2) ->
						s1.getValue().getValue() - s2.getValue().getValue()).get().getValue().intValue() + 50000) / 50000)),
				50000));

		this.setLegendVisible(false);

		Series<Number, Number> series = new Series<Number, Number>();
		
		for (Map.Entry<Integer, IntegerProperty> entry : populationData.entrySet()) {
			Data<Number,Number> data = new Data<Number, Number>(entry.getKey(), entry.getValue().getValue());
			data.setNode(new HoveredNode(entry.getValue().getValue()));
			series.getData().add(data);
		}

		this.getData().add(series);
	}
	
	 class HoveredNode extends StackPane {
		    HoveredNode(int value) {
		      setPrefSize(15, 15);

		      final Label label = createDataLabel(value);

		      setOnMouseEntered(new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent mouseEvent) {
		          getChildren().setAll(label);
		          setCursor(Cursor.NONE);
		          toFront();
		        }
		      });
		      setOnMouseExited(new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent mouseEvent) {
		          getChildren().clear();
		          setCursor(Cursor.CROSSHAIR);
		        }
		      });
		    }
	 }
	 
	 private Label createDataLabel(int value) {
	      final Label label = new Label(value + "");
	      label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
	      label.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
	      
	      label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
	      return label;
	    
	  }
}
