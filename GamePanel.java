package Start;
import java.awt.*;
import java.util.*;
import java.awt.RenderingHints.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Image.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.BufferedImage;
public class GamePanel extends JPanel implements Runnable{
	
private	boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private  MyMouseMotionListener theMouseListener;
	private int mousex;
	private Ball theball;
	private Paddle thePaddle;
	private Map theMap;
	private HUD theHud;
	
	
public GamePanel() {
	init();
}
public void init() {
	mousex=0;
	theball=new Ball();
	thePaddle=new Paddle();
	theMap=new Map(6,10);
	theHud =new HUD();
	theMouseListener =new  MyMouseMotionListener();
	
	addMouseMotionListener(theMouseListener);
	running=true;
	image=new BufferedImage(BBMain.WIDTH,BBMain.HEIGHT,BufferedImage.TYPE_INT_RGB);
	g=(Graphics2D) image.getGraphics();
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
}


//override
public void run() {
	while(running) {
		update();
		draw();
		repaint();
		try {
			Thread.sleep(10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
public void checkCollision() {
	Rectangle ballsRect =theball.getRect();
	Rectangle paddleRect =thePaddle.getRect();
	if(ballsRect.intersects(paddleRect)) {
		theball.setDY(-theball.getDY());
	if(theball.getX()<mousex+thePaddle.getWidth()/4) {
		theball.setDX(theball.getDX()-.5);
	}
	if(theball.getX()<mousex+thePaddle.getWidth() && theball.getX()>mousex+thePaddle.getWidth()/4) {
		theball.setDX(theball.getDX()+.5);
	}
	}
	
A:	for(int row=0;row<theMap.getMapArray().length;row++) {
		for(int col=0;col<theMap.getMapArray()[0].length;col++) {
			if(theMap.getMapArray()[row][col]>0) {
			int brickx=col*theMap.getBrickWidth()+theMap.HOR_PAD;
			int bricky=row*theMap.getBrickHeight()+theMap.VERT_PAD;
			int brickWidth=theMap.getBrickWidth();
			int brickHeight=theMap.getBrickHeight();
			
			Rectangle brickRect=new Rectangle(brickx,bricky,brickWidth,brickHeight);
			if(ballsRect.intersects(brickRect)) {
			
			theMap.hitBrick(row, col);
			theball.setDY(-theball.getDY());
			theHud.addScore(50);
			break A;
		}}}
	}
	
}
public void update() {
	checkCollision();
	theball.update();
	
}
public void draw() {
	g.setColor(Color.white);
	g.fillRect(0, 0,BBMain.WIDTH,BBMain.HEIGHT);
	theMap.draw(g);
	theball.draw(g);
	thePaddle.draw(g);
	theHud.draw(g);
	
	
	if(theMap.isThereMin()==true) {
		running=false;
		drawWin();}
	
}
public void drawWin() {
	g.setColor(Color.red);
	g.setFont(new Font("Courier New",Font.BOLD,50));
	g.drawString("Winner!!", 200, 200);
}

public void paintComponent(Graphics g) {
	Graphics2D g2 =(Graphics2D) g;
	
	g2.setColor(Color.black);
	g2.fillOval(20, 20, 20, 60);
	g2.drawImage(image,0,0,BBMain.WIDTH,BBMain.HEIGHT,null);
	g2.dispose();
	}
private class MyMouseMotionListener implements MouseMotionListener{
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		mousex=e.getX();
		thePaddle.mouseMoved(e.getX());
	}
}



















}