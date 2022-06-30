import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class BlockBreakComponent extends JComponent {
	private ArrayList<Block> blockList = new ArrayList<Block>();
	private Ball myBall;
	private Bar myBar;
	private BarControl myKeyControl;
	private int i,j,life,score;
	private static int retryResult;
	private boolean pushedKey;
	private boolean gameStart;
	private boolean gameOver;
	private double tempNum;
	private Font f;
	public BlockBreakComponent(BarControl bar)
	{
		myKeyControl=bar;
		myBar = new Bar(300, 500, 70, 14, Color.gray);
		myBall = new Ball(300, 482, 10, 2, Color.blue);
		gameStart = false;
		gameOver = false;
		life = 3;
		score = 0;
		for(i=0; i<10; i++)
		{
			for(j=0; j<10; j++)
			{
				if(j%7==0)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.cyan));
				else if(j%7==1)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.GREEN));
				else if(j%7==2)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.yellow));
				else if(j%7==3)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.RED));
				else if(j%7==4)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.black));
				else if(j%7==5)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.orange));
				else
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.MAGENTA));
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		if (gameStart == false && gameOver == false) {
			gameStart = myKeyControl.get_gameStart();
		}
		Graphics2D g2 = (Graphics2D) g;
		
		if(myKeyControl.getMouseControl() && gameOver == false) {
			myBar.movedByMouse(myKeyControl.getX());
			if(gameStart==false)
				myBall.setX(myBar.getX());
		}
		else {
			if(myKeyControl.getDirection()==1 && gameOver == false)
			{
				myBar.moveLeft();
				if(gameStart==false)
					myBall.setX(myBar.getX());
			}
			if(myKeyControl.getDirection()==2 && gameOver == false)
			{
				myBar.moveRight();
				if(gameStart==false)
					myBall.setX(myBar.getX());
			}
		}
		if(gameStart==true && gameOver == false)
		{
			myBall.moveBall();
			if(myBall.getX()>590)
			{
				myBall.xCollision();
				if(myBall.getX()>590) myBall.setX(590);
			}
			else if(myBall.getX()<10)
			{
				myBall.xCollision();
				if(myBall.getX()<10) myBall.setX(10);
			}
			else if(myBall.getY()>570)
			{
				gameStart = false;
				life--;
				myKeyControl.set_gameStart(gameStart);
				myBall.setX(myBar.getX());
				myBall.setY(482);
			}
			else if(myBall.getY()<10)
			{
				myBall.yCollision();
				if(myBall.getY()<10) myBall.setY(10);
			}
			myBar.renewDirection(myBall);
			if(myBar.getX() - myBar.getWidth() / 2 < myBall.getX()+myBall.getRad() && myBall.getX()-myBall.getRad() < myBar.getX() + myBar.getWidth() / 2 && myBar.getY() - myBar.getHeight() / 2 < myBall.getY()+myBall.getRad() && myBall.getY()-myBall.getRad() < myBar.getY() + myBar.getHeight() / 2)
			{
				if (myBar.getDirection() == 'L')
				{
					myBall.xCollision();
					myBall.setX(myBar.getX()-myBar.getWidth()/2-myBall.getRad());
				}
				else if (myBar.getDirection() == 'R')
				{
					myBall.xCollision();
					myBall.setX(myBar.getX()+myBar.getWidth()/2+myBall.getRad());
				}
				else if (myBar.getDirection() == 'U')
				{
					myBall.yCollision();
					myBall.setY(myBar.getY()-myBar.getHeight()/2-myBall.getRad());
				}
				else if (myBar.getDirection() == 'D')
				{
					myBall.yCollision();
					myBall.setY(myBar.getY()+myBar.getHeight()/2+myBall.getRad());
				}
				tempNum=(Math.random()*34-17)/10;
				myBall.modifyAngle(tempNum);
				new Sound ("pang.wav", 60);
			}
			for(i=0; i<blockList.size(); i++)
			{
				blockList.get(i).renewDirection(myBall);
				if (blockList.get(i).getX() - blockList.get(i).getWidth() / 2 < myBall.getX()+myBall.getRad() && myBall.getX()-myBall.getRad() < blockList.get(i).getX() + blockList.get(i).getWidth() / 2 && blockList.get(i).getY() - blockList.get(i).getHeight() / 2 < myBall.getY()+myBall.getRad() && myBall.getY()-myBall.getRad() < blockList.get(i).getY() + blockList.get(i).getHeight() / 2)
				{
					if (blockList.get(i).getDirection() == 'L')
					{
						myBall.xCollision();
						myBall.setX(blockList.get(i).getX()-blockList.get(i).getWidth()/2-myBall.getRad());
					}
					else if (blockList.get(i).getDirection() == 'R')
					{
						myBall.xCollision();
						myBall.setX(blockList.get(i).getX()+blockList.get(i).getWidth()/2+myBall.getRad());
					}
					else if (blockList.get(i).getDirection() == 'U')
					{
						myBall.yCollision();
						myBall.setY(blockList.get(i).getY()-blockList.get(i).getHeight()/2-myBall.getRad());
					}
					else if (blockList.get(i).getDirection() == 'D')
					{
						myBall.yCollision();
						myBall.setY(blockList.get(i).getY()+blockList.get(i).getHeight()/2+myBall.getRad());
					}
					blockList.remove(i);
					score++;
					new Sound ("pang.wav", 60);
				}
			}
		}
		for(i=0; i<blockList.size(); i++)
		{
			blockList.get(i).draw(g2);
		}
		myBar.draw(g2);
		myBall.draw(g2);
		g2.setColor(Color.black);
		g2.setFont(f);
		// life 와 score 를 표시한다.
		g2.drawString("Life : " + life, 10, 550);
		g2.drawString("Score : " + score, 500, 550);
		//
		// life 가 0이 되면 알림창을 띄우고, 재시작할 것인지 종료할 것인지 아니면 그대로 놔둘 것인지 정한다.
		if (life == 0) {
			String[] buttons = {"Yes", "No", "Cancel"}; 
			retryResult = JOptionPane.showOptionDialog(null, "Score : " + score + "\nRetry?", "GAMEOVER", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, "Yes");
			life = 3;
			
			if (retryResult == JOptionPane.CLOSED_OPTION) gameOver = true;
			else if (retryResult == JOptionPane.YES_OPTION) newGame();
			else if (retryResult == JOptionPane.NO_OPTION) System.exit(0);
			else if (retryResult == JOptionPane.CANCEL_OPTION) gameOver = true;
			else ;
		}
		//
		try { Thread.sleep(4); } catch (InterruptedException e) {}
		repaint();
	}
	public void newGame() {
		myBar = new Bar(300, 500, 70, 14, Color.gray);
		myBall = new Ball(300, 482, 10, 2, Color.blue);
		gameStart=false;
		life = 3;
		score = 0;
		
		blockList.clear();
		
		for(i=0; i<10; i++)
		{
			for(j=0; j<10; j++)
			{
				if(j%7==0)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.cyan));
				else if(j%7==1)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.GREEN));
				else if(j%7==2)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.yellow));
				else if(j%7==3)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.RED));
				else if(j%7==4)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.black));
				else if(j%7==5)
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.orange));
				else
					blockList.add(new Block(j*(58+2)+30,i*(22+2)+12+20,58,22,Color.MAGENTA));
			}
		}
	}
	public int getGap(int a, int b)
	{
		if(a>b)
			return a-b;
		else
			return b-a;
	}
}
