
public class TileImpl extends Tile 
{
	public TileImpl() 
	{
		super();
	}

	public String toString() 
	{
		StringBuffer strbuf = new StringBuffer();
		
		for( int row=0; row<T_ROW; row++ )
		{
			for( int col=0; col<T_COL; col++ )
			{
				int value = this.getValue(row,col);
				strbuf.append((value == 0)? '-' : value + " " );
			}
			strbuf.append("\n");
		}
		
		return strbuf.toString();
	}
	
	public static void main( String args[] )
	{
		Tile tile = new TileImpl();
		int x = tile.getValue( 3, 2);
		System.out.println( tile );
	}
}
