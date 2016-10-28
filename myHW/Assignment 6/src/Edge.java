//Jan-Michael Carrington
//CSCI 221 MWF 8:30AM

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class Edge {
	static String FILTER_MESSAGE = "Enter words you wish to filter out seperated by commas: ";
	static String ERROR_MESSAGE = "Invalid text file entered, please try again or press 'Q' to quit.";
	static String QUIT_MESSAGE = "You have exited the program.";
	static String INPUTMESSAGE = "Enter the name of a text file: ";

	public static void main(String[] args) {
		
		
		System.out.print(INPUTMESSAGE );
		
		Scanner scanner = null;
		String string = null;
		String userText = null;
		
		Scanner userInput = new Scanner(System.in);
		System.out.print(INPUTMESSAGE);
		
		boolean breaker= true;
		while(breaker)
		{
			try 
			{
				userText = userInput.next();
				if(userText.equalsIgnoreCase("q"))
				{
					System.out.println(QUIT_MESSAGE);
					break;
				}
				scanner = new Scanner( new File( userText ));
				//System.out.println(scanner.nextLine());
				breaker = false;
				
			}catch (Exception e) {
				System.out.println(ERROR_MESSAGE);
				System.out.print(INPUTMESSAGE);
			}	
		}
		
		int i =  0;
		System.out.println(FILTER_MESSAGE);
		//Scanner userString = null;
		
		String userString = userInput.next();
		//System.out.println(userString);
		
		List<String> filteredValues = new ArrayList<String>();
		String[] words_split = userString.split("[,]");
		for( int j=0; j<words_split.length; j++ ){
			filteredValues.add(words_split[j]);
		}
		//System.out.println(filteredValues);
		//List<String> filteredValues = userString.split("[,]");
		//FilteredIteratorImpl filtered = new FilteredIteratorImpl(filtered, null);
		while(scanner.hasNextLine()){
			
			String line = scanner.nextLine();
			
			String[] strings = line.split("\\ ");
			List<String> strings_list = Arrays.asList(strings);
			
			Iterator<String> itr = strings_list.iterator();
			
			FilteredIterator<String, String> filteredIterator = new FilteredIteratorImpl(itr, filteredValues);
			//System.out.println(strings_list);
			String printThis = filteredIterator.toString();	
			System.out.println(printThis);
//			while(itr.hasNext()){
//				System.out.println(itr.next());
//				i++;
//			}
			
		}
		//System.out.println(i);
		
		
		
		
	}
}


























