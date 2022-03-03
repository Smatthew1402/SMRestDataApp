import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.*;

/**
 * Will manage the display of data.
 *
 * @author Sean Miller
 * @version 2022.03.02
 */
public class JavaDataApp
{
    String city = "Shepherdstown";
    String distance = "100";
    String colors="n/a";
    static String searchTitle;
    Button loadButton, viewButton;
    JTextField cityField, distanceField, colorField;
    DataView dataView;
    BikeDataHandler bikeData;
    JFrame frame;
    String url;
    public static void main(){
        String url;
        int distance = 100;
        String city = "Shepherdstown";
        String colors = "n/a";
        //"https://bikeindex.org:443/api/v3/search?page=1&per_page=10&location=Shepherdstown%2C%20WV&distance=100&stolenness=proximity"
        url="https://bikeindex.org:443/api/v3/search?page=1&per_page=100&colors="+colors+"&location="+city+"%2C%20WV&distance="+distance+"&stolenness=proximity";
        searchTitle = "Bikes lost within "+ distance +"miles of "+city ;
        JavaDataApp jda=new JavaDataApp(url);
    }
    public JavaDataApp(String inurl){
        url = inurl;
        bikeData = new BikeDataHandler(url);
        dataView = new DataView(bikeData);
        Dimension tableSize = new Dimension(600,400);//Initial Dimension
        dataView.setPreferredSize(tableSize);
        frame=new JFrame(searchTitle);
        frame.setLayout(new FlowLayout());//Set layout
        frame.setBackground(Color.blue);
        EventsListener listener = new EventsListener(this);
        dataView.addMouseListener(listener);
        frame.add(dataView);
        JPanel buttons = new JPanel();//Panel for buttons
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        viewButton = new Button ("VIEW");
        loadButton = new Button ("LOAD");
        viewButton.addActionListener(listener);
        loadButton.addActionListener(listener);
        buttons.add(viewButton);
        buttons.add(loadButton);
        JPanel imputs = new JPanel();//imputs panel for city and distance
        imputs.setLayout(new BoxLayout(imputs, BoxLayout.PAGE_AXIS));
        JPanel cityin = new JPanel();
        cityin.add(new Label("City"));
        cityField = new JTextField(15);
        cityin.add(cityField);
        JPanel distin = new JPanel();
        distanceField = new JTextField(4);
        distin.add(new Label("Distance in miles 0-9999"));
        distin.add(distanceField);
        JPanel colorin = new JPanel();
        colorField = new JTextField(10);
        colorin.add(new Label("Color of bike"));
        colorin.add(colorField);
        imputs.add(cityin);
        imputs.add(distin);
        imputs.add(colorin);
        cityField.addActionListener(listener);
        distanceField.addActionListener(listener);
        colorField.addActionListener(listener);
        frame.add(buttons);
        frame.add(imputs);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void handleMouseClick(Point p){
    //needs to identify an entry in the list of bikes or a column to sort
    }
    
    public void handleButtonPress(Object src){
        if(src==viewButton){
            bikeData.viewBike(dataView);
        }else if(src==loadButton){
            calcURL();
            calcTitle();
            bikeData.loadURL(url);
            dataView.setBikeData(bikeData);
        }
    }
    public void handleTextEnter(Object txf){
        if(txf==cityField){
            city=cityField.getText();
            cityField.setText("");
        }else if(txf==distanceField){
            distance=distanceField.getText();
            distanceField.setText("");
        }else if(txf==colorField){
            colors=colorField.getText();
            colorField.setText("");
        }
    }
    public void calcURL(){
        url="https://bikeindex.org:443/api/v3/search?page=1&per_page=100&colors="+colors+"&location="+city+"%2C%20WV&distance="+distance+"&stolenness=proximity";
    }
    public void calcTitle(){
         frame.setTitle("Bikes lost within "+ distance +"miles of "+city) ;
    }
    
}
