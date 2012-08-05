package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This panel is used to represent the set of button at the bottom of the tank during the activity
 */
public class KidsPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private JButton b1,b2,b3,b4,b5;
    
    public KidsPanel()
    {
        setLayout(new GridLayout(1,3,10,50));        
        b1 = new JButton("Facts");
        b1.setFont(new Font("Comic Sans MS",0,24));
        b1.setForeground(Color.BLUE);
        b1.addActionListener(this);
        b2 = new JButton("Try again");
        b2.setFont(new Font("Comic Sans MS",0,24));
        b2.setForeground(Color.BLUE);
        b2.addActionListener(this);
        b3 = new JButton("Quiz");
        b3.setFont(new Font("Comic Sans MS",0,24));
        b3.setForeground(Color.BLUE);
        b3.addActionListener(this);
        b4 = new JButton("Main Menu");
        b4.setFont(new Font("Comic Sans MS",0,24));
        b4.setForeground(Color.BLUE);
        b4.addActionListener(this);
        b5 = new JButton("Help");
        b5.setFont(new Font("Comic Sans MS",0,24));
        b5.setForeground(Color.BLUE);
        b5.addActionListener(this);
        
        add(b1);
        add(b3);
        add(b2);
        add(b4);
        add(b5);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            GUI.g.startAgain();
            GUI.lessonPanel.reset();
            GUI.lesson.show();
        }
        
        else if(e.getSource()==b2)
        {
            GUI.g.createShoals(GUI.g.numShoals);
            GUI.g.initialiseShoals();
            GUI.g.t.addFishPics();
            GUI.shoalThreads.get(0).suspend();
            GUI.shoalThreads.get(1).suspend();
            GUI.g.t.paused=true;
            GUI.g.requestFocus(); 
        }
        
        else if(e.getSource()==b3)
        {
            GUI.g.startAgain();
            GUI.quiz.show();
        }
        else if(e.getSource()==b4)
        {
            GUI.g.startAgain();
            GUI.initial.show();
        }
        else if(e.getSource()==b5)
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
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e)
    {
        
    }
    
}
