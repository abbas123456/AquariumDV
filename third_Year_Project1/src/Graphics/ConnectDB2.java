package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This is a panel that asks the user to select the database name, table name and fields for the visualisation
 */
public class ConnectDB2 extends JPanel implements ActionListener,MouseListener
{
    private JLabel database1,table1,column1,column3;
    private JComboBox database, table, column, column2;
    private JButton ok,cancel;
    private Connection c;
    private DefaultComboBoxModel m1,m2,m3,m4;
    private String databaseS,tableS,columnS,column2S;
    private QuestionMark q;
    
    public ConnectDB2()
    {
        setBackground(Color.WHITE);
        setLayout(new FlowLayout());
        database1 = new JLabel("Select Database");
        table1 = new JLabel("Select table");
        column1 = new JLabel("Shoal name");
        column3 = new JLabel("Shoal performance measure");
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        database = new JComboBox();
        table = new JComboBox();
        column = new JComboBox();
        column2= new JComboBox();
        table.setEnabled(false);
        column.setEnabled(false);
        column2.setEnabled(false);
        m1 = new DefaultComboBoxModel();
        m2 = new DefaultComboBoxModel();
        m3 = new DefaultComboBoxModel();
        m4 = new DefaultComboBoxModel();
        database.setModel(m1);
        table.setModel(m2);
        column.setModel(m3);     
        column2.setModel(m4);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        database.addActionListener(this);
        table.addActionListener(this);
        JPanel p = new JPanel(new GridLayout(8,1,0,0));
        p.setBackground(Color.WHITE);
        p.add(database1);
        p.add(database);
        p.add(table1);
        p.add(table);
        p.add(column1);
        p.add(column);        
        p.add(column3);
        p.add(column2);        
        JPanel p1 = new JPanel(new GridLayout(2,1,20,0));
        JPanel p2 = new JPanel(new GridLayout(1,2));
        p2.add(ok);
        p2.add(cancel);
        JPanel p3 = new JPanel(new BorderLayout());
        q = new QuestionMark();
        q.addMouseListener(this);        
        p3.add(q,BorderLayout.CENTER);
        p1.add(p2);
        p1.add(p3);
        add(p);       
        add(p1);        
    }
    
    public void setConnection(Connection c)
    {
        this.c=c;
        m1.addElement("");
        try
        {
            ResultSet rs = c.getMetaData().getCatalogs();
            while(rs.next())
            {
                m1.addElement(rs.getString(1));
            }
        }
        catch(Exception e){}        
    }
    
    public void disconnect()    //closes the connection
    {
        try
        {
            c.close();
        }   
        catch(Exception e){}
    }
    
    public void clear()
    {
        GUI.DB.hide();        
        GUI.card.first(GUI.p1);
        GUI.cdb.clear();
        m1.removeAllElements();
        m2.removeAllElements();
        m3.removeAllElements();
        m4.removeAllElements();
    }
    
    public String getDatabase()
    {
        return databaseS;
    }
    
    public String getTable()
    {
        return tableS;
    }
    
    public String getColumn()
    {
        return columnS;
    }
    
    public String getColumn2()
    {
        return column2S;
    }
    
    public Connection getConnection()
    {
        return c;
    }
    
