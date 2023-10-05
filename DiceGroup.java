/**
 *  Create a roll of 5 dice and display them.
 *
 *  @author Mr Greenstein and Aditi Chamarthy
 *  @since October 2, 2023
*/

public class DiceGroup {
	private Dice [] die;
	
	// Create the seven different line images of a die
	String [] line = {	" _______ ",
						"|       |",
						"| O   O |",
						"|   O   |",
						"|     O |",
						"| O     |",
						"|_______|" };
	
	/**
	 *  Creates a group of 5 dice
	 */
	public DiceGroup() {
		die = new Dice[5];
		for (int i = 0; i < 5; i++){
			die[i] = new Dice();
		}
	}
		
	/**
	 *  Roll all dice.
	 */
	public void rollDice() {
		for(int i = 0; i < 5; i++){
			die[i].roll();
		}
	}
	
	/**
	 *  Roll only the dice not in the string "rawHold".
	 *  e.g. If rawHold="24", only roll dice 1, 3, and 5
	 *  @param rawHold	the numbered dice to hold
	 */
	public void rollDice(String rawHold) {
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < rawHold.length(); j++){
				char currentChar = rawHold.charAt(j);
				if(!(currentChar == i - 48)){
					die[i].roll();
				}
			}
		}
	}
	
	/**
	 *  Dice getter method
	 *  @param i	the index into the die array
	 */
	public Dice getDie(int i) { return die[i]; }
	
	/**
	 *  Computes the sum of the dice group.
	 *
	 *  @return  The integer sum of the dice group.
	 */
	public int getTotal() {
		int sum = 0;
		for (int i = 0; i < die.length; i++) sum += die[i].getLastRollValue();
		return sum;
	}
	
	/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < 6; i++) {
			System.out.print(" ");
			for (int j = 0; j < die.length; j++) {
				printDiceLine(die[j].getLastRollValue() + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	public void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i < 6; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public void printDiceLine(int value) {
		//String [] line = new String[8];
		System.out.print(line[getDiceLine(value)]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 *  @return	the index into the line array image of the dice
	 */
	public int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
}
