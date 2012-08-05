package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This panel is used as a dialog box and appears when all the fish have died
 */
public class Message extends JPanel implements ActionListener
{
    private String s;
    private JButton b1,b2,b3;
    private int x=85;
    private int type=0;
    
    public Message(String s)
    {        
        this.s=s;
        setLayout(null);
        b1 = new JButton("Try Again");
        b1.setFont(new Font("Comic Sans MS",0,22));
        b1.setForeground(Color.BLUE);
        b1.setBounds(x-10,70,140,35);
        b1.addActionListener(this);
        b2 = new JButton("Quiz");
        b2.setFont(new Font("Comic Sans MS",0,22));
        b2.setForeground(Color.BLUE);
        b2.setBounds(x+12+135,70,115,35);
        b2.addActionListener(this);
        b3 = new JButton("Main menu");
        b3.setFont(new Font("Comic Sans MS",0,22));
        b3.setForeground(Color.BLUE);
        b3.setBounds(x+12+265,70,150,35);
        b3.addActionListener(this);
        add(b1);
        add(b2);
        add(b3);
        setBackground(new Color(251,197,210));
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Comic Sans MS",0,32));
        g.drawString(s, x, 40);        
    }
    
    public void setType(int type)
    {
        this.type=type;
    }
    
    public void setString(String s)
    {
        this.s=s;
        if(type==1)
            x=85;        
        repaint();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        GUI.message.hide();
        
        if(e.getSource()==b1)
        {
            GUI.g.createShoals(GUI.g.numShoals);
            GUI.g.initialiseShoals();
            GUI.g.t.addFishPics();
            GUI.shoalThreads.get(0).suspend();
            GUI.shoalThreads.get(1).suspend();
            GUI.g.t.paused=true;
            GUI.g.requestFocus();
            GUI.clock.setMessageSeen(false);
        }
        else if(e.getSource()==b2)
        {
            GUI.g.startAgain();
            GUI.quiz.show();
        }
        else if(e.getSource()==b3)
        {
            GUI.g.startAgain();
            GUI.initial.show();
        }
        
    }
    public static void main(String[] args)
    {
        JDialog d = new JDialog();
        d.setSize(600,150);
        d.add(new Message("Oops, the fish have all died"));
        d.show();
    }
}
