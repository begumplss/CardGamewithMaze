import java.util.Scanner;
public class Main {
	public static Card pullRandomCard(Card[] deck) {
		int index = (int) (Math.random()*deck.length);
		return deck[index];
	}
	
	public static void initHand(CardMaster duelist, Card[] deck) {
		for(int i = 0; i < GameParameters.START_CARDS; i++) {
			duelist.addCard(pullRandomCard(deck));
		}
	}
	
	public static CardMaster getWorthyOpponent() {
		CardMaster enemy = new CardMaster("Enemy", GameParameters.ENEMY1_HP, GameParameters.BASE_MP);
		return enemy;
	}
	
	
	public static void playEnemyHand(CardMaster player, CardMaster enemy) {
		Card[] playerHand = player.getHand();
		for(int i = 0; i < player.getCardCount(); i++) {
			if(playerHand[i].getCost() < player.getMp()) {
				double efficiency = rollDice(player);
				player.useCard(enemy, i, efficiency);
				return;
			}
		}
		System.out.println(player.getName() + " skipped their turn.");
	}
	
	public static double rollDice(CardMaster player) {
		int roll = (int) (Math.random() * 6) + 1;
		System.out.println(player.getName() + " rolled " + roll + ".");
		double efficiency = 0;
		switch(roll) {
		case 1:
			efficiency = 0;
			break;
		case 2:
			efficiency = 0.1;
			break;
		case 3:
			efficiency = 0.3;
			break;
		case 4:
			efficiency = 0.5;
			break;
		case 5:
			efficiency = 0.8;
			break;
		case 6:
			efficiency = 1;
			break;
		}
		return efficiency;
	}
	
	public static boolean duel(CardMaster player, Card[] playerDeck, CardMaster enemy, Card[] enemyDeck) {
		boolean enemyAlive = true, playerAlive = true;
		System.out.println("Show time!\n");
		Scanner kb = new Scanner(System.in);
		while(playerAlive && enemyAlive) {
			System.out.println(player + "\n" + enemy);
			boolean validChoice = false;
			while(!validChoice) {
				player.viewHand();
				System.out.print("Select: ");
				int playerChoice = kb.nextInt();
				if(playerChoice == 0) {
					System.out.println(player.getName() + " skipped their turn.");
					break;
				}
				kb.nextLine();
				System.out.println("Press ENTER to roll dice.");
				kb.nextLine();
				double efficiency = rollDice(player);
				validChoice = player.useCard(enemy, playerChoice-1, efficiency);
			}
			enemyAlive = enemy.getHp() > 0;
			if(enemyAlive) {
				playEnemyHand(enemy, player);
				player.tick();
				enemy.tick();
				player.addCard(pullRandomCard(playerDeck));
				enemy.addCard(pullRandomCard(enemyDeck));
			}
			playerAlive = player.getHp() > 0;
		}
		if(!playerAlive) {
			System.out.println(player.getName() + " died.");
		}
		if(!enemyAlive) {
			System.out.println(enemy.getName() + " died.");
		}
		return playerAlive;
	}
	
