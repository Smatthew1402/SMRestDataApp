import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReadImage 
{    
    public static void main( String[] args )
    {
        Image image = null;
        try {
            URL url = new URL("https://files.bikeindex.org/uploads/Pu/494423/small_IMG_20200830_150015.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame();
        //frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }
    public static void displayImage(String URL){
        Image image=null;
        try {
            URL url = new URL(URL);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        try{JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);
            frame.setVisible(true);
        }catch(NullPointerException npe){
            Panel noAlert = new Panel();
            noAlert.add(new JLabel("No Image"));
            frame.add(noAlert);
            frame.setVisible(true);
        }
        
    }
}