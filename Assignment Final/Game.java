import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Dice dice = new Dice();
	private River river = new River();
	private Database results = new Database();
	private Scanner input = new Scanner(System.in);
	
	
	public void start() {
		int round = 1;
		System.out.println("Welcome to Boat Racing Game!");
		
		setupDatabase();
		
		// Creating river
		river.createRiver();
		ArrayList<Tile> riverTiles = river.getRiver();
		
		getPlayerDetails();
		
		System.out.println("Game started!\n");
		System.out.println("This is the river!");
		System.out.println(river.displayRiver(new ArrayList<Player>()));
		System.out.println("\n");
		
		while (!finished()) {
			System.out.printf("Round %d------------------------------------------------------------------------------------------------------------------------------\n", round);
			
			for (Player currentPlayer: players) {

				int moveBy = currentPlayer.rollDice(dice);
				
				currentPlayer.setBoatPos(currentPlayer.getBoatPos() + moveBy);
				
				int currentPos = currentPlayer.getBoatPos();

				
				Tile currentTile = riverTiles.get(currentPos);
				
				if (currentTile instanceof Current) {
					currentTile.generateStrength();
					currentPlayer.setBoatPos(currentPlayer.getBoatPos() + currentTile.getStrength());
					System.out.printf("%s stepped on a current and moved %d steps forward!\n", currentPlayer.getName(), currentTile.getStrength());
					
				} else if (currentTile instanceof Trap) {
					currentTile.generateStrength();
					currentPlayer.setBoatPos(currentPlayer.getBoatPos() - currentTile.getStrength());
					System.out.printf("%s stepped on a trap and moved %d steps backward!\n", currentPlayer.getName(), currentTile.getStrength());
				}
				
				currentPlayer.setScore(currentPlayer.getScore() + 1);
				System.out.printf("%s moved to %d.\n", currentPlayer.getName(), currentPlayer.getBoatPos() + 1);
			}
			System.out.println();
			System.out.println("Live River Visual:");
			System.out.println(river.displayRiver(players));
			System.out.println("\n");
			round++;
		}

		playAgain();
	}
	
	
	public boolean finished() {
		
		for (Player p: players) {
			if (p.getBoatPos() == 99) {
				
				System.out.println("Game finished!");
				System.out.printf("\n%s won with %d moves!!!\n", p.getName(), p.getScore());
				
				//Add player details into database array
				results.addResults(p);
				
				// Stores ArrayList data into results.txt
				results.storeDB();
				
				// Prints sorted leader board from ArrayList
				results.printDB();
				
				return true;
			}
		}
		
		return false;
	}
	
	
	public void getPlayerDetails() {
		int noOfPlayers = 0;	
		
		while (true)
		{	
			try
			{
				System.out.print("How many players are playing: ");
				noOfPlayers	= input.nextInt();
				
				if (noOfPlayers > 0) {
					break;
					
				} else {
					throw new Exception();
				}
			}
			
			catch(Exception e)
			{
				System.err.println("\nOnly positive numbers!");
				input.nextLine();
			}
		}
		
		for (int i = 0; i < noOfPlayers; i++) {
			Player p = new Player();
			
			System.out.printf("Enter name for Player %d: ", i + 1);
			p.setName(input.next());
			
			players.add(p);
		}
		
		System.out.println();
	}
	
	
	public void setupDatabase() {
		results.createDB();
		// Resets results ArrayList
		// Loads existing result data from results.txt into empty ArrayList
		results.loadDB();
		results.printDB();
	}
	
	
	public void playAgain() {
		System.out.print("Would you like to try again [y/n]: ");
		String tryAgain = input.next();
		
		while ((tryAgain.equals("y") == false) && (tryAgain.equals("n")== false)) {
			System.out.print("Invalid input!");
			System.out.print("\nWould you like to try again [y/n]: ");
			tryAgain = input.next();				
		}
		
		if (tryAgain.equals("y")) {
			System.out.println();
			players = new ArrayList<Player>();
			start();
			
		} else {
			System.out.print("\nThank you very much for playing!");
		}
	
	}
}
