import java.awt.Color;
import java.awt.image.BufferedImage;

public class PicRecoginition {
	public PicRecoginition(){
		maskCreator = new MaskCreator();
	}
	
	private MaskCreator maskCreator;
	private int redAmount = 36, greenAmount = 64, blueAmount = 41, execNum = 10;
	private int[] winningLocation = new int[3];
	
	public BufferedImage porcessedImg(BufferedImage pImg){
		winningLocation = new int[3];
		for(int x = 0; x < pImg.getWidth(); x++){
			for(int y = 0; y< pImg.getHeight(); y++){
				if(isColor(pImg.getRGB(x, y))){
					winCheck(percentMask(maskCreator.resizeBoiler(setMask(x,y,pImg.getHeight(), pImg)), x, y, pImg), x, y);
					pImg.setRGB(x, y, 0x000000ff);
				}
			}
		}
		System.out.println("WinnerLocated at : (" + winningLocation[1] + ", " + winningLocation[2] + ") with : " + winningLocation[0] + "%");
		
		return winningZone(pImg);
	}
	
	private int setMask(int pX, int pY, int pHeight, BufferedImage pImg){
		int pixRatio = 0;
		for(int y = pY; y < pHeight; y++){
			if(!isColor(pImg.getRGB(pX, y))){
				break;
			}
			pixRatio++;
		}
		return pixRatio;
	}
	
	private boolean isColor(int pColor){
		return (((pColor&0x000000ff) < (blueAmount + execNum) && (pColor&0x000000ff) > (blueAmount - execNum))&&
				(((pColor&0x0000ff00)>>8) < (greenAmount + execNum) && ((pColor&0x0000ff00)>>8) > (greenAmount - execNum))&&
				(((pColor&0x00ff0000)>>16) < (redAmount +execNum)) && ((pColor&0x00ff0000)>>16) > (redAmount - execNum));
	}
	
	private int percentMask(int[][] pGoal, int pX, int pY, BufferedImage pImg){
		double pixChecked = 0, pixCorrect = 0;
		if(pGoal.length < 200){
			return 0;
		}
		for(int x = 0; x < pGoal.length; x++){
			for(int y = 0; y < pGoal[0].length; y++){
				if(x+pX < pImg.getWidth() && y+pY < pImg.getHeight()){
					boolean colorTest = isColor(pImg.getRGB(x+pX, y+pY));
					if((colorTest && pGoal[x][y] == 1) || (!colorTest && pGoal[x][y] ==0)){
						pixCorrect++;
					}
					pixChecked++;
				}
			}
		}
		return (int)((pixCorrect/pixChecked)*100);
	}
	
	private void winCheck(int pPercent, int pX, int pY){
		if(pPercent > winningLocation[0] && pPercent > 40){
			winningLocation[0] = pPercent;
			winningLocation[1] = pX;
			winningLocation[2] = pY;
		}
	}
	
	private BufferedImage winningZone(BufferedImage pImg){
		for(int x = 0; x < pImg.getWidth(); x++){
			pImg.setRGB(x, winningLocation[2], 0x0000ff00);
		}
		for(int y = 0; y < pImg.getHeight(); y++){
			pImg.setRGB(winningLocation[1], y, 0x0000ff00);
		}
		return pImg;
	}
}
