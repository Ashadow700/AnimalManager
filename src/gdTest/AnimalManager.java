package gdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalManager {
	CanvasWithAnimals canAnimals;
	SelectedAnimalHandler selector;
	ArrayList<Animal> animalsList;
	Animal selectedAnimal;
	int nextId;
	final String sequenceForListView = ",   id: ";

	public AnimalManager(){
		animalsList = new ArrayList<Animal>();
		selectedAnimal = null;
		nextId = 0;
	}

	public void linkOtherClasses(CanvasWithAnimals canAnimals, SelectedAnimalHandler selector){
		this.canAnimals = canAnimals;
		this.selector = selector;
	}
	
	public String getAnimalIdentifier(Animal animal){
		String res = animal.getAnimalType()+" "+animal.getName() +sequenceForListView+animal.getId();
		return res;
	}

	public List<String> getAnimalsList(){
		List<String> animalsStringList = new ArrayList<String>();
		
		for(Animal animal: animalsList){
			String res = getAnimalIdentifier(animal);
			animalsStringList.add(res);
		}

		return animalsStringList;
	}
	
	public void randomizeFriendships(){
		Random rand = new Random();
		
		//Breaking friendships
		for(Animal animal: animalsList){
			ArrayList<Animal> friends = animal.getFriends();
			
			if(friends.size()>0){
				int index = rand.nextInt(friends.size());
				Animal unfriendFromFacebookThatWillTeachHim = friends.get(index);
				breakFriendship(animal, unfriendFromFacebookThatWillTeachHim);
				System.out.println(animal.getName() + " and " + unfriendFromFacebookThatWillTeachHim.getName() + " stopped being friends");
			}else{
				System.out.println(animal.getName() +" can't lose any friends becuase he doesn't have any. How sad");
			}
		}
		System.out.println("\n");
		
		//Making friendships
		for(Animal animal: animalsList){
			ArrayList<Animal> friends = animal.getFriends();
			
			if(friends.size() < (animalsList.size()-1) ){
				
				//This opperation is likely very inefficient, but I don't know of any faster method of finding the 
				//animals that may become new friends
				ArrayList<Animal> availableAnimals = new ArrayList<Animal>();
				availableAnimals.addAll(animalsList);
				
				availableAnimals.remove(animal);
				availableAnimals.removeAll(friends);
				
				int index = rand.nextInt( availableAnimals.size() );
				Animal newFriend = availableAnimals.get(index);
				setFriendship(animal, newFriend);
				System.out.println(animal.getName() + " and " + newFriend.getName() + " became friends");
			}else{
				System.out.println(animal.getName() +" is already friends with everyone. What an attention seeker");
			}	
		}
		System.out.println("\n");
	}

	public void setFriendship(Animal animalA, Animal animalB){
		animalA.addFriend(animalB);
		animalB.addFriend(animalA);
	}

	public void breakFriendship(Animal animalA, Animal animalB){
		animalA.removeFriend(animalB);
		animalB.removeFriend(animalA);
	}
	
	public void recalculatePositions(){
		double canHeight = canAnimals.getHeight();
		double padding = canHeight/15;
		
		double radius = (canHeight/2) - padding;
		double origoX = canAnimals.getWidth()/2;
		double origoY = canHeight/2;
		
		double angle = 360/animalsList.size();
		double alpha = 0;
		
		for(Animal animal: animalsList){
			double x = origoX;
			x = x + Math.cos( Math.toRadians(alpha) )*radius;
			
			double y = origoY;
			y = y + Math.sin( Math.toRadians(alpha) )*radius;
			
			animal.setPos(x, y);
			
			alpha = alpha+angle;
			System.out.println("\n");
		}	
	}
	
	public Animal getAnimal(int index){
		return animalsList.get(index);
	}

	public void addAnimal(Animal animal){
		animal.setId(nextId);
		nextId++;
		animalsList.add(animal);
	}

	public void addExampleAnimals(){
		Animal dog1 = new AnimalDog("'Dog One'", "Meat", "Hunting dog");
		dog1.setPos(300, 40);
		addAnimal(dog1);
		
		Animal parrot1 = new AnimalParrot("'Parrot One'", "Grain", 0.25, "Cannot speak");
		parrot1.setPos(300, 260);
		addAnimal(parrot1);

		Animal chicken1 = new AnimalChicken("'Chicken One'", "Corn", 0.75, "Is broiler");
		chicken1.setPos(140, 230);
		addAnimal(chicken1);
		
		Animal dog2 = new AnimalDog("'Dog two'", "Fresh Meat", "Assistance dog");
		dog2.setPos(30, 30);
		addAnimal(dog2);
		
		Animal parrot2 = new AnimalParrot("'Parrot two'", "Corn", 0.5, "Can speak");
		parrot2.setPos(100, 100);
		addAnimal(parrot2);
		
		Animal dog3 = new AnimalDog("'Dog three'", "Pedigree", "Racing dog");
		dog3.setPos(50, 150);
		addAnimal(dog3);
		
	}

	public void addFunExampleAnimals(){
		//You win over 9000 internet points if you can tell which series these are a reference to.
		Animal dogLelouch = new AnimalDog("Lelouch", "The World", "Conquest dog");
		dogLelouch.setPos(300, 40);

		Animal dogKallen = new AnimalDog("Kallen", "Zero",  "Ace Mecha Pilot dog");
		dogKallen.setPos(150, 60);

		Animal parrotCC = new AnimalParrot("CC", "Pizza", 0.45, "Immortal");
		parrotCC.setPos(70, 100);

		Animal chickenNina = new AnimalChicken("Nina", "Cookies", 0, "Loser");
		chickenNina.setPos(250, 250);

		addAnimal(dogLelouch);
		addAnimal(dogKallen);
		addAnimal(parrotCC);
		addAnimal(chickenNina);
	}

}
