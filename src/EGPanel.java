import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

public class EGPanel extends JPanel implements KeyListener, MouseListener
{
	//Constants
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int XOFFSET = 200;
	public static final int YOFFSET = 0;
	public static final int GAME_SIZE = 600;
	public static final int GRID_WIDTH = 40;
	public static final int GAME_ENTITY_SIZE = GRID_WIDTH;
	public static final int PLAYER_SPEED = 2;
	public static final int RAND_ROOF = 100;
	public static final int RAND_THRESH = 50;
	public static final int BOMB_DELAY = 3001;
	public static final int EXPLOSION_DELAY = 1501;
	public static final boolean DESTRUCTABLE = true;
	public static final boolean NOT_DESTRUCT = false;
	
	
	//Variables
	private long frameDelay;
	private long frameBegin;
	private long frameEnd;
	
	GameThread gameThread;
	GameState gameState;
	GameGrid gameGrid;
	Player p1;
	
	Random random;
	
	//Lists
	Vector<Direction> keyList;
	Vector<Bomb> deadBombList;
	Vector<Explosion> explosionList;
	
	//Method Temp Variables		//Method Name
	int keyCodeP;				//KeyPressed
	int keyCodeR;				//KeyReleased
	int keyCodeT;				//KeyTyped
	char keyCharT;				//"  "
	Direction kp;				//GameLoop: Gamestate = playing
	Bomb currBomb;				//"   "
	int gridSize;				//constructor
	int rand;					//GenerateObstacles
	
	
	public EGPanel()
	{
		
		gridSize = GAME_SIZE/GAME_ENTITY_SIZE;
		gameGrid = new GameGrid(gridSize,gridSize,XOFFSET,YOFFSET,GAME_ENTITY_SIZE,GAME_ENTITY_SIZE);
		
		keyList = new Vector<Direction>();
		deadBombList = new Vector<Bomb>(20);
		Bomb.setBombList(deadBombList);
		explosionList = new Vector<Explosion>(100);
		Explosion.setExplosionList(explosionList);
		p1 = new Player(XOFFSET+GAME_ENTITY_SIZE,GAME_ENTITY_SIZE, PLAYER_SPEED, Direction.RIGHT);
		
		random = new Random();
		
		generateObstacles();
		
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		
		setSize(EGPanel.WIDTH, EGPanel.HEIGHT);
		setPreferredSize(new Dimension(EGPanel.WIDTH, EGPanel.HEIGHT));
		setGameState(GameState.MAINMENU);
		setBackground(Color.black);
		
		gameThread = new GameThread();
		gameThread.start();
	}
	
