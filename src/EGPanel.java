import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

public class EGPanel extends JPanel implements KeyListener, MouseListener
{
	//Constants
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	//Variables
	GameThread gameThread;
	GameState gameState;
	
	public EGPanel()
	{
		addKeyListener(this);
		addMouseListener(this);
		
		setGameState(GameState.MAINMENU);
		setBackground(Color.black);
		
		gameThread = new GameThread();
		gameThread.start();
	}
	
	private synchronized void gameloop()
	{
		if (getGameState()== GameState.MAINMENU)
		{
			
		}
		else if (getGameState()== GameState.PLAYING)
		{
			
		}
		else if(getGameState()== GameState.PAUSED)
		{
			
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	public void mouseClicked(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void keyPressed(KeyEvent arg0){}

	public void keyReleased(KeyEvent arg0){}

	public void keyTyped(KeyEvent arg0){}
	
	public synchronized GameState getGameState()
	{
		return gameState;
	}
	
	public synchronized void setGameState(GameState gs)
	{
		gameState = gs;
	}
	
	private class GameThread extends Thread
	{
		public void run(){gameloop();}
	}
}