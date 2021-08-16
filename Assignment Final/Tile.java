import java.util.Random;

public class Tile {
	//Attributes
	private String symbol;
	private int location;
	private int strength;
	
	
	//Constructor
	public Tile() {
		setSymbol("~");
		strength = 0;
	}
	
	public Tile(String symbol) {
		setSymbol(symbol);
	}
	
	
	//Setter getters
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}

	
	//Methods
	public void generateStrength() {
		int[] strengths = {2, 4, 6};
		Random rand = new Random();
		
		setStrength(strengths[rand.nextInt(strengths.length)]);
	}
}
