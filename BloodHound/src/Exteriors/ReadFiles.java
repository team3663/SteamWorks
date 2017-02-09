package Exteriors;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.AWB;
import com.hopding.jrpicam.enums.DRC;
import com.hopding.jrpicam.enums.Encoding;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
public class ReadFiles {
	RPiCamera piCam = null;
	public ReadFiles(){
		try {
			piCam = new RPiCamera("/home/pi/Desktop");
			piCam.setAWB(AWB.AUTO);
			piCam.setDRC(DRC.HIGH);
			piCam.setContrast(75);
			piCam.setSharpness(100);
			piCam.setQuality(100);
			piCam.setFullPreviewOn();
			piCam.setEncoding(Encoding.PNG);
			piCam.setTimeout(1);
		} catch (FailedToRunRaspistillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(){
		piCam.
		BufferedImage Img = null;
		try {
			Img = piCam.takeBufferedStill();
		} catch (IOException | InterruptedException e) {
		}
		return Img;
	}
}
