import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class EGFrame extends JFrame 
{
	EGPanel egpanel;
	
	public EGFrame()
	{
		setSize(EGPanel.WIDTH, EGPanel.HEIGHT);
		
		//Centre the window in the middle of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width/2 - EGPanel.WIDTH/2,
				screenSize.height/2 - EGPanel.HEIGHT/2);
		
		egpanel = new EGPanel();
		add(egpanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Exploder Guy");
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
