
public class Card {
	private String name;
	private int cost;
	
	public Card(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public void action(CardMaster user, CardMaster enemy, double efficiency) {
		System.out.println("Undefined action");
	}
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String toString() {
		return ("Name: " + name + " Cost: " + cost);
	}
	
}

