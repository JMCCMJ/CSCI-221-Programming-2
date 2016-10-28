
public abstract class Tile 
{
	public final static int T_ROW = 3;
	public final static int T_COL = 3;
	
	private CellElement cell[][] = new CellElement[T_ROW][T_COL];
	
	public Tile()
	{
		for( int row = 0; row < T_ROW; row++ )
			for( int col=0; col < T_COL; col++ )
				cell[row][col] = new CellElement( 0, true );
	}
	
	public int getValue( int x, int y )
	{
		return cell[x][y].getValue();
	}
	
	public boolean isModifiable( int x, int y )
	{
		return cell[x][y].isModifiable();
	}
	
	public void setValue( int x, int y, int value, boolean modifiable )
	{
		cell[x][y].setValue(value);
		cell[x][y].setModifiable(modifiable);
	}
	
	public boolean isSelected( int x, int y )
	{
		return cell[x][y].isSelected();
	}
	
	public void setSelected( int x, int y, boolean selected )
	{
		cell[x][y].setSelected( selected );
	}
	
	public abstract String toString();
}
