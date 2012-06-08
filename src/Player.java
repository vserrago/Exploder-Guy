import java.awt.Color;
import java.awt.Graphics;


public class Player extends GameEntity implements Drawable
{
	private Direction direction;
	
	public Player()
	{
		this(0,0);
	}
	
	public Player(int xPos, int yPos)
	{
		this(xPos,yPos,EGPanel.GAME_ENTITY_SIZE, EGPanel.GAME_ENTITY_SIZE);
	}
	
	public Player(int xPos, int yPos, int width, int height) 
	{
		super(xPos, yPos, width, height);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.GREEN);
		super.draw(g);
	}

}
