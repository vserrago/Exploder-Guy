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
	public static final int GRID_WIDTH = 30;
	public static final int GAME_ENTITY_SIZE = GRID_WIDTH;
	
	
	//Variables
	private long frameDelay;
	private long frameBegin;
	private long frameEnd;
	
	GameThread gameThread;
	GameState gameState;
	Player p1;
	
	//Lists
	Vector<Direction> keyList;
	
	//Method Temp Variables
	int keyCodeP;				//KeyPressed
	int keyCodeR;				//KeyReleased
	char keyCharT;				//Key Typed
	Direction kp;				//GameLoop: Gamestate = playing
	
	
	public EGPanel()
	{
		keyList = new Vector<Direction>();
		p1 = new Player(200,0);
		
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		
		setGameState(GameState.MAINMENU);
		setBackground(Color.black);
		
		gameThread = new GameThread();
		gameThread.start();
	}
	
	private synchronized void gameloop()
	{
		while(true)
		{
			frameBegin = System.nanoTime();		//Get current time in nanoseconds
			if (getGameState()== GameState.MAINMENU)
			{
				setGameState(GameState.PLAYING);
			}
			else if (getGameState()== GameState.PLAYING)
			{
				if(!keyList.isEmpty())
				{
					kp = keyList.firstElement();
					if(kp == Direction.RIGHT)
						p1.setxPos(p1.getxPos()+2);
					else if(kp == Direction.LEFT)
						p1.setxPos(p1.getxPos()-2);
					else if(kp == Direction.UP)
						p1.setyPos(p1.getyPos()-2);
					else if(kp == Direction.DOWN)
						p1.setyPos(p1.getyPos()+2);
//					System.out.printf("Test");
				}
//				System.out.printf("x: %d, y: %d\n", p1.getxPos(),p1.getyPos());
			}
			else if(getGameState()== GameState.PAUSED)
			{
				//Display "Paused", etc
			}
//			System.out.println("Loop");
			repaint();
		
			//Keep frames consistent
			frameEnd = System.nanoTime() - frameBegin;
			frameDelay = (10000000 - frameEnd) / 1000000;
//			System.out.printf("%d\n", frameDelay);
			
			if (frameDelay < 10)
			frameDelay = 10;

			try
			{
				Thread.sleep(frameDelay);
			} catch (InterruptedException e)
			{
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (getGameState()== GameState.MAINMENU)
		{
		}
		else if (getGameState()== GameState.PLAYING || isGameState(GameState.PAUSED))
		{
			g.fillRect(0, 0, 200, 600);
			p1.draw(g);
		}
	}

	public void mouseClicked(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void keyPressed(KeyEvent e)
	{
		keyCodeP = e.getKeyCode();
//		System.out.printf("Key: %d", keyCodeP);
//		System.out.println(e.getKeyChar());
		//Right = Right Arrow
		if(keyCodeP == KeyEvent.VK_RIGHT && !keyList.contains(Direction.RIGHT))
			keyList.add(Direction.RIGHT);
		//Left = Left Arrow
		else if(keyCodeP == KeyEvent.VK_LEFT && !keyList.contains(Direction.LEFT))
			keyList.add(Direction.LEFT);
		//Up = Up Arrow
		else if(keyCodeP == KeyEvent.VK_UP && !keyList.contains(Direction.UP))
			keyList.add(Direction.UP);
		//Down = Down Arrow
		else if(keyCodeP == KeyEvent.VK_DOWN && !keyList.contains(Direction.DOWN))
			keyList.add(Direction.DOWN);
		//Drop Bomb = Space
//		else if(keyCodeP == KeyEvent.VK_SPACE && !keyList.contains(KeyPress.PLACEBOMB))
//			keyList.add(KeyPress.PLACEBOMB);	
	}

	public void keyReleased(KeyEvent e)
	{
		keyCodeR = e.getKeyCode();
		
		//Right = Right Arrow
		if(keyCodeR == KeyEvent.VK_RIGHT)
			keyList.remove(Direction.RIGHT);
		//Left = Left Arrow
		else if(keyCodeR == KeyEvent.VK_LEFT)
			keyList.remove(Direction.LEFT);
		//Up = Up Arrow
		else if(keyCodeR == KeyEvent.VK_UP)
			keyList.remove(Direction.UP);
		//Down = Down Arrow
		else if(keyCodeR == KeyEvent.VK_DOWN)
			keyList.remove(Direction.DOWN);
		//Drop Bomb = Space
//		else if(keyCodeR == KeyEvent.VK_SPACE)
//			keyList.remove(KeyPress.PLACEBOMB);	
	}

	public void keyTyped(KeyEvent e)
	{
		keyCharT = e.getKeyChar();
		if(keyCharT == 'p')
		{
			togglePauseState();
			System.out.printf("Toggle\n");
		}
		System.out.printf("Typed\n");
			
	}
	
	private void togglePauseState()
	{
		if(getGameState() == GameState.PLAYING)
		{
			setGameState(GameState.PAUSED);
//			gameState = GameState.PAUSED;
			System.out.printf("Paused\n");
		}
		else if(getGameState() == GameState.PAUSED)
		{
			setGameState(GameState.PLAYING);
//			gameState = GameState.PLAYING;
			System.out.printf("UnPaused\n");
		}
	}
	
	public GameState getGameState()
	{
		return gameState;
	}
	
	public boolean isGameState(GameState gs)
	{
		if(gameState == gs)
			return true;
		else
			return false;
	}
	
	public void setGameState(GameState gs)
	{
		gameState = gs;
	}
	
	private class GameThread extends Thread
	{
		public void run(){gameloop();}
	}
}