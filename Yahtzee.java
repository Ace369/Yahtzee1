/**
 *	Introduce the game here
 *
 *	@author	Aditi Chamarthy
 *	@since October 2, 2023
 */
 
public class Yahtzee {
	
	private YahtzeePlayer player1; //
	private YahtzeePlayer player2; //
	private DiceGroup dice;
	
	public Yahtzee(){
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		dice = new DiceGroup();
	}
	
	public static void main(String[] args){
		Yahtzee game = new Yahtzee();
		game.run();
	}
	
	public void run(){
		printHeader();
		setPlayerNames();
		int firstPlayer = determineFirst();
		play(firstPlayer);
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	public void setPlayerNames(){
		String name1 = Prompt.getString("Player 1, please enter your first name ");
		String name2 = Prompt.getString("Player 2, please enter your first name ");
		player1.setName(name1);
		player2.setName(name2);
	}
	
	public int determineFirst(){
		int first = 0;
		int score1 = 0;
		int score2 = 0;
		
		while(score1 == score2){
			String empty = Prompt.getString("Let's see who will go first. " + player1.getName()
			 + ", please hit enter to roll the dice");
			dice.rollDice();
			score1 = dice.getTotal();
			dice.printDice();
			System.out.println(score1);
			
			empty = Prompt.getString("Let's see who will go first. " + player2.getName()
			 + ", please hit enter to roll the dice");
			dice.rollDice();
			score2 = dice.getTotal();
			dice.printDice();
			System.out.println(score2);
		}
		
		String starterName = "";
		if(score1 > score2){
			first = 1;
			starterName = player1.getName();
		}
		else{
			first = 2;
			starterName = player2.getName();
		}
		
		System.out.println(player1.getName() + ", you rolled a sum of " +
			score1 + ", and " + player2.getName() + ", you rolled a sum of "
			+ score2);
		System.out.println(starterName + ", since your score was higher, you'll roll first.");
		
		return first;
	}
	
	public void play(int starter){
		for(int i = 1; i <= 13; i++){
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore();
			player2.getScoreCard().printPlayerScore();
		}
	}
}
