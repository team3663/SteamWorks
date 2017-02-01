package Exteriors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.AWB;
import com.hopding.jrpicam.enums.DRC;
import com.hopding.jrpicam.enums.Encoding;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class ReadFiles {
	
	ImageIcon pIMG;
	RPiCamera piCam = null;
	
	public ReadFiles(){
		try {
			 piCam = new RPiCamera("/home/pi/Desktop");
		} catch (FailedToRunRaspistillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		piCam.setAWB(AWB.AUTO);
		piCam.setDRC(DRC.HIGH);
		piCam.setContrast(100);
		piCam.setSharpness(100);
		piCam.setQuality(100);
		piCam.setTimeout(1000);
		piCam.turnOnPreview();
		piCam.setEncoding(Encoding.PNG);
	}
	
	public BufferedImage getImage(){
		try {
			return piCam.takeBufferedStill();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
