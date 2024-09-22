
public class ImmunityCard extends Card {

	private int duration;
	
	public ImmunityCard(String name, int cost, int duration) {
		super(name, cost);
		this.duration = duration;
	}
	
	@Override
	public void action(CardMaster user, CardMaster enemy, double efficiency) {
		user.setImmune((int) (duration*efficiency));
	}
	 

}
