package Exteriors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFiles {
	private BufferedReader bufferedReader;
	
	public ReadFiles(){
			FileReader fr = null;
			try {
				fr = new FileReader("C:/test.txt");
			} catch (FileNotFoundException e) {
				try {
					fr = new FileReader("C:/test.txt");
				} catch (FileNotFoundException a) {
				}
			}
			bufferedReader = new BufferedReader(fr);
	}
}
