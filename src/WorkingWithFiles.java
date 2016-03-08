
import java.util.Scanner;
import java.io.PrintWriter; 
import java.io.IOException; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.Random;

public class WorkingWithFiles {
	
	static String fileName = "creepyRoom";

	static Scanner darkly = new Scanner(System.in);
	static int isDiscovered = 0;
	static double myMoney = 0.0;
	static int[] entryFlag = new int[8];

	public static void main(String[] args) {

		try 
		{
			for(int i = 0; i < 8; i++)
			{
				entryFlag[i] = 0;
			}

			// If movies does not exist no file is  created, if movies
			// does exist then the new File object points to the existing file
			File file = new File(fileName);        
			file.createNewFile();

			//write some data to the file
			PrintWriter pw = new PrintWriter(file);

			room1(pw);
			
		}
		catch (IOException e) 
		{
			System.out.println("Oops! An error occurred.");
		}

	}

	public static void WriteFile(PrintWriter pw, String description) 
	{
		try {
			pw.println(description);
		}
		catch (Exception e) {
			System.out.println("Oops! An error occurred writing to file.");
		}
	}

	public static void ReadFile()
	{
		try
		{
			//read our file
			File file = new File(fileName);   
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ( (line = br.readLine())!= null)
			{
				System.out.println(line);
			}
			br.close();

		} catch (IOException e) {
			System.out.println("Oops! An error occurred.");
		}
	}


	private static void roomDescribe(String where, String[] stuff, String directions)
	{
		System.out.println("You have accumuated $" + myMoney);
		System.out.println("You are in " + where);
		for (String thing : stuff) {
			System.out.println("You see " + thing);
		}
		System.out.println("You can go " + directions);

	}

	public static void room1(PrintWriter pw)
	{
		String place = "the Foyer";
		String[] things = new String[1];
		things[0] = "a dead scorpion";
		String direct = " to the north (n) or the south (s)";
		double room1Money;
		Scanner sc = new Scanner(System.in);

		Random rnd = new Random();

		if(entryFlag[0]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room1Money = (double) rnd.nextInt(1001);

			//write into file what you've seen if it's the first time entering this room
			WriteFile(pw, place);
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room1Money);
			WriteFile(pw, "$"+money);
		}
		else
			room1Money = 0.0;
		
		int totalRoomEntered = 0;

		int chance = rnd.nextInt(4);

