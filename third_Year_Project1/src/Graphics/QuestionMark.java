package Graphics;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Mohammad Abbas
 * This jlabel is used to represent a question mark and is placed on the connect to datasource panels to open the user manual
 */
public class QuestionMark extends JLabel
{
    private boolean over=false;
    
    public QuestionMark()
    {
        super(" ");
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(new Color(153,217,234));
        g.fillOval(this.getWidth()/2-10,0, 20,20);
        g.setFont(new Font("Arial",1,16));
        
        if(over)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);
        g.drawString("?",this.getWidth()/2-5,this.getHeight()/2+5);
    }
    
    public void setOver(boolean b)
    {
        over=b;
        repaint();
    }

}
