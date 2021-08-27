package Start;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import java.awt.RenderingHints.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
public class Map {
private int [][]theMap;
private int brickHeight,brickWidth;
public final int HOR_PAD=50,VERT_PAD=50;
public Map(int row ,int col) {
	initMap(row,col);
	brickWidth=(BBMain.WIDTH-2*HOR_PAD)/col;
	brickHeight=(BBMain.HEIGHT/2-VERT_PAD*2)/row;
}
public void initMap(int row ,int col) {
	theMap=new int[row][col];
	for(int i=0;i<theMap.length;i++) {
		for(int j=0;j<theMap[0].length;j++) {
			int r=(int)(Math.random()*4+1);
			theMap[i][j]=r;
	}}
	theMap[3][2]=4;
	theMap[3][6]=5;
}
public void draw(Graphics2D g) {
	
	for(int row=0;row<theMap.length;row++) {
		for(int col=0;col<theMap[0].length;col++) {
			if(theMap[row][col]>0) {
			if(theMap[row][col]==1) {
				g.setColor(new Color(200,200,200));
			}
			if(theMap[row][col]==2) {
				g.setColor(new Color(150,150,150));
			}
			if(theMap[row][col]==3) {
				g.setColor(new Color(100,100,100));
			}
			if(theMap[row][col]==PowerUp.WIDEPADDLE) {
				g.setColor(PowerUp.WIDECOLOR);
			}
			if(theMap[row][col]==PowerUp.FASTBALL) {
				g.setColor(PowerUp.FASTCOLOR);
			}
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.white);
			g.drawRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
		}}
	}
}
public boolean isThereMin() {
	boolean thereIsMin=false;
	int brickRemaining=0;
	for(int row=0;row<theMap.length;row++) {
		for(int col=0;col<theMap[0].length;col++) {
			brickRemaining+=theMap[row][col];
		}
	}
	if(brickRemaining==0) {
		thereIsMin=true;
	}
	return thereIsMin;}
public int [][] getMapArray(){return theMap;}
public void setBrick(int row,int col,int value) {
	theMap[row][col]=value;}
public int getBrickWidth() {return brickWidth;}
public int getBrickHeight() {return brickHeight;}
public void hitBrick(int row,int col) {
	theMap[row][col]-=1;
	if(theMap[row][col]<0) {
		theMap[row][col]=0;
	}
}
}
