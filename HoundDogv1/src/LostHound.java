import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LostHound {
	JFrame frame;
	Graphics g;
	JPanel panel;
	
	public LostHound(){		
		frame = new JFrame("LOSTHOUND");
		frame.setBackground(Color.red);
		frame.setVisible(true);
		frame.setSize(500, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
