package view;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Canton;
import model.XmlIO;

public class ViewMain extends Application{

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		List<Canton> cantons = (List<Canton>) XmlIO.readXml();
		
//		Scene scene = new Scene(new PopulationChart(cantons.get(5).getPopulation()));
//		stage.setScene(scene);
//	    stage.show();
		
		//Scene scene = new Scene(new MapSelectionButton());
	}

}
