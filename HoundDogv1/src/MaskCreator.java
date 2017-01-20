

public class MaskCreator {
	
	public MaskCreator(){
		boilerCreation();
		gearCreation();
	}
	
	private int boilerWidth = 15, boilerHeight = 10;
	private int gearWidth = 10, gearHeight = 5;
	
	public int[][] boilerGoal = new int[boilerWidth][boilerHeight];
	public int[][] gearGoal = new int[gearWidth][gearHeight];
	
	private void boilerCreation(){
		for(int x = 0; x <= boilerWidth-1; x++){
			for(int y = 0; y <= boilerHeight-1; y++){
				if(y == 0 || y == 1 || y == 2|| y == 3 || y == 8 || y == 9){
					boilerGoal[x][y] = 1;
				}
				else{
					boilerGoal[x][y] = 0;
				}
			}
		}
	}
	private void gearCreation(){
		for(int x = 0; x <= gearWidth-1; x++){
			for(int y = 0; y <= gearHeight-1; y++){
				if(x == 0 || x ==1 || x == 8|| x== 9){
					gearGoal[x][y] = 1;
				}
				else{
					gearGoal[x][y] = 0;
				}
			}
		}
	}
	
	public int[][] resizeBoiler(int pHeight){//this is the height for the upper rim of reflective tape
		double amount = pHeight/4;
		int[][] returnGoal = new int[(int)amount*boilerWidth][(int)amount*boilerHeight];
		for(int x = 0; x <= (amount*boilerWidth)-1; x++){
			for(int y = 0; y <= (amount*boilerHeight)-1; y++){
				returnGoal[x][y] = boilerGoal[(int)(x/amount)][(int)(y/amount)];
			}
		}
		return returnGoal;
	}
}
