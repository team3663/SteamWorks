package Exteriors;

import java.awt.Desktop;

public class Overcoat {
	private static WindowSystem windowSystem;
	private static ReadFiles readFiles;
	
	public static void main(String args[]){
		windowSystem = new WindowSystem();
		readFiles = new ReadFiles();
		while(true){
			windowSystem.drawImg(readFiles.getImage());
			windowSystem.fillColor();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
