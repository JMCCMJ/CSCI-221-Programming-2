

import java.util.StringTokenizer;

public class EncodeDecodeCmdString 
{
	public static void encode( StringBuffer cmdSequence, String str )
	{
		if( str.length() > 0 )
		{
			switch( str.charAt(0))
			{
				case 'm':
				case 'l':
					cmdSequence.append( str + "|");
					break;
				case 's':
					break;
			}
		}
	}
	
	public static void decode( String cmdSequence, PolylineCommands callback )
	{
		StringTokenizer strtok = new StringTokenizer( cmdSequence, "|" );
		
		while( strtok.hasMoreTokens() )
		{
			String line;	// the line from the file
							
			line = strtok.nextToken();
			
			if( line.length() > 0 )
			{
				char c = line.charAt(0);
				
				StringTokenizer strtok2 = new StringTokenizer( line.substring(1), "," );
	
				switch( c )
				{
					case 'm':
						callback.moveTo( Float.parseFloat( strtok2.nextToken() ),
								Float.parseFloat( strtok2.nextToken() ));
						break;
					case 'l':
						callback.lineTo( Float.parseFloat( strtok2.nextToken() ),
								Float.parseFloat( strtok2.nextToken() ));
						break;
					case 's':
						break;
				}
			}
		}
	}
}
