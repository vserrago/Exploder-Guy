import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Bomb extends OwnableTimableEntity
{
	private static final int BOMB_WIDTH = EGPanel.GAME_ENTITY_SIZE;
	private static final int BOMB_HEIGHT = EGPanel.GAME_ENTITY_SIZE;
	private static final int BOMB_DET_DELAY = 3001;
	
	
	public Bomb(int xPos, int yPos, int bombPower, Player bombOwner)
	{
		this(xPos, yPos, BOMB_WIDTH, BOMB_HEIGHT, bombPower, bombOwner);
	}
	
	public Bomb(int xPos, int yPos, int width, int height, int bombPower, 
			Player bombOwner) 
	{
		super(xPos, yPos, width, height, BOMB_DET_DELAY, bombOwner);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		super.draw(g);
	}
	
	public void explode()
	{
		timerAction();
	}
	
	public boolean isDetonated()
	{
		return actionCompleted();
	}

	public void timerAction() 
	{
		getOwner().changeBombsDroppedAmount(1);
		super.timerAction();
	}
}
