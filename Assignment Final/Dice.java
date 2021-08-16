import java.util.Random;

public class Dice{
	// Creates a random dice roller with the variable name diceRoll
	private static Random diceRoll = new Random(); 
	
	// Dice.DiceRoller to call this method	
	public static int DiceRoller() {	
		return diceRoll.nextInt(6) + 1; // returns a randomly generated roll
	}
}