
public class DamageCard extends Card {
private int damage;
	
	public DamageCard(String name, int cost, int damage) {
		super(name, cost);
		this.damage = damage;
	}
	
	@Override
	public void action(CardMaster user, CardMaster enemy, double efficiency) {
		enemy.hit((int) (damage*efficiency));
	}
}
