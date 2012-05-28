import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;


public class EGFrame extends JFrame 
{

	public EGFrame()
	{
		setBounds(200, 50, 800, 600);
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
