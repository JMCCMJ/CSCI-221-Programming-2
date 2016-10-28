
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public abstract class LineBasedGraphic {

	private ArrayList<Polyline> graphic;
	
	public LineBasedGraphic()
	{
		graphic = new ArrayList<Polyline>();
	}
	
	public ArrayList<Polyline> getGraphic()
	{
		return graphic;
	}
	
	public abstract void getPolylinesFromFile( String filename ) 
		throws FileNotFoundException, IOException;
	
	public abstract int getNumPolylines();
	
	public abstract Polyline getPolylineByIndex( int i );
}
