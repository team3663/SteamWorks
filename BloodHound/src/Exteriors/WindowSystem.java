package Exteriors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class WindowSystem {
	private JFrame frame;
	private Graphics g;
	
	public WindowSystem(){
		frame = new JFrame("BloodHound");
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g = frame.getGraphics();
	}
	
	public void drawImg(BufferedImage pImg){
		g.fillRect(40, 40, 40, 40);
		//System.out.println(pImg);
		g.drawImage(pImg, 40, 40, frame);
	}

	public void fillColor(){
		
	}
}
