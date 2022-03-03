import java.util.Date;
/**
 * A stolen bike whos data is taken from the online database
 * 
 * @author Jason Miller
 */
public class Bike {
    String description, colors, make, model, ImageUrl;
    int id;
    long date_stolen;
    public Bike(int id, String make, String model,String Colors, String descrip, String imgurl)    {
        this.id=id;
        this.make=make;
        this.model=model;
        this.description=descrip;
        this.ImageUrl=imgurl;
        colors =Colors;
        date_stolen=-1;
    }
    public String getTime () {
        if (date_stolen<=0) {
            return "N/A";
        }
        java.util.Date time = new java.util.Date(this.date_stolen*1000);
        return time.toString();
    }
    public void setTime (long time) {
        this.date_stolen=time;
    }
    public String toString () {
        String showtime = this.getTime();
        String showall = "Bike ID:"+id+" "+make+"/"+model+" Stolen:"+showtime;
        return showall;
    }
    public Object[] toArr(){
        Object[] out = new Object[5];
        out[0]=id;
        out[1]=make;
        out[2]=model;
        out[4]=description;
        out[3]=colors;
        return out;
    }
    public String getImageURL(){
        return ImageUrl;
    }
    public boolean hasImage(){
        if(ImageUrl==null){
            return false;
        }else{
            return true;
        }
    }
}
