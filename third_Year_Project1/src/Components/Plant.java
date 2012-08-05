package Components;
import java.util.*;
/**
 *
 * @author Mohammad Abbas
 * This class represents a plant in the simulation and contains an arraylist of leaves
 */
public class Plant 
{

    private int numLeaves=10;
    private ArrayList<Leaf> leaves = new ArrayList<Leaf>();
    private int x;
    private int y;
    private int status=0;
    
    public Plant(int x,int y)
    {
        this.x=x;
        this.y=y;
        makeLeaves();        
    }
    
    public void makeLeaves()
    {
        leaves.clear();
        for(int i=0;i<numLeaves;i++)
        {
            leaves.add(new Leaf(-20,-(i*15)-25,500,this));
            leaves.add(new Leaf(0,-(i*15)-15,500,this));
        }
    }
    
    public ArrayList<Leaf> getLeaves()
    {
        return leaves;
    }
    
    public int getNumLeaves()
    {
        return numLeaves;
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
    
    public void setStatus(int status)
    {
        this.status=status;
    }
    
    public int getStatus()
    {
        return status;
    }
}
