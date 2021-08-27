package Start;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
public class Paddle {
	private Rectangle paddleRect;
	private double x;
	private int width,height;
	public final int YPOS=BBMain.HEIGHT-100;
public Paddle() {
	width=100;
	height=20;
	x=BBMain.WIDTH-width/2;
}
public void update() {}
public void draw(Graphics2D g) {
	g.setColor(Color.DARK_GRAY);
	g.fillRect((int)x, YPOS, width, height);
}
public void mouseMoved(int mouseXpos) {
	x=mouseXpos;
	if(x>BBMain.WIDTH-width){
		x=BBMain.WIDTH-width;
	}
	
}
public Rectangle getRect() {
	return new Rectangle((int)x,YPOS,width,height);
}
public int getWidth() {return width;} 
}
