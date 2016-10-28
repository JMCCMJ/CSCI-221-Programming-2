import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;

public class ViewModelImpl extends ViewModel implements ActionListener
{
	// boardRowCol and cellRowCol hold the coordinates for the currently selected cell
	private int boardRowCol[];
	private int cellRowCol[];
	private int currentValue;

	public ViewModelImpl(Component component, Board model, SudokuNumericInputPanel inputPanel )
	{
		super( component, model, inputPanel );
		inputPanel.setActionListenerCallback(this);
	}
	
	public void actionPerformed(ActionEvent e )
	{
		int coords[] = board.getSelectedCoordinates();
		
		if( coords[0] >= 0 && coords[1] >= 0 && coords[2] >= 0 && coords[3] >= 0 )
		{		
			currentValue = board.getValue( coords[0], coords[1], coords[2], coords[3] );
			
			if( inputPanel.getSelectionValue() != currentValue )
			{
				currentValue = inputPanel.getSelectionValue();
				
				board.setValue( coords[0], coords[1], coords[2], coords[3], 
					currentValue, true );
			
				// need to force a refresh of the panel
				component.repaint(0, 0, component.getWidth(), component.getHeight());
			}
		}
	}
	
	public void mouseClicked( MouseEvent e )
	{		
		Point point = e.getPoint();
		
		boardRowCol = determineTileFromClick( point );
		cellRowCol = determineCellFromClick( point, boardRowCol[0], boardRowCol[1] );
		
		if( (boardRowCol[0] >= 0 && boardRowCol[1] >= 0) && (cellRowCol[0] >= 0 && cellRowCol[1] >= 0) )
		{
			if( board.isModifiable(boardRowCol[0], boardRowCol[1], cellRowCol[0], cellRowCol[1]))
			{
				currentValue = board.getValue( boardRowCol[0], boardRowCol[1], cellRowCol[0], cellRowCol[1] );
				inputPanel.establishDefaultValue( currentValue );

				// show the user which cell they selected
				board.setSelected(boardRowCol[0], boardRowCol[1], cellRowCol[0], cellRowCol[1]);
				
				component.repaint( 0, 0, component.getWidth(), component.getHeight());			
			}
		}
	}

	// note: the array is used to pass row and col back from the method
	protected int[] determineTileFromClick( Point point )
	{
		int [] rtnval = new int[2];
		int row, col;
		int x=-1, y=-1;
		
		boolean found=false;
		
		// figure out which tile the click was in
		for( row=0; row<Board.B_ROW && !found; row++ )
		{
			for( col=0; col<Board.B_COL && !found; col++ )
			{
				if( boundingBoxes[row][col].contains(point) )
				{
					x = col;
					y = row;
					found = true;
				}
			}
		}
		
		rtnval[0] = y;
		rtnval[1] = x;
		
		return rtnval;
	}
	
	protected int[] determineCellFromClick( Point point, int row, int col )
	{
		int [] rtnval = new int [2];
		int x=-1, y=-1;
		
		if((row >= 0 && row < Board.B_ROW)&&(col >=0 && col < Board.B_COL))
		{
			int cellWidth = (int)boundingBoxes[row][col].getWidth()/Tile.T_COL;
			int deltaX = point.x - (int)boundingBoxes[row][col].getX();
			x = deltaX / cellWidth;
			
			int cellHeight = (int)boundingBoxes[row][col].getHeight()/Tile.T_ROW;	
			int deltaY = point.y - (int)boundingBoxes[row][col].getY();
			y = deltaY / cellHeight;
		}
		
		rtnval[0] = y;
		rtnval[1] = x;

		return rtnval;
	}
	
	protected Rectangle2D determineBoundingBoxOfCell( int bRow, int bCol, int tRow, int tCol )
	{
		Rectangle2D rtnval = new Rectangle();
		
		Rectangle2D tileBoundingBox = boundingBoxes[bRow][bCol];
		int cellWidth = (int)boundingBoxes[bRow][tRow].getWidth()/Tile.T_COL;
		int cellHeight = (int)boundingBoxes[bRow][tCol].getHeight()/Tile.T_ROW;
		
		rtnval.setRect(tileBoundingBox.getX()+(tCol * cellWidth),
				tileBoundingBox.getY()+(tRow * cellHeight), cellWidth, cellHeight);
		
		return rtnval;
	}

	protected void drawModel( Graphics g, Board model )
	{
		Graphics2D g2 = (Graphics2D)g;
		
		for( int boardRow=0; boardRow<Board.B_ROW; boardRow++ )
		{
			for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
			{
				for( int boardCol=0; boardCol<Board.B_COL; boardCol++ )
				{
					for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
					{
						int value = model.getValue(boardRow, boardCol, tileRow, tileCol );
						boolean modifiable = model.isModifiable(boardRow, boardCol, tileRow, tileCol );

						Rectangle2D cellBoundingBox = 
							determineBoundingBoxOfCell( boardRow, boardCol, tileRow, tileCol );
						
						if( model.isSelected( boardRow, boardCol, tileRow, tileCol ))
						{
							g2.setColor( Color.RED );
							float [] dash = { 4, 4, 12, 4 };
							g2.setStroke(new BasicStroke( 3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, dash, 0));
							
							g2.drawRect((int)cellBoundingBox.getX(), (int)cellBoundingBox.getY(), 
									(int)cellBoundingBox.getWidth(), (int)cellBoundingBox.getHeight() );
						}
						
						if( value != 0 )
							drawDigitInCell( g2, cellBoundingBox, modifiable, value );
					}
				}
			}
		}		
	}
	
	// the modifiable flag indicates whether the cell's value is modifiable
	private void drawDigitInCell( Graphics2D g2, Rectangle2D cellBoundingBox, boolean modifiable, int value )
	{
		// default font characteristics
		String fontName = "Courier";
		int fontStyle = Font.BOLD;
		int fontHeight = 20;
	
		Font font = new Font( fontName, fontStyle, fontHeight );
		double x, y;
	
		// set the font
		g2.setFont( font );
		
		// set the drawing color
		g2.setColor( (modifiable) ? Color.BLACK : Color.GRAY );
		
		// see page 275 in your text for centering details
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds( Integer.toString(value), context );
	
		// setup x & y for text drawing
		x = cellBoundingBox.getX() + (cellBoundingBox.getWidth() - bounds.getWidth()) / 2;
	        
		// center the text in the box - accounting for the baseline
		y = cellBoundingBox.getY() + (cellBoundingBox.getHeight() - bounds.getHeight()) / 2; 
		
		double ascent = -bounds.getY();
		double baseY = y + ascent;
			
		// call drawString to draw the text to the window
	    g2.drawString( Integer.toString(value), (int)x, (int)baseY );
	}
}
