import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class EGFrame extends JFrame 
{
	EGPanel egpanel;
	
	public EGFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Exploder Guy");
		setResizable(false);
		
		egpanel = new EGPanel();
		add(egpanel);
		
		this.pack();		//Adjust jFrame to fit egpanel
		
		//Centre the window in the middle of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width/2 - getWidth()/2,
				screenSize.height/5 - getHeight()/5);
		
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