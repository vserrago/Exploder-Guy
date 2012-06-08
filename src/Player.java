import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


public class Player extends GameEntity implements Drawable, Movable
{
	private int speed;
	private int bombCapacity;
	private Direction direction;
	
	private Vector<Bomb> bombList;
		
	public Player(int xPos, int yPos, int speed, Direction direction)
	{
		this(xPos,yPos,EGPanel.GAME_ENTITY_SIZE, EGPanel.GAME_ENTITY_SIZE, speed, direction);
	}
	
	public Player(int xPos, int yPos, int width, int height, int speed, 
			Direction direction) 
	{
		super(xPos, yPos, width, height);
		bombCapacity = 1;		//Players start off with 1 bomb each
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
//		if(isFacing(Direction.RIGHT))
//			moveRight();
//		else if(isFacing(Direction.LEFT))
//			moveLeft();
//		else if(isFacing(Direction.UP))
//			moveUp();
//		else if(isFacing(Direction.DOWN))
//			moveDown();
		if(d == Direction.RIGHT)
			moveRight();
		else if(d == Direction.LEFT)
			moveLeft();
		else if(d == Direction.UP)
			moveUp();
		else if(d == Direction.DOWN)
			moveDown();
	}

	@Override
	public void moveRight()
	{
		direction = Direction.RIGHT;
		setxPos(getxPos() + speed);
	}

	@Override
	public void moveLeft() 
	{
		direction = Direction.LEFT;
		setxPos(getxPos() - speed);
	}

	@Override
	public void moveUp() 
	{
		direction = Direction.UP;
		setyPos(getyPos() - speed);
	}

	@Override
	public void moveDown() 
	{
		direction = Direction.DOWN;
		setyPos(getyPos() + speed);
	}
	
//	public boolean isFacing(Direction d)
//	{
//		if(d.equals(direction))
//			return true;
//		else 
//			return false;
//	}

}
