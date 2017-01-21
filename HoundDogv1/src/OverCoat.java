
public class OverCoat {
	public static ShowDog showDog;
	public static PicRetrevial picRetrevial;
	public static PicRecoginition picRecoginition;
	
	public static LostHound lostHound;
	
	public static void main(String args[]){
		boolean safe = false;
		/*try{
			System.load("/home/pi/Desktop/shazam/opencv_java320.so");
			safe = true;
		}
		catch(UnsatisfiedLinkError e){
			System.out.println("System: Not Linux Defalting to Windows");
			/*try{
				System.load("C:\\Users\\curti\\OneDrive\\Programming\\opencv\\build\\java\\x64\\opencv_java320.dll");	
				safe = true;
			}
			catch(UnsatisfiedLinkError f){
				lostHound = new LostHound();
				
			}*/
		//}
		if(true){
			showDog = new ShowDog();
			picRetrevial = new PicRetrevial();
			picRecoginition = new PicRecoginition();
			showDog.drawImg(picRecoginition.porcessedImg(picRetrevial.getImg()));
		}
	}
	
	public static void killHoundDog(){
		picRetrevial.releaseCam();
	}
}
