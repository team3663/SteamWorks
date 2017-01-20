import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ShowDog {
	private Graphics g;
	JFrame window;
	
	public ShowDog(){
		window = new JFrame("HoundDog");
		window.setVisible(true);
		window.setSize(1000,1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g = window.getGraphics();
	}
	
	public void drawImg(BufferedImage pImg){
		g.drawImage(pImg, 40, 40, window);
	}
}
