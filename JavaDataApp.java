import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JTextField;

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
    static String searchTitle;
    Button loadButton;
    Button sortButton;
    Button viewButton;
    JTextField cityField;
    JTextField distanceField;
    DataView dataView;
    BikeDataHandler bikeData;
    JFrame frame;
    String url;
    
    public static void main(){
        String url;
        int distance = 100;
        String city = "Shepherdstown";
        //"https://bikeindex.org:443/api/v3/search?page=1&per_page=10&location=Shepherdstown%2C%20WV&distance=100&stolenness=proximity"
        url="https://bikeindex.org:443/api/v3/search?page=1&per_page=10&location="+city+"%2C%20WV&distance="+distance+"&stolenness=proximity";
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
        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout());
        viewButton = new Button ("VIEW");
        sortButton = new Button ("SORT");
        loadButton = new Button ("LOAD");
        viewButton.addActionListener(listener);
        sortButton.addActionListener(listener);
        loadButton.addActionListener(listener);
        buttons.add(viewButton);
        buttons.add(sortButton);
        buttons.add(loadButton);
        Panel imputs = new Panel();//imputs panel for city and distance
        imputs.setLayout(new FlowLayout());
        cityField = new JTextField(15);
        distanceField = new JTextField(4);
        cityField.addActionListener(listener);
        distanceField.addActionListener(listener);
        imputs.add(cityField);
        imputs.add(distanceField);
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
            //Bring up the photo for the selected bike
        }else if(src==loadButton){
            calcURL();
            calcTitle();
            bikeData.loadURL(url);
            dataView.setBikeData(bikeData);
        }else if(src==sortButton){
            //reload the data sorted by the selected column.
        }
    }
    public void handleTextEnter(Object txf){
        if(txf==cityField){
            city=cityField.getText();
            cityField.setText("");
        }else if(txf==distanceField){
            distance=distanceField.getText();
            distanceField.setText("");
        }
    }
    public void calcURL(){
        url="https://bikeindex.org:443/api/v3/search?page=1&per_page=10&location="+city+"%2C%20WV&distance="+distance+"&stolenness=proximity";
    }
    public void calcTitle(){
         frame.setTitle("Bikes lost within "+ distance +"miles of "+city) ;
    }
}
