import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class RobotSample {

    public static void main(String[] args) throws Exception {
        for(int i=0;i<100;i++){
            Thread.sleep(2000);
            System.out.println(" \n ");
            captureScreen("image.png");
            System.out.println(" \n Done Check PrintScreen " + i);
        }
    }

    public static void captureScreen(String fileName) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);

        Color color = new Color(255, 0, 0); // Color red
        int rgb = color.getRGB();

        boolean playsound=true;

        for (int x = 638; x < 1230; x+=2 ) {
            playsound = true;
            if( !printPixelARGB( image.getRGB(x,773), x+"px") ){
                playsound=false;
                break;
            }
            image.setRGB        (x, 773  , rgb);
        }

        if (playsound) {
            System.out.println("DO SOMETHING");
        }
        ImageIO.write(image, "png", new File(fileName));
    }

    public static boolean printPixelARGB(int pixel, String x) {
        //int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        //Look for specific color
        if(red == 34 && green == 181){
           System.out.print("Fail ---- "+x+" rgb: " + red + ", " + green + ", " + blue);
           return false;
        }
        System.out.println( "Success - "+x+"  rgb: " + red + ", " + green + ", " + blue);
        return true;
    }

}
