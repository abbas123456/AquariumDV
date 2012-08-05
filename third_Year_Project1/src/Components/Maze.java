package Components;
import java.awt.*;
import java.util.*;
/**
 *
 * @author Mohammad Abbas
 * This class represents a maze in the simulation
 */
public class Maze {
    
    private MazeDots[][] maze;
    private int status=0;
    private int x=100;
    private int y=100;
    private String path;
    private String solution;
    private MazeDots start, finish;
    private ArrayList<Point> points;
    public ArrayList<MazeDots> solution1=new ArrayList<MazeDots>();
    private String print="";
    private String print1="";
    private String print2="";
    private boolean solved=false;
    
    public Maze(MazeDots[][] maze,String path, String solution)
    {
        this.maze=maze;
        this.path=path;
        this.solution=solution;
        for(int i=0;i<maze.length;i++)
        {                
            for(int j=0;j<maze[0].length;j++)
            {
                if(maze[i][j].getState()==3)
                    start=maze[i][j];
                else if(maze[i][j].getState()==1)
                    finish=maze[i][j];
            }                         
        }
    }
    
    public Maze(String path)
    {        
        this.path=path;        
    }
    
    public void solve()     //this sets values of the walls in the maze
    {
        MazeDots upD;
        MazeDots rightD;
        MazeDots downD;
        MazeDots leftD;
        for(int i=0;i<maze.length;i++)
        {                
            for(int j=0;j<maze[0].length;j++)
            {
                if(i==0)
                    leftD=null;
                else
                    leftD= maze[i-1][j];
                if(i==maze.length-1)
                    rightD=null;
                else
                    rightD= maze[i+1][j];
                if(j==0)
                    upD=null;
                else
                    upD= maze[i][j-1];
                if(j==maze[0].length-1)
                    downD=null;
                else
                    downD= maze[i][j+1];

                maze[i][j].setNeighbours(leftD,rightD,upD,downD);
            }                         
        }
        solved=false;
        solve2(start,finish);        
    }
    
    public void solve2(MazeDots x,MazeDots y)   //this method recursively finds a path from the initial position to the goal position
    {
        if(solved|| x == null || x.isScanned())
        {
             return;
        }
        x.setScanned();
        if(x!=null)
        { 
            solution1.add(x);                   
        }
            
        if (x == y) 
            solved = true;
        
        solve2(x.getUp(),y);
        solve2(x.getRight(),y);
        solve2(x.getDown(),y);
        solve2(x.getLeft(),y);

        if(solved) 
            return;    
    }
    
    public MazeDots[][] getMaze()
    {
        return maze;
    }   
    
    public void setMaze(MazeDots[][] maze)
    {
        this.maze=maze;
        for(int i=0;i<maze.length;i++)
        {                
            for(int j=0;j<maze[0].length;j++)
            {
                if(maze[i][j].getState()==3)
                    start=maze[i][j];
                else if(maze[i][j].getState()==1)
                    finish=maze[i][j];
            }                         
        }
    }
    
    public void setStatus(int i)
    {
        status=i;
    }
    
    public void setSolution1(ArrayList<MazeDots> solution1)
    {
        this.solution1=solution1;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setX(int x)
    {
        this.x=x;
    }
    
    public void setY(int y)
    {
        this.y=y;
    }
    
    public String getPath()
    {
        return path;        
    }
    
    public MazeDots getStart()
    {
        return start;
    }
    
    public MazeDots getFinish()
    {
        return finish;
    }
    
    public void setSolution(String solution)
    {
        this.solution=solution;
    }
    
    public void getSolution() //converts path from a string to an array of points
    {
        points = new ArrayList<Point>();
        String s;
        int x;
        int y;
        
        for(int i=0;i<solution.length();i++)
        {
            s="";
            x=0;
            y=0;
            if(solution.charAt(i)=='t')
            {
                i+=2;
                while(solution.charAt(i)!=',')
                {
                    s+=(solution.charAt(i));
                    i++;
                }
                x=Integer.parseInt(s);
                s="";
                i++;
                while(solution.charAt(i)!=')')
                {
                    s+=(solution.charAt(i));
                    i++;
                }
                y=Integer.parseInt(s);
                points.add(new Point(x,y));                
            }
        }            
    }
    
    public MazeDots getNext(MazeDots current) //called by the fish when in a maze, it gets the next destination depending on the current position
    {
        int i;
        for(i=0;i<points.size();i++)
        {
            if(current.getX()/30==points.get(i).getX()&&current.getY()/25==points.get(i).getY())
            {
                break;
            }            
        }
        if(i!=points.size())
        {
            
            for(int j=0;j<maze.length;j++)
            {                
                for(int k=0;k<maze[0].length;k++)
                {
                    if(maze[j][k].getX()/30==points.get(i+1).getX()&&maze[j][k].getY()/25==points.get(i+1).getY())
                        return maze[j][k];                
                }                         
            }
            
        }
        
        return null;
        
    }
    
    public MazeDots getNext1(MazeDots current,Fish f,boolean intelligent)   //same as above but uses takes into account backtracking
    {
        if(f.getSolution().isEmpty())
        {
            for(int i=0;i<solution1.size();i++)
            {
                f.getSolution().add(solution1.get(i));
            }
        }
        
        int i;        
        
        for(i=0;i<solution1.size();i++)
        {
            if(current.getX()==solution1.get(i).getX()&&current.getY()==solution1.get(i).getY())
            {
                break;
            }            
        }    
        
        if(i!=solution1.size())
        {
            if(f.backTracking())
            {
                if(((Math.abs(f.getBackTrackTarget().getX()-current.getX())==30)&&(Math.abs(f.getBackTrackTarget().getY()-current.getY())==0))||((Math.abs(f.getBackTrackTarget().getX()-current.getX())==0)&&(Math.abs(f.getBackTrackTarget().getY()-current.getY())==25)))
                {
                    f.setBackTracking(false);                        
                }
                else
                {
                    int x = f.getSolution().indexOf(current);
                    f.getSolution().remove(current);
                    return f.getSolution().get(x-1);
                }
            }

            else
            {
                if(!f.getBackTracked())
                {
                    if(((Math.abs(solution1.get(solution1.indexOf(current)+1).getX()-current.getX())==30)&&(Math.abs(solution1.get(solution1.indexOf(current)+1).getY()-current.getY())==0))||((Math.abs(solution1.get(solution1.indexOf(current)+1).getX()-current.getX())==0)&&(Math.abs(solution1.get(solution1.indexOf(current)+1).getY()-current.getY())==25)))
                    {
                        return solution1.get(solution1.indexOf(current)+1);
                    }
                    else
                    {
                        if(intelligent)
                        {
                            f.setBackTracking(true);
                            f.setBackTrackTarget(solution1.get(solution1.indexOf(current)+1));
                        }       
                        
                    }
                }
                else
                {
                    if(((Math.abs(f.getSolution().get(f.getSolution().indexOf(current)+1).getX()-current.getX())==30)&&(Math.abs(f.getSolution().get(f.getSolution().indexOf(current)+1).getY()-current.getY())==0))||((Math.abs(f.getSolution().get(f.getSolution().indexOf(current)+1).getX()-current.getX())==0)&&(Math.abs(f.getSolution().get(f.getSolution().indexOf(current)+1).getY()-current.getY())==25)))
                    {
                        return f.getSolution().get(f.getSolution().indexOf(current)+1);
                    }
                    else
                    {
                        f.setBackTracking(true);       
                        f.setBackTrackTarget(f.getSolution().get(f.getSolution().indexOf(current)+1));
                    }
                }
            }
        }           
        
       return null;
        
    }
   
}
