package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlIO {

	private static String FILE_URL = ".." + File.separator + "resources" + File.separator + "cantons.xml";

	public static Collection<Canton> readXml() {
		Collection<Canton> cantons = new ArrayList<Canton>();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlIO.class.getResourceAsStream(FILE_URL));

			doc.getDocumentElement().normalize();

			NodeList cantonNodes = doc.getElementsByTagName("canton");

			for (int i = 0; i < cantonNodes.getLength(); i++) {

				Node canton = cantonNodes.item(i);

				if (canton.getNodeType() == Node.ELEMENT_NODE) {

					Element cantonElement = (Element) canton;

					String name = cantonElement.getElementsByTagName("name").item(0).getTextContent();
					String abbreviation = cantonElement.getElementsByTagName("abbreviation").item(0).getTextContent();
					String capital = cantonElement.getElementsByTagName("capital").item(0).getTextContent();
					int area = Integer.valueOf(cantonElement.getElementsByTagName("area").item(0).getTextContent());
					Map<Integer, Integer> population = new TreeMap<Integer, Integer>();

					Element populationElement = (Element) cantonElement.getElementsByTagName("populationdata").item(0);
					NodeList populationNodes = populationElement.getElementsByTagName("datapoint");
					
					for (int j = 0; j < populationNodes.getLength(); j++) {

						Node populationNode = populationNodes.item(j);
						if (populationNode.getNodeType() == Node.ELEMENT_NODE) {
							Integer year = Integer
									.valueOf(((Element) populationNodes.item(j)).getElementsByTagName("year").item(0).getTextContent());
							Integer amount = Integer.valueOf(
									((Element) populationNodes.item(j)).getElementsByTagName("population").item(0).getTextContent());
							population.put(year, amount);
						}
					}
					
					cantons.add(new Canton(name, abbreviation, capital, area, population));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cantons;
	}

	public static void writeXml(Collection<Canton> cantons){
		
	}
}