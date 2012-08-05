package Components;
import Graphics.GUI;
/**
 *
 * @author Mohammad Abbas
 * This class represents the moving scenery, i.e the deap sea diver
 */
public class Scenery implements Runnable
{

    private String path;
    private int x,y,status,height,width;
    private boolean camera=false;   
        
    public Scenery(String path)
    {
        this.path=path;
        this.status=0;
    }
    
    public void setX(int x)
    {
        this.x=x;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setY(int y)
    {
        this.y=y;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setWidth(int width)
    {
        this.width=width;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setHeight(int height)
    {
        this.height=height;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status=status;
    }
    
    public boolean getCamera()
    {
        return camera;
    }
    
    public void run()
    {
        boolean left=true;
        while(true)
        {
            if(!GUI.t.paused)
            {
                if(x==0)
                {
                    left=false;
                }
                else if(x+width==GUI.t.getWidth()-10)
                {
                    left=true;
                }

                if(left)
                    x--;
                else
                    x++;

                if(camera)
                    camera=false;
                else
                    camera=true;

                try
                {
                    Thread.sleep(60);
                }
                catch(InterruptedException e){}
            }
            else
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){}
            }
        }
    }
            
}
