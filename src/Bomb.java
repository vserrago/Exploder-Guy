import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Bomb extends GameEntity 
{
	private static final int BOMB_WIDTH = EGPanel.GAME_ENTITY_SIZE;
	private static final int BOMB_HEIGHT = EGPanel.GAME_ENTITY_SIZE;
	private static final int BOMB_DET_DELAY = 3001;
	
	private int bombPower;
	private Player bombOwner;
	private Timer detinationTimer;
	private boolean detonated;
	
	public Bomb(int xPos, int yPos, int bombPower, Player bombOwner)
	{
		this(xPos, yPos, BOMB_WIDTH, BOMB_HEIGHT, bombPower, bombOwner);
	}
	
	public Bomb(int xPos, int yPos, int width, int height, int bombPower, 
			Player bombOwner) 
	{
		super(xPos, yPos, width, height);
		this.bombPower = bombPower;
		this.bombOwner = bombOwner;
		
		detonated = false;
		
		ActionListener exploder = new ActionListener() 
			{public void actionPerformed(ActionEvent arg0) {explode();}};
		detinationTimer = new Timer(BOMB_DET_DELAY, exploder);
		detinationTimer.setRepeats(false);
		
		detinationTimer.start();
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		super.draw(g);
	}
	
	public void explode()
	{
		bombOwner.changeBombsDroppedAmount(1);
		detonated = true;
	}
	
	public boolean isDetonated()
	{
		return detonated;
	}
}
