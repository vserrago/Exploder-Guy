import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class EGPanel extends JPanel implements KeyListener, MouseListener
{
	//Constants
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	//Variables
	GameThread gameThread;
	
	public EGPanel()
	{
		addKeyListener(this);
		addMouseListener(this);
		gameThread = new GameThread();
		gameThread.start();
	}
	
	private synchronized void gameloop()
	{
		
	}

	public void mouseClicked(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void keyPressed(KeyEvent arg0){}

	public void keyReleased(KeyEvent arg0){}

	public void keyTyped(KeyEvent arg0){}
	
	private class GameThread extends Thread
	{
		public void run(){gameloop();}
	}
}