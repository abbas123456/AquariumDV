package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This panel is used as a dialog box and appears at the begining of the learning activity 
 */
public class Message1 extends JPanel implements ActionListener
{
    private String s="";
    private String s1="";
    private JButton b1;
    private int x=0;
    private int type=0;
    
    public Message1(String s)
    {        
        this.s=s;
        setLayout(null);
        b1 = new JButton("Ok");
        b1.setFont(new Font("Comic Sans MS",0,22));
        b1.setForeground(Color.BLUE);
        b1.setBounds(230,80,140,35);
        b1.addActionListener(this);        
        add(b1);
        setBackground(new Color(251,197,210));
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Comic Sans MS",0,24));
        g.drawString(s, x, 40);  
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",0,20));
        g.drawString(s1,x+100,70);
    }
    
    public void setType(int type)
    {
        this.type=type;
    }
    
    public void setString(String s)
    {
        this.s=s;
        if(type==1)
            x=30;
        
        repaint();
    }
    
    public void setString1(String s)
    {
        this.s1=s;
        if(type==1)
            x=30;
        
        repaint();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {            
            GUI.message.hide();
            if(GUI.g.isVisible())
                GUI.g.requestFocus();
            //GUI.card2.previous(GUI.p4);
        }              
    }
    public static void main(String[] args)
    {
        JDialog d = new JDialog();
        d.setSize(600,150);
        d.add(new Message1("Oops, the fish have all died"));
        d.show();
    }
}
