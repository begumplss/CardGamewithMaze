
public class CardMaster {
	private String name;
	private int immune;
	private int hp;
	private int mp;
	private Card[] hand;
	private int cardCount;
	
	public CardMaster(String name, int hp, int mp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		immune = 0;
		hand = new Card[GameParameters.MAX_CARDS];
		cardCount = 0;
	}
	
	public void addCard(Card newCard) {
		if(cardCount == GameParameters.MAX_CARDS) {
			System.out.println("Could not add card " + newCard.getName() + ". Hand is full.");
		}
		hand[cardCount++] = newCard;
	}
	
	public boolean useCard(CardMaster enemy, int cardIndex, double efficiency) {
		if(cardIndex >= cardCount) {
			System.out.println("Invalid choice.");
			return false;
		}
		Card turnCard = hand[cardIndex];
		if(mp > turnCard.getCost()) {
			System.out.println(name + " used " + turnCard.getName() + ".");
			turnCard.action(this, enemy, efficiency);
			for(int i = cardIndex; i < cardCount; i++) {
				hand[i] = hand[i+1];
			}
			hand[cardCount--] = null;
			mp -= turnCard.getCost();
			return true;
		} else {
			System.out.println("Out of mana.");
		}
		return false;
	}
	
	public void viewHand() {
		for(int i = 0; i < cardCount; i++) {
			System.out.println((i+1) + " -> " + hand[i]);
		}
		System.out.println("0 -> Skip Turn.");
	}
	
	public void heal(int amount) {
		System.out.println(name + " healed for " + amount + ".");
		hp += amount;
	}
	
	public void hit(int damage) {
		if(!isImmune()) {
			System.out.println(name + " took " + damage + " damage.");
			hp -= damage;
		}else {
			System.out.println(name + " is immune. No damage taken.");
		}
	}
	
	public void tick() {
		if(isImmune()) {
			immune--;
			if(immune > 0) {
				System.out.println(name + " is immune for " + immune + " turn(s).");
			}else {
				System.out.println(name + " is no longer immune.");
			}
		}
		hp += GameParameters.HP_PER_TURN;
		mp += GameParameters.MP_PER_TURN;
	}
	
	public void resetHand() {
		hand = new Card[GameParameters.MAX_CARDS];
		cardCount = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isImmune() {
		return immune > 0;
	}

	public void setImmune(int immune) {
		System.out.println(name + " is immune for " + immune + " turn(s).");
		this.immune = immune;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	
	public int getCardCount() {
		return cardCount;
	}
	
	public String toString() {
		return "Name: " + name + " HP: " + hp + " MP: " + mp;
	}
}
