package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Map;

public class Canton{
	
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty abbreviation = new SimpleStringProperty();
	private SimpleStringProperty capital = new SimpleStringProperty();
	private IntegerProperty area = new SimpleIntegerProperty();
	private Map<Integer, IntegerProperty> population;
	private File flag;

    public Canton(String name, String abbreviation, String capital, int area, Map<Integer,IntegerProperty> population) {
		this.name.setValue(name);
		this.abbreviation.setValue(abbreviation);
		this.capital.setValue(capital);
		this.area.setValue(area);
		this.population = population;
		addFlag();
	}

	public SimpleStringProperty getNameProperty() {
		return name;
	}

	public SimpleStringProperty getAbbreviationProperty() {
		return abbreviation;
	}

	public SimpleStringProperty getCapitalProperty() {
		return capital;
	}

	public IntegerProperty getAreaProperty() {
		return area;
	}

	public Map<Integer,IntegerProperty> getPopulation() {
		return population;
	}

	public void setPopulation(Map<Integer,IntegerProperty> population) {
		this.population = population;
	}

    public File getFlag() {
        return flag;
    }

    public void setFlag(File flag) {
        this.flag = flag;
    }

    private void addFlag(){
        String FILE_URL = "src" + File.separator + "resources" + File.separator + "flags" + File.separator +
				this.abbreviation.getValue().toLowerCase() + ".png";
        this.flag = new File(FILE_URL);
    }

}
