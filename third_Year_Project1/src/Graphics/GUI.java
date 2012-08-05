package Graphics;
import Components.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/** 
 *
 * @author Mohammad Abbas
 * This class is is the frame of the simulation, it is executed by the main method and is responsible for
 * controlling the different aspects of the simulation 
 */
public class GUI extends JFrame implements ComponentListener, MouseListener, ActionListener, KeyListener, WindowListener
{
    public Thread thread = new Thread();
    public Thread thread1 = new Thread();
    public Thread thread2 = new Thread();
    public Thread tankThread = new Thread();
    public static Tank t,t1;
    public static ArrayList<Shoal> testShoal;
    private Fish[] fishes;
    public static Clock clock,c2;//=new Clock(0,0,null);
    public static int z,x,y,x1,y1,r,r1,eyeSight;
    public static float speed;
    public static boolean foraging= true;
    public static boolean connected = false;
    public static boolean intelligence=false;
    public static boolean kids=false;
    public static boolean activity1=true;
    public static JLabel[] buttons;
    public static JLabel[] labels;
    public static ArrayList<Shoal> shoals = new ArrayList<Shoal>();
    public static ArrayList<Thread> shoalThreads = new ArrayList<Thread>();
    public static JDialog d,CP,DB,IS,CB,CM,options,message,splash;
    public static JFrame initial, lesson, quiz;
    public static CardLayout card,card1,card2,card3;
    public static ConnectDB cdb;
    public static ConnectDB2 cdb2;
    public static JPanel p,p1,p2,p3,p4,p5;
    public static KidsPanel kP;
    private JMenuBar menuBar;
    private JMenu file, tools, insert, help;
    private JMenuItem reset, exit, optionsMenu, maze, connectDB, connectDB2, insertScenery, insertShoal, changeBackground, helpC, about, mainMenu; 
    public static JCheckBoxMenuItem foragingMenu;
    private ChangeParameters changeP; 
    private Options optionsP;
    public static int numShoals=21;
    public static int numFish=5;
    public static MazeBuilder mB;
    public static Message messagePanel;
    public static Message1 messagePanel1;
    public static GUI g;
    public static Thread clockT;
    public static Lesson lessonPanel;
    public static Question1 question1;
    public static Question2 question2;
    public static Question3 question3;
    public static Question4 question4;
    public static Question5 question5;
    public static Question6 question6;
    public static int maximum=200;
    public static int kidClock =40;
    public static int lightOption=3;
    public static int numSeconds=10;
    public static int ratio=1;
    public static int foodEnergy=2000;
    public static int simulationSpeed=19;
    public static boolean schooling=true;
    public static int quizTotal=0;
    
