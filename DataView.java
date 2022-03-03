import javax.swing.*;
/**
 * Creates a Jtable for the Bike data
 *
 * @author Jason Miller
 * @author Sean Miller
 * @version 2022.03.02
 */
public class DataView extends JScrollPane{
    JTable table;
    BikeDataHandler bdh;
    
    public DataView(BikeDataHandler bdh){
        this.table = new JTable();
        this.bdh =bdh;
        table.setAutoCreateRowSorter(true);
        setBikeData(bdh);
    }
    public void setBikeData(BikeDataHandler bdh){
        if(bdh != null){
            table = new JTable (bdh.getAllData(), bdh.getColumnNames());
        table.setAutoCreateRowSorter(true);
        }
        setViewportView(table);
    }
}
