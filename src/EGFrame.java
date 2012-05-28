import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;


public class EGFrame extends JFrame 
{
	EGPanel egpanel;
	
	public EGFrame()
	{
		egpanel = new EGPanel();
		setSize(EGPanel.WIDTH, EGPanel.HEIGHT);
		add(egpanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		EGFrame EG = new EGFrame();
	}

}