    public void setNames()  //this method normalises the data and sets the appropriate shoals attributes to the data value
    {                       //a similar method is carried out by the clock class which refreshes the data
        ResultSet rs;
        int numRows=0;
        int max=0;
        int min=0;                    
                    
        try
        {
            Statement s = c.createStatement();
            s.execute("use "+ database.getSelectedItem()+"");
            rs = s.executeQuery("select count(*) from " +table.getSelectedItem()+"");
            rs.next();
            numRows = rs.getInt(1);
            rs = s.executeQuery("select min("+ column2.getSelectedItem()+") from " +table.getSelectedItem()+"");
            rs.next();
            min = rs.getInt(1);
            rs = s.executeQuery("select max("+ column2.getSelectedItem()+") from " +table.getSelectedItem()+"");
            rs.next();
            max = rs.getInt(1);

            rs = s.executeQuery("select * from " +table.getSelectedItem()+"");                   
            
            GUI.g.createShoals(numRows);
            GUI.g.initialiseShoals();
            int i=0;
            if(!GUI.foraging)
                i=1;

            for(;i<GUI.shoals.size();i++)
            {
                rs.next();
                String name = rs.getString(column.getSelectedItem()+"");
                int score = rs.getInt(column2.getSelectedItem()+"");

                for(int j=0;j<GUI.shoals.get(i).getFish().size();j++)
                {
                    GUI.shoals.get(i).setName(name);
                    GUI.shoals.get(i).setScore(score);
                    float newScore=0;
                    newScore = (score-min);
                    newScore = newScore/(max-min);
                    newScore++;
                    if(max-min==0)
                    {
                        newScore=2;
                    }
                    GUI.shoals.get(i).getFish().get(j).setEyeStrength(newScore*125);
                    GUI.shoals.get(i).getFish().get(j).setSpeed(newScore*1.25f);                                   
                }                               
            }
            databaseS = database.getSelectedItem()+"";
            tableS = table.getSelectedItem()+"";
            columnS = column.getSelectedItem()+"";
            column2S = column2.getSelectedItem()+"";
        }
        catch(Exception e1){}
        GUI.g.addIcons();            
        GUI.clock.setOrigNumRows(numRows);
                    
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
        {
            if(m3.getSize()!=0&&m4.getSize()!=0)
            {
                if(column.getSelectedItem().equals("")||column2.getSelectedItem().equals(""))
                {
                    JOptionPane.showMessageDialog(this, "All Fields should be complete");
                }
                else
                {
                    GUI.g.connect();
                    setNames();
                    clear();                    
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "All Fields should be complete!");
            }
        }
        else if(e.getSource()==cancel)
        {
            disconnect();
            clear();
        }        
        else if(e.getSource()==database)
        {
            if(m1.getSize()!=0)
            {
                if(database.getSelectedItem().equals(""))
                {
                   m2.removeAllElements();
                   m3.removeAllElements();
                   m4.removeAllElements();
                   table.setEnabled(false);
                   column.setEnabled(false);
                   column2.setEnabled(false);
                }
                else
                {
                    m2.removeAllElements();
                    m2.addElement("");
                    table.setEnabled(true);                
                    try
                    {
                        ResultSet rs = c.getMetaData().getTables(database.getSelectedItem()+"", null, null, null);
                        while(rs.next())
                        {
                            m2.addElement(rs.getString(3));
                        }
                    }
                    catch(Exception e1){}
                }
            }
        }
        else if(e.getSource()==table)
        {
            if(m2.getSize()!=0)
            {
                if(table.getSelectedItem().equals(""))
                {
                   m3.removeAllElements();
                   m4.removeAllElements();
                   column.setEnabled(false);
                   column2.setEnabled(false);
                }
                else
                {
                    m3.removeAllElements();
                    m4.removeAllElements();
                    m3.addElement("");
                    m4.addElement("");
                    column.setEnabled(true);   
                    column2.setEnabled(true);
                    try
                    {
                        ResultSet rs = c.getMetaData().getColumns(database.getSelectedItem()+"", null, table.getSelectedItem()+"", null);
                        while(rs.next())
                        {                            
                            if(rs.getInt(5)==4||rs.getInt(5)==8||rs.getInt(5)==6||rs.getInt(5)==2||rs.getInt(5)==7||rs.getInt(5)==5||rs.getInt(5)==-6)
                                m4.addElement(rs.getString(4)); 
                            else if(rs.getInt(5)==1||rs.getInt(5)==12)
                                m3.addElement(rs.getString(4));
                        }
                    }
                    catch(Exception e1){}
                }
            }
        }
        
    }
    public void mouseExited(MouseEvent e)
    {
        if(e.getSource()==q)
        {
            q.setOver(false);
        }
    }      
    public void mouseEntered(MouseEvent e)
    {
        if(e.getSource()==q)
        {
            q.setOver(true);
        }
    }    
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        if(e.getSource()==q)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(new URI("http://www.dcs.qmul.ac.uk/~mya3/project/user_manual/Manual#label3"));
            } 
            catch(Exception e1){}
        }
    }
    public void mouseClicked(MouseEvent e){}
    
}
