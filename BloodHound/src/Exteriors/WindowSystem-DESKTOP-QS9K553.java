package Exteriors;

import java.awt.Graphics;

import javax.swing.JFrame;

public class WindowSystem {
	private JFrame frame;
	private Graphics g;
	
	public WindowSystem(){
		frame = new JFrame("HoundDog");
		frame.setVisible(true);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g = frame.getGraphics();
	}
}
