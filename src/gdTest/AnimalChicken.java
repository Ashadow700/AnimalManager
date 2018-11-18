package gdTest;

//Animal Parrot and Animal Chicken are basically identical. It seems silly to have to separate classes for both, but I still chose to
//since 'Parrot' and 'Chicken' are both set as main categories.

public class AnimalChicken extends Animal{
	double wingspan;
	String isBroiler;
	
	public AnimalChicken(String name, String favoriteFood, double wingspan, String isBroiler){
		animalType = "Parrot";
		super.name = name;
		super.favoriteFood = favoriteFood;
		this.wingspan = wingspan;
		this.isBroiler = isBroiler;
	}
	
	@Override
	public String[] getInfoForLabels() {
		String[] info = super.getInfoForLabels();
		info[5] = "Wingspan: "+Double.toString(wingspan);
		info[6] = isBroiler;
		return info;
	}
	
	
	@Override
	public String toString() {
		String res = super.toString()
				+ "Wingspan: "+ wingspan+"\n"
				+ "isBroiler";

		return res;
	}

}