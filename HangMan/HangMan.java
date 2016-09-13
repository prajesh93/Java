    /*
   
  * HangMan.java
     *
     * Version:1.0	  	09/14/2015
     * @ author   		Prajesh Jhumkhawala
     * @ author   		Ashwini Singh
     
     */

/** 
 * Demonstration of HangMan
 *A program which will read random words from input.txt and will give that word to guess to player1.
 *If other players are also playing then they will get a word of same length.
 *This version of the game is played with n, n > 1, people. 
 *The one player with the fewest guesses wins.
 * 
 */
    


//package textfiles;
import java.io.*;
import java.util.*;
import java.math.*;
/**
 * 
 * This class reads the dictionary and store the values into String arrays.
 *
 */

class TakeInput
{
	int no_of_lines=0;
	private String path;
	TakeInput(String filepath)
	{
		path=filepath;  //current filepath
	}
	/**
	 * It reads the file from given path and counts the number of lines it has and stores the words into String array.
	 *
	 * @return      String Array of words
	 *
	 */
	public String[] OpenFile() throws IOException
	{
		
		BufferedReader br= new BufferedReader(new FileReader(path));
		BufferedReader br1= new BufferedReader(new FileReader(path));
		while(br1.readLine()!=null)
		{
			no_of_lines=no_of_lines+1;   // Reads the line of file one by one.
		}
		
		String [] input= new String [no_of_lines];
		for (int i=0; i<no_of_lines; i++)
		{
			input[i]=br.readLine();
		}
		br.close();br1.close();
		return input;
	}
}

/**
 * This class contains main method, which executes the entire program.
 *
 */

public class HangMan
{
	/**
	 * This method is used to generate words of same length.
	 *
	 * @String      Entire_word    A list of Strings with same length.   
	 * @param       random         Index of String
	 * @return      String         Desired word for next player
	 */

	public static String generate_word(String[] Entire_word, int random)throws IOException
	{
		String g="";
		for (int i=0;i<Entire_word.length;i++)
		{
			g=((Entire_word[0]).toString());
			if(i==random)
			{
				g= ((Entire_word[i]).toString());
				break;
			}
		}
		return g;
		
	}
	/**
	 * This method matches each letter entered by user with 'word'(from dictionary), if it matches then +10
	 * is awarded to user. If entered letter is wrong then 5 in deducted from score.
	 * If User enters same correct letter multiple times then chances won't be reduced and point will not be awarded.
	 * But, If user enters a wrong letter multiple times then chances are reduced and point is deducted only once.
	 * Every user is given 8 chance to complete his/her guess. 
	 *
	 * @String       word    'word', which player has to guess
	 * @char[]       word1   'word' converted to char array
	 * @char[]       blank1   it stores '_' (same as length of word), when a letter is matched, it is replaced by that letter.
	 * @int           l       length of 'word'
	 * @return       int      points scored by each player
	 */

	//@SuppressWarnings("null")
	public static int Game(String word,int l, char[]word1,char[]blank1)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
	    String ch="";int i=0;String c1="";int points=0;int index=0;int x1=0;
	    int counter=0; int check=0;int flag=0; 
	    // Until false input has been encountered 8 times or word is matched, this loop continues. 
	    while (counter!=8)
		{
			x1=0;
			if(check==l)   // check is word matching.
			{counter=8;
				break;
			}
		System.out.println("Enter your character");
		String c=br.readLine();
		ch=ch+c1;
		c=c.toLowerCase();
		
		for (i=0;i<ch.length();i++)
		{
			flag=0;
			if((Character.toString(ch.charAt(i)).equals(c))) // if same letter is read more than once then points should not be deducted.
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
		c="1";
		c1=c;
		for (index=0;index<l;index++)
		{
		if(word1[index]==c.toCharArray()[0])
		{
			points=points+10;
			
			x1=1;     // flag for letter, which is already given by user.
			check++;  // one letter is matched
			blank1[index]=c.toCharArray()[0];   // change blank space by the correct letter
		}
		
		}
		 for (i=0;i<blank1.length;i++)
			System.out.print(blank1[i]+" ");
		 System.out.println("");
		if (x1!=1 && c!="1" && flag==0)   // If letter is not matched and letter is not repeated 
			points=points-5;
		if(x1==0)
		{
		
		counter=counter+1;  // false input counter
		if(counter==8)
			break;
		}
		}
		
		return points;
	
	}
	
	/**
	 * Main() method, which invokes other methods to complete the task for multiple users.
	 * At the end, it displays scores of each player and declares winner.
	 */
	
	public static void main(String args[])throws IOException, InterruptedException
	{
		String filename="input.txt";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			int player1=0;int l=0;
			
			TakeInput file=new TakeInput(filename);
			String[] Entire_word = file.OpenFile();
			if(Entire_word[0]==null)
				System.out.println("Dictionary is empty");
				
				else
				{
				
			int i;String word="";
			Random rand=new Random();
			int random=rand.nextInt(Entire_word.length-1); // generates a random variable (0 to length_passed)
			System.out.println("Enter number of players : ");
			int n=Integer.parseInt(br.readLine());
			
			for (i=0;i<Entire_word.length;i++)
			{
				if(i==random)
				{
					word=Entire_word[i];   //pick any random variable
					break;
				}
			}
			String Entire_word_new="";
			String[] Entire_word_new1=new String[10];
			for (i=0;i<Entire_word.length;i++)
			{
				if(Entire_word[i].length()==word.length())
					Entire_word_new=Entire_word_new+(Entire_word[i]).toString()+" ";//store all strings which have same length as 'word'
			}
			Entire_word_new=Entire_word_new.substring(0, Entire_word_new.length()-1);
			Entire_word_new1=Entire_word_new.split(" ",-1);
			l=word.length();
			word=word.toLowerCase();
			String disp="";
			
			char[] word1=word.toCharArray();
			String blank="";
			int k=word.indexOf(" ");
			// Logic for space encountered
			for (i=0;i<l;i++)
			{
				if(i==k)
				{
					System.out.print("  ");
				blank=blank+" ";
				}
				else
				{
				blank=blank+"_";
				System.out.print("_ ");
				}
			}
			
			System.out.println("");
			char []blank1=blank.toCharArray();
			int max=0;int track=0;
			for (i=0;i<n;i++)
			{
			System.out.println("Try your Luck player "+(i+1));
			if (i>0)
			{
			for (int j=0;j<blank1.length;j++)
				System.out.print(blank1[j]+" ");
			 System.out.println("");
			}
			
			player1=Game(word,l,word1,blank1);
			//generate random between 0 to (length of same string)
			if(Entire_word_new1.length>1)
			random=rand.nextInt(Entire_word_new1.length-1);
			else
				random=0;
			word=generate_word(Entire_word_new1,random);  // new word with same length
			word1=word.toCharArray();
			blank1=blank.toCharArray();
			System.out.println("Total points earned for player "+(i+1)+" is "+player1);
			disp=disp+"\n"+("Player "+(i+1)+"    "+player1);
			Thread.sleep(2000);
			for (int p=0;p<100;p++)
			System.out.println("\n\n\n\n\n\n\n");
			
			if(max<=player1)
			{
				max=player1;
				track=i+1;
			}
			}
			System.out.println(disp+"\n");
			
			if(track!=0)
			System.out.println("Winner of game is player "+track+" with points "+max);
			else
				System.out.println("All players have scored 0");	
		   }
		}
		
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
