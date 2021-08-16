
public class Player {
	// Attributes for the Player object
	private String name;
	private int score;
	private Boat boat;
	
	// Constructors
	public Player() {
		this.name = null;
		this.score = 0;
		this.boat = new Boat();
	}
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	// Setters
	public void setName(String name) {
    	this.name = name;
	}
	
    public void setScore(int score) {
    	this.score = score;
	}
    
    public void setBoatPos(int pos) {
    	boat.setPos(pos);
    }
	
    // Getters
    public String getName() {
		return name;
	}
    
    public int getScore() {
		return score;
	}
    
    public int getBoatPos() {
    	return boat.getPos();
    }
    
    
    //Methods
    public int rollDice(Dice dice) {
    	return dice.DiceRoller();
    }
    
    // toString
    public String toString() {
		return String.format("%-10s %-5d", getName(), getScore());
	}
    
}
