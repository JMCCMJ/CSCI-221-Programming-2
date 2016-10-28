
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineBasedGraphicImpl extends LineBasedGraphic
{
	public LineBasedGraphicImpl()
	{
		super();
	}
	
	public void getPolylinesFromFile( String filename ) throws FileNotFoundException, IOException
	{		
		BufferedReader in;
		String str;
		StringBuffer strbuf = new StringBuffer();
	
		in = new BufferedReader( new FileReader( filename ) );
		
		str = in.readLine();
		
		// read the file a line at a time
		while( str != null )
		{
			if( str.length() > 0 )
			{
				switch( str.charAt(0))
				{
					case 's':
						// end of sequence, create the polyline
						Polyline polyline = new PolylineImpl();
						polyline.createPolylineFromCmds(strbuf.toString());
						super.getGraphic().add( polyline );
						
						// clear the StringBuffer to prepare for next polyline
						strbuf.delete(0, strbuf.length());
						break;
					case ';':
					case ' ':
						break;
					default:
						EncodeDecodeCmdString.encode(strbuf, str);
				}
			}
			
			str = in.readLine();
		}
	}
	
	public int getNumPolylines()
	{
		return super.getGraphic().size();
	}
	
	public Polyline getPolylineByIndex( int i )
	{
		return super.getGraphic().get(i);
	}
}
