package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This panel is used to display the set of learning facts
 */
public class Lesson extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private JButton b1,b2,b3,b4;
    private Image i,i1,i2,i3;
    private ImageIcon i5;
    
    private int selected=0;
    private int pageNumber=1;
    private int numPages=4;
    
    public Lesson()
    {
        setLayout(new BorderLayout());
        i= new ImageIcon(this.getClass().getResource("resources/foodchain.gif")).getImage();
        i1= new ImageIcon(this.getClass().getResource("resources/foodchain1.gif")).getImage();
        i2= new ImageIcon(this.getClass().getResource("resources/foodchain2.gif")).getImage();
        i3= new ImageIcon(this.getClass().getResource("resources/foodchain_animation.gif")).getImage();
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2));
        b1 = new JButton("Activity");
        b1.setFont(new Font("Comic Sans MS",0,20));
        b1.setForeground(Color.BLUE);
        b1.addActionListener(this);
        b2 = new JButton("Quiz");
        b2.setFont(new Font("Comic Sans MS",0,20));        
        b2.setForeground(Color.BLUE);
        b2.addActionListener(this);
        b3 = new JButton("Main Menu");
        b3.setFont(new Font("Comic Sans MS",0,20));        
        b3.setForeground(Color.BLUE);
        b3.addActionListener(this);        
        b4 = new JButton("Help");
        b4.setFont(new Font("Comic Sans MS",0,20));        
        b4.setForeground(Color.BLUE);
        b4.addActionListener(this);  
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        add(p1,"South");    
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(251,197,210));
        g.fillRect(0, 0, 600, 700);
        
        g.setColor(Color.BLUE);
        g.setFont(new Font("Comic Sans MS",0,36));
        g.drawString("Food Chains", 200, 40);
        
        if(pageNumber==1)
        {
            g.setFont(new Font("Comic Sans MS",1,24));
            g.setColor(Color.BLACK);
            g.drawString("What is a food chain?", 30, 100);
            g.setFont(new Font("Comic Sans MS",0,24));
            g.fillOval(10, 137, 10, 10);
            g.drawString("A food chain is a link between animals and plants.", 30, 150);
            g.drawImage(i, 150, 200, null);             
        }
        else if(pageNumber==2)
        {
            g.setFont(new Font("Comic Sans MS",1,24));
            g.setColor(Color.BLACK);
            g.drawString("What is a food chain?", 30, 100);
            g.setFont(new Font("Comic Sans MS",0,24));
            g.fillOval(10, 137, 10, 10);
            g.drawString("A food chain normally starts with a green plant.", 30, 150);
            g.fillOval(10, 187, 10, 10);
            g.drawString("Animals get energy by eating plants or other",30,200);
            g.drawString("animals.", 30, 230);
            g.fillOval(10, 267, 10, 10);
            g.drawString("If part of the chain is broken, the rest of the",30,280);
            g.drawString("chain will become broken:", 30, 310);
            g.drawImage(i3, 25, 335, this);             
        }
        else if(pageNumber==3)
        {
            g.setFont(new Font("Comic Sans MS",1,24));
            g.setColor(Color.BLACK);
            g.drawString("Producers and consumers", 30, 100);
            g.setFont(new Font("Comic Sans MS",0,24));
            g.fillOval(10, 137, 10, 10);
            g.drawString("A plant is a producer because it makes its own", 30, 150);
            g.drawString("food.", 30, 180);
            g.fillOval(10, 217, 10, 10);
            g.drawString("An animal is a consumer because it eat plants",30,230);
            g.drawString("or other animals.", 30, 260);
            g.drawImage(i1, 100, 300, null);
        }
        else if(pageNumber==4)
        {
            g.setFont(new Font("Comic Sans MS",1,24));
            g.setColor(Color.BLACK);
            g.drawString("Predator and prey", 30, 100);
            g.setFont(new Font("Comic Sans MS",0,24));
            g.fillOval(10, 137, 10, 10);
            g.drawString("A predator eats other animals.", 30, 150);
            g.fillOval(10, 187, 10, 10);
            g.drawString("Animals that get eaten are called prey.",30,200);
            g.drawImage(i2, 100, 300, null);
        }
        Polygon triangle,triangle2;
        int[]x1 = new int[3];
        int[]y1 = new int[3];
        int[]x2 = new int[3];
        int[]y2 = new int[3];
        x1[0]=20;x1[1]=5;x1[2]=20;
        y1[0]=585;y1[1]=600;y1[2]=615;
        x2[0]=575;x2[1]=590;x2[2]=575;
        y2[0]=585;y2[1]=600;y2[2]=615;
        triangle = new Polygon(x1,y1,3);
        triangle2 = new Polygon(x2,y2,3);
        
        if(selected==1)
        {
            if(pageNumber==1)
                g.setColor(Color.GRAY);
            else
                g.setColor(Color.BLUE);
            g.fillPolygon(triangle);
            g.fillRect(20, 596, 60, 10);
            if(pageNumber==numPages)
                g.setColor(Color.GRAY);
            else
                g.setColor(new Color(200,33,76));
            g.fillPolygon(triangle2);
            g.fillRect(515, 596, 60, 10);
        }
        else if(selected==2)
        {
            if(pageNumber==1)
                g.setColor(Color.GRAY);
            else
                g.setColor(new Color(200,33,76));
            g.fillPolygon(triangle);
            g.fillRect(20, 596, 60, 10);
            if(pageNumber==numPages)
                g.setColor(Color.GRAY);
            else
                g.setColor(Color.BLUE);
            g.fillPolygon(triangle2);
            g.fillRect(515, 596, 60, 10);
        }
        else
        {
            if(pageNumber==1)
                g.setColor(Color.GRAY);
            else
                g.setColor(new Color(200,33,76));
            g.fillPolygon(triangle);
            g.fillRect(20, 596, 60, 10);
            if(pageNumber==numPages)
                g.setColor(Color.GRAY);
            else
                g.setColor(new Color(200,33,76));
            g.fillPolygon(triangle2);
            g.fillRect(515, 596, 60, 10);
        }
    }
    
    public void reset()
    {
        pageNumber=1;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            GUI.lesson.hide();
            GUI.g.startActivity();                
        }
        else if(e.getSource()==b2)
        {
            GUI.lesson.hide();
            GUI.quiz.show();
        }
        
        else if(e.getSource()==b3)
        {
            GUI.lesson.hide();
            GUI.initial.show();
        }
        else if(e.getSource()==b4)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(new URI("http://www.dcs.qmul.ac.uk/~mya3/project/user_manual/Manual#label7"));
            } 
            catch(Exception e1){}
        }
    }
    public void mouseExited(MouseEvent e){}      
    public void mouseEntered(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        if(e.getX()>5&&e.getX()<80&&e.getY()>585&&e.getY()<615)
        {
            if(pageNumber>1)
                pageNumber--;
        }
        else if(e.getX()>515&&e.getX()<595&&e.getY()>585&&e.getY()<615)
        {
            if(pageNumber<numPages)
                pageNumber++;
        }
        
        repaint();
    }
       
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        if(e.getX()>5&&e.getX()<80&&e.getY()>585&&e.getY()<615)
        {
            selected=1;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>515&&e.getX()<595&&e.getY()>585&&e.getY()<615)
        {
            selected=2;
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
