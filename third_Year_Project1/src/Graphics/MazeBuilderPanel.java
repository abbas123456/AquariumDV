package Graphics;
import Components.MazeDots;
import Components.Maze;
import javax.swing.*;
import java.awt.*;    
import java.awt.event.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Mohammad Abbas
 * This panel is the actual set of squares that exist in the maze builder
 */
public class MazeBuilderPanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener
{

    private JPanel frame;
    private int x,y,startX,startY,endX,endY,currentX,currentY;
    private boolean dragging=false;
    private MazeDots[][] mazeDots;
    private MazeDots[][] finalMaze;
    private Maze m;
    
    public MazeBuilderPanel(JPanel frame)
    {
        this.frame=frame;
        addMouseMotionListener(this);      
        addMouseListener(this);  
        addComponentListener(this);
        setBackground(new Color(198,206,217));
        addDots();     
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D)g;
        
        for(int i=0;i<mazeDots[0].length;i++)
        {
            for(int j=0;j<mazeDots.length;j++)
            {
                if(mazeDots[j][i].getState()==0)
                {
                    g1.setColor(Color.white);
                    g1.fillRect(mazeDots[j][i].getX(), mazeDots[j][i].getY(), 28, 23);                   
                }
                else if(mazeDots[j][i].getState()==2)
                {
                    g1.setColor(Color.DARK_GRAY);       
                    g1.fillRect(mazeDots[j][i].getX(), mazeDots[j][i].getY(), 28, 23);
                }
                else if(mazeDots[j][i].getState()==3)
                {
                    g1.setColor(Color.white);
                    g1.fillRect(mazeDots[j][i].getX(), mazeDots[j][i].getY(), 28, 23);
                    g1.setColor(Color.blue);       
                    g1.fillOval(mazeDots[j][i].getX()+9, mazeDots[j][i].getY()+7, 10, 10);                    
                }
                else if(mazeDots[j][i].getState()==1)
                {
                    g1.setColor(Color.white);
                    g1.fillRect(mazeDots[j][i].getX(), mazeDots[j][i].getY(), 28, 23);
                    g1.setColor(Color.red);       
                    g1.fillOval(mazeDots[j][i].getX()+9, mazeDots[j][i].getY()+7, 10, 10);
                }                
                
                if(mazeDots[j][i].getState1()==0)
                {
                    g1.setColor(new Color(198,206,217));
                    g1.drawRect(mazeDots[j][i].getX(), mazeDots[j][i].getY(), 28, 23);
                }
                else
                {
                    g1.setColor(Color.black);
                    g1.drawRect(mazeDots[j][i].getX(), mazeDots[j][i].getY(), 28, 23);
                }                
            }
            
        }       
                
    }
    
    public void addDots()       //resets the connections again when resized or closed
    {
        x = frame.getWidth()/30;
        y = frame.getHeight()/35;
        mazeDots = new MazeDots[y][x];
        m = new Maze("maze.pl");
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                mazeDots[j][i] = new MazeDots(i*30,j*25,0,m);
            }
        }       
        repaint();
    }
    
    public void connections() //sets the connections between one square and the next, used to write the maze to a text file which is read by the tank class
    {
        int start=-1;
        int end=-1;
        int numEnds=0;
        boolean startFound=false;
        boolean endFound=false;
        int largestLine = 0;
        int smallestLine=100;
        String error="";
        String output1="";
        ArrayList<Point> nums = new ArrayList<Point>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        
        for(int i=0;i<mazeDots.length;i++)
        {
            boolean dotsFound=false;
            boolean first=true;
            for(int j=0;j<mazeDots[0].length;j++)
            {
                if(mazeDots[i][j].getState()!=0)
                {
                    if(first)
                    {
                        start=j;
                        first=false;
                    }
                    dotsFound=true;
                    end=j;
                }
            }
            
            if(dotsFound)
            {
                nums.add(new Point(start,end));
                indexes.add(i);
                if((end-start)>largestLine)
                    largestLine=(end-start);
                if(start<smallestLine)
                    smallestLine=start;
            }            
        }
        
        finalMaze = new MazeDots[nums.size()][largestLine+1];
        
        for(int i=0;i<finalMaze.length;i++)
        {
            for(int j=0;j<finalMaze[0].length;j++)
            {               
                finalMaze[i][j] = new MazeDots(0,0,0,m);                
            }              
        }
        
        
        for(int i=0;i<nums.size();i++)
        {
            if(i!=0)
                output1=output1+"\n% ";
            else
                output1=output1+"% ";
            
            for(int j=smallestLine;j<=smallestLine+largestLine;j++)                
            {                
                finalMaze[i][j-smallestLine] = mazeDots[indexes.get(i)][j];
                output1=output1+mazeDots[indexes.get(i)][j].getState();
                if(j>=nums.get(i).getX()&&j<=nums.get(i).getY())
                {
                    if(mazeDots[indexes.get(i)][j].getState()==0)                
                    {
                        try
                        {
                            if(mazeDots[indexes.get(i)-1][j].getState()<=1&&!mazeDots[indexes.get(i)-1][j].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)-1][j]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)-1][j]);
                                mazeDots[indexes.get(i)-1][j].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                            if(mazeDots[indexes.get(i)+1][j].getState()<=1&&!mazeDots[indexes.get(i)+1][j].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)+1][j]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)+1][j]);
                                mazeDots[indexes.get(i)+1][j].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                            if(mazeDots[indexes.get(i)][j+1].getState()<=1&&!mazeDots[indexes.get(i)][j+1].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)][j+1]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)][j+1]);
                                mazeDots[indexes.get(i)][j+1].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                            if(mazeDots[indexes.get(i)][j-1].getState()<=1&&!mazeDots[indexes.get(i)][j-1].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)][j-1]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)][j-1]);
                                mazeDots[indexes.get(i)][j-1].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                        }
                        catch(Exception e){}                    
                    }                        
                    else if(mazeDots[indexes.get(i)][j].getState()==3)
                    {
                        startFound=true;               
                        try
                        {
                            if(i==nums.size()-1&&mazeDots[indexes.get(i)-1][j].getState()<=1&&!mazeDots[indexes.get(i)-1][j].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)-1][j]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)-1][j]);
                                mazeDots[indexes.get(i)-1][j].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                            if(i==0&&mazeDots[indexes.get(i)+1][j].getState()<=1&&!mazeDots[indexes.get(i)+1][j].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)+1][j]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)+1][j]);
                                mazeDots[indexes.get(i)+1][j].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                            if(j==nums.get(i).getX()&&mazeDots[indexes.get(i)][j+1].getState()<=1&&!mazeDots[indexes.get(i)][j+1].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)][j+1]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)][j+1]);
                                mazeDots[indexes.get(i)][j+1].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                            if(j==nums.get(i).getY()&&mazeDots[indexes.get(i)][j-1].getState()<=1&&!mazeDots[indexes.get(i)][j-1].isConnected(mazeDots[indexes.get(i)][j])&&!mazeDots[indexes.get(i)][j].isConnected(mazeDots[indexes.get(i)][j-1]))
                            {
                                mazeDots[indexes.get(i)][j].addConnection(mazeDots[indexes.get(i)][j-1]);
                                mazeDots[indexes.get(i)][j-1].addConnection(mazeDots[indexes.get(i)][j]);
                            }
                        }
                        catch(Exception e){}                   
                    }

                    else if(mazeDots[indexes.get(i)][j].getState()==1)
                    {
                        numEnds++;
                        endFound=true;                    
                    }         
                }
            }        
            
        } 
        
        if(!startFound)
        {
            error=error+("There is no start position!\n");
        }
        if(!endFound)
        {
            error=error+("There is no end position!\n");
        }
        if(numEnds>1)
        {
            error=error+("There is more than 1 end position!\n");
        }
        
        if(!error.equals(""))
        {
            JOptionPane.showMessageDialog(null,error);
            for(int i=0;i<mazeDots.length;i++)
            {
                for(int j=0;j<mazeDots[0].length;j++)
                {               
                    mazeDots[i][j].getConnections().clear();
                }              
            }
        }
        else
        {
            try
            {
                addDots();
                output1=output1+"\n%-\n";
                FileWriter f = new FileWriter("maze");
                BufferedWriter b = new BufferedWriter(f);
                b.write(output1);
                b.close();
                GUI.t.addMaze("maze");
                GUI.CM.setVisible(false);
                GUI.CM.setSize(300,300);
                ((MazeBuilder)frame).b1.setSelected(true);    
            }
            catch(Exception e){}
        }
    }
    
    public void mouseExited(MouseEvent e)
    {
       if(e.getX()/30<x&&e.getY()/35<y)
       {
           for(int i=0;i<mazeDots.length;i++)
           {
               for(int j=0;j<mazeDots[0].length;j++)
               {
                    mazeDots[i][j].setState1(0);
               }
           }   
           repaint();
       }
    }  
    
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {        
    }
    
    public void mousePressed(MouseEvent e)
    {
        if(e.getX()/30<x&&e.getY()/25<y)
        {
            int i=0;
            if(MazeBuilder.b1.isSelected())
                i=2;
            else if(MazeBuilder.b2.isSelected())
                i=0;
            else if(MazeBuilder.b3.isSelected())
                i=3;
            else if(MazeBuilder.b4.isSelected())
                i=1;
            
            mazeDots[(e.getY()/25)][((e.getX()/30))].setState(i);
            mazeDots[(e.getY()/25)][((e.getX()/30))].setState1(1);
            
            
            repaint();
        }
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        if(e.getX()/30<x&&e.getY()/25<y)
        {
            for(int i=0;i<mazeDots[0].length;i++)
            {
                for(int j=0;j<mazeDots.length;j++)
                {
                    mazeDots[j][i].setState1(0);
                }
            }
            mazeDots[(e.getY()/25)][((e.getX()/30))].setState1(1);
            repaint();
        }
        
    }
    
    public void mouseDragged(MouseEvent e)
    {
        if(e.getX()/30<x&&e.getY()/25<y)
        {
            int i1=0;
            
            if(MazeBuilder.b1.isSelected())
                i1=2;
            else if(MazeBuilder.b2.isSelected())
                i1=0;
            else if(MazeBuilder.b3.isSelected())
                i1=3;
            else if(MazeBuilder.b4.isSelected())
                i1=1;
            
            for(int i=0;i<mazeDots[0].length;i++)
            {
                for(int j=0;j<mazeDots.length;j++)
                {
                    mazeDots[j][i].setState1(0);
                }
            }
            mazeDots[(e.getY()/25)][((e.getX()/30))].setState(i1);
            mazeDots[(e.getY()/25)][((e.getX()/30))].setState1(1);
            repaint();
        }        
    }
    public void componentHidden(ComponentEvent e){}
    public void componentMoved(ComponentEvent e){}
    public void componentResized(ComponentEvent e)
    {
        addDots();
    }
    public void componentShown(ComponentEvent e){}
}
