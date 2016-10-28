
import javax.swing.*;
import java.io.*;

public class MainWindowFrame extends JFrame
{	
	public MainWindowFrame( String filename, String gameBoard ) throws FileNotFoundException, IOException
	{
		this.setSize(410, 485);
		this.setTitle("Sudoku");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		Board model = new BoardImpl();
		model.initalizeBoard(false);
		
		SudokuNumericInputPanel inputPanel = new SudokuNumericInputPanel();
		this.add(inputPanel, "North");
		
		JPanel boardPanel = new PanelForBoard( filename, model, inputPanel );
		this.add( boardPanel, "Center" );
		
		JPanel buttonPanel = new PanelForControlButtons( model, boardPanel );
		this.add(buttonPanel, "South");
		
		this.setVisible(true);
	}
    
	public static void main(String args[])throws FileNotFoundException, IOException
	{
		MainWindowFrame mainFrame = new MainWindowFrame( "tile.dat", null );
	}
}
