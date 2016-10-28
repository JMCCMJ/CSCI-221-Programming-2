import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public abstract class ViewModel extends MouseAdapter
{
	protected Rectangle2D boundingBoxes[][];
	protected Board board;
	protected Component component;
	protected SudokuNumericInputPanel inputPanel;

	
	public ViewModel(Component component, Board board, SudokuNumericInputPanel inputPanel )
	{
		boundingBoxes = new Rectangle2D[3][3];
		this.component = component;
		this.board = board;
		this.inputPanel = inputPanel;
	}
	
	protected void drawBoard( Graphics g, GeneralPath tile, Rectangle panel )
	{
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform currentTransform = g2.getTransform();
	
		Rectangle2D boundingRect;
		
		// for the purposes of this program, there is only one Polyline graphic
		// that matters; it is found in the zeroth location.
		boundingRect = tile.getBounds2D();
				
		g2.setColor(Color.white);
		g2.fillRect((int)panel.getX(), (int)panel.getY(), (int)panel.getWidth(), (int)panel.getHeight());
		
		int tileWidth = (int)boundingRect.getWidth();
		int tileHeight = (int)boundingRect.getHeight();
		
		// center the board vertically
		int top = (int)panel.getY() + ((int)panel.getHeight()/2 - (tileHeight/2)*Board.B_ROW);
		
		// center the board horizontally
		int left = (int)panel.getX() + ((int)panel.getWidth()/2 - (tileWidth/2)*Board.B_COL);
			
	 	// loop through the path array and draw each element
	 	for( int row=0; row < Board.B_ROW; row++ )
	 		for( int col=0; col < Board.B_COL; col++ )
	 	{
	 		int l = left+(col*tileWidth)+(col*1);
	 		int t = top+(row*tileHeight)+(row*1);
	 		boundingBoxes[row][col] = new Rectangle(l, t, tileWidth, tileHeight );
	
	 		g2.setTransform(currentTransform);
	 		g2.translate( l, t );
	 		g2.setColor( Color.GRAY );
	  		g2.draw( tile );
	 	}
	 	
	 	// restore the base transformation
	 	g2.setTransform( currentTransform );
	}

	public void mouseClicked( MouseEvent e )
	{
		// stub to override in ViewModelImpl
		;
	}
	
	// note: the array is used to pass row and col back from the method
	protected abstract int[] determineTileFromClick( Point point );
	
	protected abstract int[] determineCellFromClick( Point point, int row, int col );
	
	protected abstract Rectangle2D determineBoundingBoxOfCell( int bRow, int bCol, int tRow, int tCol );
	
	protected abstract void drawModel( Graphics g, Board model );
}
