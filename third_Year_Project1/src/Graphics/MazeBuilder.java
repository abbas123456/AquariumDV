package Graphics;
import javax.swing.*;
import java.awt.*;   
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This panel is used as a dialog box when the user is building a maze
 */
public class MazeBuilder extends JPanel implements ActionListener
{
    public static JRadioButton b1,b2,b3,b4;
    private MazeBuilderPanel mp;
    private JButton b;
    
    public MazeBuilder()
    {
        setSize(318,360);
        setLayout(new BorderLayout());
        mp = new MazeBuilderPanel(this);
        add(mp,BorderLayout.CENTER);
        JPanel p = new JPanel(new GridLayout(2,1));
        JPanel p1 = new JPanel(new GridLayout(1,6));
        JPanel p2 = new JPanel();        
        b1=new JRadioButton();
        b2=new JRadioButton();
        b3=new JRadioButton();
        b4=new JRadioButton();
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        bg.add(b4);
        b1.setSelected(true);        
        b=new JButton("Done");
        b.addActionListener(this);
        
        p1.add(new JLabel("Wall"));
        p1.add(b1);
        p1.add(new JLabel("Path"));
        p1.add(b2);
        p1.add(new JLabel("Start"));
        p1.add(b3);
        p1.add(new JLabel("Finish"));
        p1.add(b4);        
        p2.add(b);        
        p.add(p1);
        p.add(p2);       
        
        add(p,BorderLayout.SOUTH);
        setVisible(true);
    }
    
    public MazeBuilderPanel getPanel()
    {
        return mp;
    }
   
    public void actionPerformed(ActionEvent e)
    {
        mp.connections();
    }

}
