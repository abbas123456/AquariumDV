package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This panel is used to display the sixth question of the quiz
 */
public class Question6 extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private JButton b1,b2,b3,b4;
    private int marked=0;    
    private int x1,y1,x2,y2;
    private Image i1,i2;
    private int moving=0;
    private int total=0;
    private boolean ready=false;
    private boolean moved1=false;
    private boolean moved2=false;
    
    public Question6()
    {
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2));
        b1 = new JButton("Facts");
        b1.setFont(new Font("Comic Sans MS",0,20));
        b1.setForeground(Color.BLUE);
        b1.addActionListener(this);
        b2 = new JButton("Activity");
        b2.setFont(new Font("Comic Sans MS",0,20));        
        b2.setForeground(Color.BLUE);
        b2.addActionListener(this);
        b3 = new JButton("Main menu");
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
        x1 = 30;
        y1 = 300;
        x2 = 30;
        y2 = 360;
        i1 = new ImageIcon(this.getClass().getResource("resources/pred.jpg")).getImage();
        i2 = new ImageIcon(this.getClass().getResource("resources/prey.jpg")).getImage();        
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(251,197,210));
        g.fillRect(0, 0, 520, 500);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp=null;
        gp = new GradientPaint(430,10,Color.BLUE,430,45,Color.cyan); 
        g2.setPaint(gp);        
        g2.fillRoundRect(430, 10, 75, 55, 20,20);
        gp = new GradientPaint(183,310,Color.BLUE,183,345,Color.cyan); 
        g.setColor(Color.WHITE);
        g.drawLine(430, 38, 505, 38);
        g.setFont(new Font("Arial",0,20));
        g.drawString("Score", 440 , 30);
        g.drawString(GUI.quizTotal+"", 463 , 60);
        
        g.setColor(Color.BLUE);
        g.setFont(new Font("Comic Sans MS",0,36));
        g.drawString("Question 6", 20, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",0,22));
        g.drawString("Put the pictures in the correct groups", 20, 100);
        g.setColor(Color.BLUE);
        g.drawString("Predator",70,150);
        g.drawString("Prey", 370, 150);        
        
        if(!ready)
        {
            if(moved1&&moved2)
            {
                g2.setPaint(gp);        
                g2.fillRoundRect(183, 310, 150, 55, 20,20);
                g.setColor(Color.WHITE);            
            }        
            else
            {
                g.setColor(Color.GRAY);
                g.fillRoundRect(183, 310, 150, 55, 20,20);
                g.setColor(Color.DARK_GRAY);                
            }
            
            g.setFont(new Font("Arial",0,20));
            g.drawString("Check answer", 195, 345);    
        }
        
        if(ready)
        {
            if(marked==1)
            {
                g2.setPaint(gp);        
                g2.fillRoundRect(183, 310, 150, 55, 20,20);
                g.setColor(Color.BLACK);   
                g.drawString("Correct, you scored a total of "+GUI.quizTotal, 100, 300);  
                g.setFont(new Font("Arial",0,20));
                g.setColor(Color.WHITE);   
                g.drawString("Start again", 210, 345);     
            }
            else if(marked==0)
            {
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(10.0f));
                g2.drawLine(200, 290, 310, 400);
                g2.drawLine(200, 400, 310, 290);            
                g2.setStroke(new BasicStroke(1.0f));
                g2.setColor(Color.BLACK);
                g2.drawString("Try again", 210,355);
            }            
        }
        
        
        g.drawImage(i1, this.x1, this.y1, null);
        g.drawImage(i2, this.x2, this.y2, null);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5.0f));
        g2.drawLine(260, 140, 260, 260);
        g2.setStroke(new BasicStroke(1.0f));
        
        
        
    }
    public void reset()
    {
        GUI.quizTotal=0;
        marked=0;
        ready=false;
        moved1=false;
        moved2=false;        
        x1 = 30;
        y1 = 300;
        x2 = 30;
        y2 = 360;
        GUI.card3.show(GUI.p5,"1");
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            reset();
            GUI.quiz.hide();
            GUI.lessonPanel.reset();
            GUI.lesson.show();
        }
        else if(e.getSource()==b2)
        {
            reset();
            GUI.quiz.hide();
            GUI.g.startActivity();            
        }        
        
        else if(e.getSource()==b3)
        {
            reset();
            GUI.quiz.hide();
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
    public void mouseReleased(MouseEvent e)
    {
        moving=0;
    }
    public void mousePressed(MouseEvent e)
    {
        if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&(moved1&&moved2)&&!ready)
        {
            if(this.x1+i1.getWidth(null)<260&&this.x2>265
                &&this.y1+i1.getHeight(null)<300&&this.y2+i2.getHeight(null)<300)
            {
                marked=1;
                GUI.quizTotal++;               
            }
            else
                marked=0;
            
            ready=true;
        }
        else if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&ready&&marked==1)
        {
            marked=0;
            ready=false;
            moved1=false;
            moved2=false;
            x1 = 30;
            y1 = 300;
            x2 = 30;
            y2 = 360;
            GUI.quizTotal=0;            
            GUI.card3.show(GUI.p5,"1");
        }
        
        repaint();
        
    }
       
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {        
        if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&(moved1&&moved2)&&!ready)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }        
        else if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&ready&&marked==1)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }    
        else
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));            
        }        
        
        repaint();
    }
    public void mouseDragged(MouseEvent e)
    {
        if(e.getX()>x1&&e.getX()<x1+i1.getWidth(null)&&e.getY()>y1&&e.getY()<y1+i1.getHeight(null))
        {
            moving=1;
            moved1=true;
            if(ready&&marked==0)
            {
                ready=false;
            }
        }
        else if(e.getX()>x2&&e.getX()<x2+i2.getWidth(null)&&e.getY()>y2&&e.getY()<y2+i2.getHeight(null))
        {
            moving=2;
            moved2=true;
            if(ready&&marked==0)
            {
                ready=false;
            }
        }        
        
        if(marked==0)
        {
            if(moving==1)        
            {
                x1=e.getX()-i1.getWidth(null)/2;
                y1=e.getY()-i1.getHeight(null)/2;
            }
            else if(moving==2)
            {
                x2=e.getX()-i2.getWidth(null)/2;
                y2=e.getY()-i2.getHeight(null)/2;;
            }

            repaint();
        }
    }
    
        

}
