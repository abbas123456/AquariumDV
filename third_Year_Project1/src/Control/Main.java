package Control;
import Graphics.GUI;
import javax.swing.*;
/**
 *
 * @author Mohammad Abbas
 * This is the main class of the program and contains the main method, it calls the constructor of the GUI class which starts everything
 */
public class Main 
{
    public static void main(String[] args) throws Exception
    {   
        
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel); 
        new GUI("Aquarium data visualiser v1.0");   
        
    }   
}
