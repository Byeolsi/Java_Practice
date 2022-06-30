import java.awt.Color;
import java.awt.*;

import javax.swing.*;

public class Block extends JComponent {
	protected char direction = 'X';
	protected int x, y, width, height;
	protected Color myColor;
	protected Rectangle box;
	public Block(int inputX, int inputY, int inputW, int inputH, Color inputColor)
	{
		x=inputX;
		y=inputY;
		width=inputW;
		height=inputH;
		myColor=inputColor;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public char getDirection()
	{
		return direction;
	}
	public void renewDirection(Ball myBall)
	{
		if (x - width / 2 < myBall.getX()+myBall.getRad() && myBall.getX()-myBall.getRad() < x + width / 2 && y - height / 2 < myBall.getY()+myBall.getRad() && myBall.getY()-myBall.getRad() < y + height / 2)
		{
		}
		else if (y - height / 2 <= myBall.getY()+myBall.getRad() && myBall.getY()-myBall.getRad() <= y + height / 2 && x - width / 2 >= myBall.getX()+myBall.getRad())
		{
			direction = 'L';
		}
		else if (y - height / 2 <= myBall.getY()+myBall.getRad() && myBall.getY()-myBall.getRad() <= y + height / 2 && x + width / 2 <= myBall.getX()-myBall.getRad())
		{
			direction = 'R';
		}
		else if (x - width / 2 <= myBall.getX()+myBall.getRad() && myBall.getX()-myBall.getRad() <= x + width / 2 && y - height / 2 >= myBall.getY()+myBall.getRad())
		{
			direction = 'U';
		}
		else if (x - width / 2 <= myBall.getX()+myBall.getRad() && myBall.getX()-myBall.getRad() <= x + width / 2 && y + height / 2 <= myBall.getY()-myBall.getRad())
		{
			direction = 'D';
		}
		else {
			direction = 'X';
		}
	}
	public void draw(Graphics2D g2)
	{
		box= new Rectangle(x-width/2, y-height/2, width, height);
		g2.setColor(Color.black);
		g2.draw(box);
		g2.setColor(myColor);
		g2.fill(box);
	}
}
