package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This is a panel used to show the user a list of mazes to choose from or to choose to make their own maze
 */
public class InsertMaze extends JPanel implements MouseListener, MouseMotionListener{

    private int numMazes=3;
    private int selected=numMazes+1;
    private boolean over;
    
    public InsertMaze()
    {
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(selected<numMazes)
        {
            g.setColor(new Color(153,217,234));
            g.fillRect(40, selected*60, 95, 70);
        }
            
        g.setColor(Color.black);
        for(int i=0;i<numMazes;i++)
        {
            g.drawImage(new ImageIcon(this.getClass().getResource("resources/maze"+(i+1)+".jpg")).getImage(), 50, (i*60)+10, null);            
        }
        g.setColor(Color.BLUE);
        g.drawString("Or create your own...", 30,(numMazes*60)+25);
        g.drawLine(30, (numMazes*60)+28, 140, (numMazes*60)+28);
        
        g.setColor(Color.WHITE);
        g.setColor(new Color(153,217,234));
        g.fillOval(this.getWidth()/2-10,(numMazes*60)+35, 20,20);
        g.setFont(new Font("Arial",1,16));
        
        if(over)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);
        g.drawString("?",this.getWidth()/2-5,(numMazes*60)+50);
    }
    
    public void mouseExited(MouseEvent e){}      
    public void mouseEntered(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        if(e.getY()/60+1<=numMazes)
        {
            GUI.d.setVisible(false);
            selected=numMazes+1;
            GUI.t.addMaze("resources/maze"+((e.getY()/60)+1)+".pl");
            
        }
        else if(e.getY()<(numMazes*60)+30)
        {
            GUI.d.setVisible(false);
            selected=numMazes+1;
            GUI.CM.setSize(287,282);
            GUI.mB.b1.setSelected(true);
            GUI.mB.getPanel().addDots();
            GUI.CM.show();           
        }
        else
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(new URI("http://www.dcs.qmul.ac.uk/~mya3/project/user_manual/Manual#label4"));
            } 
            catch(Exception e1){}
        }
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        selected=(e.getY()/60);            
        over=false;
        if(e.getY()/60+1<=numMazes)
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            
        }        
        else if(e.getY()<(numMazes*60)+30)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            over=true;
        }
        repaint();        
    }
    public void mouseDragged(MouseEvent e){}
    
}
