import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


public class Explosion extends OwnableTimableEntity 
{
	private static final int EXPLOSION_WIDTH = EGPanel.GAME_ENTITY_SIZE;
	private static final int EXPLOSION_HEIGHT = EGPanel.GAME_ENTITY_SIZE;
	private static final int EXPLOSION_DELAY = EGPanel.EXPLOSION_DELAY;
	
	private static Vector<Explosion> explosionList;

	public Explosion(Player owner)
	{
		super(0,0,EXPLOSION_WIDTH,EXPLOSION_HEIGHT,EXPLOSION_DELAY,owner);
	}
	
	public Explosion(int xPos, int yPos, int width, int height, int timeToWait,
			Player owner) 
	{
		super(xPos, yPos, width, height, timeToWait, owner);
	}
	
	public static void setExplosionList(Vector<Explosion> v)
	{
		explosionList= v;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.ORANGE);
		super.draw(g);
	}
	
	public boolean isDoneExploding()
	{
		return actionCompleted();
	}
	
	public void timerAction() 
	{
		super.timerAction();
		explosionList.add(this);
	}
}
