package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

/**
 *
 * @author Mohammad abbas
 * This panel is used to display the fourth question of the quiz
 */
public class Question4 extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private JButton b1,b2,b3,b4;
    private int marked=0;
    private boolean arrow=false;
    private int x1,y1,x2,y2,x3,y3;
    private Image i1,i2,i3;
    private int moving=0;
    private int total=0;
    private boolean ready=false;
    private boolean moved1=false;
    private boolean moved2=false;
    private boolean moved3=false;
    
    public Question4()
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
        x2 = 80;
        y2 = 300;
        x3 = 80;
        y3 = 360;
        i1 = new ImageIcon(this.getClass().getResource("resources/plant_1.jpg")).getImage();
        i2 = new ImageIcon(this.getClass().getResource("resources/pred.jpg")).getImage();
        i3 = new ImageIcon(this.getClass().getResource("resources/prey.jpg")).getImage();
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
        g.drawString("Question 4", 20, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",0,22));
        g.drawString("Put the pictures in the right part of the chain", 20, 100);
        
        if(!ready)
        {
            if(moved1&&moved2&&moved3)
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
        
        Polygon triangle,triangle2,triangle3;
        int[]x1 = new int[3];
        int[]y1 = new int[3];
        int[]x2 = new int[3];
        int[]y2 = new int[3];
        int[]x3 = new int[3];
        int[]y3 = new int[3];
        
        x1[0]=475;x1[1]=490;x1[2]=475;
        y1[0]=385;y1[1]=400;y1[2]=415;
        
        x2[0]=375;x2[1]=390;x2[2]=375;
        y2[0]=185;y2[1]=200;y2[2]=215;
        
        x3[0]=175;x3[1]=190;x3[2]=175;
        y3[0]=185;y3[1]=200;y3[2]=215;
        g.setColor(Color.BLACK);
        
        g.fillRect(315, 196, 60, 10);
        g.fillRect(115, 196, 60, 10);
        
        triangle = new Polygon(x1,y1,3);
        triangle2 = new Polygon(x2,y2,3);
        triangle3 = new Polygon(x3,y3,3);
        g.fillPolygon(triangle2);
        g.fillPolygon(triangle3);
        
        if(ready)
        {
            if(marked==1)
            {
                g2.setColor(Color.green.darker());
                g2.setStroke(new BasicStroke(10.0f));
                g2.drawLine(190, 360, 230, 400);
                g2.drawLine(230, 400, 310, 290);

                g2.setStroke(new BasicStroke(1.0f));                
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
            if(arrow)
                    g.setColor(Color.BLUE);
                else
                    g.setColor(new Color(200,33,76));
                g.fillPolygon(triangle);
                g.fillRect(415, 396, 60, 10);
        }
        g.drawImage(i1, this.x1, this.y1, null);
        g.drawImage(i3, this.x3, this.y3, null);
        g.drawImage(i2, this.x2, this.y2, null);
        
        
    }
    public void reset()
    {
        GUI.quizTotal=0;
        marked=0;
        arrow=false;
        ready=false;
        moved1=false;
        moved2=false;
        moved3=false;
        x1 = 30;
        y1 = 300;
        x2 = 80;
        y2 = 300;
        x3 = 80;
        y3 = 360;
        GUI.card3.show(GUI.p5,"1");
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            reset();
            GUI.lesson.show();
            GUI.lessonPanel.reset();
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
        if(e.getX()>415&&e.getX()<495&&e.getY()>385&&e.getY()<415&&ready)
        {            
            marked=0;
            arrow=false;
            ready=false;
            moved1=false;
            moved2=false;
            moved3=false;
            x1 = 30;
            y1 = 300;
            x2 = 80;
            y2 = 300;
            x3 = 80;
            y3 = 360;
            GUI.card3.show(GUI.p5,"5");
        }
        else if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&(moved1&&moved2&&moved3)&&!ready)
        {
            if(this.x1+i1.getWidth(null)<115&&this.x3>190&&this.x3+i3.getWidth(null)<315&&this.x2>375
                &&this.y1+i1.getHeight(null)<300&&this.y2+i2.getHeight(null)<300&&this.y3+i3.getHeight(null)<300)
            {
                marked=1;
                GUI.quizTotal++;
            }
            else
                marked=0;
            
            ready=true;
        }       
          
        repaint();
    }
       
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
        if(e.getX()>415&&e.getX()<495&&e.getY()>385&&e.getY()<415&&ready)
        {            
            arrow=true;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&(moved1&&moved2&&moved3)&&!ready)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }        
        else
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            arrow=false;
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
        else if(e.getX()>x3&&e.getX()<x3+i3.getWidth(null)&&e.getY()>y3&&e.getY()<y3+i3.getHeight(null))
        {
            moving=3;
            moved3=true;
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
            else if(moving==3)
            {
                x3=e.getX()-i3.getWidth(null)/2;
                y3=e.getY()-i3.getHeight(null)/2;;
            }
            repaint();
        }
    }
    
        

}
