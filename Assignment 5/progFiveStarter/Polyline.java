import java.awt.geom.*;
import java.awt.Color;

public abstract class Polyline 
{
	protected Path2D.Float polyline;
	protected Color color;
	
	public Polyline()
	{
		polyline = new GeneralPath();
		color = null;
	}

	public String toString()
	{
		AffineTransform aft = new AffineTransform();
		PathIterator pathItr = polyline.getPathIterator(aft);
		
		StringBuffer strbuf = new StringBuffer();
		strbuf.append( "Polyline color: " + (color == null ? "black\n" : color + "\n") );
	
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