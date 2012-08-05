package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*; 
/**
 *
 * @author Mohammad Abbas
 * This is the panel gives the user the options to change different attributes of the shoal
 */
public class ChangeParameters extends JPanel implements ChangeListener, ActionListener
{
    private int index=-1;
    private SpinnerNumberModel model,model1;
    private JSpinner modelS,model1S;
    private JButton colour,colour2,ok,cancel,delete;
    private JColorChooser tcc;
    private JDialog CP;
    private JComboBox comboBox;
    private JLabel l1,l2,l3,l4,l5;
    private JSlider slider1,slider2;
    private JPanel p;

    public ChangeParameters()
    {
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(new GridLayout(6,2));
       
        setLayout(new BorderLayout());
        model = new SpinnerNumberModel(0, 0, 300, 1.0f);
        model1 = new SpinnerNumberModel(0, 0, 300, 1.0f); 
        modelS = new JSpinner(model);
        model1S = new JSpinner(model1);
        slider1 = new JSlider(JSlider.HORIZONTAL,0,300,0);
        slider2 = new JSlider(JSlider.HORIZONTAL,0,300,0);
        slider1.addChangeListener(this);
        slider2.addChangeListener(this);
        model.addChangeListener(this);
        model1.addChangeListener(this);
        colour = new JButton();
        colour2 = new JButton();
        colour.setToolTipText("Click to change the first colour");
        colour2.setToolTipText("Click to change the second colour");
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        delete = new JButton("Delete");
        delete.setToolTipText("Remove the shoal");
        ok.addActionListener(this);
        cancel.addActionListener(this);
        delete.addActionListener(this);
        String[]s = {"Gradient 1","Gradient 2","Striped"};
        comboBox = new JComboBox(s);
        comboBox.addActionListener(this);
        colour.addActionListener(this);
        colour2.addActionListener(this);
        l1 = new JLabel("Eyesight");
        l2 = new JLabel("Speed");
        l3 = new JLabel("Colour 1");
        l4 = new JLabel("Colour 2");
        l5 = new JLabel("Color type");
        change();        
    }
    
    public void change()    //called when simulation changes from learning to normal and vice versa
    {
        p.removeAll();
        removeAll();
        if(GUI.kids)
        {
            l1.setFont(new Font("Comic Sans MS",0,24));
            l1.setForeground(Color.BLUE);
            l2.setFont(new Font("Comic Sans MS",0,24));
            l2.setForeground(Color.BLUE);
            l3.setFont(new Font("Comic Sans MS",0,24));
            l3.setForeground(Color.BLUE);
            l4.setFont(new Font("Comic Sans MS",0,24));
            l4.setForeground(Color.BLUE);
            l5.setFont(new Font("Comic Sans MS",0,24));
            l5.setForeground(Color.BLUE);     
            ok.setFont(new Font("Comic Sans MS",0,24));
            ok.setForeground(Color.BLUE);     
            cancel.setFont(new Font("Comic Sans MS",0,24));
            cancel.setForeground(Color.BLUE);     
            comboBox.setFont(new Font("Comic Sans MS",0,24));
        }
        else
        {
            l1.setFont(null);
            l1.setForeground(Color.BLACK);
            l2.setFont(null);
            l2.setForeground(Color.BLACK);
            l3.setFont(null);
            l3.setForeground(Color.BLACK);
            l4.setFont(null);
            l4.setForeground(Color.BLACK);
            l5.setFont(null);
            l5.setForeground(Color.BLACK);     
            ok.setFont(null);
            ok.setForeground(Color.BLACK);     
            cancel.setFont(null);
            cancel.setForeground(Color.BLACK);     
            comboBox.setFont(null);
        }
        
        p.add(l1);
        if(GUI.kids)
            p.add(slider1);
        else
            p.add(modelS);        
        p.add(l2);
        if(GUI.kids)
            p.add(slider2);
        else
            p.add(model1S);        
        
        if(!GUI.kids)
        {
            p.add(l3);        
            p.add(colour);
            p.add(l4);
            p.add(colour2);
            p.add(l5);
            p.add(comboBox);
        }
        
        p.add(ok);        
        p.add(cancel);
        add("Center",p);
        if(!GUI.kids)
            add("South",delete);
        p.validate();
        validate();
               
    }
    