		myMoney += room1Money;
		entryFlag[0] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("n")) 
			{
				room2(pw);
			}
			else if (choice.equals("s")) // exits the house
			{			
				System.out.println("You are exiting the house");
				for (int i = 0; i < 8; ++i)
				{
					totalRoomEntered += entryFlag[i];
					
				}
				
				WriteFile(pw, "Total room entered: ");
				WriteFile(pw, Integer.toString(totalRoomEntered));
				
				WriteFile(pw, "Total money accumuated: ");
				WriteFile(pw, Double.toString(myMoney));
				
				//flush and close file
				pw.flush();
				pw.close();
				
				System.out.println("Do you want to see the history of your tour (y/n)?");
				String history = sc.next();
				if (history.equalsIgnoreCase("y"))		//read from file
				{
					
					ReadFile();
					
				}
				
				if (chance == 3) 
				{
					System.out.println("You are followed by a ghost of Zork");
				}
				else 
				{
					System.out.println("You are safe...for now");
				}
				System.exit(0);
			}

		} while (1==1);
	}

	public static void room2(PrintWriter pw)
	{
		String place = "the Front Room";
		String[] things = new String[1];
		things[0] = "a piano";
		String direct = " to the east (e), the south (s) or the west (w)";
		double room2Money = 0.0;

		Random rnd = new Random();
		if(entryFlag[1]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room2Money = (double) rnd.nextInt(1001);	
			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room2Money);
			WriteFile(pw, "$"+money);
		}
		else
			room2Money = 0.0;

		myMoney += room2Money;
		entryFlag[1] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("s")) 
			{
				room1(pw);
			} else if (choice.equals("e")) 
			{
				room4(pw);
			} else if (choice.equals("w")) 
			{
				room3(pw);
			}
		} while (1==1);
	}

	public static void room3(PrintWriter pw)
	{
		String place = "the Library";
		String[] things = new String[1];
		things[0] = "spiders";
		String direct = " to the north (n), the east (e)";
		double room3Money;

		Random rnd = new Random();
		if(entryFlag[2]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room3Money = (double) rnd.nextInt(1001);
	
			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room3Money);
			WriteFile(pw, "$"+money);
		}
		else
			room3Money = 0.0;


		myMoney += room3Money;
		entryFlag[2] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("n")) 
			{
				room5(pw);
			} else if (choice.equals("e")) 
			{
				room2(pw);
			} 
		} while (1==1);
	}

	public static void room4(PrintWriter pw)
	{
		String place = "the Kitchen";
		String[] things = new String[1];
		things[0] = "many bats";
		String direct = " to the north (n), or the west (w)";
		double room4Money;

		Random rnd = new Random();
		if(entryFlag[3]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room4Money = (double) rnd.nextInt(1001);
			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room4Money);
			WriteFile(pw, "$"+money);
		}
		else
			room4Money = 0.0;

		myMoney += room4Money;
		entryFlag[3] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("n")) 
			{
				room7(pw);
			} else if (choice.equals("w")) 
			{
				room2(pw);
			} 
		} while (1==1);
	}

	public static void room5(PrintWriter pw)
	{
		String place = "the Dining Room";
		String[] things = new String[1];
		things[0] = "dust and emply boxes";
		String direct = " to the south (s) only";
		double room5Money;

		Random rnd = new Random();
		if(entryFlag[4]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room5Money = (double) rnd.nextInt(1001);

			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room5Money);
			WriteFile(pw, "$"+money);
		}
		else
			room5Money = 0.0;

		myMoney += room5Money;
		entryFlag[4] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("s")) 
			{
				room3(pw);
			} 
		} while (1==1);
	}

	public static void room6(PrintWriter pw)
	{
		Random rnd = new Random();	//get one out of four
		int chance = rnd.nextInt(4);
		double room6Money;
		String place = "the Vault";
		String[] things = new String[1];
		things[0] = "3 walking skeletons";
		String direct;

		if(entryFlag[5]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room6Money = (double) rnd.nextInt(1001);

			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room6Money);
			WriteFile(pw, "$"+money);
		}
		else
			room6Money = 0.0;

		myMoney += room6Money;
		entryFlag[5] = 1;

		if (isDiscovered == 1 || chance == 0)
		{
			direct = " to the secret room (s), or to the parlor (p)";
		}
		else 
		{
			direct = " to the parlor (p)";
		}


		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();
			if (isDiscovered == 1 || chance == 0) //0 means secret room is discovered
			{
				isDiscovered = 1;
				if (choice.equals("s")) 
				{
					room8(pw);
				} else if (choice.equals("p")) 
				{
					room7(pw);
				} 
			}
			else 
			{
				if (choice.equals("p"))
					room7(pw);
			}
		} while (1==1);
	}

	public static void room7(PrintWriter pw)
	{
		String place = "the Parlor Room";
		String[] things = new String[1];
		things[0] = "a treasure chest";
		String direct = " to the south (s) or the west (w)";
		double room7Money;

		//int isDiscovered = 0;

		Random rnd = new Random();
		if(entryFlag[6]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room7Money = (double) rnd.nextInt(1001);
			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room7Money);
			WriteFile(pw, "$"+money);
		}
		else
			room7Money = 0.0;


		myMoney += room7Money;
		entryFlag[6] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("s")) 
			{
				room4(pw);
			} else if (choice.equals("w")) 
			{
				room6(pw);
			}
		} while (1==1);
	}

	public static void room8(PrintWriter pw)
	{
		String place = "the Secret Room";
		String[] things = new String[1];
		things[0] = "you found gold!!";
		String direct = " to the west (w) to exit the secret room";
		double room8Money;

		Random rnd = new Random();
		if(entryFlag[7]==0) //so if you've already been in this room before, no more money to collect.
		{
			// the machine randomly generating money between 1 to 1000
			room8Money = (double) rnd.nextInt(1001);
			
			WriteFile(pw, place);
			//write into file what you've seen if it's the first time entering this room
			for (String thing : things) {
				WriteFile(pw, thing);
			}

			String money = Double.toString(room8Money);
			WriteFile(pw, "$"+money);
		}
		else
			room8Money = 0.0;

		myMoney += room8Money;
		entryFlag[7] = 1;

		do
		{
			roomDescribe(place, things, direct);

			String choice = darkly.next();

			if (choice.equals("w")) 
			{
				room6(pw);
			} 
		} while (1==1);
	}


}