import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class PanelForControlButtons extends JPanel implements ActionListener
{
	JButton open, save, reset, undo, exit;
	Board model;
	Component component;

	PanelForControlButtons( Board model, Component component )
	{
		super();
		
		this.model = model;
		this.component = component;
	
		this.setLayout(new FlowLayout());
		open = new JButton( "Open" );
		save = new JButton( "Save" );
		reset = new JButton( "Reset" );
		undo = new JButton( "Undo" );
		exit = new JButton( "Exit" );
		
		open.addActionListener( this );
		save.addActionListener( this );
		reset.addActionListener( this );
		undo.addActionListener( this );
		exit.addActionListener( this );

		this.add( open );
		this.add( save );
		this.add( reset );
		this.add( undo );
		this.add( exit );
	}

	public void actionPerformed( ActionEvent e )
 	{
		// obtain the source of the event and toggle the value
		if( e.getSource() == open )
		{
			openHandler();
		}
		else if( e.getSource() == save )
		{
			saveHandler();
		}
		else if( e.getSource() == reset )
		{
			resetHandler();
		}
		else if( e.getSource() == undo )
		{
			undoHandler();
		}
		else if( e.getSource() == exit )
		{
			exitHandler();
		}
	}
	

	private void openHandler()
	{
		boolean perform = false;
		
		if( model.moves.size() != 0 )
		{
			int selection = JOptionPane.showConfirmDialog(null,
					"If you open a new file, you will loose your unsaved changes." +
					"\nDo you wish to continue?",
					"Warning Message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if( selection == JOptionPane.OK_OPTION)
				perform = true;
		}
		else
			perform = true;
		
		if( perform )
		{
			String filename;
			
			JFileChooserDemo demo = new JFileChooserDemo();
			
			filename = demo.getOpenDialogFilename();
			
			if( filename != null )
			{
				model.initalizeBoard(true);
				model.loadBoardFromFile(filename);
				model.moves = new Stack();
			}
			
			component.repaint();
		}
	}
	
	private void saveHandler()
	{
		String filename;
		boolean perform = false;
		
		JFileChooserDemo demo = new JFileChooserDemo();
		
		filename = demo.getSaveDialogFilename();
		
		if( filename != null )
		{		
			if( new File(filename).exists() )
			{
				int selection = JOptionPane.showConfirmDialog(null,
						"You selected an existing file, it will be overwritten." +
						"\nDo you wish to continue?",
						"Warning Message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if( selection == JOptionPane.OK_OPTION)
					perform = true;
			}
			else 
				perform = true;

			if( perform )
			{
				try
				{
					BufferedWriter buf = new BufferedWriter( new FileWriter( filename) );
					for( int boardRow=0; boardRow<Board.B_ROW; boardRow++ )
					{
						for( int boardCol=0; boardCol<Board.B_COL; boardCol++ )
						{
							for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
							{
								for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
								{
									int value = model.getValue( boardRow, boardCol, tileRow, tileCol );
									
									if( value != 0 )
									{
										StringBuffer strbuf = new StringBuffer();
										
										strbuf.append("? ");
										strbuf.append(boardRow + ", ");
										strbuf.append(boardCol + ", ");
										strbuf.append(tileRow + ", ");
										strbuf.append(tileCol + ", ");
										strbuf.append(value + "\n");
										
										String line = strbuf.toString();
									
										if( model.isModifiable(boardRow, boardCol, tileRow, tileCol ) )
											line = line.replace('?', 'u');
										else
											line = line.replace('?', 'd');
											
										buf.write(line);
										buf.flush();
									}
								}
							}
						}	
					}
					
					buf.close();
					// clear the moves stack
					model.moves = new Stack();
				}
				catch( Exception e )
				{
					
				}
			}
		}		
	}
	
	private void resetHandler()
	{
		int selection = JOptionPane.showConfirmDialog(null,
				"Reset restores the board to its initial state." +
				"\nDo you wish to continue?",
				"Warning Message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if( selection == JOptionPane.OK_OPTION)
		{
			for( int boardRow=0; boardRow<Board.B_ROW; boardRow++ )
			{
				for( int boardCol=0; boardCol<Board.B_COL; boardCol++ )
				{
					for( int tileRow=0; tileRow<Tile.T_ROW; tileRow++ )
					{
						for( int tileCol=0; tileCol<Tile.T_COL; tileCol++ )
						{
							if( model.isModifiable(boardRow, boardCol, tileRow, tileCol ) )
								model.setValue( boardRow, boardCol, tileRow, tileCol, 0, true );
								
						}
					}
				}
			}
		}
		
		model.clearSelected();
		model.moves = new Stack();
		
		component.repaint();
	}

	private void undoHandler()
	{
		String undo = (String)model.moves.pop();
		
		if( undo != null )
		{
			int bx, by, tx, ty, value;
			bx=by=tx=ty=value = 0;
			
			StringTokenizer strtok = new StringTokenizer( undo, " :,");
			
			while( strtok.hasMoreTokens() )
			{
				String id = strtok.nextToken();
				
				if( id.equals("bx"))
					bx = Integer.parseInt( strtok.nextToken() );
				else if( id.equals("by"))
					by = Integer.parseInt( strtok.nextToken() );
				else if( id.equals("tx"))
					tx = Integer.parseInt( strtok.nextToken() );
				else if( id.equals("ty"))
					ty = Integer.parseInt( strtok.nextToken() );
				else if( id.equals("oldValue"))
					value = Integer.parseInt( strtok.nextToken() );
			}
			
			// show the user which cell which has been undone
			model.setSelected(bx, by, tx, ty);
			model.setValue(bx, by, tx, ty, value, true);
			model.moves.pop();
		
			component.repaint();
		}
	}
	
	private void exitHandler()
	{
		int selection = JOptionPane.showConfirmDialog(null,
				(model.moves.size() > 0) 
						? "If you exit now, you will loose all changes since your last save."
						+ "\nAre you sure you want to Exit?"
						: "Are you sure you want to Exit?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if( selection == JOptionPane.YES_OPTION)
			System.exit( 0 );
	}
}
