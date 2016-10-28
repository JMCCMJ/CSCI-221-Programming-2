
import java.awt.geom.*;

public abstract class Polyline 
{
	private GeneralPath polyline;
	
	public Polyline()
	{
		polyline = new GeneralPath();
	}
	
	public GeneralPath getPolyline()
	{
		return polyline;
	}
	
	public String toString()
	{
		AffineTransform aft = new AffineTransform();
		PathIterator pathItr = polyline.getPathIterator(aft);
		
		StringBuffer strbuf = new StringBuffer();
	
		int type;
		float [] coords = new float[6];

		while( !pathItr.isDone() )
		{
			type = pathItr.currentSegment(coords);
			
			if( type == PathIterator.SEG_MOVETO )
				strbuf.append( "moveto ");
			else if( type == PathIterator.SEG_LINETO )
				strbuf.append("lineto " );
			
			strbuf.append( coords[0] + " " + coords[1] + "\n" );
			
			pathItr.next();
			
		}
		
		return( strbuf.toString() );
	}
	
	public abstract void createPolylineFromCmds( String cmdSequence );
}