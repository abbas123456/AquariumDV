package Components;
import Components.Maze;
import java.util.*;
import javax.vecmath.Vector2f;
/**
 *
 * @author Mohammad Abbas
 * This class represents a square in the maze
 */
public class MazeDots
{
    private ArrayList<MazeDots> connections = new ArrayList<MazeDots>();
    private Vector2f position;
    private int state=0;
    private int state1=0;
    private int x;
    private int y;
    private Maze m;
    private MazeDots up,right,down,left;
    private boolean upWall=false,rightWall=false,downWall=false,leftWall=false,scanned=false;
    
    public MazeDots(int x, int y, int state,Maze m)
    {
        this.x = x;
        this.y = y;
        position = new Vector2f(x,y);
        this.state=state;
        this.m=m;
    }
   
    public void setNeighbours(MazeDots up, MazeDots right, MazeDots down, MazeDots left)
    {
        this.up=up;
        if(up!=null)
            upWall=up.getState()==2;
        this.right=right;
        if(right!=null)
            rightWall=right.getState()==2;
        this.down=down;
        if(down!=null)
            downWall=down.getState()==2;
        this.left=left;
        if(left!=null)
            leftWall=left.getState()==2;
    }
    
    public boolean isScanned()
    {
        return scanned;
    }
    
    public void setScanned()
    {
        scanned=true;
    }
    
    public MazeDots getUp() 
    { 
        if (!upWall) 
            return up;                
        else 
            return null; 
    }
   
    public MazeDots getRight() 
    { 
        if (!rightWall) 
            return right;                
        else 
            return null; 
    }
    
    public MazeDots getDown() 
    { 
        if (!downWall) 
            return down;                
        else 
            return null; 
    }
    
    public MazeDots getLeft() 
    { 
        if (!leftWall) 
            return left;                
        else 
            return null; 
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setState(int x)
    {
        state=x;
    }
    
    public int getState()
    {
        return state;
    }
    
    public void setState1(int x)
    {
        state1=x;
    }
    
    public int getState1()
    {
        return state1;
    }
    
    public Vector2f getPosition()
    {
        return position;
    }
    
    public Maze getMaze()
    {
        return m;
    }
    
    public void addConnection(MazeDots md)
    {
        connections.add(md);                
    }
    
    public boolean isConnected(MazeDots md)
    {
        return connections.contains(md);        
    }
    
    public ArrayList<MazeDots> getConnections()
    {
        return connections;
    }
         
    
}
