import java.awt.Graphics;


public class Player extends GameEntity implements Drawable
{
	public static final int PLAYER_WIDTH = 10;
	public static final int PLAYER_HEIGHT = 10;
	private Direction direction;
	
	public Player(int xPos, int yPos, int width, int height) 
	{
		super(xPos, yPos, width, height);
	}
	
	public void draw(Graphics g)
	{
		super.draw(g);
	}

}
