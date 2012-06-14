import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;


public class GameGrid 
{
	private int xOffset;			//How far to the right and down the grid is
	private int yOffset;			//from the left and top borders of the panel
	private int gridUnitWidth;		//width of a grid unit in pixels
	private int gridUnitHeight;
	private GameEntity [][] grid;
	
	public GameGrid(int xElements, int yElements, int xOffset, int yOffset, 
			int unitWidth, int unitHeight)
	{
		grid = new GameEntity[xElements][yElements];
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		gridUnitWidth = unitWidth;
		gridUnitHeight = unitHeight;
	}
	
	public void addToGrid(Point p, GameEntity g)
	{
		addToGrid(p.x, p.y, g);
	}
	
	public void addToGrid(int x, int y, GameEntity g)
	{
		g.setPosition(x*gridUnitWidth + xOffset, y*gridUnitHeight + yOffset);
		grid [x][y] = g;
	}
	
	public boolean isLocatedInGrid(Point p)
	{
		return(0<=p.x && p.x<grid.length && 0<=p.y && p.y<grid[0].length);
	}
	
	public void setToNearestGridLocation(GameEntity g)
	{
		Point p = getGridCoordinates(g);
		g.setPosition(p.x*gridUnitWidth + xOffset, p.y*gridUnitHeight 
				+ yOffset);
	}
	
	public void addToNearestGridLocation(GameEntity g)
	{
//		setToNearestGridLocation(g);
		Point p = getGridCoordinates(g);
		addToGrid(p.x, p.y, g);
	}
	
	public void removeFromGrid(GameEntity g)
	{
		Point p = getGridCoordinates(g);
		grid[p.x][p.y] = null;
	}
	
	public void clearGrid()
	{
		int xElements = grid.length;
		int yElements = grid[0].length;
		grid = new GameEntity[xElements][yElements];
	}
	
	public GameEntity getGameEntityAt(Point p)
	{
		return getGameEntityAt(p.x, p.y);
	}
	
	public GameEntity getGameEntityAt(int x, int y)
	{
		if(0<=x && x<grid.length && 0<=y && y<grid[0].length)
			return grid[x][y];
		else
			return null;
	}

	public Point getGridCoordinates(GameEntity g)
	{
		Point p = g.getCentre();
		p.x = (p.x - xOffset)/gridUnitWidth;
		p.y = (p.y - yOffset)/gridUnitHeight;
		return p;
	}
	
	public int getGridWitdh()
	{
		return grid.length;
	}
	
	public int getGridHeight()
	{
		return grid[0].length;
	}
	
	public void drawComponents(Graphics g)
	{
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length;j++)
			{
//				if(grid[i][j]!=null)
				if(!locationIsEmpty(i, j))
					grid[i][j].draw(g);
			}
		}
	}
	
	public boolean locationIsEmpty(int x, int y)
	{
		return (grid[x][y] == null);
	}
}
