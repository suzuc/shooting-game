package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Target {
	
	Random rand = new Random();
	
	int targetx;
	int targety;
	Ellipse2D e;
	
	public Target() {
		int targetx = rand.nextInt(340);
		int targety = rand.nextInt(215) + 25;
		Ellipse2D.Double e = new Ellipse2D.Double(targetx, targety, 60, 60);
		this.targetx = targetx;
		this.targety = targety;
		this.e = e;
	}
	
	public void drawTarget(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(targetx, targety, 60, 60);
	}
	
	public void changeTarget() {
		targetx = rand.nextInt(340);
		targety = rand.nextInt(215) + 25;
		e = new Ellipse2D.Double(targetx, targety, 60, 60);
	}
	
	public int get_targetx() {
		return targetx;
	}
	
	public Ellipse2D get_Ellipse() {
		return e;
	}
}


