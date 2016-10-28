import java.util.ArrayList;


public class Metrics {

	public static int wordCount;
	public static int lineCount;
	public static int charCount;
	public static int sentenceCount;	
	public static ArrayList<WordFrequency> wordFrequency = new ArrayList<WordFrequency>();
	
	public static boolean containsSentencePunctuation( String word )
	{
		boolean rtnval = false;
		
		char lastChar = word.charAt( word.length()-1 );
		
		switch( lastChar )
		{
			case '.':
			case '?':
			case '!':
				rtnval = true;
				break;
		}		
		
		return rtnval;
	}
	
	// prepares word for entry into the list.
	// converts word to lower case and strips ending punctuation
	public static String prepareWord( String word )
	{
		String rtnval = word.toLowerCase();
		
		char lastChar = rtnval.charAt( rtnval.length()-1 );
		
		switch( lastChar )
		{
			case '.':
			case '?':
			case '!':
			case ';':
			case ',':
			{
				rtnval = rtnval.substring( 0, word.length()-1 );
			}			
		}
		
		if( rtnval.endsWith("'s"))
		{
				rtnval = rtnval.substring( 0, word.length()-2 );
		}
				
		return rtnval;		
	}
	
	public static void enterIntoWordFrequency( String word )
	{
		WordFrequency wf = new WordFrequency( word );
		
		int i;

		for( i=0; i<wordFrequency.size(); i++ )
		{
			WordFrequency listElement = wordFrequency.get(i);
			int compareToValue = listElement.compareTo(wf);

			if( compareToValue == 0)
			{
				listElement.incrementCount();
				break;
			}
			else if( compareToValue > 0 )
			{
				wordFrequency.add( i, wf );
				break;
			}
		}

		// did we get to the end of the list without taking action on the word?
		// if so, insert it at the end of the list.
		if( i == wordFrequency.size() )
			wordFrequency.add( wf );
	}
	
	public static void processLine( String line )
	{
		String[] strings;
		
		// increment the lineCount
		lineCount++;
		
		// compute the wordCount
		strings = line.split(" ");
		wordCount+= strings.length;
		
		// compute the charCount
		for( int i=0; i<strings.length; i++ )
		{
			String word  = strings[i];
			
			charCount += word.length();
			
			if( containsSentencePunctuation(word) )
				sentenceCount++;
			
			enterIntoWordFrequency(prepareWord( word ));
		}
	}
	
	
 	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println( prepareWord("Hi!"));
		System.out.println( prepareWord("Paul's"));
	}

}
