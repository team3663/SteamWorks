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
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class ReadFiles {
	
	ImageIcon pIMG;
	
	public BufferedImage getImage(){
		RPiCamera piCam = null;
		try {
			 piCam = new RPiCamera();
		} catch (FailedToRunRaspistillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		piCam.setFullPreviewOff();
		piCam.setBrightness(30);
		piCam.setWidth(50);
		piCam.setHeight(50);
		piCam.setAddRawBayer(true);
		piCam.turnOffPreview();
		try {
			return piCam.takeBufferedStill();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
