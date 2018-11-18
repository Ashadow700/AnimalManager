package gdTest;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasWithAnimals {
	private StackPane stack;

	private Canvas can;

	private Rectangle background;

	private LayerInfoText layerInfoText;
	private LayerDots layerDots;
	private LayerLines layerLines;
	private LayerOutline layerOutline;

	private AnimalManager manager;
	private SelectedAnimalHandler selector;

	double dotSize;
	
	public CanvasWithAnimals(StackPane stack, Canvas can){
		this.can = can;

		dotSize = can.getHeight()/50;

		background = new Rectangle(can.widthProperty().get(), can.heightProperty().get());
		background.setFill(Color.BLACK);

		Canvas canText = new Canvas();
		copyCanvasProperties(canText);
		layerInfoText = new LayerInfoText(canText);
		
		Canvas canDots = new Canvas();
		copyCanvasProperties(canDots);
		layerDots = new LayerDots(canDots, dotSize );

		Canvas canLines = new Canvas();
		copyCanvasProperties(canLines);
		layerLines = new LayerLines(canLines);
		
		Canvas canOutline = new Canvas();
		copyCanvasProperties(canOutline);
		layerOutline = new LayerOutline(canOutline, dotSize);
		
		stack.setPadding(new Insets(10, 7, 10, 7));
		stack.getChildren().addAll(background, canOutline, canLines, canDots, canText, can);


	}
	
	public void linkOtherClasses(AnimalManager manager, SelectedAnimalHandler selector){
		this.manager = manager;
		this.selector = selector;
	}
	
	
	public void drawAllFriendships(){
		ArrayList<Animal> animalsList = manager.animalsList;
		for(Animal animal: animalsList){
			layerLines.drawLinesToFriends(animal);
		}	
	}
	
	
	public void selectAnimal(double mousePosX, double mousePosY){
		Animal animal = findAnimal(mousePosX, mousePosY);
		layerLines.clear();
		if(animal!=null){
			selector.selectAnimal(animal);
		}else{
			selector.deselectAnimal();
		}
	}
	
	public void displayAnimal(double mousePosX, double mousePosY){
		
		if( !selector.hasSelectedAnimal() ){
			Animal animal = findAnimal(mousePosX, mousePosY);
			layerLines.clear();
			if(animal!=null){
				selector.displayAnimal(animal);
			}else{
				selector.deselectAnimal();
			}
		}
	}
	
	public Animal findAnimal(double mousePosX, double mousePosY){
		
		ArrayList<Animal> animalsList = manager.animalsList;

		for(int index = 0; index<animalsList.size(); index++){

			Animal animal = animalsList.get(index);
			double posX = animal.getX();
			double posY = animal.getY();

			if( (posX-dotSize/2)<mousePosX  &&  mousePosX<(posX+dotSize/2)  &&  (posY-dotSize/2)<mousePosY  &&  mousePosY<(posY+dotSize/2)){
				return animal;
			}
		}
		
		return null;
	}

	public double getWidth(){
		return can.getWidth();
	}

	public double getHeight(){
		return can.getHeight();
	}

	private void copyCanvasProperties(Canvas canvasCopy){
		canvasCopy.widthProperty().setValue(can.getWidth());
		canvasCopy.heightProperty().setValue(can.getHeight());
	}

	public void drawLinesToFriends(Animal animal){
		layerLines.drawLinesToFriends(animal);
	}

	public void clearLines(){
		layerLines.clear();
	}
	
	public void drawOutline(Animal animal){
		layerOutline.drawOutline(animal);
	}
	
	public void clearOutline(){
		layerOutline.clear();
	}

	public void drawAnimals(){
		layerDots.drawAnimals( manager.animalsList );
	}

}

