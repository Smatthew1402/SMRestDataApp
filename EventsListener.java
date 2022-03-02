import java.awt.event.*;
import java.awt.*;
/**
 * The Listener responds to user actions.
 */
public class EventsListener extends MouseAdapter implements ActionListener{
    JavaDataApp master;
    /** 
     * Constructor. 
     * @param master The object that should be notified of events.
     */
    public EventsListener (JavaDataApp master) {
        this.master = master;
    }
    /**
     * Respond to mouse click. 
     * Delegate to the appropriate program component.
     * @param me A mouse event.
     */
    public void mouseClicked(MouseEvent me)    {
        Point p = me.getPoint();
        master.handleMouseClick(p);
    }
    /**
     * Respond to a button click.
     * Delegate to the appropriate program component.
     * @param e An action event.
     */
    public void actionPerformed (ActionEvent e) {
        Object src = e.getSource();
        master.handleButtonPress (src);
    }
}