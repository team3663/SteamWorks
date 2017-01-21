//import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Hello {
    public static void main(String[] args){
    	 System.load("C:\\opencv-3.2.0-vc14\\opencv\\build\\java\\x64\\opencv_java320.dll");
    	/*System.loadLibrary("opencv_java320");
        /*System.loadLibrary(Core.NATIVE_LIBRARY_NAME);*/
    	Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());
    }
}
