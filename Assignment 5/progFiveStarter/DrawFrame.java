import javax.swing.*;
import java.io.*;

public class DrawFrame extends JFrame
{	
	public DrawFrame( String filename ) throws FileNotFoundException, IOException
	{
		this.setSize(400, 400);
		this.setTitle("Coordinate Graph");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new DrawPanel( filename );
		this.add( panel );
		
		this.setVisible(true);
	}
    
	public static void main(String args[])throws FileNotFoundException, IOException
	{
		// prompt the user to enter the filename
		BufferedReader in = new BufferedReader(
				new InputStreamReader( System.in ) );
		String filename;
	
		// prompt the user to enter the name of the file
		System.out.print( "Enter the name of the text file: " );
		filename = in.readLine();
		
		DrawFrame mainFrame = new DrawFrame( filename );
	}
}
