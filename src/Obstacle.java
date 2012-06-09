import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends GameEntity 
{
	public Obstacle(boolean destructable)
	{
		this(0,0,destructable);
	}
	
	public Obstacle(int xPos, int yPos, boolean destructable)
	{
		this(xPos, yPos, EGPanel.GAME_ENTITY_SIZE, EGPanel.GAME_ENTITY_SIZE, destructable);
	}
	
	public Obstacle(int xPos, int yPos, int width, int height, boolean destructable) 
	{
		super(xPos, yPos, width, height);
		setDestructability(destructable);
	}
	
	public void draw(Graphics g)
	{
		if(isDestructable())
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLUE);
		super.draw(g);
	}
	
}