	private void generateObstacles()
	{	
		//Clear the previous grid if any
		gameGrid.clearGrid();		
		
		//Generate border obstacles
		for(int i=0;i<gridSize;i++)//Columns
		{
			for(int j=0;j<gridSize;j++)//Rows
			{
				//The left and right borders
				if(i==0 || i==gridSize-1)
				{
//					gameGrid.addToGrid(i, j, new Obstacle(i*GAME_ENTITY_SIZE 
//							+ XOFFSET, j*GAME_ENTITY_SIZE, NOT_DESTRUCT));
					gameGrid.addToGrid(i, j, new Obstacle(NOT_DESTRUCT));
				}
				//The top and bottom borders
				else if(j==0 || j==gridSize-1)
				{
					gameGrid.addToGrid(i, j, new Obstacle(NOT_DESTRUCT));
				}
				//The indestructable obstacles in the game area
				else if((i&1)==0 && (j&1)==0)
				{
					gameGrid.addToGrid(i, j, new Obstacle(NOT_DESTRUCT));
				}
				//The guaranteed-to-spawn destructable obstacles in
				else if((i==1 && (j==3 || j==gridSize-4)) 			||
						(i==3 && (j==1 || j==gridSize-2)) 			||
						(i==gridSize-4 && (j==1 || j==gridSize-2)) 	||
						(i==gridSize-2 && (j==3 || j==gridSize-4)))
				{
					gameGrid.addToGrid(i, j, new Obstacle(DESTRUCTABLE));
				}
				//The areas guaranteed to be free of destructable obstacles
				else if((i==1 && (j==1 || j==2 || j==gridSize-3 || j==gridSize-2))	||
						(i==2 && (j==1 || j==gridSize-2)) 							||
						(i==gridSize-3 && (j==1 || j==gridSize-2)) 					||
						(i==gridSize-2 && (j==1 || j==2 || j==gridSize-3 || j==gridSize-2)))
				{
					//Do nothing; we want nothing here
				}
				else
				{
					rand = random.nextInt(RAND_ROOF);
					if(rand<RAND_THRESH)
					{
						gameGrid.addToGrid(i, j, new Obstacle(DESTRUCTABLE));
					}
				}
			}
		}
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
					kp = keyList.lastElement();
					p1.move(kp);
				}
				for(int i=0; i<deadBombList.size();i++)//Won't execute if size=0
				{
					currBomb = deadBombList.get(i);
					bombExplosion(currBomb);
					deadBombList.remove(i);
					i--;
				}
				for(int i=0; i<explosionList.size();i++)//Won't execute if size=0
				{
					{
						gameGrid.removeFromGrid(explosionList.get(i));
						explosionList.remove(i);
						i--;
					}
				}
//				System.out.println(gameGrid.getGridCoordinates(p1));
//				System.out.printf("x: %d, y: %d\n", p1.getxPos(),p1.getyPos());
			}
			else if(getGameState()== GameState.PAUSED)
			{
				//Display "Paused", etc
				//TODO make all timers pause while paused
			}
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
			} 
			catch (InterruptedException e) {}
		}
	}
	
	public void bombExplosion(Bomb b)
	{
		int power = b.getBombPower();
		Point bombPoint = gameGrid.getGridCoordinates(b);
		Point mp = new Point();
		GameEntity g;
		Direction d = Direction.RIGHT;
		int i= 0;
		int j = 0;
		
		//Center Spot
		gameGrid.addToGrid(bombPoint, new Explosion(b.getOwner()));
		
		for(int a=0; a<4; a++)
		{
			i=0; 
			j=0;
			
			while(Math.abs(i)<=Math.abs(power) && Math.abs(j)<=Math.abs(power))
			{
				switch(a)
				{
				case 0: i++; break;		//Right
				case 1: i--; break;		//Left
				case 2: j--; break;		//Up
				case 3: j++; break;		//Down
				}
				
				mp.setLocation(bombPoint.x+i, bombPoint.y+j);
				g = gameGrid.getGameEntityAt(mp);
				if(g==null)
				{
					gameGrid.addToGrid(mp, new Explosion(b.getOwner()));
				}
				else if(g.isDestructable())
				{
					gameGrid.addToGrid(mp, new Explosion(b.getOwner()));
					break;
				}
				else
					break;
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		//Objects drawn later in paintComponent will be drawn over those
		//drawn before them
		super.paintComponent(g);
		if (getGameState()== GameState.MAINMENU)
		{
		}
		else if (getGameState()== GameState.PLAYING || isGameState(GameState.PAUSED))
		{
			g.fillRect(0, 0, 200, 600);
			//Draw Obstacles
			gameGrid.drawComponents(g);
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
	}

	public void keyTyped(KeyEvent e)
	{
		keyCharT = e.getKeyChar();
		keyCodeT = e.getKeyCode();
		if(keyCharT == 'p')
		{
			togglePauseState();
		}
		//Drop Bomb = Space
//		else if(keyCodeT == KeyEvent.VK_SPACE)
		else if(keyCharT == ' ')
		{
			dropBomb(p1);
		}
	}
	
	public void dropBomb(Player p)
	{
//		System.out.println(p.canDropBomb());
		Point pp = gameGrid.getGridCoordinates(p);
		if(p.canDropBomb() && gameGrid.locationIsEmpty(pp.x,pp.y))
		{
			gameGrid.addToNearestGridLocation(p.dropBomb());
//			System.out.println("BombDropped!");
		}
	}
	
	private void togglePauseState()
	{
		if(getGameState() == GameState.PLAYING)
		{
			setGameState(GameState.PAUSED);
			System.out.printf("Paused\n");
		}
		else if(getGameState() == GameState.PAUSED)
		{
			setGameState(GameState.PLAYING);
			System.out.printf("UnPaused\n");
		}
	}
	
	public GameState getGameState()
	{
		return gameState;
	}
	
	public boolean isGameState(GameState gs)
	{
		return (gameState == gs);
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