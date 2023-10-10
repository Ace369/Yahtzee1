/**
 *	Introduce the game here
 *
 *	@author	Aditi Chamarthy
 *	@since October 2, 2023
 */
 
public class Yahtzee {
	
	private YahtzeePlayer player1; //holds player 1 data
	private YahtzeePlayer player2; //holds player 2 data
	private DiceGroup dice;//group of 5 dice
	
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
	
	/**Prompts players and stores thier names in FVs*/
	public void setPlayerNames(){
		String name1 = Prompt.getString("Player 1, please enter your first name ");
		String name2 = Prompt.getString("Player 2, please enter your first name ");
		player1.setName(name1);
		player2.setName(name2);
	}
	
	/**Rolls dice for each player, determining who goes first based on who rolled the highest
	 * @return first - int that determines if first player is 1 or 2*/
	public int determineFirst(){
		int first = 0;
		int score1 = 0;
		int score2 = 0;
		
		while(score1 == score2){
			if(score1 != 0){
				System.out.println("Whoops, we have a tie (both rolled " + score1
					+ "). Looks like we'll have to try that again . . .");
			}
			String empty = Prompt.getString("Let's see who will go first. " + player1.getName()
			 + ", please hit enter to roll the dice");
			dice.rollDice();
			score1 = dice.getTotal();
			dice.printDice();
			
			empty = Prompt.getString("Let's see who will go first. " + player2.getName()
			 + ", please hit enter to roll the dice");
			dice.rollDice();
			score2 = dice.getTotal();
			dice.printDice();
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
		printScoreCard();
		return first;
	}
	
	/**Calls on the playerPlay methods after determining who goes first, looped 13 times*/
	public void play(int starter){
		for(int i = 1; i <= 13; i++){
			
			System.out.println("Round " + i + " of 13 rounds.");
			if(starter == 1){
				playerPlay(1);
				playerPlay(2);
			}
			else{
				playerPlay(2);
				playerPlay(1);
			}
			

		}
	}
	
	/**Prints the scorecards*/
	public void printScoreCard(){
		player1.getScoreCard().printCardHeader();
		player1.getScoreCard().printPlayerScore(player1);
		player2.getScoreCard().printPlayerScore(player2);
	}
	
	/**Player rolls the dice, and then depending on the input, can re-roll
	 * some dice up to 2 times. After the dice are finalized, the scorecard is printed
	 * and the score is added based on the player's choice
	 * @param player - int determining which player is rolling */
	public void playerPlay(int player){
			String blank = "";
			int choice = 0;
			if(player == 1){
				blank = Prompt.getString(player1.getName() + ", it's your turn to play. Please hit enter to roll the dice ");
			}
			else{
				blank = Prompt.getString(player2.getName() + ", it's your turn to play. Please hit enter to roll the dice ");
			}
			
			dice.rollDice();
			dice.printDice();
			
			for(int k = 0; k < 2; k++){
				String hold = Prompt.getString("Which di(c)e would you like to keep? Enter " + 
					"the values you'd like to 'hold' without spaces. For examples, " + 
					"if you'd like to 'hold' die 1, 2, and 5, enter 125 " + 
					"(enter -1 if you'd like to end the turn) : -1");
				if(hold.contains("-1")){
					k = 2;
				}
				else{
					dice.rollDice(hold);
					dice.printDice();
				}
			}
			printScoreCard();
			if(player == 1){
				choice = Prompt.getInt(player1.getName() + ", now you need to make a choice. Pick a valid integer from the list above ");
				while (!(player1.getScoreCard().changeScore(choice, dice))){
					System.out.println("This choice was already taken.");
					choice = Prompt.getInt(player1.getName() + ", now you need to make a choice. Pick a valid integer from the list above ");
				}
			}
			else{
				choice = Prompt.getInt(player2.getName() + ", now you need to make a choice. Pick a valid integer from the list above ");
				while (!(player2.getScoreCard().changeScore(choice, dice))){
					System.out.println("This choice was already taken.");
					choice = Prompt.getInt(player2.getName() + ", now you need to make a choice. Pick a valid integer from the list above ");
				}
			}
			
			printScoreCard();
	}
}
