package gdTest;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SelectedAnimalHandler {
	
	private Animal selectedAnimal;
	private CanvasWithAnimals canAnimals;
	private AnimalManager manager;
	private ListView listViewAnimals;
	private Label[] labels;
	
	
	public SelectedAnimalHandler(ListView listViewAnimals, Label[] labels){
		selectedAnimal = null;
		this.listViewAnimals = listViewAnimals;
		this.labels = labels;
	}
	
	public void linkOtherClasses(CanvasWithAnimals canAnimals, AnimalManager manager){
		this.canAnimals = canAnimals;
		this.manager = manager;
	}
	
	public Animal getSelectedAnimal(){
		return selectedAnimal;
	}
	
	public boolean hasSelectedAnimal(){
		return !(selectedAnimal == null);
	}
	
	public void selectAnimal(Animal animal){
		selectedAnimal = animal;
		canAnimals.clearOutline();
		canAnimals.drawOutline(animal);
		displayAnimal(animal);
	}
	
	public void selectAnimal(String identifyer){
		int index = -1;
		String listAnimalString;
		do{
			index++;
			listAnimalString = (String)listViewAnimals.getItems().get(index);
		}while( !listAnimalString.equals(identifyer) );
		
		Animal animal = manager.getAnimal(index);
		selectedAnimal = animal;
		
		canAnimals.clearOutline();
		canAnimals.drawOutline(animal);
		display(animal, identifyer);
	}
	
	public void displayAnimal(Animal animal){
		String identifier = manager.getAnimalIdentifier(animal);
		display(animal, identifier);
	}
	
	private void display(Animal animal, String identifyer){
		canAnimals.drawLinesToFriends(animal);
		
		int index = -1;
		String listAnimalString;
		do{
			index++;
			listAnimalString = (String)listViewAnimals.getItems().get(index);
		}while( !listAnimalString.equals(identifyer) );
		
		listViewAnimals.getSelectionModel().clearAndSelect(index);
		
		String[] labelsToBePlaced = animal.getInfoForLabels();
		
		for(int k = 0; k<labels.length; k++){
			labels[k].setText(labelsToBePlaced[k] );
		}
		
	}
	
	public void deselectAnimal(){
		selectedAnimal = null;
		canAnimals.clearOutline();
		canAnimals.clearLines();
		listViewAnimals.getSelectionModel().clearSelection();
		labels[0].setText("Name: ");
		labels[1].setText("Animal: ");
		labels[2].setText("Id: ");
		labels[3].setText("Favorite food: ");
		labels[4].setText("Friends: ");
		labels[5].setText(" ");
		labels[6].setText(" ");
	}

}
