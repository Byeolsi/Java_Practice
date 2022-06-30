import java.awt.Color;


public class Bar extends Block {

	public Bar(int inputX, int inputY, int inputW, int inputH, Color inputColor) {
		super(inputX, inputY, inputW, inputH, inputColor);
	}
	public void moveLeft()
	{
		x-=2;
		if(x<width/2)
			x=width/2;
	}

	public void moveRight()
	{
		x+=2;
		if(x>600-width/2)
			x=600-width/2;
	}

	public void movedByMouse(int inputX) {
		x = inputX;
	}
}
