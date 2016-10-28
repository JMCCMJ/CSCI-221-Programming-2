
import java.util.Scanner;
import java.io.FileInputStream;

public class FunWithFileIO {

	public static void main(String[] args) throws Exception
	{
		Scanner scanner = null;
		String string = null;
	
		if( args.length > 0 )		
			// obtain a scanner attached to the filename that was provided
			scanner = new Scanner( new FileInputStream( args[0]) );
		else
		{
			System.out.println("A filename must be supplied via command line parameters");
			System.exit(-1);
		}

		while( scanner.hasNextLine() )
		{
			string = scanner.nextLine();
//			System.out.println( string );
			
			if( string.charAt(0) == ';')
				; // do nothing, strip comments from the data
			else if( !Character.isDigit(string.charAt(0)))
				System.out.println("Station line: " + string );
			else
			{
				String[] scheduleEntries;
				
				System.out.println("Schedule line: " + string );
				scheduleEntries = string.split("[|]");
				
				for( int i = 0; i < scheduleEntries.length; i++ )
				{
					System.out.println( scheduleEntries[i] );
				}
			}				
		}
		
		scanner.close();
	}
}
