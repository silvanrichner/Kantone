package model;

import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Map;

public class Canton{
	
	private String name;
	private String abbreviation;
	private String capital;
	private int area;
	private Map<Integer, Integer> population;
	private File flag;

    public Canton(String name, String abbreviation, String capital, int area, Map<Integer,Integer> population) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.capital = capital;
		this.area = area;
		this.population = population;
		addFlag();
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

    public File getFlag() {
        return flag;
    }

    public void setFlag(File flag) {
        this.flag = flag;
    }

    private void addFlag(){
        String FILE_URL = "src" + File.separator + "resources" + File.separator + "flags" + File.separator + this.getAbbreviation().toLowerCase() + ".png";
        this.flag = new File(FILE_URL);
    }

}
