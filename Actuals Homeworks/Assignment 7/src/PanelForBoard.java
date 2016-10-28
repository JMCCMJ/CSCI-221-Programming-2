
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PanelForBoard extends JPanel
{
	private LineBasedGraphic graphic;
	private ViewModel viewModel;
	private Board model;
	
	public PanelForBoard( String filename, Board model, SudokuNumericInputPanel inputPanel ) throws FileNotFoundException, IOException
	{
		graphic = new LineBasedGraphicImpl();
		this.model = model;
		graphic.getPolylinesFromFile(filename);
		viewModel = new ViewModelImpl(this, model, inputPanel );
		this.addMouseListener(viewModel);
	}
	
	public void paintComponent(Graphics g)
    {
		Rectangle panelRect = this.getBounds();
		panelRect.x = 0;
		panelRect.y = 0;
		
		viewModel.drawBoard(g, graphic.getPolylineByIndex(0).getPolyline(), panelRect );
		
		viewModel.drawModel(g, model );
    }

}
