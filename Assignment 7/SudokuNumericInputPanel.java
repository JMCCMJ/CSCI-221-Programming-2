
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class SudokuNumericInputPanel extends JPanel implements ActionListener
{
	private ButtonGroup group;
	private int selectionValue = 0;
	private ActionListener callback = null;
	
	public SudokuNumericInputPanel()
	{
		super();
		
		buildPanel();	
	}
	
	public void setActionListenerCallback( ActionListener callback )
	{
		this.callback = callback;
	}
	
	public void establishDefaultValue( int defaultValue )
	{
		selectionValue = defaultValue;
		Enumeration enumeration = group.getElements();
		
		while( enumeration.hasMoreElements() )
		{
			AbstractButton button = (AbstractButton)enumeration.nextElement();
			
			if( button.getActionCommand().equals(Integer.toString(defaultValue)))
			{
				button.setSelected(true);
				break;
			}
		}	
	}
	
	private void buildPanel()
	{
		JPanel inputPanel = new JPanel();
		
		String choices[] = {"1","2","3","4","5","6","7","8","9","Clear"};
		
		group = new ButtonGroup();
		
		for( int i=0; i<choices.length; i++ )
		{
			JCheckBox b = new JCheckBox( choices[i] );
			
			if( choices[i].equalsIgnoreCase("Clear"))
			{
				b.setSelected(true);
				b.setActionCommand("0");
			}
			else
			{
				b.setSelected(false);
				b.setActionCommand(choices[i]);
			}				
			
			b.addActionListener(this);
			
			inputPanel.add(b);
			group.add(b);
		}
		
		
		this.add(inputPanel, "Center" );
	}
	
	public void actionPerformed( ActionEvent e )
 	{		
		selectionValue = Integer.parseInt(e.getActionCommand());
		
		if( callback != null )
			callback.actionPerformed(e);
	}
	
	public int getSelectionValue()
	{
		return selectionValue;
	}
}
