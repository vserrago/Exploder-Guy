import java.awt.Color;
import java.awt.Graphics;


public class Bomb extends GameEntity 
{
	private static final int BOMBWIDTH = EGPanel.GAME_ENTITY_SIZE;
	private static final int BOMBHEIGHT = EGPanel.GAME_ENTITY_SIZE;
	
	int bombPower;
	Player bombOwner;
	
	public Bomb(int xPos, int yPos, int bombPower, Player bombOwner)
	{
		this(xPos, yPos, BOMBWIDTH, BOMBHEIGHT, bombPower, bombOwner);
	}
	
	public Bomb(int xPos, int yPos, int width, int height, int bombPower, 
			Player bombOwner) 
	{
		super(xPos, yPos, width, height);
		this.bombPower = bombPower;
		this.bombOwner = bombOwner;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		super.draw(g);
	}
}
