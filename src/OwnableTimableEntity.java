import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public abstract class OwnableTimableEntity extends GameEntity 
{
	private Player owner;
	private Timer countDownTimer;
	private boolean actionCompleted;

	public OwnableTimableEntity(int xPos, int yPos, int width, int height, 
			int timeToWait, Player owner) 
	{
		super(xPos, yPos, width, height);
		
		this.owner = owner;
		actionCompleted = false;
		countDownTimer = new Timer(timeToWait, new timerActioner());
		countDownTimer.setRepeats(false);
		
		countDownTimer.start();
	}
	
	public Player getOwner()
	{
		return owner;
	}
	
	public boolean actionCompleted()
	{
		return actionCompleted;
	}
	
	public void timerAction()
	{
		actionCompleted = true;
	}
	
	private class timerActioner implements ActionListener
	{public void actionPerformed(ActionEvent arg0) {timerAction();}};

}