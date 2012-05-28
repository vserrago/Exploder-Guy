import java.awt.Graphics;


public class Player extends GameEntity implements Drawable
{
	public static final int PLAYER_WIDTH = 50;
	public static final int PLAYER_HEIGHT = 50;
	private Direction direction;
	
	public Player()
	{
		this(0,0);
	}
	
	public Player(int xPos, int yPos)
	{
		this(xPos,yPos,PLAYER_WIDTH,PLAYER_HEIGHT);
	}
	
	public Player(int xPos, int yPos, int width, int height) 
	{
		super(xPos, yPos, width, height);
	}
	
	public void draw(Graphics g)
	{
		super.draw(g);
	}

}
