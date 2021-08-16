import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Database {

	private Scanner pinput;
	private Formatter poutput;
	// Creating an array list to store Player objects called 'players'
	private ArrayList<Player> players;
	
	// Creates the results.txt file without replacing old one
	public void createDB() {
		createFile("results.txt");
	}
	
//	// Creates the results.txt file while replacing any existing ones
//	public void createNewDB() {
//		createNewFile("results.txt");
//	}
	
	// Resets ArrayList
	// Opens results.txt
	// Loads data from results.txt into empty ArrayList
	// Closes results.txt
	public void loadDB() {
		players = new ArrayList<Player>(); // ArrayList resets every time a new round is started to prevent duplicate records
		pinput = openInputFile("results.txt");
		readResultsFile();
		closeInputFile(pinput);
	}
	
	// Opens results.txt
	// Stores data from ArrayList into results.txt
	// Closes results.txt
	public void storeDB(){
		poutput = openOutputFile("results.txt");
		writeResultsFile();
		closeOutputFile(poutput);
	}
	
	// Sort the results in ArrayList in ascending order
	// Print the scores in a table
	public void printDB() {
		sortResults();
		printResults();
	}
	
	//
	// Individual methods
	//
	// Creates the results text file with the parameter as filename 
	// File is created if there are no existing file with the same filename
	public void createFile(String filename) {
		try{
			File myFile = new File(filename);
			if (myFile.createNewFile()) {
//				System.out.println("File created: " + myFile.getName());
			} else {
//				System.out.println("File already exists.");
			}
		}
		catch (IOException e) {
			System.err.println("Error reading from file.");
			System.exit(1);
		}
	}
	
	// Creates the results text file with the parameter as filename 
	// Overwrites the old file if file with same name exists
//	public void createNewFile(String filename) {
//		try {
//			Writer fileWriter = new FileWriter(filename, false);
//		} 
//		catch (IOException e) {
//			System.err.println("Error reading from file.");
//			System.exit(1);
//		}
//	}
	
	// Opens the results.txt file for the results to be loaded into the ArrayList for reading
	// Returns the file into the 'pinput' scanner is file is found
	public Scanner openInputFile(String filename) {
		Scanner tempinput = null;
		try
		{
			tempinput = new Scanner(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		}
		return tempinput;
	}
	
	// Load the results from results.txt into the ArrayList
	public void readResultsFile(){
		try
		{
			while(pinput.hasNext())
			{
				Player newplayer = new Player();
				newplayer.setName(pinput.next());
				newplayer.setScore(pinput.nextInt());
				
				players.add(newplayer);
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File improperly formed.");
			pinput.close();
			System.exit(1);
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Error reading from file.");
			System.exit(1);
		}
		
	}
	
	// Close results.txt after loading into the ArrayList
	public void closeInputFile(Scanner input) {
		if (input != null) {
			input.close();
		}
	}
	
	// Opens results.txt for results to be written in
	// Returns the file into the 'poutput' formatter if the file is found
	public Formatter openOutputFile(String filename) {
		Formatter tempoutput = null;
		try
		{
			tempoutput = new Formatter (new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file. File not found");
			System.exit(1);
		}
		return tempoutput;
	}
	
	// Writes all results from the Players ArrayList into results.txt
	public void writeResultsFile()
	{
		for (int j = 0; j < players.size(); j++) {
			poutput.format("%s %d\n", players.get(j).getName(), players.get(j).getScore());
		}
	}
	
	// Close results.txt file after writing in
	public void closeOutputFile(Formatter output) {
		if (output != null) {
			output.close();
		}
	}
	
	// Method to add Player results to ArrayList after every round of game
	public void addResults(Player p1)
	{
		Player winner = p1;

		try
		{
			players.add(winner);
		}
		catch (FormatterClosedException formatterClosedException)
		{
			System.err.println("Error writing to file.");
			return;
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("invalid input");
			return;
		}
	}
	
	// Method to sort the results in the ArrayList
	public void sortResults() {
		Comparator<Player> scoreComparator = (p1, p2) -> (int) (p1.getScore() - p2.getScore());
		Collections.sort(players, scoreComparator);
	}
	
	// Print the leaderboard in a table
	public void printResults() {
		int size;
		System.out.println();
		System.out.println("LEADERBOARD");
		System.out.println("Pos. Name       Score");
		if (players.size() < 5)
		{
			size = players.size();
		}
		else {
			size = 5;
		}
		for (int i = 0; i < size; i++) {
			System.out.printf("%3d. %-10s %-5d\n", i+1, players.get(i).getName(), players.get(i).getScore());
		}
		System.out.println();
	}
	
	
}
