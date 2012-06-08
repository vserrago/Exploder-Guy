import java.awt.Graphics;


public class GameGrid 
{
	private GameEntity [][] grid;
	
	public GameGrid(int xElements, int yElements)
	{
		grid = new GameEntity[xElements][yElements];
	}
	
	public void addToGrid(int x, int y, GameEntity g)
	{
		grid [x][y] = g;
	}
	
	public void clearGrid()
	{
		int xElements = grid.length;
		int yElements = grid[0].length;
		grid = new GameEntity[xElements][yElements];
	}
	
	public void drawComponents(Graphics g)
	{
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length;j++)
			{
				if(grid[i][j]!=null)
					grid[i][j].draw(g);
			}
		}
	}
}
