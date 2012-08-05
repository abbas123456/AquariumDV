package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This jpanel is used as the main menu for the program and allows the user to go to different parts of the simulation
 */
public class SplashSreen extends JPanel implements MouseListener, MouseMotionListener
{
    private String path;
    private Image i;
    private Image  i2;
    private int x=0;
    private int y=0;
    private int selected=0;
    private int size=20;
    
    public SplashSreen()
    {
        this.path=path;
        i = new ImageIcon(this.getClass().getResource("resources/logo 2.jpg")).getImage();     
        addMouseListener(this);        
        addMouseMotionListener(this);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(i, 0, 0, null);
        if(selected==1)
            g.setColor(Color.WHITE);
        else
            g.setColor(new Color(245,228,156));
        g.setFont(new Font("Arial",1,35));
        g.drawString("Visualise", 95, 290); 
        if(selected==2)
            g.setColor(Color.WHITE);
        else
            g.setColor(new Color(245,228,156));
        g.setFont(new Font("Arial",1,35));
        g.drawString("A-Life", 280, 200); 
        if(selected==3)
            g.setColor(Color.WHITE);
        else
            g.setColor(new Color(245,228,156));
        g.setFont(new Font("Comic Sans MS",1,43));
        g.drawString("Facts", 248,394); 
        if(selected==4)
            g.setColor(Color.WHITE);
        else
            g.setColor(new Color(245,228,156));
        g.setFont(new Font("Comic Sans MS",1,40));
        g.drawString("Activity", 420,300);
        if(selected==5)
            g.setColor(Color.WHITE);
        else
            g.setColor(new Color(245,228,156));
        g.setFont(new Font("Comic Sans MS",1,42));
        g.drawString("Quiz", 560,425); 
        
    }
    
    public void mouseExited(MouseEvent e){}      
    public void mouseEntered(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        selected=0;
            
        if(e.getX()>95&&e.getX()<240&&e.getY()>260&&e.getY()<290)
        {
            GUI.numShoals=0;
            GUI.numFish=7;
            GUI.foraging=true;
            GUI.foragingMenu.setState(!GUI.foraging);
            GUI.intelligence=true;
            GUI.kids=false;
            if(GUI.t!=null)
                GUI.t.kids=false;
            GUI.initial.hide();
            GUI.g.startActivity();
            GUI.DB.show();
        }
        else if(e.getX()>280&&e.getX()<375&&e.getY()>170&&e.getY()<200)
        {
            GUI.numShoals=9;
            GUI.numFish=7;
            GUI.foraging=true;
            GUI.foragingMenu.setState(!GUI.foraging);
            GUI.intelligence=true;
            GUI.kids=false;          
            if(GUI.t!=null)
                GUI.t.kids=false;
            GUI.initial.hide();
            GUI.g.startActivity();
        }
        else if(e.getX()>250&&e.getX()<355&&e.getY()>360&&e.getY()<395)
        {
            GUI.kids=true;
            if(GUI.t!=null)
                GUI.t.kids=true;
            GUI.initial.hide();
            GUI.lesson.show();
        }
        else if(e.getX()>420&&e.getX()<570&&e.getY()>270&&e.getY()<300)
        {
            GUI.kids=true;
            if(GUI.t!=null)
                GUI.t.kids=true;
            GUI.initial.hide();
            GUI.g.startActivity();

        }
        else if(e.getX()>560&&e.getX()<650&&e.getY()>390&&e.getY()<425)
        {
            GUI.kids=true;
            if(GUI.t!=null)
                GUI.t.kids=true;
            GUI.initial.hide();
            GUI.quiz.show();
        }
        
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        if(e.getX()>95&&e.getX()<240&&e.getY()>260&&e.getY()<290)
        {
            selected=1;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>280&&e.getX()<375&&e.getY()>170&&e.getY()<200)
        {
            selected=2;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>250&&e.getX()<355&&e.getY()>360&&e.getY()<395)
        {
            selected=3;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>420&&e.getX()<570&&e.getY()>270&&e.getY()<300)
        {
            selected=4;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>560&&e.getX()<650&&e.getY()>390&&e.getY()<425)
        {
            selected=5;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else
        {
            selected=0;
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        repaint();
    }
    public void mouseDragged(MouseEvent e){}
}

