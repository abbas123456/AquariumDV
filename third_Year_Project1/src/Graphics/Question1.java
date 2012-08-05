package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This panel is used to display the first question of the quiz
 */
public class Question1 extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private JButton b1,b2,b3,b4;
    private int selected=0;
    private int marked=0;
    private boolean arrow=false;
    private int correct=1;
    private boolean ready=false;
    public Question1()
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
        
        if(marked==1)
            g.fillRoundRect(10,140,40,40,-10,-10);            
        else if(marked==2)
            g.fillRoundRect(10,190,40,40,-10,-10);            
        else if(marked==3)
            g.fillRoundRect(10,240,40,40,-10,-10);            
        
        if(!ready)
        {
            if(marked!=0)
            {
                g2.setPaint(gp);        
                g2.fillRoundRect(183, 310, 150, 55, 20,20);
            }        
            else
            {
                g.setColor(Color.GRAY);
                g.fillRoundRect(183, 310, 150, 55, 20,20);
            }
            if(marked==0)
                g.setColor(Color.DARK_GRAY);
            else
                g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",0,20));
            g.drawString("Check answer", 195, 345);
            
        
        }
        else
        {
            g.setColor(Color.GREEN.darker());
            
            if(correct==1)
                g.fillRoundRect(10,140,40,40,-10,-10);
            else if(correct==2)
                g.fillRoundRect(10,190,40,40,-10,-10);
            else if(correct==3)
                g.fillRoundRect(10,240,40,40,-10,-10);
        }
        
        g.setColor(Color.BLUE);
        g.setFont(new Font("Comic Sans MS",0,36));
        g.drawString("Question 1", 20, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",0,28));
        g.drawString("What is a food chain?", 20, 100);
        if(selected==1)
            g.setColor(Color.WHITE);
        else
            g.setColor(new Color(200,33,76));            
        
        g.fillRoundRect(15,145,30,30,-10,-10);        
        g.setColor(Color.BLACK);
        g.drawString("A", 20,170);
        g.drawString("A link between animals and plants", 60, 170);
        if(selected==2)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.CYAN);
        
        g.fillRoundRect(15,195,30,30,-10,-10);
        g.setColor(Color.BLACK);
        g.drawString("B", 20,220);
        g.drawString("A group of animals and plants", 60, 220);
        if(selected==3)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.YELLOW);
        
        g.fillRoundRect(15,245,30,30,-10,-10);
        g.setColor(Color.BLACK);
        g.drawString("C", 20,270);
        g.drawString("A group of animals", 60, 270);
        
        
        Polygon triangle;
        int[]x1 = new int[3];
        int[]y1 = new int[3];
        x1[0]=475;x1[1]=490;x1[2]=475;
        y1[0]=385;y1[1]=400;y1[2]=415;
        triangle = new Polygon(x1,y1,3);
        if(marked==correct&&ready)
        {
            g2.setColor(Color.GREEN.darker());
            g2.setStroke(new BasicStroke(10.0f));
            g2.drawLine(190, 360, 230, 400);
            g2.drawLine(230, 400, 310, 290);
            
            g2.setStroke(new BasicStroke(1.0f));
            if(arrow)
                g.setColor(Color.BLUE);
            else
                g.setColor(new Color(200,33,76));
            g.fillPolygon(triangle);
            g.fillRect(415, 396, 60, 10);
        }
        else if(marked!=0&&ready)
        {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(10.0f));
            g2.drawLine(200, 290, 310, 400);
            g2.drawLine(200, 400, 310, 290);
            
            g2.setStroke(new BasicStroke(1.0f));
            if(arrow)
                g.setColor(Color.BLUE);
            else
                g.setColor(new Color(200,33,76));
            g.fillPolygon(triangle);
            g.fillRect(415, 396, 60, 10);
        }
        
        
    }
    public void reset()
    {
        selected=0;
        marked=0;
        arrow=false;
        ready=false;
        GUI.quizTotal=0;
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
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        if(e.getX()>15&&e.getX()<45&&e.getY()>145&&e.getY()<175&&!ready)
        {
            selected=1;            
            marked=1;
        }
        else if(e.getX()>15&&e.getX()<45&&e.getY()>195&&e.getY()<225&&!ready)
        {
            selected=2;            
            marked=2;
        }
        else if(e.getX()>15&&e.getX()<45&&e.getY()>245&&e.getY()<275&&!ready)
        {
            selected=3;            
            marked=3;
        }
        else if(e.getX()>415&&e.getX()<495&&e.getY()>385&&e.getY()<415&&ready)
        {            
            selected=0;
            marked=0;
            arrow=false;
            ready=false;
            GUI.card3.show(GUI.p5,"2");
        }
        else if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&marked!=0&&!ready)
        {
            ready=true;
            if(marked==correct)
                GUI.quizTotal++;            
        }        
        repaint();
    }
       
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        if(e.getX()>15&&e.getX()<45&&e.getY()>145&&e.getY()<175&&!ready)
        {
            selected=1;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>15&&e.getX()<45&&e.getY()>195&&e.getY()<225&&!ready)
        {
            selected=2;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>15&&e.getX()<45&&e.getY()>245&&e.getY()<275&&!ready)
        {
            selected=3;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>415&&e.getX()<495&&e.getY()>385&&e.getY()<415&&ready)
        {            
            arrow=true;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(e.getX()>183 && e.getX()<333&&e.getY()>310&&e.getY()<365&&marked!=0&&!ready)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else
        {
            selected=0;
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            arrow=false;
        }        
        repaint();
        
    }
    public void mouseDragged(MouseEvent e){}
    
        

}
