package gdTest;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * This Layer, layerInfoText, was intended to display the information about the animals on the Canvas
 * I later decided to change this to display it on the sidebar inside labels instead. I have not removed
 * The layer though, since there is the possibility that other developers may wish to expand the program and
 * add text to the canvas.
 */

public class LayerInfoText {
	Canvas canText;
	GraphicsContext gcText; 
	
	public LayerInfoText(Canvas canText){
		this.canText = canText;
		gcText = canText.getGraphicsContext2D();
		gcText.setStroke(Color.WHITE);
		
	}
	
	public void displayInfo(String info){
		clear();
		gcText.strokeText(info, 20, 20);
	}
	
	public void displaydefault(){
		clear();
		String defaultText = "Animaltype: -\n"
				+ "Name: -\n"
				+ "ID: -\n"
				+ "Favorite Food: -\n";
		gcText.strokeText(defaultText, 20, 20);
	}
	
	public void clear(){
		gcText.clearRect(0, 0, canText.getWidth(), canText.getHeight());
	}

}
