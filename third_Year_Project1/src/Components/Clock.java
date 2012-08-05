package Components;
import Graphics.GUI;
import Graphics.Tank;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Mohammad Abbas
 * This class acts as a clock and is responsible for managing distribution of food and refreshing data from a database
 */
public class Clock implements Runnable
{
    private int numSeconds;
    private int count;
    private int numFood=0;
    private int energy;
    private Tank t;
    public boolean popup=true;
    private int ratio = 3;
    private int lightOption;
    private int origNumRows=0;
    private boolean messageSeen;
    
    public Clock(int numSeconds, int energy, Tank t)
    {
        this.numSeconds=numSeconds;
        this.t=t;
        this.energy=energy;
        count=numSeconds;
        t.setClock(this);
        ratio=GUI.ratio;
        lightOption = GUI.lightOption;
    }
    
    public void setCount(int numSeconds)
    {
        this.numSeconds=numSeconds;
        count=numSeconds;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public void setMessageSeen(boolean b)
    {
        messageSeen=b;
    }
    
    public void setLightOption(int lightOption)
    {
        this.lightOption=lightOption;
    }
    
    public int getLightOption()
    {
        return lightOption;
    }
    
    public void setRatio(int ratio)
    {
        this.ratio=ratio;
    }
    
    public int getRatio()
    {
        return ratio;
    }
    
    public int getNumFood()
    {
        return numFood;
    }
    
    public void setEnergy(int energy)
    {
        this.energy=energy;
    }
    
    public int getNumSeconds()
    {
        return numSeconds;
    }
    
    public void setNumSeconds(int numSeconds)
    {
        this.numSeconds=numSeconds;
    }
    
    public void setOrigNumRows(int origNumRows)
    {
        this.origNumRows=origNumRows;
    }
    
    public int getOrigNumRows()
    {
        return origNumRows;
    }
    
    public void run()
    {
        for(int i=0;i<t.getShoals().size();i++)
        {
            try{
                numFood+=t.getShoals().get(i).getFish().size();}
            catch(Exception e){}
        }
        numFood*=ratio;        
        boolean blocked;
        boolean darker=true;
        while(true)
        {
            if(count==1)
            {
                try
                {
                    if(t.kids)      //regrows leaves on plants
                    {
                        for(int i=0;i<t.getPlants().size();i++)
                        {
                            t.getPlants().get(i).makeLeaves();
                        }
                        count=numSeconds;

                    }
                    else        //adds food to tank
                    {
                        numFood=0;
                        for(int i=0;i<t.getShoals().size();i++)
                        {
                            numFood+=t.getShoals().get(i).getFish().size();
                        }
                        numFood*=ratio;

                        for(int k=0;k<numFood/2;k++)        //puts a lot of food in maze goal position
                        {
                            blocked=false;
                            int x = new Random().nextInt(t.getWidth()-50);
                            int y = new Random().nextInt(t.getHeight()-50);
                            for(int z=0;z<t.getMazes().size();z++)
                            {
                                for(int i=0;i<t.getMazes().get(z).getMaze().length;i++)
                                {
                                    for(int j=0;j<t.getMazes().get(z).getMaze()[0].length;j++)
                                    {
                                        if(t.getMazes().get(z).getMaze()[i][j].getState()==1)
                                        {
                                            if((x+5)>=t.getMazes().get(z).getX()+t.getMazes().get(z).getMaze()[i][j].getX()&&(x+20)<t.getMazes().get(z).getX()+t.getMazes().get(z).getMaze()[i][j].getX()+30
                                                    && (y-5)>=t.getMazes().get(z).getY()+t.getMazes().get(z).getMaze()[i][j].getY()&&(y+15)<t.getMazes().get(z).getY()+t.getMazes().get(z).getMaze()[i][j].getY()+30)
                                                blocked=true;
                                        }

                                    }
                                }
                            }

                            if(blocked)
                                t.getFood().add(new Food(x+10,y,energy));
                            else if(!blocked &&t.getMazes().size()!=0)
                                k--;

                        }

                        for(int k=0;k<numFood;k++)
                        {
                            blocked=false;
                            int x = new Random().nextInt(t.getWidth()-50);
                            int y = new Random().nextInt(t.getHeight()-50);
                            for(int z=0;z<t.getMazes().size();z++)
                            {
                                for(int i=0;i<t.getMazes().get(z).getMaze().length;i++)
                                {
                                    for(int j=0;j<t.getMazes().get(z).getMaze()[0].length;j++)
                                    {
                                            if((x+10)>=t.getMazes().get(z).getX()+t.getMazes().get(z).getMaze()[i][j].getX()&&(x+10)<t.getMazes().get(z).getX()+t.getMazes().get(z).getMaze()[i][j].getX()+30
                                                    && y>=t.getMazes().get(z).getY()+t.getMazes().get(z).getMaze()[i][j].getY()&&y<t.getMazes().get(z).getY()+t.getMazes().get(z).getMaze()[i][j].getY()+30)
                                                blocked=true;      
                                    }
                                }
                            }

                            if(!blocked)
                                t.getFood().add(new Food(x+10,y,energy));
                            else
                                k--;
                        }                    
                        count=numSeconds;                
                    }
                }
                catch(Exception e){}
            }
            else if(count==numSeconds-1)
            {
                if(popup&&t.kids)
                {
                    try
                    {
                        GUI.card2.show(GUI.p4,"2");
                        GUI.messagePanel1.setType(1);
                        GUI.messagePanel1.setString("Keep the fish alive using the buttons on the left");
                        GUI.messagePanel1.setString1("Click on the screen to play and pause");
                        GUI.message.show();
                        popup=false;
                    }
                    catch(Exception e){}
                }
                count=numSeconds-2;
            }
            else
            {
                count--;
            }
            if(this.equals(GUI.clock))      //simulates night and day
            {
                try
                {
                    if(lightOption==3)
                    {
                        if(t.getAlpha()==0)
                            darker=true;
                        else if(t.getAlpha()>=255)
                            darker=false;

                        if(darker)
                            t.setAlpha(t.getAlpha()+1);
                        else
                            t.setAlpha(t.getAlpha()-1);
                    }
                    else if(lightOption==1)
                    {
                        t.setAlpha(0);
                    }
                    else if(lightOption==2)
                    {
                        t.setAlpha(255);
                    }
                }
                catch(Exception e){}
            }
            
            if(GUI.connected&&this.equals(GUI.clock))       //refreshes data
            {           
                ResultSet rs;
                int max=0;
                int min=0;
                int numRows=0;
                String name="";
                int score=0;
                try
                {
                    Statement s = GUI.cdb2.getConnection().createStatement();
                    s.execute("use "+ GUI.cdb2.getDatabase());
                    rs = s.executeQuery("select count(*) from " +GUI.cdb2.getTable()+"");
                    rs.next();
                    numRows = rs.getInt(1);
                    rs = s.executeQuery("select min("+ GUI.cdb2.getColumn2()+") from " +GUI.cdb2.getTable());
                    rs.next();
                    min = rs.getInt(1);
                    rs = s.executeQuery("select max("+ GUI.cdb2.getColumn2()+") from " +GUI.cdb2.getTable());
                    rs.next();
                    max = rs.getInt(1);

                    rs = s.executeQuery("select * from " +GUI.cdb2.getTable());                   
                    
                    if(numRows>origNumRows) //added records
                    {
                        GUI.g.addShoals(numRows-origNumRows);                        
                        origNumRows=numRows;                        
                    }
                    ArrayList<Integer>shoals=new ArrayList<Integer>();
                    
                    for(int i=0;i<numRows;i++)
                    {
                        rs.next();
                        name = rs.getString(GUI.cdb2.getColumn());
                        score = rs.getInt(GUI.cdb2.getColumn2());
                        int k;
                        for(k=0;k<GUI.shoals.size();k++)
                        {
                            if(GUI.shoals.get(k).getName().equals(name)&&!GUI.shoals.get(k).getFish().get(0).getPredator())
                            {
                                break;
                            }
                        }
                        
                        if(k==GUI.shoals.size())
                        {
                            for(int x=0;x<GUI.shoals.size();x++)
                            {
                                if(GUI.shoals.get(x).getName().equals("")&&!GUI.shoals.get(x).getFish().get(0).getPredator())
                                {
                                    k=x;
                                    break;
                                }
                            }   
                        }
                        shoals.add(k);
                        if(k<GUI.shoals.size())
                        {
                            for(int j=0;j<GUI.shoals.get(k).getFish().size();j++)
                            {
                                GUI.shoals.get(k).setName(name);
                                GUI.shoals.get(k).setScore(score);                    
                                float newScore=0;
                                newScore = (score-min);
                                newScore = newScore/(max-min);
                                newScore++;
                                if(max-min==0)
                                {
                                    newScore=2;
                                }
                                GUI.shoals.get(k).getFish().get(j).setEyeStrength(newScore*125);
                                GUI.shoals.get(k).getFish().get(j).setSpeed(newScore*1.25f);                                   
                            }
                        }
                    }
                   
                    if(numRows<origNumRows)
                    {
                        for(int i=0;i<GUI.shoals.size();i++)
                        {
                            if(!shoals.contains(i)&&!GUI.shoals.get(i).getFish().get(0).getPredator())
                            {
                                GUI.numShoals--;
                                GUI.shoalThreads.get(i).stop();
                                GUI.shoalThreads.remove(i);
                                GUI.shoals.remove(i);
                                break;
                            }
                        }
                        origNumRows--;
                    }                    
                GUI.g.addIcons();   
                }
                
                catch(Exception e1){e1.printStackTrace();}
                
            }
            
            if(t.kids&&GUI.kids&&!messageSeen)
            {
                if(GUI.t.getShoals().get(0).getFish().size()==1&&GUI.t.getShoals().get(1).getFish().size()==1)
                {
                    try
                    {
                        GUI.card2.show(GUI.p4,"1");
                        GUI.messagePanel.setType(1);
                        GUI.messagePanel.setString("Oops, the fish have all died");                    
                        GUI.message.show();                    
                    }
                    catch(Exception e){}
                }
            }
            
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
        }
        
    }     
}
