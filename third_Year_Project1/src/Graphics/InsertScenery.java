package Graphics;
import Components.Scenery;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This is a panel used to show the user a list of scenery to choose to be added to the simulation
 */
public class InsertScenery extends JPanel implements MouseListener, MouseMotionListener{

    private int numImages=4;
    private int selected=numImages+1;
    
    public InsertScenery()
    {
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(selected<numImages)
        {
            g.setColor(new Color(153,217,234));
            g.fillRect(40, selected*60, 95, 70);
        }
            
        g.setColor(Color.black);
        for(int i=0;i<numImages;i++)
        {
            g.drawImage(new ImageIcon(this.getClass().getResource("resources/scenery"+(i+1)+"B.png")).getImage(), 50, (i*60)+10, null);           
        }      
        
    }
    
    public void mouseExited(MouseEvent e){}      
    public void mouseEntered(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        Scenery s = new Scenery("scenery"+((e.getY()/60)+1)+".png");
        if(s.getPath().equals("scenery3.png"))
            new Thread(s).start();
        
        GUI.t.addScenery(s);
        GUI.IS.setVisible(false);
        selected=numImages+1;
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        selected=(e.getY()/60);
        repaint();
    }
    public void mouseDragged(MouseEvent e){}
    
}
