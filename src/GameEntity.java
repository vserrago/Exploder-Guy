import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public abstract class GameEntity implements Drawable
{
	private Rectangle entityBoundingBox;
	
	
	public GameEntity(int xPos, int yPos, int width, int height)
	{
		entityBoundingBox = new Rectangle(xPos,yPos,width,height);
	}
	
	public void draw (Graphics g)
	{
		g.fillRect(entityBoundingBox.x, entityBoundingBox.y, 
				entityBoundingBox.width, entityBoundingBox.height);
	}
	
	public void setPosition(int xPos, int yPos)
	{
		entityBoundingBox.setLocation(xPos, yPos);
	}
	
	public Point getCentre()
	{
		return new Point(getxPos()+getWidth()/2, getyPos()+getHeight()/2);
	}

	public int getxPos() 
	{
		return entityBoundingBox.x;
	}

	public void setxPos(int xPos) 
	{
		entityBoundingBox.x = xPos;
	}

	public int getyPos() 
	{
		return entityBoundingBox.y;
	}

	public void setyPos(int yPos) 
	{
		entityBoundingBox.y = yPos;
	}

	public int getWidth() 
	{
		return entityBoundingBox.width;
	}

	public void setWidth(int width) 
	{
		entityBoundingBox.width = width;
	}

	public int getHeight() 
	{
		return entityBoundingBox.height;
	}

	public void setHeight(int height) 
	{
		entityBoundingBox.height = height;
	}
	
	public String toString()
	{
		String str = String.format("xPos: %d, yPos: %d", getxPos(), getyPos());
		return str;
	}
}
