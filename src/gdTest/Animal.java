package gdTest;

import java.util.ArrayList;

abstract public class Animal {
	protected String animalType;
	protected String name;
	protected double posX;
	protected double posY;
	protected int id;
	protected String favoriteFood;
	protected ArrayList<Animal> friends = new ArrayList<Animal>();
	
	public String[] getInfoForLabels() {
		
		String friendsString = "Friends: ";
		for(int index = 0; index<friends.size(); index++){
			Animal temp = friends.get(index);
			int id = temp.getId();
			friendsString = friendsString+id+", ";
		}
		friendsString = friendsString.substring(0, friendsString.length()-2 );
		
		String[] info = {"Name: "+name, "Animaltype: "+animalType, "Id: "+Integer.toString(id), "Favorite food: "+favoriteFood, friendsString, " " , " "};
		return info;
	}
	
	public void addFriend(Animal newFriend){
		
		if(newFriend == this){
			return;
		}
		
		int newFriendId = newFriend.getId();
		
		//If the friend already exist, it's not added
		for(int index = 0; index<friends.size(); index++){
			int oldFriendId = friends.get(index).getId();
			if(newFriendId == oldFriendId){
				return;
			}
		}
		
		friends.add(newFriend);
	}
	
	public void removeFriend(Animal unfriend){
		if(unfriend == this){
			return;
		}
		
		int unfriendId = unfriend.getId();
		
		for(int index = 0; index<friends.size(); index++){
			int oldFriendId = friends.get(index).getId();
			
			if(unfriendId == oldFriendId){
				friends.remove(index);
			}	
		}	
	}
	
	public String toString(){
		String res = "Animaltype: "+animalType+"\n"
				+ "Name: "+name+"\n"
				+ "ID: "+id+"\n"
				+ "Favorite Food: "+favoriteFood+"\n";
		
		res = res+"Friends: ";
		for(int index = 0; index<friends.size(); index++){
			
			Animal temp = friends.get(index);
			int id = temp.getId();
			res = res+id+", ";
		}
		res = res.substring(0, res.length()-2 );
		res = res +"\n";
		return res;
		
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public String getAnimalType(){
		return animalType;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setFavoriteFood(String favoriteFood){
		this.favoriteFood = favoriteFood;
	}
	
	public String getFavoriteFood(){
		return favoriteFood;
	}
	
	public ArrayList<Animal> getFriends(){
		return friends;
	}
	
	public void setPos(double x, double y){
		posX = x;
		posY = y;
	}
	
	public double getX(){
		return posX;
	}
	
	public double getY(){
		return posY;
	}
		

}
