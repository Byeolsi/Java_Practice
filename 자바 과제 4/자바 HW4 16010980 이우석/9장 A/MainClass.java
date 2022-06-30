import java.awt.Component;

import javax.swing.JFrame;

public class MainClass {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		BarControl myBarControl = new BarControl();
		frame.addKeyListener(myBarControl);
		frame.addMouseListener(myBarControl);
		frame.addMouseMotionListener(myBarControl);
		frame.setSize(600, 600);
	    frame.setResizable(false);;
		frame.setTitle("Block Break Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BlockBreakComponent(myBarControl));
		frame.setVisible(true);
	}
}
