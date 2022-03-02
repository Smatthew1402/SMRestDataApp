import java.util.*;
/**
 * Will get the data from a given URL and arrange it for the program to display
 *
 * @author Sean Miller
 * @version 2022.03.02
 */
public class BikeDataHandler
{
    /*  Need to incorporate the Bike REST information processing into this
     */
    
    Object [][] dataArray;
    Object [] columNames;
    int rows, cols;
    String url;
    public BikeDataHandler(){
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
}
