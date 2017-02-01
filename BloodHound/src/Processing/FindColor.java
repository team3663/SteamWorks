package Processing;

import java.awt.image.BufferedImage;

public class FindColor {
	private LinkedThreadItem MostAccepted;
	private LinkedThreadItem Start;
	private LinkedThreadItem Next;
	
	public void addToList(BufferedImage pImg, int pX, int pY, int pColor, byte[][] pGoal){
		if(Start == null){
			Start = new LinkedThreadItem(pImg, pX, pY, pColor, pGoal);
		}
		else{
			Next = Start;
		}
	}
}
