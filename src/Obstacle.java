import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends GameEntity 
{
	private boolean destructable;
	
	public Obstacle(int xPos, int yPos, boolean destructable)
	{
		this(xPos, yPos, EGPanel.GAME_ENTITY_SIZE, EGPanel.GAME_ENTITY_SIZE, destructable);
	}
	
	public Obstacle(int xPos, int yPos, int width, int height, boolean destructable) 
	{
		super(xPos, yPos, width, height);
		this.destructable = destructable;
	}
	
	public void draw(Graphics g)
	{
		if(destructable)
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLUE);
		super.draw(g);
	}
	
}
