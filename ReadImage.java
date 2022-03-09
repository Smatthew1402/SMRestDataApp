import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReadImage 
{    
    
    public static void displayImage(String URL){
        Image image=null;
        try {
            URL url = new URL(URL);
            image = ImageIO.read(url);
        } catch (IOException e) {
            //e.printStackTrace();
        } 
        
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        try{JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        }catch(NullPointerException npe){
            Panel noAlert = new Panel();
            noAlert.add(new JLabel("No Image"));
            frame.add(noAlert);
            frame.pack();
            frame.setVisible(true);
        }
        
    }
}