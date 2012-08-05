package Components;
import java.util.*;
/**
 *
 * @author Mohammad Abbas
 * This class represents the leaf object that is part of a plant
 */
public class Leaf 
{
    private int x,y,energyValue;
    private ArrayList<Fish> s;
    private Plant p;
    
    public Leaf(int x, int y, int energyValue,Plant p)
    {
        this.p=p;
        this.x=x;
        this.y=y;
        this.energyValue=energyValue;
        s=new ArrayList<Fish>();
    }
    
    public int getX()
    {
        return x;
    }
    
    public Plant getPlant()
    {
        return p;
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
    
    public boolean hasBeenSeen(Fish fish)   //prevents fish from the same shoal competing for the same lead
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
