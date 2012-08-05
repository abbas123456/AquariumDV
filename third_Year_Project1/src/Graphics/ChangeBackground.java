package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This is a panel used to show the user a list of background to choose from or to choose their own picture
 */
public class ChangeBackground extends JPanel implements MouseListener, MouseMotionListener
{

    private int numImages=4;
    private int selected=2;
    
    public ChangeBackground()
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
            g.drawImage(new ImageIcon(this.getClass().getResource("resources/untitled"+(i+1)+"B.jpg")).getImage(), 50, (i*60)+10, null);            
        }      
        g.setColor(Color.BLUE);
        g.drawString("Browse for more pictures...", 20,(numImages*60)+25);
        g.drawLine(20, (numImages*60)+28, 150, (numImages*60)+28);
    }
    
    public void mouseExited(MouseEvent e){}      
    public void mouseEntered(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}
    
    public void mousePressed(MouseEvent e)
    {        
        if(e.getY()/60+1<=numImages)
        {
            GUI.CB.setVisible(false);        
            GUI.t.setPath("resources/untitled"+((e.getY()/60)+1)+".jpg");
            GUI.t.makeBackground(GUI.t.getWidth(),GUI.t.getHeight(),false);            
        }
        else
        {
            JFileChooser chooser = new JFileChooser();
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            {
                String s = chooser.getSelectedFile()+"";                
                if(s.substring(s.lastIndexOf('.')+1).equalsIgnoreCase("jpg")||s.substring(s.lastIndexOf('.')+1).equalsIgnoreCase("gif"))
                {
                    GUI.CB.setVisible(false);        
                    GUI.t.setPath(chooser.getSelectedFile()+"");
                    GUI.t.makeBackground(GUI.t.getWidth(),GUI.t.getHeight(),true);                
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "File must be either jpg or gif");
                }
                
            }

        }
    }
    public void mouseClicked(MouseEvent e){}
    
    public void mouseMoved(MouseEvent e)
    {
        selected=(e.getY()/60);
        repaint();
        if(e.getY()/60+1<=numImages)
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            
        }        
        else
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }
    public void mouseDragged(MouseEvent e){}
    
}
