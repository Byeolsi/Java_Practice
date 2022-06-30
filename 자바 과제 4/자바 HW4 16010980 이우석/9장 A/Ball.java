import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class Ball extends JComponent {
	private double x, y, speed;
	private int rad;
	private double angle;
	private double displacementX, displacementY;
	private Color myColor;
	private Ellipse2D.Double circle;
	public Ball(double inputX, double inputY, int inputRad, double inputSpeed, Color inputColor)
	{
		x = inputX;
		y = inputY;
		rad = inputRad;
		speed = inputSpeed;
		angle = -Math.PI/2;
		displacementX = Math.cos(angle)*speed;
		displacementY = Math.sin(angle)*speed;
		myColor=inputColor;
	}
	public void xCollision()
	{
		angle = Math.PI-angle;
		if(angle<-Math.PI*2) angle +=Math.PI*2;
		if(angle>0) angle -=Math.PI*2;
		displacementX = Math.cos(angle)*speed;
		displacementY = Math.sin(angle)*speed;
	}
	public void yCollision()
	{
		angle = 2*Math.PI-angle;
		if(angle<-Math.PI*2) angle +=Math.PI*2;
		if(angle>0) angle -=Math.PI*2;
		displacementX = Math.cos(angle)*speed;
		displacementY = Math.sin(angle)*speed;
	}
	public void modifyAngle(double A)
	{
		while(angle<-Math.PI*2) angle+=Math.PI*2;
		while(angle>0) angle-=Math.PI*2;
		angle += A;
		if(angle>-Math.PI/8) angle = -Math.PI/8;
		else if(angle<-Math.PI*7/8) angle = -Math.PI*7/8;
		displacementX = Math.cos(angle)*speed;
		displacementY = Math.sin(angle)*speed;
	}
	public void moveBall()
	{
		x += displacementX;
		y += displacementY;
	}
	public int getX()
	{
		return (int)x;
	}
	public int getY()
	{
		return (int)y;
	}
	public int getRad()
	{
		return rad;
	}
	public void setX(int inputX)
	{
		x=inputX;
	}
	public void setY(int inputY)
	{
		y=inputY;
	}
	public int getBeforeX()
	{
		return (int)(x-displacementX);
	}
	public int getBeforeY()
	{
		return (int)(y-displacementY);
	}
	public void draw(Graphics2D g2)
	{
		circle = new Ellipse2D.Double(x-rad, y-rad, 2*rad, 2*rad);
		g2.setColor(Color.black);
		g2.draw(circle);
		g2.setColor(myColor);
		g2.fill(circle);
	}
}
