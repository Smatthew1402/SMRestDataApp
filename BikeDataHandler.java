import java.util.*;
import java.io.*;
import javax.json.*;
import java.net.*;

/**
 * Will get the data from a given URL and arrange it for the program to display
 *
 * @author Sean Miller
 * @version 2022.03.02
 */
public class BikeDataHandler
{
    
    
    Object [][] dataArray;
    Object [] columNames;
    int rows, cols;
    String url;
    Bike[] bikes;
    public BikeDataHandler(String inurl){
        url=inurl;
        rows=0;
        cols=0;
        dataArray=new Object[0][0];
        columNames=new Object[0];
    }
    public Object[][] getAllData(){
        return dataArray;
    }
    public Object[] getColumnNames(){
        return columNames;
    }
    
    public void URLtoBikes(){
        InputStream is = openURL(url);
        JsonReader jsonReader = Json.createReader(is);
        JsonStructure js = jsonReader.read();
        jsonReader.close();
        closeStream(is);
        
        JsonArray jsa =null;
        JsonObject jso=null;
        if(js instanceof JsonObject){
            jso=(JsonObject)js;
            jsa=jso.getJsonArray("bikes");
        }else{
            jsa=(JsonArray)js;
        }
        int s = jsa.size();
        bikes = new Bike[s];
        for(int i=0; i<s; i++){
            JsonObject jo = jsa.getJsonObject(i);
            int id = jo.getInt("id");
            String unknown = "??";
            String make = jo.getString("manufacturer_name",unknown);
            String model = jo.getString("frame_model",unknown);
            String descrip = jo.getString("description",unknown);
            String image = jo.getString("large_img", unknown);
            long time=0;
            try {
                time = jo.getJsonNumber("date_stolen").longValue();
            } catch (Exception e) {
                time = 0;
            }
            Bike theft = new Bike(id,make,model,descrip,image);
            theft.setTime(time);
            bikes[i]=theft;
        }
        for (int i=0; i<bikes.length; i++) {
            Bike theft = bikes[i];
        }
    }
    private static InputStream openURL(String http){
        URL url;
        InputStream source = null;
        try{
            url = new URL(http);
            source=url.openStream();
        }catch(Exception e){
            System.err.println("Cannot open URL "+http);
            System.err.println(e);
        }
        return source;
    }
    private static void closeStream (InputStream is) {
        try {
            is.close();
        } catch (Exception e) {
            System.err.println("Could not close the input stream.");
            System.err.println(e);
        }
    }
    public void bikeToObject(){
        columNames = new Object[4];
        columNames[0]="id";
        columNames[1]="Make";
        columNames[2]="Model";
        columNames[3]="Description";
        dataArray = new Object[bikes.length][4];
        for(int i=0;i<bikes.length;i++){
            dataArray[i]=bikes[i].toArr();
        }
    }
    public void loadURL(String http){
        url = http;
        URLtoBikes();
        bikeToObject();
    }
    private Bike findBike(int id){
        for(Bike b:bikes){
            if(b.id==id){
                return b;
            }
        }
        return null;
    }
    public void viewBike(DataView dv){
        int bikeID = dv.getSelectedID();
        Bike viewbike = findBike(bikeID);
        
        try{
            ReadImage.displayImage(viewbike.getImageURL());
        }catch(NullPointerException npe){
            System.out.println("No Image");
        }
    }
}
