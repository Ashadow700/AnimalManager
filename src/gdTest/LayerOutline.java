package gdTest;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LayerOutline {
	private Canvas canOutline;
	private GraphicsContext gcOutline; 
	private double dotSize;
	private double outlineSize;
	
	public LayerOutline(Canvas canOutline, double dotSize){
		this.canOutline = canOutline;
		gcOutline = canOutline.getGraphicsContext2D();
		gcOutline.setFill(Color.RED);
		this.dotSize = dotSize;
		outlineSize = dotSize/4;
	}
	
	public void drawOutline(Animal animal){
		double posX = animal.getX();
		double posY = animal.getY();
		gcOutline.fillRect(posX-(dotSize/2)-outlineSize, posY-(dotSize/2)-outlineSize, dotSize+outlineSize*2, dotSize+outlineSize*2);
	}
	
	public void clear(){
		gcOutline.clearRect(0, 0, canOutline.getWidth(), canOutline.getHeight());
	}

}
