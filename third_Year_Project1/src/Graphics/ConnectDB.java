package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This is a panel that asks the user to input the server credentials when connecting to a data source
 */
public class ConnectDB extends JPanel implements ActionListener,KeyListener,MouseListener
{
    private JLabel server1,userName1,password1,dbType;
    private JTextField server,userName;
    private JPasswordField password;
    private JButton ok,cancel;
    private JComboBox dbTypeCB;
    private QuestionMark q;
    
    public ConnectDB()
    {
        setBackground(Color.WHITE);
        setLayout(new FlowLayout());
        dbType = new JLabel("Database Type");
        server1 = new JLabel("Server address");
        userName1 = new JLabel("User Name");
        password1 = new JLabel("Password");
        String[]s = {"","MySql","Oracle",};
        dbTypeCB = new JComboBox(s);
        server = new JTextField();
        userName = new JTextField();
        password = new JPasswordField();
        server.addKeyListener(this);
        userName.addKeyListener(this);
        password.addKeyListener(this);
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        ok.addActionListener(this);
        cancel.addActionListener(this);
        JPanel p = new JPanel(new GridLayout(8,1,0,0));
        p.setBackground(Color.WHITE);
        p.add(dbType);
        p.add(dbTypeCB);
        p.add(server1);
        p.add(server);
        p.add(userName1);
        p.add(userName);
        p.add(password1);
        p.add(password);                
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
    
    public void clear()
    {
        userName.setText("");
        password.setText("");
    }
    
    public void ok()    //when the user presses ok and the fields are filled, the program attempts to make a connection to the datasource
    {
        if(!server.getText().equals("")&&!userName.getText().equals("")&&!password.getText().equals("")&&dbTypeCB.getSelectedIndex()!=0)
        {
            try
            {
                Connection c=null;
                if(dbTypeCB.getSelectedItem().equals("MySql"))
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    c = DriverManager.getConnection("jdbc:mysql://"+server.getText()+"/", userName.getText(), password.getText());
                }
                else if(dbTypeCB.getSelectedItem().equals("Oracle"))
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    c = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST="+server.getText()+")(PORT=1521)))(CONNECT_DATA=(SID=orcl)))", userName.getText(), password.getText());
                }
                GUI.cdb2.setConnection(c);
                GUI.card.next(GUI.p1);
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(this, "Could not connect, please check details");
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, "All Fields should be complete!");
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
        {
            ok();            
        }        
        else if(e.getSource()==cancel)
        {
            GUI.DB.hide();
            clear();
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==10)
        {
            ok();
        }
    }
    
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
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
