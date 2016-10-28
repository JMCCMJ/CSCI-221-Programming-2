
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public abstract class LineBasedGraphic {
	
	

	protected ArrayList<Polyline> graphic;
	
	public LineBasedGraphic()
	{
		graphic = new ArrayList<Polyline>();
	}
	
	public abstract void getPolylinesFromFile( String filename ) 
		throws FileNotFoundException, IOException;
	
	public abstract int getNumPolylines();
	
	public abstract Polyline getPolylineByIndex( int i );
}
