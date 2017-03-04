import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class PicRetrevial {
	/*VideoCapture camera = new VideoCapture();
	public Mat mat = new Mat();*/
	
	public PicRetrevial(){
		//camera.open(2);
	}
	
	public BufferedImage getImg(){
		try {
			return ImageIO.read(new File("C:\\Users\\curti\\OneDrive\\Programming\\SteamWorks\\BloodHound\\test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		 /*camera.read(mat);
		 int type = BufferedImage.TYPE_BYTE_GRAY;
	     if ( mat.channels() > 1 ) {
	         type = BufferedImage.TYPE_3BYTE_BGR;
	     }
	     int bufferSize = mat.channels()*mat.cols()*mat.rows();
	     byte [] b = new byte[bufferSize];
	     mat.get(0,0,b); // get all the pixels
	     BufferedImage image = new BufferedImage(mat.cols(),mat.rows(), type);
	     final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	     System.arraycopy(b, 0, targetPixels, 0, b.length);  
	     return image;*/
	}
	
	public void releaseCam(){
		//camera.release();
	}
}
