package Components;
import java.util.*;
/**
 *
 * @author Mohammad Abbas
 * This class represents a food object in the simulation
 */
public class Food //implements Runnable
{

    private int x;
    private int y;
    private int energyValue;
    private ArrayList<Fish> s;
  
    public Food(int x, int y, int energyValue)
    {
        this.x=x;
        this.y=y;
        this.energyValue=energyValue;
        s=new ArrayList<Fish>();
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;   
    }
    
    public int getEnergyValue()
    {
        return energyValue;
    }
    
    public ArrayList<Fish> getFish()
    {
        return s;
    }
    
    public boolean hasBeenSeen(Fish fish)       //prevents fish from the same shoal competing for the same food
    {
        for(int i=0;i<s.size();i++)
        {
            if(s.get(i).equals(fish))
                return false;
            
            if(s.get(i).getShoal().equals(fish.getShoal()))
                return true;
        }
        
        return false;
    }
    
    public void addFish(Fish fish)
    {
        s.add(fish);
    }    
}
