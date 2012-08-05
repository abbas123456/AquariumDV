package Components;
import Graphics.GUI;
import Graphics.Tank;
import java.util.*;
import java.awt.*;
import javax.vecmath.Vector2f;
/**
 *
 * @author Mohammad Abbas
 * This class represents the fish in the simulation
 */
public class Fish// implements Runnable
{
    private int length,width,tail,colorType,energy,tailSpeed;
    private float x,y,x1,y1;
    private Shoal s;
    private Shoal s1=null;
    private boolean predator;
    private int count=0;
    private int count2=0;
    private Color color1, color2;
    private Vector2f position, velocity;
    private float maxSpeed;
    private float eyeStrength;
    private float seperation=1.8f;
    private float alignment=10.5f;
    private float cohesion=2.0f;
    private boolean alive=true;
    private ArrayList<Fish> fish;
    private int b =10;
    private boolean inMaze = false;
    private boolean eating=false;
    private Maze m;
    private MazeDots currentSquare;
    private Tank t;
    private boolean kids=false;
    private ArrayList<MazeDots> solution = new ArrayList<MazeDots>();
    private boolean backTrack=false;
    private MazeDots backTrackTarget=null;
    private boolean backTracked=false;
    private boolean bounce=false;
    private boolean dying=false;
    private boolean test=false;
    
    public Fish(float x, float y, float x1, float y1, int length, int width, float eyeStrength, float maxSpeed, Color color1, Color color2, int colorType, boolean b)
    {
        this.length=length;
        this.width=width;
        this.eyeStrength=eyeStrength;
        this.tail=0;
        this.tailSpeed=2;
        this.color1=color1;
        this.color2=color2;
        this.colorType=colorType;
        predator=b;
        position = new Vector2f(x,y);
        velocity = new Vector2f(x1,y1);
        this.maxSpeed=maxSpeed;
        if(GUI.foraging)
            energy=750;
        else
            energy=-1;
        fish = new ArrayList<Fish>();
    }

    public void setTank(Tank t)
    {
        this.t=t;
        if(t.kids&&!kids)
        {
            energy=2000;
        }            
    }
    
    public Tank getTank()
    {
        return t;
    }
    
    public boolean getKids()
    {
        return kids;
    }
    
    public ArrayList<Fish> getFish()
    {
        return fish;
    }
    
    public void addFish(Fish f)
    {
        fish.add(f);
    }
    
    public boolean hasBeenSeen(Fish f)  //used to avoid predators from the same shoal competing with each other
    {
        for(int i=0;i<fish.size();i++)
        {
            if(fish.get(i).equals(f))
                return false;
            
            if(fish.get(i).getShoal().equals(f.getShoal()))
                return true;
        }
        
        return false;
    }
    
    public ArrayList<MazeDots> getSolution()
    {
        return solution;
    }
    
    public void setSolution(ArrayList<MazeDots> solution)
    {
        this.solution=solution;
    }
    
    public Vector2f getPosition()
    {
        return position;
    }
    
