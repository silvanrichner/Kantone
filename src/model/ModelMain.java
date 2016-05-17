package model;

import java.util.Collection;
import java.util.Map.Entry;

public class ModelMain {

	public static void main(String[] args) {
		Collection<Canton> cantons = XmlIO.readXml();
		
		for(Canton canton: cantons){
			System.out.println(canton.getName());
			System.out.println("    " + canton.getAbbreviation());
			System.out.println("    " + canton.getCapital());
			System.out.println("    " + canton.getArea());
			
			for (Entry<Integer, Integer> entry : canton.getPopulation().entrySet()) {
			     System.out.println("    Key: " + entry.getKey() + " Value: " + entry.getValue());
			}
		}
		
		//XmlIO.writeXml(cantons);
	}

}
