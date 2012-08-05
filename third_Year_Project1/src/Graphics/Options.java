package Graphics;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Mohammad Abbas
 * This panel is used to display the simulation options to the user in a dialog box
 */
public class Options extends JPanel implements ChangeListener, ActionListener
{
    private JRadioButton b1,b2,b3;
    private SpinnerNumberModel model,model1,model2;
    public JSpinner spinner,spinner1,spinner2;
    private JButton ok,cancel,apply;
    private JSlider slider;
    private JCheckBox checkBox,checkBox2;
    
    public Options()
    {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        int x=10;
        int y=10;
        JPanel p = new JPanel(new GridLayout(1,2,10,10));
        p.setBackground(Color.WHITE);
        JPanel p7 = new JPanel(new GridLayout(1,2));
        p7.setBackground(Color.WHITE);
        p7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JPanel p8 = new JPanel(new GridLayout(1,2));
        p8.setBackground(Color.WHITE);
        p8.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JPanel p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        JPanel p2 = new JPanel(new GridLayout(5,1));
        p2.setBackground(Color.WHITE);
        JPanel p3 = new JPanel(new GridLayout(4,1));
        p3.setBackground(Color.WHITE);
        JPanel p4 = new JPanel(new GridLayout(4,1));
        p4.setBackground(Color.WHITE);
        JPanel p6 = new JPanel(new FlowLayout());
        p6.setBackground(Color.WHITE);
        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(getWidth()/2,10));        
        empty.setBackground(Color.WHITE);
        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(0,10));
        empty1.setBackground(Color.WHITE);
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(0,100));
        empty2.setBackground(Color.WHITE);
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(0,20));
        empty3.setBackground(Color.WHITE);        
        Box b = Box.createVerticalBox();        
        JLabel l1 = new JLabel("Day or night:");
        b.add(empty1);
        b.add(l1);
        b.add(empty2);        
        b1 = new JRadioButton("Day");
        b1.setBackground(Color.white);
        b1.addActionListener(this);
        p2.add(b1);
        b2 = new JRadioButton("Night");
        b2.setBackground(Color.white);
        b2.addActionListener(this);
        p2.add(b2);
        b3 = new JRadioButton("Both");
        b3.setSelected(true);
        b3.setBackground(Color.white);
        b3.addActionListener(this);
        p2.add(b3);
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(b1);
        bg1.add(b2);
        bg1.add(b3);
        JLabel l2 = new JLabel("Feeding time:");
        b.add(l2);
        b.add(empty3);
        model = new SpinnerNumberModel(20, 0, 50, 1);
        spinner = new JSpinner(model);
        spinner.addChangeListener(this);
        p2.add(spinner);
        JLabel l3 = new JLabel("Food ratio:      ");
        b.add(l3);
        model1 = new SpinnerNumberModel(3, 1, 10, 1);
        spinner1 = new JSpinner(model1);
        spinner1.addChangeListener(this);
        p2.add(spinner1);
        JLabel l7 = new JLabel("Shoal size:");
        JLabel l4 = new JLabel("Simulation speed:");
        JLabel l5 = new JLabel("Schooling:");
        JLabel l6 = new JLabel("Intelligence:");
        p3.add(l7);
        p3.add(l4);
        p3.add(l5);
        p3.add(l6);
        model2 = new SpinnerNumberModel(15, 1, 15, 1);
        spinner2 = new JSpinner(model2);
        spinner2.addChangeListener(this);
        spinner2.setToolTipText("This will only take effect when a shoal is added or the mode is changed");
        slider = new JSlider(JSlider.HORIZONTAL,0,20,0);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        slider.setBackground(Color.WHITE);
        checkBox = new JCheckBox();        
        checkBox.addActionListener(this);
        checkBox.setBackground(Color.WHITE);
        checkBox2 = new JCheckBox();        
        checkBox2.addActionListener(this);
        checkBox2.setBackground(Color.WHITE);
        p4.add(spinner2);
        p4.add(slider);
        p4.add(checkBox);
        p4.add(checkBox2);
        ok = new JButton("OK");
        ok.addActionListener(this);
        p6.add(ok);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        p6.add(cancel);
        apply = new JButton("Apply");
        apply.setEnabled(false);
        apply.addActionListener(this);
        p6.add(apply);
        p1.add(b);
        p7.add(p1);
        p7.add(p2);
        p8.add(p3);
        p8.add(p4);
        p.add(p7);
        p.add(p8);
        add(p,BorderLayout.CENTER);
        add(p6,BorderLayout.SOUTH);        
    }    
    
    public void setOptions()
    {
        b1.setSelected(GUI.clock.getLightOption()==1);
        b2.setSelected(GUI.clock.getLightOption()==2);
        b3.setSelected(GUI.clock.getLightOption()==3);
        model.setValue(GUI.clock.getNumSeconds());
        model1.setValue(GUI.clock.getRatio());
        model2.setValue(GUI.numFish);
        slider.setValue(25-GUI.simulationSpeed);
        checkBox.setSelected(GUI.schooling);
        checkBox2.setSelected(GUI.intelligence);
        apply.setEnabled(false);        
    }
    
    public void ok()
    {
        if(b1.isSelected())
            GUI.clock.setLightOption(1);
        else if(b2.isSelected())
            GUI.clock.setLightOption(2);
        else if(b3.isSelected())
            GUI.clock.setLightOption(3);
        GUI.simulationSpeed=25-slider.getValue();
        GUI.clock.setNumSeconds(Integer.parseInt(model.getValue()+""));
        GUI.clock.setRatio(Integer.parseInt(model1.getValue()+""));                    
        GUI.schooling=checkBox.isSelected();
        GUI.intelligence=checkBox2.isSelected();
        GUI.numFish = (Integer.parseInt(model2.getValue()+""));                   
    }
    
    public void stateChanged(ChangeEvent e)
    {
        apply.setEnabled(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JRadioButton||e.getSource() instanceof JCheckBox)
        {
            apply.setEnabled(true);
        }
        else if(e.getSource()==apply)
        {
            apply.setEnabled(false);
            ok();
        }
        else if(e.getSource()==cancel)
        {
            GUI.options.hide();
        }
        else if(e.getSource()==ok)
        {
            ok();
            GUI.options.hide();
        }
        
    }
}
