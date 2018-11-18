package gdTest;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LayerDots {
	private Canvas canDots;
	private GraphicsContext gcDots; 
	private double dotSize;
	
	//I don't pass the AnimalManager object to the layer objects, as I don't want them to communicate directly
	//with the Animal Manager, I prefer to keep it so that the layers only communicate with the CanvasWithAnimals class
	
	public LayerDots(Canvas canDots, double dotSize){
		this.canDots = canDots;
		gcDots = canDots.getGraphicsContext2D();
		gcDots.setFill(Color.WHITE);
		this.dotSize = dotSize;
	}
	
	public void drawAnimals(ArrayList<Animal> animalsList ){
		
		gcDots.clearRect(0, 0, canDots.getWidth(), canDots.getHeight() );
		
		for(int index = 0; index<animalsList.size(); index++){
			Animal temp = animalsList.get(index);
			double x = temp.getX();
			double y = temp.getY();
			gcDots.fillRect(x-dotSize/2, y-dotSize/2, dotSize, dotSize);
		}	
	}

}


