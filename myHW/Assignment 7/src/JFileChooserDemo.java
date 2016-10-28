import javax.swing.*;
import java.io.*;

public class JFileChooserDemo {
	
	public String getOpenDialogFilename()
	{
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setCurrentDirectory(new File("c:/Workspace3.2/Prog Seven"));
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int result = fileChooser.showOpenDialog( null );
		
		if( result == JFileChooser.CANCEL_OPTION )
			return null;
		else
		{
			File f = fileChooser.getSelectedFile();
			return f.getAbsolutePath();
		}
	}
	
	public String getSaveDialogFilename()
	{
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
		fileChooser.setCurrentDirectory(new File("c:/Workspace3.2/Prog Seven"));
		
		int result = fileChooser.showSaveDialog(null);
		
		if( result == JFileChooser.CANCEL_OPTION )
			return null;
		else
		{
			File f = fileChooser.getSelectedFile();
			return f.getAbsolutePath();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		// pg 484 and 485 in our text describe the API for JFileChooser
		JFileChooserDemo demo = new JFileChooserDemo();
		
		System.out.println( demo.getOpenDialogFilename());
		
		// pg 455 starts a description of option dialogs
		// the following code is explained on pg 457
		int selection = JOptionPane.showConfirmDialog(null,
				"Do you wish to show the Save File Dialog?",
				"", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if( selection == JOptionPane.YES_OPTION)
			System.out.println( demo.getSaveDialogFilename());
	}

}
