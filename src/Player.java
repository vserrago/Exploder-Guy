import java.awt.Color;
import java.awt.Graphics;


public class Player extends GameEntity implements Drawable, Movable
{
	private static final int PLAYER_SIZE = EGPanel.GAME_ENTITY_SIZE;
	private static final int DEFAULTBOMBCAPACITY = 1;
	private static final int DEFAULTBOMBPOWER = 1;
	
	private int speed;
	private int bombCapacity;	//How many bombs a player can drop at a time
	private int bombPower;		//The bomb's explosive capability in grid units
	private int bombsDropped;
	private Direction direction;
	
	public Player(int xPos, int yPos, int speed, Direction direction)
	{
		this(xPos,yPos, PLAYER_SIZE, PLAYER_SIZE, speed, direction);
	}
	
	public Player(int xPos, int yPos, int width, int height, int speed, 
			Direction direction) 
	{
		super(xPos, yPos, width, height);
		bombCapacity = 100; //DEFAULTBOMBCAPACITY;
		bombPower = DEFAULTBOMBPOWER;
		bombsDropped = 0;
		this.speed = speed;
		this.direction = direction;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.GREEN);
		super.draw(g);
	}
	
	public void changeBombsDroppedAmount(int delta)
	{
		bombsDropped += delta;
	}
	
	public boolean canDropBomb()
	{
		return (bombsDropped<bombCapacity);
	}
	
	public Bomb dropBomb()
	{
		bombsDropped++;
		return new Bomb(getxPos(), getyPos(), bombPower, this);
	}

	@Override
	public void move()
	{
		move(direction);
	}

	public void move(Direction d) 
	{
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
