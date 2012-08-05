package Graphics;
import Components.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.vecmath.Vector2f;
import java.io.*;
import java.awt.geom.*;
import java.net.URI;
/**
 *
 * @author Mohammad Abbas
 * This panel is used to represent the actual tank and holds arraylists containing the shoals food plants etc that are contained within it
 */
public class Tank extends JPanel implements Runnable, MouseListener, MouseMotionListener
{
    private ArrayList<Shoal> shoals;
    private ArrayList<Food> food;
    private ArrayList<Plant> plants = new ArrayList<Plant>();
    private Image i;
    private Image i2;
    private ArrayList<Maze> mazes = new ArrayList<Maze>();
    private ArrayList<Scenery> scenery = new ArrayList<Scenery>();
    private ArrayList<Image> sceneryI = new ArrayList<Image>();
    private int width,height,alpha;
    private Clock c;
    private String path="resources/untitled3.jpg";
    private int selected=0;
    private int x=0;
    public boolean kids;
    public boolean paused=false;
    private boolean hintStay=false;
    private int hintCount=200;
    private int hintAlpha=255;
    private int hintX;
    private int hintY;
    private boolean loaded;
    
    public Tank(ArrayList<Shoal> shoals, ArrayList<Food> food, int width, int height,boolean kids)
    {
        this.shoals=shoals;
        this.food=food;
        this.alpha=0;
        this.kids=kids;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);        
        makeBackground(width, height,false);   
        if(kids)
        {
            addFishPics();
        }        
        showHints();
    }
    
    public void addFishPics()       //used to add the fish icons on the left of the screen during the activity
    {
        Fish f = shoals.get(0).getFish().get(0);
        Fish newFish = new Fish(f.getPosition().getX(),f.getPosition().getY(),f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
        newFish.setKids(true);
        newFish.getPosition().setX(35);
        newFish.getPosition().setY(270);
        newFish.getVelocity().setX(1);
        shoals.get(0).addFish(newFish);
        Fish f1 = shoals.get(1).getFish().get(0);
        Fish newFish1 = new Fish(f1.getPosition().getX(),f1.getPosition().getY(),f1.getVelocity().getX(),f1.getVelocity().getY(),f1.getLength(),f1.getWidth(),f1.getEyeStrength(),f1.getSpeed(),f1.getColor1(),f1.getColor2(),f1.getColorType(),f1.getPredator());
        newFish1.setKids(true);
        newFish1.getPosition().setX(43);
        newFish1.getPosition().setY(400);
        newFish1.getVelocity().setX(1);
        shoals.get(1).addFish(newFish1);
    }
    
    public void makeBackground(int width, int height, boolean loaded)   //sets the background to the chosen picture
    {
        this.loaded=loaded;
        if(loaded)
            i = new ImageIcon(path).getImage();
        else
                i = new ImageIcon(this.getClass().getResource(path)).getImage();
        i2 = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.width=width;
        this.height=height;      
    }
    
    public boolean getLoaded()
    {
        return loaded;
    }
    
    public void makeBackground()
    {
        i = new ImageIcon(this.getClass().getResource(path)).getImage();
        i2 = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.width=width;
        this.height=height;  
        repaint();
    }
    
    public void showHints()
    {
        hintCount=200;
        hintAlpha=255;    
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Polygon triangle,triangle2,triangle3,triangle4,triangle5,triangle6,triangle7;
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5.0f));
        
        if(kids&&GUI.kids)      //activity mode
        {
            int image1X = 25;
            int image1Y = 30;
            
            x=100;
            g.setColor(new Color(251,197,210));
            g.fillRect(0,0,100,height);
            if(selected==1)
            {
                g.setColor(new Color(153,217,234));
                g.fillRect(image1X-5, image1Y-5, 55, 170);
            }
            else if(selected==2)
            {
                g.setColor(new Color(153,217,234));
                g.fillRect(20,260,65,60);
            }
            else if(selected==3)
            {
                g.setColor(new Color(153,217,234));
                g.fillRect(29,388,40,35);
            }
            g2.setColor(new Color(0,100,0));
            g2.drawLine(image1X+20,image1Y+10,image1X+20,image1Y+160);
            for(int j=0;j<10;j++)
            {
                g2.fillOval(image1X, image1Y+(j*15), 20, 10);
                g2.fillOval(image1X+20, image1Y+(j*15)+10, 20, 10);                
            }
            g2.setColor(Color.BLUE);
            g2.setFont(new Font("Comic Sans MS",0,20));
            g2.drawString("Add Plant", image1X-18, image1Y+185);
            g2.drawString("Change ", 18, 340);
            g2.drawString("Change ", 18, 443);
            if(selected==4)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GRAY);
            g.fillOval(25,490,46,42);
            g.setColor(new Color(251,197,210));
            g.drawLine(42,513,42,528);
            g.drawLine(53,513,53,528);
            g.drawLine(45,510,50,510);
            if(selected==4)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GRAY);
            g.drawLine(39,532,56,532);
            g.drawLine(39,539,56,539);
            g.drawLine(39,546,56,546);
            g.drawLine(42,550,53,550);
            g2.setColor(Color.BLUE);
            g2.drawString("Light", 25, 575);
            
        }
        else
        {
            x=0;
        }
        g.drawImage(i2,x,0,null);
        
        for(int i=0;i<scenery.size();i++)   //paints scenery
        {
            g.drawImage(sceneryI.get(i), scenery.get(i).getX(), scenery.get(i).getY(), null);            
        }
        g.setColor(new Color(0,0,0,alpha)); //paints black sheet which is used for day and night which is controlled by the alpha field
        g.fillRect(x,0,width,height);
        for(int i=0;i<scenery.size();i++)
        {
            if(scenery.get(i).getCamera())
            {
                g.setColor(Color.WHITE);
                g.fillRect(2+scenery.get(i).getX(), 66+scenery.get(i).getY(), 13, 12);
                g.fillOval(27+scenery.get(i).getX(), 68+scenery.get(i).getY(), 19, 19);
            }            
        }
        try         //paints the plants
        {
            for(int i=0;i<plants.size();i++)
            {
                if(plants.get(i).getStatus()==0)
                    g2.setColor(Color.gray);                
                else 
                    g2.setColor(new Color(0,100,0));

                g2.drawLine(plants.get(i).getX(), plants.get(i).getY(), plants.get(i).getX(),plants.get(i).getY()-(plants.get(i).getNumLeaves()*15));
                for(int j=0;j<plants.get(i).getLeaves().size();j++)
                {
                    g.fillOval(plants.get(i).getX()+plants.get(i).getLeaves().get(j).getX(), plants.get(i).getY()+plants.get(i).getLeaves().get(j).getY(), 20, 10);
                }
            }
        }
        catch(Exception e){}
        
        g.setColor(Color.WHITE);            //messages at the bottom of the screen
        if(kids&&!plants.isEmpty())
            g.drawString("New leaves in " + c.getCount() + " seconds", 100, getHeight()-2);
        else if(!kids&&this.equals(GUI.t))
            g.drawString("Left click to feed the fish, right click to pause", 0, getHeight()-2);
        
        try         //paints the fish
        {
            for(int i=0;i<shoals.size();i++)
            {
                for(int j=0;j<shoals.get(i).getFish().size();j++)
                {
                    int x = (int)shoals.get(i).getFish().get(j).getPosition().getX();
                    int y = (int)shoals.get(i).getFish().get(j).getPosition().getY();
                    float z = shoals.get(i).getFish().get(j).getVelocity().getX();
                    int length = shoals.get(i).getFish().get(j).getLength();
                    int width = shoals.get(i).getFish().get(j).getWidth();
                    int tail = shoals.get(i).getFish().get(j).getTail();
                    int colorType = shoals.get(i).getFish().get(j).getColorType();
                    Color color1=shoals.get(i).getFish().get(j).getColor1();
                    Color color2=shoals.get(i).getFish().get(j).getColor2();
                    boolean alive=shoals.get(i).getFish().get(j).isAlive();
                    boolean predator=shoals.get(i).getFish().get(j).getPredator();
                    boolean eating=shoals.get(i).getFish().get(j).isEating();
                    int eyeX=0;
                    int eyeY=0;
                    int [] x1 = new int[4];
                    int [] y1 = new int[4];
                    int [] x2 = new int[3];
                    int [] y2 = new int[3];
                    int [] x3 = new int[3];
                    int [] y3 = new int[3];
                    int [] x4 = new int[3];
                    int [] y4 = new int[3];
                    int [] x5 = new int[3];
                    int [] y5 = new int[3];
                    int [] x6 = new int[3];
                    int [] y6 = new int[3];
                    int [] x7 = new int[3];
                    int [] y7 = new int[3];
                    int offset=0;
                    int angle=0;
                    int angle2=0;
                    int angle3=0;
                    GradientPaint gp=null;
                    if(colorType==0)
                    gp = new GradientPaint(x,y,color1,x,(y+width)-(width/5),color2); 
                    else if(colorType==1)
                    gp = new GradientPaint(x,y+width,color1,x+(length/5),y,color2); 
                    else if(colorType==2)
                    gp = new GradientPaint(x,y,color1,x+(length/5),y,color2,true); 
                    
                    if(z>=0)
                    {
                        x2[0] = (x+length)-4; x2[1]=(x+length)-8; x2[2]=(x+length)-4;
                        y2[0] = y+9; y2[1]=y+15; y2[2]=y+21;
                        x3[0] = (x+length)-9; x3[1]=(x+length)-14; x3[2]=(x+length)-9;
                        y3[0] = y+9; y3[1]=y+17; y3[2]=y+21;
                        x4[0] = (x+length)-15; x4[1]=(x+length)-20; x4[2]=(x+length)-15;
                        y4[0] = y+9; y4[1]=y+19; y4[2]=y+21;
                        x5[0] = (x+length)-4; x5[1]=(x+length)-8; x5[2]=(x+length)-4;
                        y5[0] = y+22; y5[1]=y+30; y5[2]=y+31;
                        x6[0] = (x+length)-9; x6[1]=(x+length)-14; x6[2]=(x+length)-9;
                        y6[0] = y+22; y6[1]=y+30; y6[2]=y+33;
                        x7[0] = (x+length)-15; x7[1]=(x+length)-20; x7[2]=(x+length)-15;
                        y7[0] = y+22; y7[1]=y+30; y7[2]=y+33;

                        offset=2;
                        angle=90;
                        angle2=20;
                        angle3=150;
                        eyeX = x+(length-(length/3));
                        eyeY = y+(length-(length-(length/10)));
                        if(tail==0)
                        {
                            x1[0] = x-(length/3); x1[1] = x+(length/4); x1[2] = x-(length/3); x1[3] = x-(length/5); 
                            y1[0] = y+(width/5); y1[1] = y+(width/2); y1[2] = y+(width-(width/5)); y1[3] = y+(width/2);
                        }
                        else if(tail==1)
                        {
                            x1[0] = x-(length/6); x1[1] = x+(length/5); x1[2] = x-(length/6); x1[3] = x-(length/6); 
                            y1[0] = y+(width/10); y1[1] = y+(width/2); y1[2] = y+(width-(width/10)); y1[3] = y+(width/2);
                        }                       
                    }

                    else if (z<0)
                    {
                        x2[0] = x+4; x2[1]=x+8; x2[2]=x+4;
                        y2[0] = y+9; y2[1]=y+15; y2[2]=y+21;
                        x3[0] = x+9; x3[1]=x+14; x3[2]=x+9;
                        y3[0] = y+9; y3[1]=y+17; y3[2]=y+21;
                        x4[0] = x+15; x4[1]=x+20; x4[2]=x+15;
                        y4[0] = y+9; y4[1]=y+19; y4[2]=y+21;
                        x5[0] = x+4; x5[1]=x+8; x5[2]=x+4;
                        y5[0] = y+22; y5[1]=y+30; y5[2]=y+31;
                        x6[0] = x+9; x6[1]=x+14; x6[2]=x+9;
                        y6[0] = y+22; y6[1]=y+30; y6[2]=y+33;
                        x7[0] = x+15; x7[1]=x+20; x7[2]=x+15;
                        y7[0] = y+22; y7[1]=y+30; y7[2]=y+33;

                        offset=2;
                        angle=-90;
                        angle2=-22;
                        angle3=-150;
                        eyeX = x+(length/5);
                        eyeY = y+(length-(length-(length/10)));
                        if (tail==0)
                        {
                            x1[0] = x+length+(length/3); x1[1] = x+length-(length/4); x1[2] = x+length+(length/3); x1[3] = x+length+(length/5);
                            y1[0] = y+(width/5); y1[1] = y+(width/2); y1[2] = y+(width-(width/5)); y1[3] = y+(width/2);
                        }
                        else if(tail==1)
                        {
                            x1[0] = x+length+(length/6); x1[1] = x+length-(length/5); x1[2] = x+length+(length/6); x1[3] = x+length+(length/6);
                            y1[0] = y+(width/10); y1[1] = y+(width/2); y1[2] = y+(width-(width/10)); y1[3] = y+(width/2);
                        }                        
                    }

                    triangle = new Polygon(x1,y1,4);
                    triangle2 = new Polygon(x2,y2,3);
                    triangle3 = new Polygon(x3,y3,3);
                    triangle4 = new Polygon(x4,y4,3);
                    triangle5 = new Polygon(x5,y5,3);
                    triangle6 = new Polygon(x6,y6,3);
                    triangle7 = new Polygon(x7,y7,3);
                    Arc2D.Double semiCircleBig = new Arc2D.Double(x, y, length, width, angle, 180, Arc2D.OPEN);
                    Arc2D.Double semiCircleSmall = new Arc2D.Double(x, y, length, width, angle2, 180, Arc2D.OPEN);
                    Arc2D.Double semiCircleSmall2 = new Arc2D.Double(x, y+offset, length+offset, width+offset, angle3, 180, Arc2D.OPEN);

                    if(alive)
                    {
                        if(!predator)
                        {
                            g2.setPaint(gp);
                            g2.fillOval(x,y,length,width);
                        }
                        else
                        {
                            g.setColor(Color.WHITE);
                            g.fillPolygon(triangle2);
                            g.fillPolygon(triangle3);
                            g.fillPolygon(triangle4);
                            g.fillPolygon(triangle5);
                            g.fillPolygon(triangle6);
                            g.fillPolygon(triangle7);

                            g2.setPaint(gp);
                            if(eating)
                            {
                                g2.fillOval(x,y,length,width);                            
                            }
                            else
                            {
                                g2.fill(semiCircleBig);
                                g2.fill(semiCircleSmall);
                                g2.fill(semiCircleSmall2);
                            }

                        }
                        if(tail==0)
                                g2.fillPolygon(triangle);
                        if(predator)
                        {
                            g.setColor(Color.BLACK);
                            g.fillOval(eyeX,eyeY,7,7);
                            g.setColor(Color.WHITE);
                            g.fillOval(eyeX+2,eyeY+2,3,3);

                        }
                        else
                        {
                            g.setColor(Color.BLACK);
                            g.fillOval(eyeX,eyeY,4,4);
                        }
                    }

                    else
                    {
                        g2.fillOval(x, y, (length/3)+length, 5);
                    }

                }
            }
        }
        catch(Exception e){}
        
        for(int x=0;x<mazes.size();x++) //draws the mazes
        {
            if(mazes.get(x).getStatus()==0)
                g.setColor(Color.GRAY);
            else
                g.setColor(Color.BLACK);
            
            for(int i=0;i<mazes.get(x).getMaze().length;i++)
            {
                for(int j=0;j<mazes.get(x).getMaze()[0].length;j++)
                {
                    if(mazes.get(x).getMaze()[i][j].getState()==2)
                    {
                        g.fillRect(mazes.get(x).getX()+mazes.get(x).getMaze()[i][j].getX(), mazes.get(x).getY()+mazes.get(x).getMaze()[i][j].getY(), 30, 25);
                    }
                }
            }
        }
        
        //paint food
        try
        {
            g.setColor(Color.WHITE);
                
            for(int i=0;i<food.size();i++)
            {
                g.fillOval(food.get(i).getX(),food.get(i).getY(),5,5);
            }
        }
        catch(Exception e){}
        
        if(this.equals(GUI.t)&&!GUI.kids)           //displays a hint at the bottom right of screen when run in normal mode
        {                                           //which fades away and comes back when the mouse goes over it
            hintX=this.getWidth()-370;
            hintY=this.getHeight()-65;

            if(hintCount!=0||hintStay)
            {
                if(hintCount>0)
                    hintCount--;
                g.setColor(new Color(255,249,189));
                g.fillRoundRect(hintX,hintY, 360, 50, 20, 20);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial",0,14));
                g.drawString("Use the buttons below to change a shoal's attributes", hintX+15, hintY+25);
                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial",0,10));
                g.drawString("Click for help", hintX+150, hintY+40);
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawRoundRect(hintX,hintY, 360, 50, 20, 20);
                int x2[]=new int[3];
                int y2[]=new int[3];
                Polygon triangle1;
                        
                x2[0] =hintX+155; x2[1] = hintX+195; x2[2] = hintX+175; 
                y2[0] = hintY+45; y2[1] = hintY+45; y2[2] =hintY+60;
                
                g.setColor(new Color(255,249,189));
                triangle1 = new Polygon(x2,y2,3);
                g.fillPolygon(triangle1);
                
            }
            else if(hintCount==0&&hintAlpha>20)
            {
                g.setColor(new Color(255,249,189,hintAlpha));
                g.fillRoundRect(hintX,hintY, 360, 50, 20, 20);                
                g.setColor(new Color(0,0,0,hintAlpha));
                g.setFont(new Font("Arial",0,14));
                g.drawString("Use the buttons below to change a shoal's attributes", hintX+15, hintY+25);
                g.setColor(new Color(0,0,255,hintAlpha));
                g.setFont(new Font("Arial",0,10));
                g.drawString("Click for help", hintX+150, hintY+40);
                g2.setColor(new Color(0,0,0,hintAlpha));
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawRoundRect(hintX,hintY, 360, 50, 20, 20);
                int x2[]=new int[3];
                int y2[]=new int[3];
                Polygon triangle1;
                        
                x2[0] =hintX+155; x2[1] = hintX+195; x2[2] = hintX+175; 
                y2[0] = hintY+45; y2[1] = hintY+45; y2[2] =hintY+60;
                
                g.setColor(new Color(255,249,189,hintAlpha));
                triangle1 = new Polygon(x2,y2,3);
                g.fillPolygon(triangle1);
                hintAlpha-=6;                      
            }            
        }
    }
    
    public void setPath(String path)
    {
        this.path=path;
    }
    
    public void setAlpha(int alpha)
    {
        this.alpha=alpha;
    }
   
    public int getAlpha()
    {
        return alpha;        
    }
    
    public void addPlants(Plant p)
    {
        plants.add(p);
    }
    
    public ArrayList<Plant> getPlants()
    {
        return plants;
    }
    
    public void addScenery(Scenery s)
    {
        scenery.add(s);
        Image i = new ImageIcon(this.getClass().getResource("resources/"+s.getPath())).getImage();
        sceneryI.add(i);   
        s.setHeight(i.getHeight(null));
        s.setWidth(i.getWidth(null));        
    }
    
    public ArrayList<Scenery> getScenery()
    {
        return scenery;
    }
    
    public ArrayList<Image> getSceneryImages()
    {
        return sceneryI;
    }
    public void setClock(Clock c)
    {
        this.c=c;
    }
    
    public void addMaze(Maze m)
    {
        mazes.add(m);
    }
    
    public ArrayList<Maze> getMazes()
    {
        return mazes;
    }
    
    public ArrayList<Shoal> getShoals()
    {
        return shoals;
    }
    
    public void setShoals(ArrayList<Shoal> shoals)
    {
        this.shoals=shoals;
    }
    
    public ArrayList<Food> getFood()
    {
        return food;
    }
    
    public void addMaze(String s)       //takes a filename and parses it and creates a maze object which is added to the maze
    {
        try
        {
            InputStreamReader fileIn;           
            Maze m;
            if(s.equals("maze"))
            {
                fileIn = new InputStreamReader(new FileInputStream(s));                
            }
            else
            {
                fileIn = new InputStreamReader(this.getClass().getResourceAsStream(s));
            }
            BufferedReader in = new BufferedReader(fileIn);            
            
            int c=0;
            int x=0;
            int y=0;
            String mazeString="";
            c=in.read();
            c=in.read();
            while(true)
            {
                y++;
                x=0;
                while((char)c!='\n')
                {
                    c=in.read();
                    if(Character.getNumericValue((char)c)>=0)
                    {
                        mazeString=mazeString+((char)c);
                        x++;
                    }                        
                }
                c=in.read();
                c=in.read();
                if((char)c=='-')
                    break;
            }
            fileIn.close();
            in.close();
            MazeDots[][] maze = new MazeDots[y][x];
            m = new Maze(s);
            for(int i=0;i<maze.length;i++)
            {                
                for(int j=0;j<maze[0].length;j++)
                {
                    maze[i][j]=new MazeDots(j*30,i*25,Character.getNumericValue(mazeString.charAt((i*maze[0].length)+j)),m);
                }                         
            }
            
            m.setMaze(maze);
            addMaze(m);
            mazes.get(mazes.size()-1).solve();    
        }
        
        catch(Exception e){e.printStackTrace();}
    }
    
        
    public MazeDots inMaze(Fish fish)   //called by a fish object, checks to see if the fish is in a maze or not
    {
        float x=fish.getPosition().getX();
        float y=fish.getPosition().getY();
        int length=fish.getLength();
        int width=fish.getWidth();
        float dir = fish.getVelocity().getX();
        
        for(int z=0;z<mazes.size();z++)
        {
            for(int i=0;i<mazes.get(z).getMaze().length;i++)
            {
                for(int j=0;j<mazes.get(z).getMaze()[0].length;j++)
                {
                    try
                    {
                        if(mazes.get(z).getMaze()[i][j].getState()!=1)
                        {
                            if(dir>=0)
                            { 
                                if(x+length>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX() && x+length<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                        && y+width>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY() && y+width<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                    return mazes.get(z).getMaze()[i][j];
                            }

                            else if(dir<0)
                            {
                                if(x>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX() && x<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                        && y+width>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY() && y+width<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                    return mazes.get(z).getMaze()[i][j];
                            }
                        }
                        
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }
        }
        return null;
    }
    
    public Vector whatIsNearby(Fish fish)       //called by a fish objects perception method, returns all objects within eyesight range
    {
        int eyeX=0;
        int eyeY=0;
        Vector2f fishPos = new Vector2f(fish.getPosition());
        if(fish.getVelocity().getX()>=0)
        {
            eyeX = (int)fishPos.getX()+(fish.getLength()-(fish.getLength()/5));
            eyeY = (int)fishPos.getY()+(fish.getLength()-(fish.getLength()-(fish.getLength()/10)));
        }
        else if(fish.getVelocity().getX()<0)
        {
            eyeX = (int)fishPos.getX()+(fish.getLength()/5);
            eyeY = (int)fishPos.getY()+(fish.getLength()-(fish.getLength()-(fish.getLength()/10)));
        }
        Vector v=new Vector();
        Vector2f v1 = new Vector2f();
        Vector2f v2 = new Vector2f();
        int alphaSight=10;
        if(GUI.kids&&kids)      //used to take daylight into account of visibility
        {
            alphaSight=1;
        }
        else if(!GUI.kids&&!kids)
        {
            alphaSight=5;
        }
        for(int i=0;i<food.size();i++)      //loops through the food
        {
            try
            {
                v1.set(new Vector2f(eyeX,eyeY));
                v2.set(new Vector2f(food.get(i).getX(),food.get(i).getY()));
                v1.sub(v2);
                if (v1.length() < (fish.getEyeStrength()-(alpha/alphaSight)))
                    v.add(food.get(i));
            }
            catch(Exception e){}      
        }
        
        for(int i=0;i<shoals.size();i++)        //loops through the fish
        {
            for(int j=0;j<shoals.get(i).getFish().size();j++)
            {
                try
                {
                
                    v1.set(new Vector2f(eyeX,eyeY));
                    v2.set(new Vector2f(shoals.get(i).getFish().get(j).getPosition()));
                    v1.sub(v2);
                    
                    if (v1.length() < (fish.getEyeStrength()-(alpha/alphaSight)) && shoals.get(i).getFish().get(j)!=fish && fish.getShoal()!=shoals.get(i)&&!shoals.get(i).getFish().get(j).getKids())
                        v.add(shoals.get(i).getFish().get(j));
                }
                catch(Exception e){}
                
            }
            
        }        
        
        for(int i=0;i<plants.size();i++)        //loops through the mazes
        {
            for(int j=0;j<plants.get(i).getLeaves().size();j++)
            {
                try
                {                
                    v1.set(new Vector2f(eyeX,eyeY));
                    v2.set(new Vector2f(plants.get(i).getX()+plants.get(i).getLeaves().get(j).getX(),plants.get(i).getY()+plants.get(i).getLeaves().get(j).getY()));
                    v1.sub(v2);
                    
                    if (v1.length() < (fish.getEyeStrength()-(alpha/alphaSight)))
                        v.add(plants.get(i).getLeaves().get(j));
                }
                catch(Exception e){}
            }
        }
        return v;
    }
    
    public int inPatch(Fish fish)   //called by the fish, checks whether the fish is within the tank or not, responsible for the fact that the fish
    {                               //fish bounce off the bottom and go from one end of the tank to the other, also used to stop fish going 
        int patchX;                 //through the wall of a maze
        int patchX1;
        int patchY;
        int patchY1;
        int result=0;
        int result1=3;
        float x=fish.getPosition().getX();
        float y=fish.getPosition().getY();
        int length=fish.getLength();
        int width=fish.getWidth();
        float dir=fish.getVelocity().getX();
        float dir2=fish.getVelocity().getY();
        patchX = 0;
        patchX1 = getWidth()-10;
        patchY = 0;
        patchY1 = getHeight()-10;
        if(kids&&GUI.kids)
            patchX=100;
        
        for(int z=0;z<mazes.size();z++) //loops through mazes
        {
            for(int i=0;i<mazes.get(z).getMaze().length;i++)
            {
                for(int j=0;j<mazes.get(z).getMaze()[0].length;j++)
                {
                    if(mazes.get(z).getMaze()[i][j].getState()==2)
                    {
                        if(dir>=0)
                        { 
                            if(x+length>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX() && x+length<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                    && y+width>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY() && y+width<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                result1=4;
                        }

                        else if(dir<0)
                        {
                            if(x>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX() && x<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                    && y+width>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY() && y+width<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                result1=4;
                        }

                        if(dir2>=0)
                        {
                            if(x+length>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX() && x+length<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                    && y+width>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY() && y+width<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                result1+=2;
                        }

                        else if(dir2<0)
                        {
                            if(x>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX() && x<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                    && y>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY() && y<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                result1+=2;
                        }
                        
                        if(result1!=3)
                            return result1;
                    }
                    
                }
            }
        }
        if(dir>=0)  
        {
            if(x+length>=patchX1)   //going off the screen to the right
                result=1;            
        }
        
        else if(dir<0)
        {
            if(x<=patchX)           //going off the screen to the left
               result=1;           
        }
        
        if(dir2>=0)
        {
            if(y+width>=patchY1)            //going off the screen to the bottom
                result+=2;
        }
        
        else if(dir2<0)
        {
            if(y<=patchY)                   //going off the screen to the top
                result+=2;
        }
        
        if(getWidth()!=0&&getHeight()!=0)
            return result;
        else
            return 0;
    }
    
    public void moved(int x, int y) //called when the mouse is moved, used to move objects like mazes and plants around the screen
    {
        if(!kids&&!GUI.kids)
        {
            selected=0;
            repaint();
            
            for(int i=0;i<mazes.size();i++)
            {
                if(mazes.get(i).getStatus()==0)
                {
                    mazes.get(i).setX(x-((mazes.get(i).getMaze()[0].length*30)/2));
                    mazes.get(i).setY(y-((mazes.get(i).getMaze().length*25)/2));
                }            
            }

            for(int i=0;i<scenery.size();i++)
            {
                if(scenery.get(i).getStatus()==0)
                {
                    scenery.get(i).setX(x-(scenery.get(i).getWidth()/2));
                    scenery.get(i).setY(y-(scenery.get(i).getHeight()/2));
                }
            }            
        }
        
        else if(kids&&GUI.kids)
        {
            if(x>100)
            {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                for(int i=0;i<plants.size();i++)
                {
                    if(plants.get(i).getStatus()==0)
                    {
                        plants.get(i).setX(x);
                        plants.get(i).setY(y+(plants.get(i).getNumLeaves()*15)/2);
                    }
                }
                selected=0;
                repaint();
            }
            else
            {
                if(y>30&&y<200)
                {
                    selected=1;
                    repaint();
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else if(y>270&&y<320)
                {
                    selected=2;
                    repaint();
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else if(y>390&&y<425)
                {
                    selected=3;
                    repaint();
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else if(y>490&&y<550)
                {
                    selected=4;
                    repaint();
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else
                {
                    selected=0;
                    repaint();
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }
        
        if(hintCount==0&&hintAlpha>20)
        {
            if(x>hintX&&x<hintX+360&&y>hintY&&y<hintY+50)
            {
                hintAlpha=255;
                hintStay=true;
            }                
            else
            {
                hintStay=false;
            }
        }
        
    }
    
    public void released(int x, int y) //called when the mouse is released, used for dragging and dropping
    {
        if(!GUI.kids&&!kids)
        {
            for(int i=0;i<mazes.size();i++)
            {
                if(mazes.get(i).getStatus()==0)
                {
                    mazes.get(i).setStatus(1);
                }            
            }

            for(int i=0;i<scenery.size();i++)
            {
                if(scenery.get(i).getStatus()==0)
                {
                    scenery.get(i).setStatus(1);
                }
            }
        }
        else if(GUI.kids&&kids&&x>100)
        {
            for(int i=0;i<plants.size();i++)
            {
                if(plants.get(i).getStatus()==0)
                {
                    plants.get(i).setStatus(1);
                }
            }
        }                         
    }
    
    public void pressed(int x, int y, int button)   //called when the mouse is press, used for dragging and dropping, feeding fish and pausing
    {
        boolean mazeClick=false;
        int mazeNumber=0;
        boolean sceneryClick=false;
        int sceneryNumber=0;
        boolean plantClick=false;
        int plantNumber=0;
        
        if(hintAlpha>20&&this.equals(GUI.t)&&!GUI.kids&&!kids)
        {
            if(x>hintX&&x<hintX+360&&y>hintY&&y<hintY+50)
            {
                if(button==1)
                {
                    try
                    {
                        java.awt.Desktop.getDesktop().browse(new URI("http://www.dcs.qmul.ac.uk/~mya3/project/user_manual/Manual#label8"));
                    } 
                    catch(Exception e1){}
                }            
                hintCount=0;
                hintAlpha=0;                
                hintStay=false;
                return;
            }
        }
        
        boolean found=false;
        if(kids&&GUI.kids)
        {            
            if(x<100)
            {
                if(y>30&&y<200)
                {
                    addPlants(new Plant(0,0));
                }
                else if(y>270&&y<320)
                {
                    GUI.g.changeParameters(0);
                }
                else if(y>390&&y<425)
                {
                    GUI.g.changeParameters(1);
                }
                else if(y>490&&y<550)
                {
                    if(c.getLightOption()==1)
                        c.setLightOption(2);
                    else
                        c.setLightOption(1);
                }
            }
            else
            {
                for(int i=0;i<plants.size();i++)
                {
                    if(plants.get(i).getStatus()==0)
                    {
                        found=true;
                        plants.get(i).setStatus(1);
                    }
                }
            
                if(!found)
                {
                    for(int i=0;i<plants.size();i++)
                    {
                        if(x>=plants.get(i).getX()-20&&x<plants.get(i).getX()+20&&y>=plants.get(i).getY()-plants.get(i).getNumLeaves()*15&&y<plants.get(i).getY())
                        {
                            plantClick=true;
                            plantNumber=i;

                        }
                    }

                    if(plantClick)
                    {
                        plants.get(plantNumber).setStatus(0);
                    }
                    else
                    {
                        if(!paused)
                        {
                            try{
                                GUI.shoalThreads.get(0).suspend();
                                GUI.shoalThreads.get(1).suspend();
                                GUI.clockT.suspend();
                            }
                            catch(Exception e){}
                            paused=true;
                        }
                        else
                        {
                            GUI.shoalThreads.get(0).resume();
                            GUI.shoalThreads.get(1).resume();
                            GUI.clockT.resume();
                            paused=false;
                        }
                        
                    }
                }
            }
        }
        
        else
        {
            if(button==3&&this.equals(GUI.t))
            {
                if(!paused)
                {
                    try{
                        for(int i=0;i<GUI.shoalThreads.size();i++)
                        {
                            GUI.shoalThreads.get(i).suspend();
                        }                               
                        GUI.clockT.suspend();
                    }
                    catch(Exception e){}
                    paused=true;
                }
                else
                {
                    for(int i=0;i<GUI.shoalThreads.size();i++)
                    {
                        GUI.shoalThreads.get(i).resume();
                    }
                    GUI.clockT.resume();
                    paused=false;
                }
                return;
            }
            for(int i=0;i<scenery.size();i++)
            {
                if(scenery.get(i).getStatus()==0)
                {
                    found=true;
                    scenery.get(i).setStatus(1);
                }
            }

            for(int i=0;i<mazes.size();i++)
            {
                if(mazes.get(i).getStatus()==0)
                {
                    found=true;
                    mazes.get(i).setStatus(1);
                }            
            }


            if(!found)
            {
                for(int z=0;z<mazes.size();z++)
                {
                    for(int i=0;i<mazes.get(z).getMaze().length;i++)
                    {
                        for(int j=0;j<mazes.get(z).getMaze()[0].length;j++)
                        {
                            if(mazes.get(z).getMaze()[i][j].getState()==2)
                            {                         
                                if(x>=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()&&x<=mazes.get(z).getX()+mazes.get(z).getMaze()[i][j].getX()+30
                                        &&y>=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()&&y<=mazes.get(z).getY()+mazes.get(z).getMaze()[i][j].getY()+25)
                                {
                                    mazeClick=true;
                                    //System.out.println(mazes.get(z).getMaze()[i][j].getX()+":"+mazes.get(z).getMaze()[i][j].getY());
                                    mazeNumber=z;
                                }
                            }

                        }
                    }
                }
                for(int i=0;i<scenery.size();i++)
                {
                    if(x>=scenery.get(i).getX()&&x<scenery.get(i).getX()+scenery.get(i).getWidth() 
                            && y>=scenery.get(i).getY()&&y<scenery.get(i).getY()+scenery.get(i).getHeight())
                    {
                        sceneryClick=true;
                        sceneryNumber=i;
                    }
                }
                for(int i=0;i<plants.size();i++)
                {
                    if(x>=plants.get(i).getX()-20&&x<plants.get(i).getX()+20&&y>=plants.get(i).getY()-plants.get(i).getNumLeaves()*15&&y<plants.get(i).getY())
                    {
                        plantClick=true;
                        plantNumber=i;

                    }
                }

                if(mazeClick)
                {
                    mazes.get(mazeNumber).setStatus(0);
                }       
                else if(sceneryClick)
                {
                    scenery.get(sceneryNumber).setStatus(0);
                }
                else if(plantClick)
                {
                    plants.get(plantNumber).setStatus(0);
                }
                else
                {
                    int energy=0;                
                    if(GUI.foraging)
                        energy=1000;

                    Food f = new Food(x,y,energy);
                    food.add(f);
                }

            }
        }
        
    }
    
    public void run()   //the tank is a thread, this is the run method which is called every 40 ms
    {
        while(true)
        {
            try
            {
                repaint();
                Thread.sleep(40);
            }
            catch(InterruptedException e){}
        }            
    }
    
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {
        released(e.getX(),e.getY());
    }
    public void mousePressed(MouseEvent e)
    {
        int button = e.getButton();
        pressed(e.getX(),e.getY(),button);
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        moved(e.getX(),e.getY());        
    }
    public void mouseDragged(MouseEvent e)
    {
        moved(e.getX(),e.getY());
    }
    
    
}
