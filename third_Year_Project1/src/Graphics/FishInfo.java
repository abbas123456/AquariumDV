package Graphics;
import Components.Fish;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Mohammad Abbas
 * This is a jlabel class that is uses to make the shoal keys at the bettom of the screen
 */
public class FishInfo extends JLabel
{

    private Color color1;
    private Color color2;
    private int colorType;
    private boolean over=false;
    private Fish f;
    private String s;
    
    public FishInfo(Fish f, String s)
    {
        super(" ");
        this.s=s;
        this.f=f;
        this.color1 = f.getColor1();
        this.color2 = f.getColor2();
        this.colorType = f.getColorType();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(over)
        {
            g.setColor(new Color(153,217,234));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        int [] x1 = new int[4];
        int [] y1 = new int[4];
        int length=18;
        int width=13;
        int x=(getWidth()/2)-(length/2)-20;
        int x2 = x+50;
        int y=0;
        int y2=11;
        
        Polygon triangle;
        
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gp=null;
        if(colorType==0)
        gp = new GradientPaint(x,y,color1,x,(y+width)-(width/5),color2); 
        else if(colorType==1)
        gp = new GradientPaint(x,y+width,color1,x+(length/5),y,color2); 
        else if(colorType==2)
        gp = new GradientPaint(x,y,color1,x+(length/5),y,color2,true); 
    		
        x1[0] = x+length+(length/3); x1[3] = x+length-(length/4); x1[2] = x+length+(length/3); x1[1] = x+length+(length/5);
        y1[0] = y+(width/5); y1[3] = y+(width/2); y1[2] = y+(width-(width/5)); y1[1] = y+(width/2);
                        
        triangle = new Polygon(x1,y1,4);
                
        g2.setPaint(gp);
        g2.fillOval(x,y,length,width);
        g2.fillPolygon(triangle);
        g.setColor(Color.black);
        g.fillOval(x+3,y+1,width/3,width/3);        
        g.setFont(new Font("Arial",0,14));
        g.drawString(s, x2, y2);    
        
    }
    
    public void updateDetails()
    {
        this.color1 = f.getColor1();
        this.color2 = f.getColor2();
        this.colorType = f.getColorType();
        repaint();        
    }
    
    public void over(boolean b)
    {
        over=b;
        repaint();
    }
    
            
}
