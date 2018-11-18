package gdTest;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LayerLines {
	private Canvas canLines;
	private GraphicsContext gcLines; 

	public LayerLines(Canvas canLines){
		this.canLines = canLines;
		gcLines = canLines.getGraphicsContext2D();
		gcLines.setStroke(Color.LIGHTBLUE);
	}
	
	public void drawLinesToFriends(Animal animal){
		ArrayList<Animal> friends = animal.getFriends();
		
		double posX = animal.getX();
		double posY = animal.getY();
		
		gcLines.beginPath();
		for(int index = 0; index<friends.size(); index++){
			Animal friend = friends.get(index);
			double friendPosX = friend.getX();
			double friendPosY = friend.getY();
			gcLines.moveTo(posX, posY);
			gcLines.lineTo(friendPosX, friendPosY);
		}
		gcLines.stroke();
		
	}
	
	public void clear(){
		gcLines.clearRect(0, 0, canLines.getWidth(), canLines.getHeight());
	}


}


