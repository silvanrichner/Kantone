package model;

import javafx.beans.property.IntegerProperty;

import java.util.Collection;
import java.util.Map.Entry;

public class ModelMain {

	public static void main(String[] args) {
		Collection<Canton> cantons = XmlIO.readXml();
		
		for(Canton canton: cantons){
			System.out.println(canton.getNameProperty().getValue());
			System.out.println("    " + canton.getAbbreviationProperty());
			System.out.println("    " + canton.getCapitalProperty());
			System.out.println("    " + canton.getAreaProperty());
			
			/*for (Entry<Integer, IntegerProperty> entry : canton.getPopulation().entrySet()) {
			     System.out.println("    Key: " + entry.getKey() + " Value: " + entry.getValue());
			}*/
			
			canton.getNameProperty().setValue(canton.getNameProperty().getValue() + "testitest");
		}

		
		
		
		XmlIO.writeXml(cantons);
	}

}
