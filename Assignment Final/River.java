import java.util.ArrayList;
import java.util.Random;

public class River {

	//river has 100 spaces.
	//type should be obstacle
	private ArrayList<Tile> myRiver = new ArrayList<Tile>();
	private ArrayList<Tile> currentList;
	private ArrayList<Tile> trapList;
	private Random rand = new Random();
	
	//setters
	public void setTraps() {
		trapList = new ArrayList<Tile>(); //arrayList to hold trap objects
		
		while (trapList.size() < 10) { //only 10 trap objects
			Trap trap = new Trap(); //creating trap objects
			int index = rand.nextInt(99); //randomize up till 99 because traps or currents shouldn't be placed at the finish line
			
			if (index %2 == 1) {	//traps are odd numbers
				if (ifIndexTaken(index, trapList) == false) { //if trapList doesn't contain index then set location and add into arrayList
					trap.setLocation(index);
					trapList.add(trap);
				}
			}
		}
		
		//puts traps into myRiver
		for (Tile t: trapList) {
			myRiver.set(t.getLocation(), t);
		}
	}
	
	
	public void setCurrents() {
		currentList = new ArrayList<Tile>();
		
		while (currentList.size() < 10) { //only 10 current objects
			Current c = new Current(); //creating current object to be passed off into the CurrentList 
			int index = rand.nextInt(99); //randomize up till 99 because traps or currents shouldn't be placed at the finish line
			
			//the location attribute of each current will only be on even tiles
			//if the index is not in currentList, then the current object will be set to that index and added into currentList
			if (index % 2 == 0) { //curents are even
				if (ifIndexTaken(index, currentList) == false) {
					c.setLocation(index);
					currentList.add(c);
				}
			}
		}
		
		//puts current into myRiver
		for (Tile t: currentList) {
			myRiver.set(t.getLocation(),t);
		}
	}
	
	// Gets back the river ArrayList
	public ArrayList<Tile> getRiver() {
		return myRiver;
	}
	
	//create river by initializing 100 spaces and updating the spots with traps and currents
	public void createRiver() {
		for (int i=0; i < 100; i++) {
			Tile t = new Tile();
			t.setLocation(i);
			myRiver.add(t);
		}
		
		setCurrents();
		setTraps();
	}
	
	//display the river to the users
	//for loop to iterate through all the objects in myRiver
	//objects are saved into a string
	public String displayRiver(ArrayList<Player> players) {
		String riverSymbols="|Start|";
		ArrayList<String> playerNames;
		
		for (Tile t: myRiver) {
			boolean playerAtCurrentTile = false;
			playerNames = new ArrayList<String>();
			int currentTilePos = t.getLocation();
			
			for (Player p: players) {
				if (p.getBoatPos() == currentTilePos) {
					playerNames.add(p.getName());
					playerAtCurrentTile = true;
				}
			}
			
			if (playerAtCurrentTile) {
				riverSymbols += String.format("(%s %s)", t.getSymbol(), String.join(", ", playerNames));
				
			} else {
				riverSymbols += t.getSymbol();
			}
		}
		return riverSymbols + "|Finish|";
	}
	
	//check if the index is taken
	//for loop to iterate through the object's location
	//if it matches with index then it'll return true
	public boolean ifIndexTaken(int index, ArrayList<Tile> arrayToBeChecked) {
		for (Tile t: arrayToBeChecked) {
			if (index == t.getLocation()) {
				return true;
			}
		}
		return false;
	}
}


