package gdTest;

public class AnimalParrot extends Animal{
	double wingspan;
	String canSpeak;
	
	public AnimalParrot(String name, String favoriteFood, double wingspan, String canSpeak){
		animalType = "Parrot";
		super.name = name;
		super.favoriteFood = favoriteFood;
		this.wingspan = wingspan;
		this.canSpeak = canSpeak;
	}
	
	@Override
	public String[] getInfoForLabels() {
		String[] info = super.getInfoForLabels();
		info[5] = "Wingspan: "+Double.toString(wingspan);
		info[6] = canSpeak;
		return info;
	}
	
	@Override
	public String toString() {
		String res = super.toString()
				+ "Wingspan: "+ wingspan+"\n"
				+ canSpeak;
		return res;
	}
}