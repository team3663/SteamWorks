package Processing;

import java.awt.image.BufferedImage;

public class ImgProcessing {
	private int redAmount		= 255;
	private int blueAmount 		= 255;
	private int greenAmount 	= 255;
	private int eceptAmount 	= 5;
	private int endingXLoc = 200;
	private int endingYLoc = 200;
	private int endingExc = 50;
	
	private byte[][] upperGoal = new byte[15][10];
	
	public ImgProcessing(){
		makeUpperGoal();
	}
	
	private void makeUpperGoal(){
		for(int x = 0; x < upperGoal.length; x++){
			for(int y = 0; y < upperGoal[0].length; y++){
				if(y < 4 || y > 8){
					upperGoal[x][y] = 1;
				}
			}
		}
	}
	
	public BufferedImage process(BufferedImage pImg){
		wipeList();
		boolean correct;
		for(int x = 0; x < pImg.getWidth(); x++){
			for(int y = 0; y < pImg.getHeight(); y++){
				if(isColor(pImg.getRGB(x, y))){
					correct = true;
					double conversionHeight = getGoalHeight(x,y,pImg,upperGoal);
					if(conversionHeight > 10 && conversionHeight < 25){
						addToList(upperGoal, x, y, conversionHeight);
					}
					pImg.setRGB(x, y, 0x000000ff);
				}
				else{
					correct = false;
				}
				cycleList(x,y,correct);
			}
		}
		pImg = drawWantedLoc(returnMostLikly(pImg));
		getBits();
		return pImg;
	}
	
	private BufferedImage returnMostLikly(BufferedImage pImg){
		Next = Start;
		mostLike = Next;
		if(Start != null){
			while(Next.next != null){
				if(mostLike.percent < Next.percent){
					mostLike = Next;
				}
				Next = Next.next;
			}
			
			//System.out.println("Most Likly was at (" + mostLike.xStart + ", " + mostLike.yStart + ") with a percent of " + mostLike.percent);
			if(mostLike.percent < 25){
				mostLike = null;
			}
			else{
				return drawCross(pImg);
			}
		}
		return pImg;
	}
	
	private void cycleList(int x, int y, boolean correct){
		int testing = 0;
		if(Start != null){
			Next = Start;
			while(Next.next != null){
				Next.goalNumber(x, y, correct);
				compareCurrentAndRemove(Next);
				Next = Next.next;
				testing++;
			}
		}
		//System.out.println("list is " + testing + " items long");
	}
	
	private void compareCurrentAndRemove(ProcessingItem pI){
		if(pI.next != null && pI.next.done){
			if(mostLike == null){
				mostLike = pI;
			}
			if(pI.next.percent > mostLike.percent){
				mostLike = pI.next;
				//System.out.println("changing best");
			}
			pI.next = pI.next.next;
		}
	}
	
	private void addToList(byte[][] pGoal, int x, int y, double pCon){
		if(Start == null){
			Start = new ProcessingItem(pGoal, x, y, pCon);
		}
		else{
			Next = Start;
			while(Next.next != null){
				Next = Next.next;
			}
			Next.next = new ProcessingItem(pGoal, x, y, pCon);
		}
	}
	
	private double getGoalHeight(int x, int y, BufferedImage pImg, byte[][] goal){
		int iY = 0;
		while(y+iY < pImg.getHeight() && isColor(pImg.getRGB(x, y+iY))){
			iY++;
		}
		int gY = 0;
		while(goal[0][gY] == 1 && gY < goal[0].length){
			gY++;
		}
		return iY/gY;
	}
	
	private boolean isColor(int color){
		return (((color&0x000000ff) < (blueAmount + eceptAmount) && (color&0x000000ff) > (blueAmount - eceptAmount))&&
				(((color&0x0000ff00)>>8) < (greenAmount + eceptAmount) && ((color&0x0000ff00)>>8) > (greenAmount - eceptAmount))&&
				(((color&0x00ff0000)>>16) < (redAmount + eceptAmount)) && ((color&0x00ff0000)>>16) > (redAmount - eceptAmount));
	}
	
	private void wipeList(){
		mostLike = null;
		Start = null;
		Next = null;
	}
	
	private int[] getBits(){
		int[] holder = new int[4];
		if(mostLike != null){
			if((mostLike.xStart+mostLike.xStart+mostLike.goal.length)/2 - endingXLoc + endingExc > 0){
				System.out.print("  Move Right");
				holder[0] = 1;
			}
			if((mostLike.xStart+mostLike.xStart+mostLike.goal.length)/2 - endingXLoc - endingExc < 0){
				System.out.print("  Move Left");
				holder[1] = 1;
			}
			if((mostLike.yStart+mostLike.yStart+mostLike.goal[0].length)/2 - endingYLoc + endingExc > 0){
				System.out.print("  Move Up");
				holder[2] = 1;
			}
			if((mostLike.yStart+mostLike.yStart+mostLike.goal[0].length)/2 - endingYLoc - endingExc < 0){
				System.out.print("  Move Down");
				holder[3] = 1;
			}
			System.out.println("");
		}
		return holder;
	}
	
	private BufferedImage drawWantedLoc(BufferedImage pImg){
		int x = 0;
		int y = 0;
		while(endingXLoc < pImg.getWidth() && y < pImg.getHeight()){
			pImg.setRGB(endingXLoc, y++, 0x0000ff00);
		}
		while(endingYLoc < pImg.getHeight() && x < pImg.getWidth()){
			pImg.setRGB(x++, endingYLoc, 0x0000ff00);
		}
		return pImg;
	}
	
	private BufferedImage drawCross(BufferedImage pImg){
		int y = 0;
		int x = 0;
		for(x = 0; x < mostLike.goal.length; x++){
			if(x + mostLike.xStart < pImg.getWidth()){
				if(mostLike.goal[0].length + mostLike.yStart< pImg.getHeight()){
					pImg.setRGB(x + mostLike.xStart, mostLike.goal[0].length + mostLike.yStart, 0x0000ff00);
				}
				pImg.setRGB(x + mostLike.xStart, mostLike.yStart, 0x0000ff00);
			}
		}
		for(y = 0; y < mostLike.goal[0].length; y++){
			if(y + mostLike.yStart < pImg.getHeight()){
				if(mostLike.goal.length + mostLike.xStart < pImg.getWidth()){
					pImg.setRGB(mostLike.goal.length + mostLike.xStart, y+mostLike.yStart, 0x0000ff00);
				}
				pImg.setRGB(mostLike.xStart,y+ mostLike.yStart, 0x0000ff00);
			}
		}
		x = 0; y = 0;
		int middleX = ((mostLike.xStart + mostLike.xStart + mostLike.goal.length)/2);
		int middleY = ((mostLike.yStart + mostLike.yStart + mostLike.goal[0].length)/2);
		if(middleX < pImg.getWidth()){
			while(y < pImg.getHeight()){
				pImg.setRGB(middleX, y++, 0x00ff0000);
			}
		}
		if(middleY < pImg.getHeight()){
			while(x < pImg.getWidth()){
				pImg.setRGB(x++, middleY, 0x00ff0000);
			}
		}
		
		return pImg;
	}
	
	private ProcessingItem Start = null;
	private ProcessingItem Next = null;
	private ProcessingItem mostLike = null;
}
