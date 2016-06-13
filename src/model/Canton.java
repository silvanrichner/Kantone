package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Canton{
	
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty abbreviation = new SimpleStringProperty();
	private SimpleStringProperty capital = new SimpleStringProperty();
	private SimpleStringProperty area = new SimpleStringProperty();
	private Map<Integer, StringProperty> population;
	private File flag;

    public Canton(String name, String abbreviation, String capital, String area, Map<Integer, StringProperty> population) {
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

	public SimpleStringProperty getAreaProperty() {
		return area;
	}

	public Map<Integer, StringProperty> getPopulation() {
		return population;
	}

	public void setPopulation(Map<Integer, StringProperty> population) {
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

	public int levenshteinDistance(String a, String[] b){
		return Math.min(levenshteinDistance(a, b[0]), levenshteinDistance(a, b[1]));
	}

	public int levenshteinDistance(String a, String b) {

		if(b.contains(" ")){
			return levenshteinDistance(a, b.split(" "));
		}else if(b.contains("-")){
			return levenshteinDistance(a, b.split("-"));
		}else{

			// ignore case
			a = a.toLowerCase();
			b = b.toLowerCase();

			// initalize matrix with length of number of caracters in a + 1 and
			// width of number of characters in b + 1
			int costs[][] = new int[a.length() + 1][b.length() + 1];

			// set the values in the first column as 0...length of a
			for (int i = 0; i < a.length() + 1; i++) {
				costs[i][0] = i;
			}

			// set the values in the first row as 0...length of b
			for (int i = 0; i < b.length() + 1; i++) {
				costs[0][i] = i;
			}

			for (int i = 1; i < a.length() + 1; i++) {
				for (int j = 1; j < b.length() + 1; j++) {
					// If a[i] equals b[j], the cost is 0, If a[i] doesn't equal
					// b[j], the cost is 1.
					int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

					// Set cell costs[i,j] of the matrix equal to the minimum of:
					// a. The cell immediately above plus 1: costs[i-1,j] + 1.
					// b. The cell immediately to the left plus 1: costs[i,j-1] + 1.
					// c. The cell diagonally above and to the left plus the cost:
					// costs[i-1,j-1] + cost.
					costs[i][j] = Math.min(Math.min(costs[i - 1][j] + 1, costs[i][j - 1] + 1), costs[i - 1][j - 1] + cost);

					if (i > 2 && j > 2 && a.charAt(i - 1) == b.charAt(j
							- 2) && a.charAt(i - 2) == b.charAt(j - 1)) {
						costs[i][j] = Math.min(costs[i][j], costs[i - 2][j - 2] + cost);
					}
				}
			}
			return costs[a.length()][b.length()];
		}
	}


	public List<Integer> getYearList(){
		return population.keySet().stream().collect(Collectors.toList());
	}

}
