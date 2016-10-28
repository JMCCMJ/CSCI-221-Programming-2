import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DrawPanel extends JPanel
{
	private LineBasedGraphic graphic;
	
	public DrawPanel( String filename ) throws FileNotFoundException, IOException
	{
		graphic = new LineBasedGraphicImpl();
		graphic.getPolylinesFromFile(filename);
		
		
		
		
	}
	
	public void paintComponent(Graphics g)
    {
		Graphics2D g2 = (Graphics2D)g;
//		g2.drawLine(0, 0, 100, 100);
//		g.drawLine(0,10,500,500);
		
		AffineTransform currentTransform = g2.getTransform();
		
    	g2.translate( this.getWidth() / 2, this.getHeight() / 2 );
		g2.scale( 4, 4 );
		
		// flip the Y-Axis so that positive values are above the X-axis
    	AffineTransform at = new AffineTransform( 1, 0, 0, -1, 0, 0 );
		g2.transform( at );
	
	 	// loop through the path array and draw each element
	 	for( int i=0; i<graphic.getNumPolylines(); i++ )
	 	{
	 		Color penColor = graphic.getPolylineByIndex(i).color;
	 		if( penColor != null )
	 			g2.setColor( penColor );
	 		else // use the default color of black
	 			g2.setColor( Color.BLACK );
	 		//System.out.println(graphic.getPolylineByIndex(i).polyline);
	  		g2.draw( graphic.getPolylineByIndex(i).polyline );
	 	}
	 	
	 	// restore the base transformation
	 	g2.setTransform( currentTransform );
    }

}
