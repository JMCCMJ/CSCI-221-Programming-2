import java.io.*;
import java.util.*;

public class BoardImpl extends Board 
{
	public BoardImpl() 
	{
		super();
	}

	public void loadBoardFromFile(String filename) 
	{
		try
		{
			BufferedReader buf = new BufferedReader( new FileReader( filename ));
			
			String str = buf.readLine();

			int temp = 0;

			moves = new Stack();
			while( str != null )
			{
				loadCellFromLine( str );
				if(str.charAt(0)=='b'){
					temp++;
					moves.push(str.toString());
				}
				str = buf.readLine();
			}
			for( int i = 0; i<=temp-1; i++){
				moves.popEnd();
			}		
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "File not found exception in loadBoardFromFile" );
			System.exit(0);
		}
		catch( IOException e )
		{
			System.out.println("IO exception in loadBoardFromFile" );
			System.exit(0);
		}
		catch( FileFormatException e )
		{
			System.out.println( e.getMessage() );
			System.exit(0);
		}
	}
	
	int lineCount = 0;
	
	private void loadCellFromLine( String line ) throws FileFormatException
	{	
		lineCount++;
		
		if( line != null && line.length() > 0 && 
			(line.toUpperCase().charAt(0) == 'D' || line.toUpperCase().charAt(0) == 'U' ))
		{
			StringTokenizer strtok = new StringTokenizer( line.substring(2), " ," );
			
			if( strtok.countTokens() == 5 )
			{
				try
				{
					int bx, by, tx, ty, value;
					
					bx = Integer.parseInt( strtok.nextToken() );
					by = Integer.parseInt( strtok.nextToken() );
					tx = Integer.parseInt( strtok.nextToken() );
					ty = Integer.parseInt( strtok.nextToken() );
					value = Integer.parseInt( strtok.nextToken() );
					
					if( (bx >= 0 && bx < B_ROW) && (by >= 0 && by < B_COL) &&
							(tx >= 0 && tx < Tile.T_ROW) && (ty >= 0 && ty < Tile.T_COL) &&
							(value >= 1 && value < 10) )
						this.setValue(bx, by, tx, ty, value, 
							(line.toUpperCase().charAt(0) == 'D') ? false : true );
					else
						throw new FileFormatException( "Invalid integer on line " + lineCount + 
								", range check violation.");
				}
				catch( NumberFormatException e )
				{
					throw new FileFormatException( "Invalid integer on line " + 
							lineCount + ", " + e.getMessage());
				}
			}
			else
				throw new FileFormatException( "Wrong number of tokens on line: " + lineCount );
		}
	}

	
	public String toString() 
	{
		StringBuffer strbuf = new StringBuffer();
		
		for( int boardRow=0; boardRow<B_ROW; boardRow++ )
		{
			for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
			{
				for( int boardCol=0; boardCol<B_COL; boardCol++ )
				{
					for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
					{
						int value = this.getValue(boardRow, boardCol, tileRow, tileCol );
						strbuf.append((value == 0)? "- " : value + " " );
					}
					strbuf.append((boardCol != 2)? "| " : " ");
				}
				strbuf.append("\n");
			}
		}
	
		return strbuf.toString();
	}

	public static void main( String args[] )
	{
		Board board = new BoardImpl();
		board.loadBoardFromFile("board.dat");
		System.out.println( board );
	}
}
