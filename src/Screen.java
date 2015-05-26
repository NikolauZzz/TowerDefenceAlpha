import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Screen  extends JPanel implements Runnable{
	public Thread thread = new Thread(this);
	
	public static Image[] tileset_ground = new Image[100];
	public static Image[] tileset_air = new Image [100];
	public static Image[] tileset_mob = new Image [100];
	public static Image[] tileset_res = new Image [100];
	
	public static int myWidth, myHeight;
	public static int coinage = 10, health = 100;
	public static int killed = 0, killsToWin = 0, level = 1, maxLevel = 3;
	public static int winTime = 4000, winFrame = 0;
	
	public static boolean isFirst = true;
	public static boolean isDead = false;
	public static boolean isDebug = false;
	public static boolean isWin = false;
	
	
	public static Point mse = new Point(0,0);
	
	public static Room room;
	public static Save save;
	public static Store store;
	
	public static Mob[] mobs = new Mob[100];

	
	
	public Screen(Frame frame) {
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		
		
		thread.start();
		
	}
	
	public static void hasWon() {
		if(killed == killsToWin) {
			isWin = true;
			killed = 0;
			coinage = 0;
		
		}
	}
	
	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();
		
		coinage = 10;
		health = 100;
		
		
		for(int i=0; i<tileset_ground.length; i++){
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0,26*i,26,26)));
		}
		for(int i=0; i<tileset_air.length; i++){
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0,26*i,26,26)));
		}
		tileset_res[0] = new ImageIcon("res/hearth.png").getImage();
		tileset_res[1] = new ImageIcon("res/coins.png").getImage();
         
		tileset_mob[0] = new ImageIcon("res/Mob.png").getImage();
		
		
		
		save.loadSave(new File("save/mission" + level + ".ivt"));
		
		for(int i=0;i<mobs.length;i++){
			mobs[i] = new Mob();}
			
		
	}
	
	public void paintComponent (Graphics g){ //risuem tut
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();
			
			isFirst=false;
			
		}
		g.setColor(new Color(0, 128, 64));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(50, 50, 50));
		
		room.draw(g); //risuem komnatu
		for(int i=0; i<mobs.length;i++){
			if(mobs[i].inGame){
				mobs[i].draw(g); //risuem mobov
			}
				
		}
		
		
		store.draw(g); //risuem magaz
		
		if(health <1) {
			g.setColor(new Color (240,20,20));
			g.fillRect(0, 0, myWidth, myHeight);
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			g.drawString("Game over",10,30); // kogda zhizni koncshatsya, viletit game over
		}
		
		if(isWin) {
			g.setColor(new Color (255, 255, 255));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			if(level > maxLevel) {
				g.drawString("You comlete the game!!!",10,30);
			}else {
				g.drawString("Wait for next level, you win!!!",10,30);
			}
		}
	}
	public int spawnTime = 2400, spawnFrame = 0; 
	public void mobSpawner() {
		if(spawnFrame >= spawnTime) {
			for( int i=0; i<mobs.length;i++){
				if(!mobs[i].inGame){
					mobs[i].spawnMob(Value.mobGreeny);
					
					break;
					
				}
			}
			spawnFrame = 0;
			}else {
				spawnFrame += 1;
			}
		
	}
	public void run(){ 
		while(true) {
			if(!isFirst && health > 0 && !isWin) {
				room.physic();
				mobSpawner();                         // zapuskaem mobov
				for(int i=0; i<mobs.length;i++){ 
					if(mobs[i].inGame) {
						mobs[i].physic();
					}
				}
			}else {
				if(isWin) {
					if(winFrame >= winTime) {
						if(level > maxLevel){
							System.exit(0); // exit
						} else {
							level += 1;
							define();
							isWin = false;
						}
						
						winFrame = 0;
					}else {
						winFrame =+ 1;
					}
				}
			}
			
		
			
			repaint();
			
			try {
			Thread.sleep(1);
			} catch(Exception e) { }
		}
	}
}
