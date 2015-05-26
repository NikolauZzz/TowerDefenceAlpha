import java.awt.*;
public class Room {      //dobavlyaem urovny
	public int wordWidth = 12;
	public int wordHeight = 8;
	public int blockSize = 52;
	public Block [] [] block;
	
	public Room(){
		define ();
	}
	
	public void define(){
		block = new Block [wordHeight] [wordWidth];
		
		for(int y=0; y<block.length; y++){
			for (int x=0; x<block[0].length; x++){
				block[y][x]=new Block((Screen.myWidth/2) - ((wordWidth*blockSize)/2) + (x*blockSize), y*blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir );
			}
		}
	}
	
	
	
	public void physic(){
		for(int y=0;y<block.length;y++) {
			for(int x=0;x<block[0].length;x++) {
				block[y][x].physic();
			}
		}
		
	}
	
	
	public void draw(Graphics g){
		for(int y=0; y<block.length; y++){
			for (int x=0; x<block[0].length; x++){
				block[y][x].draw(g);
			}
		}
		for(int y=0; y<block.length; y++){
			for (int x=0; x<block[0].length; x++){
				block[y][x].fight(g);
			}
		}	
	}
	
}
