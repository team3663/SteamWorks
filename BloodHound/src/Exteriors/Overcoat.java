package Exteriors;

import java.awt.Desktop;

import Processing.ImgProcessing;


public class Overcoat {
	private static WindowSystem windowSystem;
	private static ReadFiles[] readFiles;
	private static ImgProcessing imgProcessing;
	
	public static void main(String args[]){
	
		windowSystem = new WindowSystem();
		readFiles = new ReadFiles[10];
		imgProcessing = new ImgProcessing();
		int i = 0;
		
		for(int z = 0; z < readFiles.length; z++){
			readFiles[i] = new ReadFiles();
		}
		
		while(true){
			windowSystem.drawImg(imgProcessing.process(readFiles[i++].getImage()));
			windowSystem.fillColor();
			if(!(i < readFiles.length)){i = 0;}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
