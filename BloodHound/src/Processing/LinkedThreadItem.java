package Processing;

import java.awt.image.BufferedImage;

public class LinkedThreadItem {
	public LinkedThreadItem next = null;
	private RunningThread rt;
	public LinkedThreadItem(BufferedImage pImg, int pX, int pY, int pColor, byte[][] pGoal){
		rt = new RunningThread(pImg, pX, pY, pColor, pGoal);
		Thread test = new Thread(rt);
		test.start();
		while(!rt.done&&next==null){}
		
	}
}

class RunningThread implements Runnable{
	public boolean done;
	public int percent;
	public int Height = 0; public int Width = 0; public int X; public int Y; 
	
	private int Color;
	private BufferedImage Image;
	private byte[][] Goal;
	
	public void run(){
		Goal = resizeGoal();
		percent = findPercent();
		done = true;
	}
	
	public RunningThread(BufferedImage pImg, int pX, int pY, int pColor, byte[][] pGoal){
		X = pX; Y = pY; Color = pColor;
		Image = pImg;
		Goal = pGoal;
	}
	
	public byte[][] resizeGoal(){
		byte[][] temp;
		int imageGoalHeight = 0;
		int maskGoalHeight = 0;
		int z = Y;
		while(z < Image.getHeight() && isColor(X,z)){
			Y++;
			imageGoalHeight++;
		}
		int i = 0;
		while(i < Goal[0].length && Goal[0][i] == 1){
			i++;
			maskGoalHeight++;
		}
		double conversion = (double)maskGoalHeight/(double)imageGoalHeight;
		temp = new byte[(int)(Goal.length*conversion)][(int)(Goal[0].length*conversion)];
		for(int x=0; x<temp.length; x++){
			for(int y=0; y<temp[0].length; y++){
				temp[x][y] = Goal[(int) (x/conversion)][(int) (y/conversion)];
			}
		}
		return temp;
	}
	
	public int findPercent(){
		int totalCorrect = 0;
		for(int x=0; x<Goal.length; x++){
			for(int y=0; y<Goal[0].length; y++){
				if((isColor(x,y) && Goal[x][y] == 1)||(!isColor(x,y) && Goal[x][y] == 0)){
					totalCorrect++;
				}
			}
		}
		return (int)((double)totalCorrect/(double)(Goal.length*Goal[0].length))*100;
	}
	
	private boolean isColor(int pX, int pY){
		int imgColor = Image.getRGB(pX, pY);
		return((((Color&0x000000ff)>>0)+((Color&0xff000000)>>32))<((imgColor&0x000000ff)>>0) &&
				(((Color&0x000000ff)>>0)-((Color&0xff000000)>>32))>((imgColor&0x000000ff)>>0) &&
				(((Color&0x0000ff00)>>0)+((Color&0xff000000)>>32))<((imgColor&0x0000ff00)>>0) &&
				(((Color&0x0000ff00)>>0)-((Color&0xff000000)>>32))>((imgColor&0x0000ff00)>>0) && 
				(((Color&0x00ff0000)>>0)+((Color&0xff000000)>>32))<((imgColor&0x00ff0000)>>0) &&
				(((Color&0x00ff0000)>>0)-((Color&0xff000000)>>32))>((imgColor&0x00ff0000)>>0));
	}
}