    public Vector2f getVelocity()
    {
        return velocity;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getWidth()
    {
        return width;
    }
       
    public float getEyeStrength()
    {
        return eyeStrength;
    }
    
    public float getSpeed()
    {
        return maxSpeed;
    }
    
    public void setEyeStrength(float f)
    {
        eyeStrength=f;
    }
    
    public void setSpeed(float f)
    {
        maxSpeed=f;
    }
    
    public int getTail()
    {
        return tail;
    }
    
    public Color getColor1()
    {
        return color1;
    }
    
    public Color getColor2()
    {
        return color2;
    }
    
    public void setColor1(Color color)
    {
        color1=color;
    }
    
    public void setColor2(Color color)
    {
        color2=color;
    }
    
    public int getColorType()
    {
        return colorType;
    }
    
    public void setColorType(int i)
    {
        colorType=i;
    }
    
    public int getEnergy()
    {
        return energy;
    }
    
    public void setEnergy(int energy)
    {
        this.energy=energy;
    }
    
    public boolean getPredator()
    {
        return predator;
    }
    
    public boolean getTest()
    {
        return test;
    }
    
    public void setTest(boolean b)
    {
        test=b;
    }
    
    public void setShoal2(Shoal s)
    {
        s1=s;
    }
    
    public Shoal getShoal2()
    {
        return s1;
    }
    
    public void setShoal(Shoal s)
    {
        this.s=s;
    }
    
    public Shoal getShoal()
    {
        return s;
    }
    
    public boolean inMaze()
    {
        return inMaze;
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public boolean dying()
    {
        return dying;
    }
    public boolean isEating()
    {
        return eating;
    }
    
    public void setKids(boolean b)
    {
        kids=b;
    }
    
    public boolean backTracking()
    {
        return backTrack;
    }
    
    public void setBackTracking(boolean backTrack)
    {
        this.backTrack=backTrack;
        this.backTracked=true;
    }
    
    public MazeDots getBackTrackTarget()
    {
        return backTrackTarget;
    }
    
    public boolean getBackTracked()
    {
        return backTracked;
    }
    
    public void setBackTrackTarget(MazeDots backTrackTarget)
    {
        this.backTrackTarget=backTrackTarget;
    }
    
    public Vector2f seek(Object o,boolean flee)     //used to move fish to a target or away from target depending on boolean parameter
    {
        Vector2f target = new Vector2f(velocity);
        
        bounce=false;        
        Vector2f fishPos;
        int distanceX=2;
        int distanceY=2;
        
        if(velocity.getX()>=0)
            distanceX=length;
        if(velocity.getY()>=0)
            distanceY=width;
        
        if(predator)
        {
            distanceY-=5;
        }
        fishPos = new Vector2f(position);
        
        if(o instanceof Food)
        {
            target.set(((Food)o).getX(),((Food)o).getY());
        }
        else if(o instanceof Leaf)
        {
            target.set(((Leaf)o).getPlant().getX()+((Leaf)o).getX(),((Leaf)o).getPlant().getY()+((Leaf)o).getY());
        }
        else if(o instanceof Fish)
        {
            target.set(((Fish)o).getPosition()); 
        }
        else if(o instanceof MazeDots)
        {
            target.set(((MazeDots)o).getPosition());
            target.setX(target.getX()+((MazeDots)o).getMaze().getX()+5);
            target.setY(target.getY()+((MazeDots)o).getMaze().getY()+5);
        }
            
        else
            target.set((Vector2f)o);
            
        target.sub(fishPos);
        
        if(Math.abs(target.getX())<distanceX && Math.abs(target.getY())<distanceY && o instanceof Food)
        {
            if(predator&&!eating)
            {
                eating=true;
                count2=10;
            }
            t.getFood().remove(o);
            energy+=((Food)o).getEnergyValue();
        }
        
        else if(Math.abs(target.getX())<distanceX && Math.abs(target.getY())<distanceY && o instanceof Leaf)
        {
            for(int j=0;j<t.getPlants().size();j++)
                t.getPlants().get(j).getLeaves().remove(o);
            energy+=((Leaf)o).getEnergyValue();
        }
        
        else if(Math.abs(target.getX())<distanceX && Math.abs(target.getY())<distanceY && o instanceof Fish&&!flee)
        {
            if(predator&&!eating)
            {
                eating=true;
                count2=10;
            }
            ((Fish)o).die();
            
            if(t.kids)
            {
                energy+=((Fish)o).getEnergy();                
            }
        }        
        if(flee)
            target.negate();        
        
        target.scale(maxSpeed);
        return target;
    }
           
    public Vector2f school()        //first ensures that fish remains in the tank and then calls the shoaling methods found in the shoal class
    {
        Vector2f v = new Vector2f(velocity);
        bounce=false;
        int i = t.inPatch(this);
        if(i!=0)
        {
            if(i==1)
            {
                if(velocity.getX()>=0)
                {
                    if(t.kids)
                        position.setX(100);
                    else
                        position.setX(0);
                }
                else
                    position.setX(t.getWidth());
                    
            }
            else if(i==2)
            {
                if(t.kids&&false)
                {
                    if(velocity.getY()>=0)
                        position.setY(0);
                    else
                        position.setY(t.getHeight());
                }
                    
                else
                    v.setY(-velocity.getY()/2);
            }
            else if(i==3)
            {
                if(velocity.getX()>=0)
                {
                    if(t.kids)
                        position.setX(100);
                    else
                        position.setX(0);
                }
                else
                    position.setX(t.getWidth());
                
                if(t.kids&&false)
                {
                    if(velocity.getY()>=0)
                        position.setY(0);
                    else
                        position.setY(t.getHeight());
                }
                    
                else
                    v.setY(-velocity.getY()/2);
            }
            else if(i==4)
            {
                v.setX(-velocity.getX());
            }
            else if(i==5)
            {
                v.setY(-velocity.getY()/2);
            }
            
             else if(i==6)
            {
                v.setX(-velocity.getX());
                v.setY(-velocity.getY());
            }
            
           
        }
        else if(i==0&&!inMaze&&GUI.schooling)
        {
            Vector2f f1 = s.separation(this);
            f1.scale(seperation);
            Vector2f f2 = s.alignment(this);
            f2.scale(alignment);
            Vector2f f3 = s.cohesion(this) ;
            f3.scale(cohesion);
            
            v.add(f1);
            v.add(f2);
            v.add(f3);
        }
        
        if (velocity.length() > maxSpeed) 
            v.scale(maxSpeed / velocity.length());
        
        return v;
    }
    
    public void move()          //moves the fish depending on result of school method or gives a random offset movement
    {
        int r1 = new Random().nextInt(200);
        
        if(r1<198)  
        {
            velocity.set(school());
            position.add(velocity);
            flapTail();
            chomp();
        }
        
        else if(r1>=198&&!inMaze)
        {
            if(r1==198)
            {
               if(velocity.getX()<0)
                   velocity.setX(-(length/3));
               else
                   velocity.setX(length/3);                   
            }
            if(r1==199)
            {
               if(velocity.getY()<0)
                   velocity.setY(-(width/2));
               else
                   velocity.setY(width/2);
            }
        }
        
        
        
    }
        
    public void flapTail()
    {
        if(tail==0&&count==tailSpeed)
        {
            tail=1;
            count=0;
        }
        else if(tail==1&&count==tailSpeed)
        {
            tail=0;
            count=0;
        }            
        count++;
    }
    
    public void chomp()     //makes predator move his mouth when eating a prey
    {
        if(eating)
        {
            count2--;
            if(count2==0)
                eating=false;
        }            
    }
    
    public void percieve()          //responsible for retriving the objects that are around the fish and reasoning what to do 
    {       
        MazeDots m = t.inMaze(this);
        MazeDots g=null;
        if(m!=null)
        {
            g=m.getMaze().getNext1(m,this,s.lessThanAverage());
            if(g!=null&&GUI.intelligence)
            {
                velocity.set(seek(g,false));
            }
            inMaze=true;
        }
        else
        {
            inMaze=false;
            solution.clear();
            backTrack=false;
            backTrackTarget=null;
            backTracked=false;
        }
        
        Vector v = t.whatIsNearby(this);
        float smallest=100000;
        int index=-1;
        Vector2f target=new Vector2f();
        Vector2f fishPos=new Vector2f();
        boolean b1=false;
        boolean b2=false;
        if(!v.isEmpty()&&!inMaze)
        {       
            for(int i=0;i<v.size();i++)
            {
                fishPos = new Vector2f(position);
        
                if(v.get(i) instanceof Food)
                {
                    if(!((Food)v.get(i)).hasBeenSeen(this))
                    {
                        b1=true;
                        target.set(((Food)v.get(i)).getX(),((Food)v.get(i)).getY());
                        target.sub(fishPos);
                        if(target.length()<smallest)
                        {
                            smallest=target.length();
                            index=i;
                        }
                    }
                }
                
                else if(v.get(i) instanceof Leaf&&!predator)
                {
                    if(!((Leaf)v.get(i)).hasBeenSeen(this))
                    {
                        b1=true;
                        target.set(((Leaf)v.get(i)).getPlant().getX()+((Leaf)v.get(i)).getX(),((Leaf)v.get(i)).getPlant().getY()+((Leaf)v.get(i)).getY());
                        target.sub(fishPos);
                        if(target.length()<smallest)
                        {
                            smallest=target.length();
                            index=i;
                        }
                    }
                }
                
                else if(v.get(i) instanceof Fish)
                {
                    if(!((Fish)v.get(i)).hasBeenSeen(this))
                    {
                        b2=((Fish)v.get(i)).predator&&!predator;
                        target.set(((Fish)v.get(i)).getPosition());
                        target.sub(fishPos);
                        if(target.length()<smallest)
                        {
                            smallest=target.length();
                            index=i;
                        }
                    }
                }   
            }
            
            if(index!=-1)
            {
                if(v.get(index) instanceof Fish &&((Fish)v.get(index)).predator&&!predator)
                {
                    velocity.set(seek(v.get(index),true));                    
                }
                else if(v.get(index) instanceof Fish &&!((Fish)v.get(index)).predator&&predator)
                {
                    if(t.kids)
                    {
                        velocity.set(seek(v.get(index),false));
                        if(!((Fish)v.get(index)).getFish().contains(this))
                            ((Fish)v.get(index)).addFish(this);
                    }
                    else 
                    {
                        if(((Fish)v.get(index)).s.getFish().size()>2)
                        {
                            velocity.set(seek(v.get(index),false));
                            if(!((Fish)v.get(index)).getFish().contains(this))
                                ((Fish)v.get(index)).addFish(this);
                        }
                    }                    
                }
                else if(v.get(index) instanceof Food||(v.get(index) instanceof Leaf&&!predator))
                {
                    if(b1&&b2)
                    {
                        smallest=100000;
                        index=-1;
                        for(int i=0;i<v.size();i++)
                        {
                            if(v.get(i) instanceof Fish)
                            {
                                if(((Fish)v.get(i)).predator)
                                {
                                    target.set(((Fish)v.get(i)).getPosition());
                                    target.sub(fishPos);
                                    if(target.length()<smallest)
                                    {
                                        smallest=target.length();
                                        index=i;
                                    }
                                }
                            }
                        }
                        
                        velocity.set(seek(v.get(index),true));
                    }
                    else
                    {
                        velocity.set(seek(v.get(index),false));
                        if(v.get(index) instanceof Food)
                        {
                            if(!((Food)v.get(index)).getFish().contains(this))
                                ((Food)v.get(index)).addFish(this);
                        }
                        else if(v.get(index) instanceof Leaf)
                        {
                            if(!((Leaf)v.get(index)).getFish().contains(this))
                                ((Leaf)v.get(index)).addFish(this);
                        }
                    }
                    
                }

               
            }
                 
        }
        
    }
    
    public void die()
    {
        alive=false;
        s.getFish().remove(this);
    }
    
    public void floatUp()       //results in the fish floating up to the top and disappearing
    {
        tail=0;
        maxSpeed=0.6f;
        dying=true;
        velocity.set(seek(new Vector2f(position.getX(),position.getY()-1),false));
        position.add(velocity);
        if(position.getY()<=0)
            die();
    }
    
    
    public void run()       //called by the shoal for each fish it has, this is not a thread class so this is not a normal run method
    {
        if(alive&&!kids)
        {
            if(energy==0)   //if the energy is 0 it will die unless it is one of the last two fish, or a predator which doesnt die in the normal mode
            {
                if(GUI.kids)
                {
                    floatUp();
                }
                else
                {
                    if(!predator)
                    {
                        int number=0;
                        for(int i=0;i<s.getFish().size();i++)
                        {
                            if(!s.getFish().get(i).dying&&!s.getFish().get(i).equals(this))
                                number++;
                        }
                        if(number>=2)
                            floatUp();
                        else
                            energy=750;
                    }
                    else
                    {
                        energy=-1;
                    }
                }                
            }
            
            else
            {
                try
                {
                    energy--;
                    move();
                    percieve();                    
                }
                catch(Exception e){}
            }
            
        }
    }
}