    public void setIndex(int index, int n)
    {
        this.index=index;
        model.setValue(Math.round(GUI.shoals.get(index).getFish().get(n).getEyeStrength()));
        model1.setValue(Math.round(GUI.shoals.get(index).getFish().get(n).getSpeed()*100));
        slider1.setValue((int)GUI.shoals.get(index).getFish().get(n).getEyeStrength());
        slider2.setValue((int)GUI.shoals.get(index).getFish().get(n).getSpeed()*100);
        colour.setBackground(GUI.shoals.get(index).getFish().get(n).getColor1());
        colour2.setBackground(GUI.shoals.get(index).getFish().get(n).getColor2());
        comboBox.setSelectedIndex(GUI.shoals.get(index).getFish().get(n).getColorType());
        if(GUI.connected)
        {
            modelS.setEnabled(false);
            model1S.setEnabled(false);
            delete.setEnabled(false);
        }
        else if(!GUI.connected)
        {
            modelS.setEnabled(true);
            model1S.setEnabled(true);
            delete.setEnabled(true);
            if(!GUI.foraging&&index==0)
            {
                delete.setEnabled(false);
            }
        
        }
    }
    
    public void stateChanged(ChangeEvent e)
    {
        if(e.getSource()==model)
        {
            for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
            {
                GUI.testShoal.get(0).getFish().get(j).setEyeStrength(Float.parseFloat(model.getValue()+""));              
            }
        }
        
        else if(e.getSource()==model1)
        {
            for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
            {
                GUI.testShoal.get(0).getFish().get(j).setSpeed(Float.parseFloat(model1.getValue()+"")/100);                        
            }           
        }
        
        else if(e.getSource()==slider1)
        {
            for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
            {
                GUI.testShoal.get(0).getFish().get(j).setEyeStrength(Float.parseFloat(slider1.getValue()+""));              
            }
        }
        
        else if(e.getSource()==slider2)
        {
            for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
            {
                GUI.testShoal.get(0).getFish().get(j).setSpeed(Float.parseFloat(slider2.getValue()+"")/100);                        
            }
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
        {
            try
            {
                if(GUI.shoals.indexOf(GUI.testShoal.get(0).getFish().get(0).getShoal2())==index)
                {
                    for(int j=0;j<GUI.shoals.get(index).getFish().size();j++)
                    {
                        if(GUI.kids)
                        {
                            GUI.shoals.get(index).getFish().get(j).setEyeStrength(Float.parseFloat(slider1.getValue()+""));
                            GUI.shoals.get(index).getFish().get(j).setSpeed(Float.parseFloat(slider2.getValue()+"")/100);
                        }
                        else if(!GUI.kids&&!GUI.shoals.get(index).getFish().get(j).dying())
                        {
                            GUI.shoals.get(index).getFish().get(j).setEyeStrength(Float.parseFloat(model.getValue()+""));
                            GUI.shoals.get(index).getFish().get(j).setSpeed(Float.parseFloat(model1.getValue()+"")/100);                    
                        }
                        GUI.shoals.get(index).getFish().get(j).setColor1(colour.getBackground());
                        GUI.shoals.get(index).getFish().get(j).setColor2(colour2.getBackground());
                        GUI.shoals.get(index).getFish().get(j).setColorType(comboBox.getSelectedIndex());   
                    }
                    if(!GUI.kids)
                    {
                        GUI.g.addIcons();
                    }
                }
            }
            catch(Exception e1){e1.printStackTrace();}
            GUI.CP.hide();
            
        }
        
        else if(e.getSource()==cancel)
        {
            GUI.CP.hide();
        }
        
        else if(e.getSource()==delete)
        {
            GUI.numShoals--;
            GUI.shoalThreads.get(index).stop();
            GUI.shoalThreads.remove(index);
            GUI.shoals.remove(index);
            if(!GUI.kids)
            {
                GUI.g.addIcons();
            }
            GUI.CP.hide();
        }
        
        else if(e.getSource() instanceof JButton)
        {
            Color c = ((JButton)e.getSource()).getBackground();
            Color newColor = JColorChooser.showDialog(GUI.CP,"Choose Background Color",c);  //this brings up the colour chooser pallete
            if(newColor!=null)
            {
                if(e.getSource()==colour)
                {
                    for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
                    {
                        GUI.testShoal.get(0).getFish().get(j).setColor1(newColor);                        
                    }
                    colour.setBackground(newColor);
                }

                else if(e.getSource()==colour2)
                {
                    for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
                    {
                        GUI.testShoal.get(0).getFish().get(j).setColor2(newColor);                        
                    }
                    colour2.setBackground(newColor);
                }
            }
        }
        
        else if(e.getSource()==comboBox)
        {
            for(int j=0;j<GUI.testShoal.get(0).getFish().size();j++)
            {
                GUI.testShoal.get(0).getFish().get(j).setColorType(comboBox.getSelectedIndex());                        
            }
        }
        

    }
}
