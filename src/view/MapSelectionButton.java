package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class MapSelectionButton extends Button {
	
	List<Image> images;

	public MapSelectionButton() throws IOException {
		super();
		
		//load images
		Files.walk(new File(".." + File.separator + "resources" + File.separator + "positions").toPath()).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		        System.out.println(filePath);
		    }
		});
	}

	

}
