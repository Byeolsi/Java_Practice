import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BarControl implements KeyListener, MouseListener, MouseMotionListener {
	private int x, y;
	private int pushedKey;
	private int direction;
	private boolean pushedSpace;
	private boolean mouseControl = false;
	private boolean gameStart = false;

	public void keyPressed(KeyEvent e) {
			pushedKey = e.getKeyCode();
			if(gameStart == false && pushedKey == KeyEvent.VK_SPACE)
			{
				gameStart = true;
			}
			if(pushedKey == KeyEvent.VK_LEFT || pushedKey == KeyEvent.VK_A)
			{
				direction=1;
			}
			else if(pushedKey == KeyEvent.VK_RIGHT || pushedKey == KeyEvent.VK_D)
			{
				direction=2;
			}
			else
				direction=0;
	}

	public void keyReleased(KeyEvent e) {
		direction=0;
	}
	
	public int getX() {
		return x;
	}

	public int getDirection()	{
		return direction;
	}
	
	public boolean getMouseControl() {
		return mouseControl;
	}
	//사용하지 않는 기본 메소드
	public void keyTyped(KeyEvent e) {

	}
	
	public boolean get_gameStart() {
		return gameStart;
	}
	public void set_gameStart(boolean checkStart) {
		gameStart = checkStart;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// 마우스가 더블 클릭 되면, 마우스로 컨트롤 할 수 있게 된다.
		if (e.getClickCount() == 2) {
			mouseControl = true;
		}
		if (mouseControl) {
			gameStart = true;
		}
		//
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// 마우스가 움직일 때, 마우스의 좌표를 받는다.
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
	}
}
