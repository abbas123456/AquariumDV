package Components;
import Graphics.GUI;
import java.util.*;
import javax.vecmath.Vector2f;
/**
 *
 * @author Mohammad Abbas
 * This class represents a shoal in the simulation and contains an arraylist of fish and has the methods for shoaling.
 * It is also responsible for the reproduction of a shoal
 */
public class Shoal implements Runnable
{

    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private Vector objects = new Vector();
    private String name="";
    private int score=0;
    private float schoolStrength =0.001f;
    private float distance=750.0f;
    private int count=0;
    
    public Shoal(Fish [] fish)
    {
        for(int i=0;i<fish.length;i++)
            this.fish.add(fish[i]);
    }
    
    public ArrayList<Fish> getFish()
    {
        return fish;
    }
    
    public void addFish(Fish f)
    {
        fish.add(f);
    }
    
    public void setName(String s)
    {
        name=s;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setScore(int i)
    {
        score=i;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public Vector2f alignment(Fish fish)    //calculates the average velocity of the shoal mates
    {
	Vector2f v = new Vector2f();
        
        int numShoalMates = 0;
	
        for(int i=0;i<this.fish.size();i++) 
        {
            if(this.fish.get(i).isAlive()&&!this.fish.get(i).equals(fish))
            {
                v.add(this.fish.get(i).getVelocity());
                numShoalMates++;		
            }
        }
	if (numShoalMates > 0) 
        {
            v.scale(1.0f / numShoalMates);		
            v.scale(schoolStrength);
	}
	return v;
    } 
        
    public Vector2f separation(Fish fish) //calculates the average position of the shoal mates and returns a negation of this, to make the fish
    {                                       //move from the average
        Vector2f v = new Vector2f();
        
        int numShoalMates = 0;
		
	for(int i=0;i<this.fish.size();i++) 
        {
            if(this.fish.get(i).isAlive()&&!this.fish.get(i).equals(fish))
            {
                v.add(this.fish.get(i).getPosition());                
                numShoalMates++;
            }
        }
	
        if (numShoalMates > 0) 
        { 
            v.scale(1.0f / numShoalMates); 
            v.sub(fish.getPosition());
            v.scale(schoolStrength);
            v.negate();
	}
	return v;
    }	
    
    public Vector2f cohesion(Fish fish) //calculates the average position of the shoal mates
    {
	Vector2f v = new Vector2f();
        
        int numShoalMates = 0;
		
	for(int i=0;i<this.fish.size();i++) 
        {
            if(this.fish.get(i).isAlive()&&!this.fish.get(i).equals(fish))
            {
                v.add(this.fish.get(i).getPosition());                
                numShoalMates++;
            }
        }
	
        if (numShoalMates > 0) 
        { 
            v.scale(1.0f / numShoalMates); 
            v.sub(fish.getPosition());
            v.scale(schoolStrength);
	}
	return v;
    }
    
    public boolean lessThanAverage()        //used for innovation, it works out if the shoal soze is less than average, if so the fish can innovate when solving a maze
    {
        int sum=0;
        int avg=0;
        for(int i=0;i<GUI.shoals.size();i++)
        {
            sum+=GUI.shoals.get(i).getFish().size();
        }
        
        avg=sum/GUI.shoals.size();
        
        return fish.size()<(avg);        
    }
    
    public void run()           //thread run method, each shoal is a thread and it loops through the fish and calls their run method(not a thread run method)
    {
        while(true)
        {
            try
            {
                count++;
                
                int x = new Random().nextInt(500);
                int totalEnergy=0;
                for(int i=0;i<fish.size();i++)
                {
                    totalEnergy+=fish.get(i).getEnergy();
                    if(x==0&&!fish.get(i).getKids()&&!fish.get(i).inMaze())
                        fish.get(i).getVelocity().setX(-fish.get(i).getVelocity().getX());

                    fish.get(i).run();
                }
                int totalFish=0;
                for(int i=0;i<GUI.shoals.size();i++)
                    totalFish+=GUI.shoals.get(i).getFish().size();
                if(!GUI.kids &&totalFish<GUI.maximum)       //only reproduce if the number of fish is less than the maximum amount of fish
                {
                    if((!GUI.foraging)&&(count==100)&&(!fish.get(0).getPredator())) //predator mode
                    {
                        count=0;
                        Fish f = fish.get(0);
                        Fish newFish = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
                        newFish.setShoal(this);
                        newFish.setTank(f.getTank());
                        newFish.setShoal2(f.getShoal2());                            
                        fish.add(newFish);                        
                    }

                    else if((GUI.foraging)&&totalEnergy>fish.size()*6000)   // foraging mode
                    {
                        for(int i=0;i<fish.size();i++)
                        {
                            if(!fish.get(i).dying())
                                    fish.get(i).setEnergy(750);
                        }
                        Fish f = fish.get(0);
                        Fish newFish = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
                        newFish.setShoal(this);
                        newFish.setTank(f.getTank());
                        newFish.setShoal2(f.getShoal2());
                        fish.add(newFish);
                        Fish newFish2 = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
                        newFish2.setShoal(this);
                        newFish2.setTank(f.getTank());
                        newFish2.setShoal2(f.getShoal2());
                        fish.add(newFish2);
                        Fish newFish3 = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
                        newFish3.setShoal(this);
                        newFish3.setTank(f.getTank());
                        newFish3.setShoal2(f.getShoal2());
                        fish.add(newFish3);
                        Fish newFish4 = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
                        newFish4.setShoal(this);
                        newFish4.setTank(f.getTank());
                        newFish4.setShoal2(f.getShoal2());
                        fish.add(newFish4);
                    }
                }
                else if(GUI.kids)       //kids mode, normal reproduction for predator and prey based on consumption
                {
                    if(totalEnergy>((fish.size()-1)*3000))
                    {
                        int x1=0;
                        for(int i=0;i<fish.size();i++)
                        {
                            if(!fish.get(i).dying()&&(!fish.get(i).getKids()))
                            {
                                fish.get(i).setEnergy(2000);
                                x1=i;
                            }
                        }
                        Fish f = fish.get(x1);
                        Fish newFish = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
                        newFish.setShoal(this);
                        newFish.setTank(f.getTank());
                        newFish.setShoal2(f.getShoal2());
                        fish.add(newFish);
                    }
                }
            }
            
            catch(Exception e){}
            try
            {
                Thread.sleep(GUI.simulationSpeed);
            }
            catch(InterruptedException e){}
      
        }
        
            
    }
}
