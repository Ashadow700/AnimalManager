package gdTest;

public class AnimalDog extends Animal{
	private String dogType;
	
	public AnimalDog(String name, String favoriteFood, String dogType){
		animalType = "Dog";
		super.name = name;
		super.favoriteFood = favoriteFood;
		this.dogType = dogType;
	}
	
	@Override
	public String[] getInfoForLabels() {
		String[] info = super.getInfoForLabels();
		info[5] = "Dogtype: "+dogType;
		return info;
	}
	
	@Override
	public String toString() {
		String res = super.toString();
		res = res + "Dogtype: "+dogType+"\n";
		return res;

	}
	
}
