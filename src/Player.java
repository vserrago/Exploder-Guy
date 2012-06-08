import java.awt.Color;
import java.awt.Graphics;


public class Player extends GameEntity implements Drawable, Movable
{
	private int speed;
	private Direction direction;
		
	public Player(int xPos, int yPos, int speed, Direction direction)
	{
		this(xPos,yPos,EGPanel.GAME_ENTITY_SIZE, EGPanel.GAME_ENTITY_SIZE, speed, direction);
	}
	
	public Player(int xPos, int yPos, int width, int height, int speed, 
			Direction direction) 
	{
		super(xPos, yPos, width, height);
		this.speed = speed;
		this.direction = direction;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.GREEN);
		super.draw(g);
	}

	@Override
	public void move()
	{
		move(direction);
	}

	public void move(Direction d) 
	{
		if(isFacing(Direction.RIGHT))
			moveRight();
		else if(isFacing(Direction.LEFT))
			moveLeft();
		else if(isFacing(Direction.UP))
			moveUp();
		else if(isFacing(Direction.DOWN))
			moveDown();
	}

	@Override
	public void moveRight()
	{

	}

	@Override
	public void moveLeft() 
	{
	}

	@Override
	public void moveUp() 
	{
	}

	@Override
	public void moveDown() 
	{
	}
	
	public boolean isFacing(Direction d)
	{
		if(d == direction)
			return true;
		else 
			return false;
	}

}
