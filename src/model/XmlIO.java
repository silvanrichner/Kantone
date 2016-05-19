package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlIO {

	private static String FILE_URL = ".." + File.separator + "resources" + File.separator + "cantons.xml";


	public static Collection<Canton> readXml() {
		Collection<Canton> cantons = new ArrayList<>();

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
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.newDocument();
			
			Element rootElement = doc.createElement("cantons");
			doc.appendChild(rootElement);
			
			for(Canton canton: cantons){
				Element cantonElement = doc.createElement("canton");
				rootElement.appendChild(cantonElement);
				
				Element nameElement = doc.createElement("name");
				nameElement.setTextContent(canton.getName());
				cantonElement.appendChild(nameElement);
				
				Element abbreviationElement = doc.createElement("abbreviation");
				abbreviationElement.setTextContent(canton.getAbbreviation());
				cantonElement.appendChild(abbreviationElement);
				
				Element capitalElement = doc.createElement("capital");
				capitalElement.setTextContent(canton.getCapital());
				cantonElement.appendChild(capitalElement);
				
				Element areaElement = doc.createElement("area");
				areaElement.setTextContent(Integer.toString(canton.getArea()));
				cantonElement.appendChild(areaElement);
				
				Element populationdataElement = doc.createElement("populationdata");
				for (Entry<Integer, Integer> entry : canton.getPopulation().entrySet()) {
					Element datapointElement = doc.createElement("datapoint");
					
					Element yearElement = doc.createElement("year");
					yearElement.setTextContent(entry.getKey().toString());
					datapointElement.appendChild(yearElement);
					
					Element populationElement = doc.createElement("population");
					populationElement.setTextContent(entry.getValue().toString());
					datapointElement.appendChild(populationElement);
					
					populationdataElement.appendChild(datapointElement);
				}
				cantonElement.appendChild(populationdataElement);
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			Result output = new StreamResult(new File("src" + File.separator + "resources" + File.separator + "cantons.xml"));
			transformer.transform(new DOMSource(doc), output);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}