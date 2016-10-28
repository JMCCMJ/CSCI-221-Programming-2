
public abstract class Board 
{
	public final static int B_ROW = 3;
	public final static int B_COL = 3;
	private Tile board[][] = new Tile[B_ROW][B_COL];
	public Stack moves;
	
	public Board()
	{
		for(int row=0; row < board.length; row++ )
			for( int col=0; col<board[row].length; col++ )
				board[row][col] = new TileImpl();
		
		moves = new Stack();
	}
	
	public int getValue( int bx, int by, int tx, int ty )
	{
		Tile t = board[bx][by];
		
		return t.getValue(tx, ty);
	}
	
	public boolean isModifiable( int bx, int by, int tx, int ty )
	{
		Tile t = board[bx][by];
		
		return t.isModifiable(tx, ty);
	}
	
	public void undo( int bx, int by, int tx, int ty, int value )
	{
		Tile t = board[bx][by];
		
		t.setValue(tx, ty, value, true );
	}
	
	public void setValue( int bx, int by, int tx, int ty, int value, boolean modifiable )
	{
		Tile t = board[bx][by];
		
		// setValue is the common entry point for all changes in the model
		if( modifiable )
		{
			StringBuffer strbuf = new StringBuffer();
			
			strbuf.append("bx: " + bx + ", " );
			strbuf.append("by: " + by + ", " );
			strbuf.append("tx: " + tx + ", " );
			strbuf.append("ty: " + ty + ", " );
			strbuf.append("oldValue: " + t.getValue(tx, ty) + ", " );
			strbuf.append("newValue: " + value );
			
			moves.push( strbuf.toString() );
		}
		
		t.setValue(tx, ty, value, modifiable );
		
	}
	
	public boolean isSelected( int bx, int by, int tx, int ty )
	{
		Tile t = board[bx][by];
		
		return t.isSelected(tx, ty );
	}
	
	public void setSelected( int bx, int by, int tx, int ty )
	{
		Tile t = board[bx][by];
		
		// clear the currently selected value since only one cell can
		// be selected at a time
		clearSelected();
		
		t.setSelected(tx, ty, true );
	}
	
	public int [] getSelectedCoordinates()
	{
		int [] rtnval = new int[4];
		rtnval[0]=rtnval[1]=rtnval[2]=rtnval[3] = -1;
		
		for( int boardRow=0; boardRow<B_ROW; boardRow++ )
		{
			for( int boardCol=0; boardCol<B_COL; boardCol++ )
			{
				for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
				{
					for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
					{
						if( isSelected(boardRow, boardCol, tileRow, tileCol ) )
						{
							rtnval[0] = boardRow;
							rtnval[1] = boardCol;
							rtnval[2] = tileRow;
							rtnval[3] = tileCol;
						}
					}
				}
			}
		}
		
		return rtnval;
	}
	
	public void initalizeBoard( boolean modifiable )
	{
		for( int boardRow=0; boardRow<B_ROW; boardRow++ )
		{
			for( int boardCol=0; boardCol<B_COL; boardCol++ )
			{
				for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
				{
					for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
					{
						setValue( boardRow, boardCol, tileRow, tileCol, 0, modifiable );
						
						if( isSelected(boardRow, boardCol, tileRow, tileCol ) )
							clearSelected( boardRow, boardCol, tileRow, tileCol );
					}
				}
			}
		}	
		
		// clear the moves stack by creating another...
		moves = new Stack();
	}
	
	private void clearSelected( int bx, int by, int tx, int ty )
	{
		Tile t = board[bx][by];
		
		t.setSelected(tx, ty, false );
	}

	public void clearSelected()
	{
		for( int boardRow=0; boardRow<B_ROW; boardRow++ )
		{
			for( int boardCol=0; boardCol<B_COL; boardCol++ )
			{
				for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
				{
					for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
					{
						if( isSelected(boardRow, boardCol, tileRow, tileCol ) )
							clearSelected( boardRow, boardCol, tileRow, tileCol );
					}
				}
			}
		}
	}
	
	public abstract void loadBoardFromFile( String filename );
	
	public abstract String toString();
}