	public static int[] getPlayer(int[][] maze) {
		int[] playerLocation = {-1, -1};
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				if(maze[i][j] == 0) {
					playerLocation[0] = i;
					playerLocation[1] = j;
				}
			}
		}
		return playerLocation;
	} 
	
	public static char handleInput(int[][] maze) {
		boolean validInput = false;
		Scanner kb = new Scanner(System.in);
		char sel = '0';
		while(!validInput) {
			renderMaze(maze);
			System.out.println("WASD: ");
			sel = kb.nextLine().toLowerCase().charAt(0);
			if(sel == 'w' || sel == 'a' || sel == 's' || sel == 'd') {
				validInput = true;
			}
		}
		return sel;
	}
	
	public static CardMaster updateMaze(int[][] maze, char input) {
		int[] player = getPlayer(maze);
		int tile = 2;
		switch(input) {
		case 'w':
			if(player[0] == 0 
					|| maze[player[0]-1].length <= player[1]) {
				System.out.println("Out of bounds.");
				return null;
			}
			tile = maze[player[0]-1][player[1]];
			if(tile == 3) {
				System.out.println("Wall");
				return null;
			}
			maze[player[0]-1][player[1]] = 0;
			maze[player[0]][player[1]] = 2;
			break;
		case 'a':
			if(player[1] == 0) {
				System.out.println("Out of bounds.");
				return null;
			}
			tile = maze[player[0]][player[1]-1];
			if(tile == 3) {
				System.out.println("Wall");
				return null;
			}
			maze[player[0]][player[1]-1] = 0;
			maze[player[0]][player[1]] = 2;
			break;
		case 's':
			if(player[0] == maze.length-1
					|| maze[player[0]+1].length <= player[1]) {
				System.out.println("Out of bounds.");
				return null;
			}
			tile = maze[player[0]+1][player[1]];
			if(tile == 3) {
				System.out.println("Wall");
				return null;
			}
			maze[player[0]+1][player[1]] = 0;
			maze[player[0]][player[1]] = 2;
			break;
		case 'd':
			if(player[1] == maze[player[0]].length-1) {
				System.out.println("Out of bounds.");
				return null;
			}
			tile = maze[player[0]][player[1]+1];
			if(tile == 3) {
				System.out.println("Wall");
				return null;
			}
			maze[player[0]][player[1]+1] = 0;
			maze[player[0]][player[1]] = 2;
			break;
		}
		if(tile == 1) {
			System.out.println("Enemy Found!");
			return getWorthyOpponent();
		}
		if(tile == 4) {
			
		}
		return null;
	}
	
	public static void renderMaze(int[][] maze) {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				switch(maze[i][j]) {
				case 0:
					System.out.print("p ");
					break;
				case 1:
					System.out.print("x ");
					break;
				case 2:
					System.out.print("o ");
					break;
				case 3:
					System.out.print("w ");
					break;
				case 4:
					System.out.print("S ");
					break;
				default:
					System.out.print("- ");
					break;
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static void credits() {
		System.out.println("Congratz you won!");
	
ddawswas		System.exit(0);
	}
	
	public static void main(String[] args){
		int[][] maze = {
			{3, 3, 3},
			{3, 2, 2, 2, 2, 2, 2},
			{3, 2, 3, 3, 3, 3, 2},
			{3, 2, 3, 2, 2, 3, 4},
			{2, 2, 2, 2, 2, 3, 1},
			{3, 2, 3, 1, 2, 2, 2},
			{3, 2, 3},
			{3, 2, 3},
			{3, 2, 3},
			{3, 0, 3}
		};
		CardMaster player = new CardMaster("Player", GameParameters.BASE_HP, GameParameters.BASE_MP);
		Card[] playerDeck = new Card[3];
		playerDeck[0] = new HealCard("Heal Card", GameParameters.HEAL_COST, GameParameters.BASE_HEAL);
		playerDeck[1] = new DamageCard("Damage Card", GameParameters.DAMAGE_COST, GameParameters.BASE_DAMAGE);
		playerDeck[2] = new ImmunityCard("Immune Card", GameParameters.IMMUNE_COST, GameParameters.BASE_IMMUNE);
		Card[] enemyDeck = new Card[2];
		enemyDeck[0] = new HealCard("Heal Card", GameParameters.HEAL_COST, GameParameters.BASE_HEAL);
		enemyDeck[1] = new DamageCard("Damage Card", GameParameters.DAMAGE_COST, GameParameters.BASE_DAMAGE);
		boolean playerAlive = true;
		while(playerAlive) {
			char input = handleInput(maze);
			CardMaster enemy = updateMaze(maze, input);
			if(enemy != null) {
				initHand(enemy, enemyDeck);
				player.resetHand();
				initHand(player, playerDeck);
				playerAlive = duel(player, playerDeck, enemy, enemyDeck);
			}
		}
		System.out.println("Game Over.");
	}
	
}


