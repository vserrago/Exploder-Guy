import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class GameEntity implements Drawable
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Rectangle hitBox;
	
	
	public GameEntity(int xPos, int yPos, int width, int height)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		hitBox = new Rectangle(xPos,yPos,width,height);
	}
	
	public void draw (Graphics g)
	{
//		Graphics2D g2 = (Graphics2D) g;
		g.fillRect(xPos, yPos, width, height);
	}

	public int getxPos() 
	{
		return xPos;
	}

	public void setxPos(int xPos) 
	{
		this.xPos = xPos;
	}

	public int getyPos() 
	{
		return yPos;
	}

	public void setyPos(int yPos) 
	{
		this.yPos = yPos;
	}

	public int getWidth() 
	{
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}

	public int getHeight() 
	{
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}
	
	public String toString()
	{
		String str = String.format("xPos: %d, yPos: %d", getxPos(), getyPos());
		return str;
	}
}
