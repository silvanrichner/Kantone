package model;

import java.util.Map;

public class Canton{
	
	private String name;
	private String abbreviation;
	private String capital;
	private int area;
	private Map<Integer, Integer> population;

	public Canton(String name, String abbreviation, String capital, int area, Map<Integer,Integer> population) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.capital = capital;
		this.area = area;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Map<Integer,Integer> getPopulation() {
		return population;
	}

	public void setPopulation(Map<Integer,Integer> population) {
		this.population = population;
	}

}