    public GUI(String s) throws Exception //initialisation of different swing components
    {
        super(s);           
        setSize(1200,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g=this;
        
        d = new JDialog(this,"Select a maze",true);
        d.setSize(180,265);
        d.add(new InsertMaze());
        d.setLocationRelativeTo(null);
        d.setResizable(false);
        
        CM = new JDialog(this,"Create maze",true);
        CM.setSize(270,300);
        mB = new MazeBuilder();
        CM.add(mB);
        CM.setLocationRelativeTo(null);
        
        CP = new JDialog(this,"Edit fish",true);
        CP.setSize(180,280);
        CP.setLocationRelativeTo(null);
        CP.setResizable(false);
        CP.setLayout(new GridLayout(2,1));
        
        IS = new JDialog(this,"Select an image",true);
        IS.setSize(180,280);
        IS.add(new InsertScenery());
        IS.setLocationRelativeTo(null);
        IS.setResizable(false);
        
        CB = new JDialog(this,"Select an image",true);
        CB.setSize(180,300);
        CB.add(new ChangeBackground());
        CB.setLocationRelativeTo(null);
        CB.setResizable(false);
        
        options = new JDialog(this,"Options",true);
        options.setSize(400,250);
        optionsP = new Options();
        options.add(optionsP);
        options.setLocationRelativeTo(null);
        options.setResizable(false);
        
        initial = new JFrame("Aquarium data visualiser v1.0");
        initial.setSize(797,624);
        card1 = new CardLayout();        
        p3 = new JPanel(card1);
        card1.show(p3, "1");
        initial.add(new SplashSreen());
        initial.setLocationRelativeTo(null);
        initial.setResizable(false);
        initial.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initial.addWindowListener(this);
        
        splash = new JDialog(this,"",true);
        splash.setSize(797,633);
        splash.add(new SplashSreen());
        splash.setDefaultLookAndFeelDecorated(false);
        splash.setLocationRelativeTo(null);
        splash.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        message = new JDialog(this,"Message",true);
        message.setSize(600,150);
        messagePanel = new Message("");
        messagePanel1 = new Message1("");
        card2 = new CardLayout();
        p4 = new JPanel(card2);
        p4.add(messagePanel,"1");
        p4.add(messagePanel1,"2");
        card2.show(p4, "2");
        message.add(p4);
        message.setLocationRelativeTo(null);
        message.setResizable(false);
        message.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        lesson = new JFrame("Aquarium data visualiser v1.0 - Facts");
        lesson.setSize(600,700);
        lessonPanel = new Lesson();
        lesson.add(lessonPanel);
        lesson.setLocationRelativeTo(null);
        lesson.setResizable(false);
        //lesson.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        lesson.addWindowListener(this);
        
        quiz = new JFrame("Aquarium data visualiser v1.0 - Quiz");
        quiz.setSize(520,500);
        question1 = new Question1();
        question2 = new Question2();
        question3 = new Question3();
        question4 = new Question4();
        question5 = new Question5();
        question6 = new Question6();
        card3 = new CardLayout();        
        p5 = new JPanel(card3);
        p5.add(question1,"1");
        p5.add(question2,"2");
        p5.add(question3,"3");
        p5.add(question4,"4");
        p5.add(question5,"5");        
        p5.add(question6,"6");        
        card3.show(p5, "1");
        quiz.add(p5);
        quiz.setLocationRelativeTo(null);
        quiz.setResizable(false);
        quiz.addWindowListener(this);
        
        
        DB = new JDialog(this,"Connect to database",true);
        DB.setSize(180,280);
        card = new CardLayout();
        cdb = new ConnectDB();
        cdb2 = new ConnectDB2();
        p1 = new JPanel(card);     
        p1.add(cdb,"1");
        p1.add(cdb2,"2");        
        card.show(p1,"1");
        DB.add(p1);
        DB.setLocationRelativeTo(null);
        DB.setResizable(false);
        
        menuBar = new JMenuBar();
        file = new JMenu("File");
        tools = new JMenu("Tools");
        insert = new JMenu("Insert");
        help = new JMenu("Help");
        mainMenu = new JMenuItem("Main menu");
        mainMenu.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        optionsMenu = new JMenuItem("Options");
        optionsMenu.addActionListener(this);
        
        reset = new JMenuItem("Reset");
        reset.addActionListener(this);
        maze = new JMenuItem("Maze");
        maze.addActionListener(this);
        insertScenery = new JMenuItem("Scenery");
        insertScenery.addActionListener(this);
        insertShoal = new JMenuItem("Shoal");
        insertShoal.addActionListener(this);
        connectDB = new JMenuItem("Connect to Database");
        connectDB.addActionListener(this);
        connectDB2 = new JMenuItem("Disconnect from Database");
        connectDB2.addActionListener(this);
        foragingMenu = new JCheckBoxMenuItem("Predator mode");
        foragingMenu.addActionListener(this);
        foragingMenu.setState(!foraging);
        changeBackground = new JMenuItem("Change Background");
        changeBackground.addActionListener(this);
        helpC = new JMenuItem("Contents");
        helpC.addActionListener(this);
        about = new JMenuItem("About");
        about.addActionListener(this);
        
        if(connected)
        {
            connectDB.setEnabled(false);
            insertShoal.setEnabled(false);
        }
        else
        {
            connectDB2.setEnabled(false);
            insertShoal.setEnabled(true);
        }
        
        file.add(mainMenu);
        file.add(exit);
        tools.add(connectDB);
        tools.add(connectDB2);
        tools.add(reset);
        tools.add(foragingMenu);        
        tools.add(changeBackground);
        tools.add(optionsMenu);              
        insert.add(maze);
        insert.add(insertScenery);
        insert.add(insertShoal);
        help.add(helpC);
        help.add(about);
        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(insert);
        menuBar.add(help);
        setJMenuBar(menuBar);         
        initial.show();              
    }
    
    public void startActivity() //this method is called when the simulation is started or restarted and has different brances for the learning
    {                           //simulation and normal simulation
        if(kids)
        {
            numShoals=1;
            numFish=10;
            foraging=false;
            CP.setSize(300,400);
            setJMenuBar(new JMenuBar());
            setResizable(false);
            setSize(1200,655);        
        }
        else
        {
            CP.setSize(180,280);
            setJMenuBar(menuBar);
            setResizable(true);
            setSize(1200,550);
        }
        if(changeP!=null)
        {
            changeP.change();
            t1.makeBackground(CP.getWidth(),CP.getHeight()/2,false);
        }
        if(activity1)
        {
           createShoals(numShoals);
            
            ArrayList<Food> food = new ArrayList<Food>();
            t = new Tank(shoals,food,getWidth(),getHeight(),kids); 
            initialiseShoals();

            createTestShoals();

            tankThread = new Thread(t);
            tankThread.start();        

            if(!foraging&&!kids)
            {
                clock = new Clock(-1,0,t);
                clock.setLightOption(3);
            }
            else if(kids)
            {
                clock = new Clock(kidClock,500,t);
                clock.setLightOption(1);
            }
            else
            {
                clock = new Clock(numSeconds,foodEnergy,t);
                clock.setLightOption(3);
            }

            clockT = new Thread(clock);//
            clockT.start();
            p = new JPanel();
            p.setLayout(new BorderLayout());
            p.add("Center",t);
            
            if(!kids)
            {
                p2 = new JPanel(new GridLayout(3,5));        
                p.add("South",p2);
                addIcons();                   
            }
            else
            {
                kP = new KidsPanel();
                p.add("South",kP);   
                shoalThreads.get(0).suspend();
                shoalThreads.get(1).suspend();
                clock.setMessageSeen(false);
            }
            add(p);
            setVisible(true);
            requestFocus();
            setLocationRelativeTo(null);
            addComponentListener(this);
            addKeyListener(this);            
            show();
        }
        else
        {
            if(!kids)
            {
                if(kP!=null)
                    p.remove(kP);

                if(p2!=null)
                    p.remove(p2);

                p2 = new JPanel(new GridLayout(3,5));        
                p.add("South",p2);
                clockT.resume();
                tankThread.resume();
                createShoals(numShoals);
                initialiseShoals();
                addIcons();   
            }
            else
            {
                if(p2!=null)
                        p.remove(p2);
                        
                if(kP!=null)
                    p.remove(kP);
                kP = new KidsPanel();
                p.add("South",kP);   
                createShoals(numShoals);
                initialiseShoals();
                GUI.g.t.addFishPics();
                GUI.clock.popup=true;
                t.paused=true;
                requestFocus();            
                shoalThreads.get(0).suspend();
                shoalThreads.get(1).suspend();
                clockT.resume();
                tankThread.resume(); 
                clock.setMessageSeen(false);
            }
            if(!foraging&&!kids)
            {
                clock.setCount(-1);
                clock.setEnergy(0);                
                clock.setLightOption(lightOption);
            }
            else if(foraging&&!kids)
            {
                clock.setCount(numSeconds);
                clock.setEnergy(foodEnergy);                
                clock.setLightOption(lightOption);
            }
            else if(kids)
            {
                clock.setCount(kidClock);
                clock.setEnergy(500);  
                clock.setLightOption(1);
            }
            show();
            t.makeBackground(getWidth(), getHeight(),false);            
        }

    }
    
    public void connect()   //called when database is connected
    {
        connected=true;
        insertShoal.setEnabled(false);
        connectDB.setEnabled(false);
        connectDB2.setEnabled(true);
        optionsP.spinner.setEnabled(false);
        optionsP.spinner1.setEnabled(false);
        GUI.clock.setNumSeconds(numSeconds);
        GUI.clock.setRatio(ratio);
        GUI.clockT.resume();
        t.paused=false;
    }
    
    public void disconnect() //called when database is disconnected
    {
        cdb2.disconnect();
        connected=false;
        insertShoal.setEnabled(true);
        connectDB.setEnabled(true);
        connectDB2.setEnabled(false);
        for(int i=0;i<shoals.size();i++)
        {
            for(int j=0;j<shoals.get(i).getFish().size();j++)
            {
                shoals.get(i).setName("");
            }
        }
        optionsP.spinner.setEnabled(true);
        optionsP.spinner1.setEnabled(true);
        t.paused=false;
    }
    
    public void sort()      //sorts the keys at the bottom of the screen in numerical order
    {
        for(int j=0;j<shoals.size();j++)
        {
            for(int i =1;i<shoals.size()-j;i++)
            {
                int alive1=0;
                int alive2=0;
                for(;alive1<shoals.get(i).getFish().size();alive1++)
                {
                    if(!shoals.get(i).getFish().get(alive1).dying())
                        break;
                }
                for(;alive2<shoals.get(i-1).getFish().size();alive2++)
                {
                    if(!shoals.get(i-1).getFish().get(alive2).dying())
                        break;
                }
                if (shoals.get(i).getFish().get(alive1).getEyeStrength()+(shoals.get(i).getFish().get(alive1).getSpeed()*100) > 
                        shoals.get(i-1).getFish().get(alive2).getEyeStrength()+(shoals.get(i-1).getFish().get(alive2).getSpeed()*100)&&
                        !shoals.get(i-1).getFish().get(0).getPredator())
                {
                    Shoal temp = shoals.get(i);
                    Thread temp2 = shoalThreads.get(i);
                    shoals.set(i,shoals.get(i-1));
                    shoals.set(i-1, temp);                    
                    shoalThreads.set(i, shoalThreads.get(i-1));
                    shoalThreads.set(i-1,temp2);                                      
                }
            }           
        }
    }
    
    public void addIcons()      //refreshes the keys at the bottom of the screen
    {
        p2.removeAll();
        buttons = new JLabel[shoals.size()];
        sort();
        
        for(int i=0;i<shoals.size();i++)
        {
            String s;
            if(!foraging)
            {
                if(i==0)
                    s="Predator";
                else if(i==1)
                    s="1st";
                else if(i==2)
                    s="2nd";
                else if(i==3)
                    s="3rd";                
                else 
                    s=i+"th";
            }
            else
            {
                if(i==0)
                    s="1st";
                else if(i==1)
                    s="2nd";
                else if(i==2)
                    s="3rd";
                else 
                    s=(i+1)+"th";
            }           
            
            Fish f = shoals.get(i).getFish().get(0);
            if(f.getShoal().getName().equals("")||f.getPredator())
                buttons[i] = new FishInfo(f,s);                
            else
                buttons[i] = new FishInfo(f,f.getShoal().getName()+"("+f.getShoal().getScore()+")");            
            buttons[i].addMouseListener(this);
            buttons[i].setToolTipText("Click to change the shoal's attributes.");
            p2.add(buttons[i]);            
        }       
        if(numShoals==0);
            p2.add(new JLabel(" "));        
        p2.validate();
        repaint();
    }
    
    public void createShoals(int n)         //called when mode reset or program started, creates the shoals in the simulation 
    {                                       
        int offset=0;
        if(!kids)
        {
            if(!foraging)
                offset++;
        
            if(n>20&&!connected)
                numShoals=20;
            else
                numShoals=n;

            numShoals+=offset;   
        }
        
        if(!foraging)
            n++;
        
        for(int i=0;i<shoalThreads.size();i++)
        {
            shoalThreads.get(i).stop();
        }
        
        shoals.clear();
        for(int i=0;i<n;i++)
        {
            if(shoals.size()>=20+offset&&!connected)
            {
                JOptionPane.showMessageDialog(null,"You cannot have more than 20 shoals");
                break;
            }        
            try
            {
                z = new Random().nextInt(3);
                speed = new Random().nextFloat()+1;
                eyeSight = new Random().nextInt(400)+10;
                int j1 = new Random().nextInt(255);
                int j2 = new Random().nextInt(255);
                int j3 = new Random().nextInt(255);
                int j4 = new Random().nextInt(255);
                int j5 = new Random().nextInt(255);
                int j6 = new Random().nextInt(255);

                if(!foraging&&i==0&&numFish!=1)
                    fishes = new Fish[numFish/2];
                else
                    fishes = new Fish[numFish];
                for(int j=0;j<fishes.length;j++)
                {

                    if(kids)
                        x = new Random().nextInt(this.getWidth()-150)+100;
                    else
                        x = new Random().nextInt(this.getWidth()-50);
                    y = new Random().nextInt(this.getHeight()-200);
                    x1 = new Random().nextInt(2)+1;
                    y1 = new Random().nextInt(2)+1;
                    r = new Random().nextInt(5);
                    r1 = new Random().nextInt(5);

                    if(r<1)
                        x1=-x1;
                    if(r1<1)
                        y1=-y1;

                    if(!foraging&&i==0)
                        fishes[j] = new Fish(x,y,x1,y1,50,40,200,2.0f,new Color(j1,j2,j3),new Color(j4,j5,j6),z,true);
                    else if(kids)
                        fishes[j] = new Fish(x,y,x1,y1,16,12,200,2.5f,new Color(j1,j2,j3),new Color(j4,j5,j6),z,false);
                    else
                        fishes[j] = new Fish(x,y,x1,y1,16,12,speed*100,speed,new Color(j1,j2,j3),new Color(j4,j5,j6),z,false);

                }
                shoals.add(new Shoal(fishes));
            }
            catch(Exception e){}       
        }        
    }
    
    public void createTestShoals()          //creates the shoals used as a preview when changing a shoals parameters
    {
        ArrayList<Food> food1 = new ArrayList<Food>();
        testShoal = new ArrayList<Shoal>();
        t1 = new Tank(testShoal,food1,CP.getWidth(),CP.getHeight()/2,false);   
        c2 = new Clock(10,0,t1);   
        thread1 = new Thread(c2);
        thread1.start();
        CP.add(t1);
        changeP = new ChangeParameters();
        CP.add(changeP);        
        new Thread(t1).start();
    }
    
    public void initialiseShoals()      //associates each fish with a shoal and starts the shoal threads
    {
        shoalThreads.clear();
        for(int i=0;i<shoals.size();i++)
        {
            for(int j=0;j<shoals.get(i).getFish().size();j++)
            {
                shoals.get(i).getFish().get(j).setShoal(shoals.get(i));
                shoals.get(i).getFish().get(j).setTank(t);
            }
            shoalThreads.add(new Thread(shoals.get(i)));
            shoalThreads.get(i).start();
        }
        t.setShoals(shoals);        
    }
    
    public void addShoals(int n)        //adds shoals to the simulation
    {
        int offset=0;
        for(int i=0;i<n;i++)
        {
            if(shoals.size()>=20&&!connected)
            {
                JOptionPane.showMessageDialog(null,"You cannot have more than 20 shoals");
                break;
            }
            z = new Random().nextInt(3);
            speed = new Random().nextFloat()+1;
            eyeSight = new Random().nextInt(400)+10;
            int j1 = new Random().nextInt(255);
            int j2 = new Random().nextInt(255);
            int j3 = new Random().nextInt(255);
            int j4 = new Random().nextInt(255);
            int j5 = new Random().nextInt(255);
            int j6 = new Random().nextInt(255);
            
            fishes = new Fish[numFish];
            for(int j=0;j<fishes.length;j++)
            {
                x = new Random().nextInt(this.getWidth()-50);
                y = new Random().nextInt(this.getHeight()-200);
                x1 = new Random().nextInt(2)+1;
                y1 = new Random().nextInt(2)+1;
                r = new Random().nextInt(5);
                r1 = new Random().nextInt(5);
                
                if(r<1)
                    x1=-x1;
                if(r1<1)
                    y1=-y1;
                
                fishes[j] = new Fish(x,y,x1,y1,16,12,speed*100,speed,new Color(j1,j2,j3),new Color(j4,j5,j6),z,false);
                
            }
            shoals.add(new Shoal(fishes));   
        }
        for(int i=numShoals+offset;i<shoals.size();i++)
        {
            for(int j=0;j<shoals.get(i).getFish().size();j++)
            {
                shoals.get(i).getFish().get(j).setShoal(shoals.get(i));
                shoals.get(i).getFish().get(j).setTank(t);
            }
            shoalThreads.add(new Thread(shoals.get(i)));
            shoalThreads.get(i).start();                
            if(t.paused)
                shoalThreads.get(i).suspend();                
        }
        
        if(numShoals<20||connected)
        {
            addIcons();
            p.validate();
            numShoals+=shoals.size()-(numShoals+offset);
        }
        
    }
    
    public void changeParameters(int index)     //called when the user chooses to change a shoals parameters
    {                                           //and creates and initialises the shoals used in the preview
        thread.stop();
        thread2.stop();
        testShoal.clear();
        int numFish=0;
        Fish f1=null;
        Fish newFish1=null;
        Fish f=null;
        int i;
        for(i=0;i<shoals.get(index).getFish().size();i++)
        {
            if(!shoals.get(index).getFish().get(i).dying())
            {
                f = shoals.get(index).getFish().get(i);
                break;
            } 
        }       
        Fish newFish = new Fish(0.0f,0.0f,f.getVelocity().getX(),f.getVelocity().getY(),f.getLength(),f.getWidth(),f.getEyeStrength(),f.getSpeed(),f.getColor1(),f.getColor2(),f.getColorType(),f.getPredator());
        newFish.setEnergy(-1);
        newFish.setShoal2(f.getShoal());
        Fish[] fish = new Fish[1];
        fish[0] = newFish;        
        testShoal.add(new Shoal(fish));
        newFish.setShoal(testShoal.get(0));
        thread = new Thread(testShoal.get(0));
        t1.getFood().clear();
        if(!foraging&&index!=0)
        {
            f1 = shoals.get(0).getFish().get(0);
            newFish1 = new Fish(0.0f,0.0f,Math.abs(f1.getVelocity().getX()),Math.abs(f1.getVelocity().getY()),f1.getLength(),f1.getWidth(),f1.getEyeStrength(),f1.getSpeed(),f1.getColor1(),f1.getColor2(),f1.getColorType(),f1.getPredator());            
            Fish[] fish1 = new Fish[1];
            fish1[0] = newFish1;        
            testShoal.add(new Shoal(fish1));
            newFish1.setShoal(testShoal.get(1));        
            thread2 = new Thread(testShoal.get(1));
            thread2.start();
            newFish1.setTank(t1);
        }
        else
        {
            Fish [] dummy = new Fish[0];
            testShoal.add(new Shoal(dummy));
        }
        thread.start();
        newFish.setTank(t1);
        changeP.setIndex(index,i);        
        CP.show();
    }
    
    public void mouseExited(MouseEvent e)
    {
        JLabel b = (JLabel)e.getSource();
        int index=0;
        for(int i=0;i<buttons.length;i++)
        {
            if(b.equals(buttons[i]))
            {
                index=i;
                break;
            }
        }
        ((FishInfo)buttons[index]).over(false);        
    }
    
    public void mouseEntered(MouseEvent e)
    {
        JLabel b = (JLabel)e.getSource();
        int index=0;
        for(int i=0;i<buttons.length;i++)
        {
            if(b.equals(buttons[i]))
            {
                index=i;
                break;
            }
        }
        ((FishInfo)buttons[index]).over(true);
    }
    
    public void mousePressed(MouseEvent e)
    {
        JLabel b = (JLabel)e.getSource();
        int index=0;
        for(int i=0;i<buttons.length;i++)
        {
            if(b.equals(buttons[i]))
            {
                index=i;
                break;
            }
        }

        changeParameters(index);        
    }
    
    public void componentResized(ComponentEvent e)
    {
        if(getWidth()<=55||getHeight()<=205)
        {
            this.setSize(55, 205);          
        }
        t.makeBackground(getWidth(), getHeight(),t.getLoaded());
    }  
    
    public void startAgain()            //called when the user goes back to the main menu and resets appropriate fields
    {
        try
        {
            activity1=false;
            disconnect();
            this.clockT.suspend();
            this.tankThread.suspend();
            t.getMazes().clear();
            t.getFood().clear();
            t.getPlants().clear();
            t.getScenery().clear();
            t.getSceneryImages().clear();
            t.setAlpha(0);
            t.showHints();
            t.setPath("resources/untitled3.jpg");
            clock.setLightOption(lightOption);
            clock.setMessageSeen(true);
            simulationSpeed=19;
            clock.setNumSeconds(numSeconds);
            clock.setRatio(ratio);                    
            schooling=true;
            intelligence=true;
            for(int i=0;i<shoalThreads.size();i++)
            {
                shoalThreads.get(i).stop();
            }            
            setVisible(false);           
        }
        catch(Exception e1){};
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==maze)
        {
            d.show();
        }
        
        else if(e.getSource()==insertScenery)
        {
            IS.show();
        }
        
        else if(e.getSource()==changeBackground)
        {
            CB.show();
        }
        
        else if(e.getSource()==insertShoal)
        {
            SpinnerNumberModel sp = new SpinnerNumberModel(1,1,10,1);
            String [] options = {"Ok","Cancel"};
            if(JOptionPane.showOptionDialog(this, new JSpinner(sp),"Select number of shoals:",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,null)==0)
            {
                addShoals(Integer.parseInt(sp.getValue()+""));                    
            }            
        }
        
        else if(e.getSource()==exit)
        {
            System.exit(0);           
        }
        
        else if(e.getSource()==mainMenu)
        {
            if(connected)
            {
                if(JOptionPane.showOptionDialog(this, "Are you sure you want to disconnect and return to the main menu?","Main Menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null)==0)
                    connected=false;      
            }
            if(!connected)
            {
                startAgain();
                initial.show();
            }  
            
        }
        
        else if(e.getSource()==connectDB)
        {
            DB.show();
        }
        
        else if(e.getSource()==connectDB2)
        {
            disconnect();
            
            GUI.clockT.resume();
            t.paused=false;
            if(!foraging)
                createShoals(9);
            else
                createShoals(9);
            initialiseShoals();
            addIcons();             
        }
        
        else if(e.getSource()==reset)
        {
            GUI.clockT.resume();
            t.paused=false;
            if(!foraging)
                createShoals(shoals.size()-1);
            else
                createShoals(shoals.size());
            initialiseShoals();
            addIcons();            
        }
        
        else if(e.getSource()==foragingMenu)
        {
            GUI.clockT.resume();
            t.paused=false;
            if(foragingMenu.getState()==false) 
            {
                foraging=true;
                clock.setCount(numSeconds);
                clock.setEnergy(foodEnergy);
                createShoals(shoals.size()-1);
            }
            else
            {
                foraging=false;
                clock.setCount(-1);
                clock.setEnergy(0);
                createShoals(shoals.size());
                t.getFood().clear();
            }
            initialiseShoals();
            addIcons();
            int i=0;
            if(!foraging)
                i=1;
            for(;i<shoals.size();i++)
            {
                shoals.get(i).setName("");
            }           
        }
        
        else if(e.getSource()==optionsMenu)
        {
            optionsP.setOptions();
            options.show();
        }
        
        else if(e.getSource()==helpC)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(new URI("http://www.dcs.qmul.ac.uk/~mya3/project/user_manual/Manual.html"));
            } 
            catch(Exception e1){}
        }
        
        else if(e.getSource()==about)
        {
            String [] options = {"Close",};          
            Image i=new ImageIcon(this.getClass().getResource("resources/logo 2.jpg")).getImage();
            Image i2 = i.getScaledInstance(300, 250, Image.SCALE_SMOOTH);
            JOptionPane.showOptionDialog(this, new ImageIcon(i2),"About",JOptionPane.YES_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==127)
        {
            for(int i=0;i<t.getMazes().size();i++)
            {
                if(t.getMazes().get(i).getStatus()==0)
                {
                    t.getMazes().remove(i);
                }            
            }
            
            for(int i=0;i<t.getScenery().size();i++)
            {
                if(t.getScenery().get(i).getStatus()==0)
                {
                    t.getScenery().remove(i);
                    t.getSceneryImages().remove(i);
                }
            }
            
            for(int i=0;i<t.getPlants().size();i++)
            {
                if(t.getPlants().get(i).getStatus()==0)
                {
                    t.getPlants().remove(i);                    
                }
            }
            
        }        
    }
    
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void componentShown(ComponentEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}    
    public void componentHidden(ComponentEvent e){}
    public void componentMoved(ComponentEvent e){}
    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }    
    public void windowDeactivated(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){}
            
}
