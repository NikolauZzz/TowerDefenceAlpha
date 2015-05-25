import java.awt.*;


public class Store {
	public static int shopWidth = 8;
	public static int bottonSize = 52;
	public static int cellSpace = 2;
	public static int awayFromRoom = 29;
	public static int iconSize = 20;
	public static int iconSpace = 3;
	public static int iconTextY = 15;
	public static int itemIn = 4;
	public static int heldID = -1;
	public static int[] buttonID = {Value.airTowerLaser,Value.airAir,Value.airAir,Value.airAir,Value.airAir,Value.airAir,Value.airAir,Value.airTrashCan}; // niznya panel magaza
	public static int[] buttonPrice = {10,0,0,0,0,0,0,0};
	 
	
	
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	
	public boolean holdsItem = false;
	
	public Store() {
		define();
	}
	public void Click(int mouseButton){
		if(mouseButton == 1){
			for ( int i=0;i<button.length;i++){
				if(button[i].contains(Screen.mse)){
					if(buttonID[i] != Value.airAir){
					if(buttonID[i] == Value.airTrashCan){	// ydalenie predmeta 
					holdsItem = false;
						heldID = Value.airAir;
					} else { heldID = buttonID[i];
					holdsItem = true; 
					}
					}
					
			} 
					
			}
		}
		
	}
	
	public void define() {
		for(int i=0; i<button.length; i++) {
			button[i] = new Rectangle((Screen.myWidth/2) - ((shopWidth*(bottonSize+cellSpace))/2) + ((bottonSize+cellSpace)*i), (Screen.room.block[Screen.room.wordHeight-1][0].y) + Screen.room.blockSize + awayFromRoom, bottonSize, bottonSize );
			
		}
		buttonHealth = new Rectangle(Screen.room.block[0][0].x-1,button[0].y, iconSize, iconSize);
		buttonCoins = new Rectangle(Screen.room.block[0][0].x-1,button[0].y + button[0].height-iconSize, iconSize, iconSize);
	}
	
	public void draw(Graphics g) {
		
		for(int i=0; i<button.length; i++) {
			if (button[i].contains(Screen.mse)){
				g.setColor(new Color(255,255,255,50));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			if(buttonID[i] != Value.airAir)g.drawImage(Screen.tileset_air[buttonID[i]],button[i].x + itemIn, button[i].y + itemIn, button[i].width -(itemIn*2), button[i].height - (itemIn*2), null);
		if(buttonPrice[i] > 0){
			g.setColor(new Color (137,214,126));
			g.setFont(new Font("Coucier New", Font.BOLD,14));
			g.drawString("$"+buttonPrice[i] + "", button[i].x + itemIn, button[i].y + itemIn + 10);
		}
		}
		g.drawImage(Screen.tileset_res[0],buttonHealth.x, buttonHealth.y,buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.tileset_res[1],buttonCoins.x, buttonCoins.y,buttonCoins.width, buttonCoins.height, null);
		g.setFont(new Font("Courier New", Font.BOLD,14));
		g.drawString("" + Screen.health,buttonHealth.x + buttonHealth.width + iconSpace,buttonHealth.y + iconTextY );   // otobrazhaem zhizn'
		g.drawString("" + Screen.coinage,buttonCoins.x + buttonCoins.width + iconSpace,buttonCoins.y + iconTextY );     // otobrazzhaem den'gi
	
		if(holdsItem){
			g.drawImage(Screen.tileset_air[heldID],Screen.mse.x - ((button[0].width -(itemIn*2))/2) + itemIn,Screen.mse.y - ((button[0].width -(itemIn*2))/2) + itemIn, button[0].width -(itemIn*2), button[0].height - (itemIn*2),  null);
			
		}
	}
}
