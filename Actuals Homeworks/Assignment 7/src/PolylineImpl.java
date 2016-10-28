
import java.util.StringTokenizer;

public class PolylineImpl extends Polyline implements PolylineCommands
{
	public PolylineImpl()
	{
		super();
	}
	
	// you will need to implement the guts of the constructor
	public void createPolylineFromCmds( String cmdSequence )
	{				
		EncodeDecodeCmdString.decode(cmdSequence, this );
	}
	
	public void moveTo( float x, float y )
	{
		super.getPolyline().moveTo(x, y);
	}
	
	public void lineTo( float x, float y )
	{
		super.getPolyline().lineTo(x, y);
	}

	public static void main( String args[] )
	{
		PolylineImpl polyline = new PolylineImpl();
		polyline.createPolylineFromCmds("m -11,-11|l -10,-12|l -10,-17|l -11,-16");
		System.out.println( polyline );
	}
}
