import java.awt.Graphics;
import java.awt.Point;


public class GameGrid 
{
	private int xOffset;		//How far to the right and down the grid is
	private int yOffset;		//from the left and top borders of the panel
	private int gridUnitWidth;	//width of a grid unit in pixels
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
	
	public void addToGrid(int x, int y, GameEntity g)
	{
		grid [x][y] = g;
	}
	
	public void addToNearestGridLocation(GameEntity g)
	{
		Point p = getGridCoordinates(g);
		g.setPosition(p.x*gridUnitWidth + xOffset, p.y*gridUnitHeight 
				+ yOffset);
		addToGrid(p.x, p.y, g);
	}
	
	public void clearGrid()
	{
		int xElements = grid.length;
		int yElements = grid[0].length;
		grid = new GameEntity[xElements][yElements];
	}
	

	public Point getGridCoordinates(GameEntity g)
	{
		Point p = g.getCentre();
		p.x = (p.x - xOffset)/gridUnitWidth;
		p.y = (p.y - yOffset)/gridUnitHeight;
		return p;
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
