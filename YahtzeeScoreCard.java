/**
 *	Describe the scorecard here.
 *
 *	@author	Aditi Chamarthy
 *	@since October 2, 2023
 */
public class YahtzeeScoreCard {
	private int [] scores; // holds the scores
	private boolean [] taken; // if score hasn't been taken yet
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
	public YahtzeeScoreCard(){
		scores = new int [14];
		taken = new boolean[14];
	} 
	
	public int getScore(int i) {
		return scores[i];
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *	
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if(taken[choice]){
			return false;
		}
		else if(choice > 0 && choice < 7){
			numberScore(choice, dg);
		}
		else if(choice == 7){
			threeOfAKind(dg);
		}
		else if(choice == 8){
			fourOfAKind(dg);
		}
		else if(choice == 9){
			fullHouse(dg);
		}
		else if(choice == 10){
			smallStraight(dg);
		}
		else if(choice == 11){
			largeStraight(dg);
		}
		else if(choice == 12){
			chance(dg);
		}
		else if(choice == 13){
			yahtzeeScore(dg);
		}
		taken[choice] = true;
		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int value = 0;
		int sum = 0;
		for(int i = 0; i < 5; i++){
			value = dg.getDie(i).getLastRollValue();
			if(value == choice){
				sum += value;
			}
		}
		scores[choice] = sum;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
			scores[7] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fourOfAKind(DiceGroup dg) {
			scores[8] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Full House choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fullHouse(DiceGroup dg) {
		scores[9] = 25;
	}
	
	/**
	 *	Updates the scorecard for Small Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void smallStraight(DiceGroup dg) {
			scores[10] = 30;
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void largeStraight(DiceGroup dg) {
			scores[11] = 40;
	}
	
	/**
	 *	Updates the scorecard for Chance choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void chance(DiceGroup dg) {
			scores[12] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void yahtzeeScore(DiceGroup dg) {
			scores[13] = 50;
	}

}
