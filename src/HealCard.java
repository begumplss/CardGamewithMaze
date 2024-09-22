
public class HealCard extends Card {

	private int healAmount;
	
	public HealCard(String name, int cost, int healAmount) {
		super(name, cost);
		this.healAmount = healAmount;
	}
	
	@Override
	public void action(CardMaster user, CardMaster enemy, double efficiency) {
		user.heal((int) (healAmount*efficiency));
	}

}
