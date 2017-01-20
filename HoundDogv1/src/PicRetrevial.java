import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class PicRetrevial {
	VideoCapture camera = new VideoCapture();
	public Mat mat = new Mat();
	
	public PicRetrevial(){
		camera.open(2);
	}
	
	public BufferedImage getImg(){
		camera.read(mat);
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
	     return image;
	}
	
	public void releaseCam(){
		camera.release();
	}
}
