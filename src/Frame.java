import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame {
	public static String title = "Bashenki vs Monsters - \"Wait us on GreenLight soon!\"";
	public static Dimension size  = new Dimension (700, 550);   //Razmer ramki

	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	
	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Screen screen = new Screen(this);
		add(screen);
		setVisible(true); //vidimost' ramki
		
		
	}
	public static void main(String args[]) {
		Frame frame = new Frame();
	}
	
}
