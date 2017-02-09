package Processing;

public class ProcessingItem {
	public ProcessingItem next = null;
	public int percent = 0;
	public int numberCorrect = 0;
	public int numberTotal = 0;
	public int xStart = 0;
	public int yStart = 0;
	public byte[][] goal = null;
	public boolean done = false;
	
	public ProcessingItem(byte[][] pGoal, int pX, int pY, double pCon){
		goal = new byte[(int)(pGoal.length*pCon)][(int)(pGoal[0].length*pCon)];
		xStart = pX; yStart = pY;
		for(int x = 0; x < goal.length; x++){
			for(int y = 0; y < goal[0].length; y++){
				goal[x][y] = pGoal[(int)(x/pCon)][(int)(y/pCon)];
			}
		}
		numberTotal = goal.length*goal[0].length;
	}
	
	public void goalNumber(int pX, int pY, boolean correct){
		int x = pX-xStart;
		int y = pY-yStart;
		if(y < goal[0].length && y >= 0){
			if(pX >= xStart && x < goal.length){
				if((goal[x][y] == 1 && correct)||(goal[x][y] == 0 && !correct)){
					numberCorrect++;
				}
			}
		}
		else{
			assignPer();
			done = true;
		}
	}
	
	public void assignPer(){
		percent = (int)(((double)numberCorrect/(double)numberTotal)*100);
	}
}
