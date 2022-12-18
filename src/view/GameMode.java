package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import object.Target;

public class GameMode extends JPanel{
	
	int x = 200;
	int y = 150;
	
	int targetx;
	int targety;
	
	boolean isInTarget;
	int point = 0;
	
	static boolean is_ascending;
	static boolean is_descending;
	static boolean is_toRight;
	static boolean is_toLeft;
	
	Target t;
	
	boolean endOfGameMode;
	
	public GameMode() {
		setKeyBindings();
		Target t = new Target();
		this.t = t;
		
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
 
			//int cnt=0;
 
			@Override
			public void run() {
				System.out.println("change");
				t.changeTarget();
				//cnt++;
				//5回実行で停止
				//if ( cnt >= 50 ) timer.cancel();
			}
		};
		timer.schedule(task, 0, 5000);
		
		Timer timer2 = new Timer(false);
		TimerTask task2 = new TimerTask() {
 
			@Override
			public void run() {
				System.out.println("終了");
				endOfGameMode = true;
				timer.cancel();
				timer2.cancel();
			}
		};
		timer2.schedule(task2, 10000);
		
		
	}
	
	private void setKeyBindings() {
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_W, 0),  "ascending_on");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_W, 0, true),  "ascending_off");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, 0),  "descending_on");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, 0, true),  "descending_off");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, 0),  "toLeft_on");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, 0, true),  "toLeft_off");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_D, 0),  "toRight_on");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_D, 0, true),  "toRight_off");
		getInputMap(2).put(KeyStroke.getKeyStroke(
				KeyEvent.VK_ENTER, 0),  "attack");
		
		getActionMap().put("ascending_on", new AscendingAction(true));
		getActionMap().put("ascending_off", new AscendingAction(false));
		getActionMap().put("descending_on", new DescendingAction(true));
		getActionMap().put("descending_off", new DescendingAction(false));
		getActionMap().put("toLeft_on", new ToLeftAction(true));
		getActionMap().put("toLeft_off", new ToLeftAction(false));
		getActionMap().put("toRight_on", new ToRightAction(true));
		getActionMap().put("toRight_off", new ToRightAction(false));
		getActionMap().put("attack", new AttackAction());
	}
	
	public void  paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(endOfGameMode == false) {
		t.drawTarget(g);
		drawOwnMachine(g);
		
		if(t.get_Ellipse().contains(x, y)) {
			g.setColor(Color.RED);
			g.drawString("当たっています！！！！！！！！", 50,50);
			isInTarget = true;
		} else {
			g.setColor(Color.BLUE);
			g.drawString("当たってません！！！！！！！", 50,50);
			isInTarget = false;
		}
		
		g.drawString("" + point, 50, 70);
	} else if(endOfGameMode){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,400,300);
		g.setColor(Color.WHITE);
		g.drawString("あなたの得点は"+point+"点です", 100, 100);
		g.drawString("おしまい", 100, 200);
		
	}
	}
	
	public void drawOwnMachine(Graphics g) {
		if (  is_ascending == true && 0<y ) {
			//上キーを押し下げ状態ならyを減算させ、画像を上に移動させる
			y -= 10;
		}
		if ( is_descending && y<300 ) {
			//下キーを押し下げ状態ならyを加算させ、画像を下に移動させる
			y += 10;
		}
		if ( is_toLeft && 0<x ) {
			//左キーを押し下げ状態ならxを減算させ、画像を左に移動させる
			x -= 10;
		}
		if ( is_toRight && x<400 ) {
			//右キーを押し下げ状態ならxを加算させ、画像を右に移動させる
			x += 10;
		}
		
		g.setColor(Color.ORANGE);
		g.fillOval(x-5, y-5, 10, 10);
	}
	
	public static void set_is_ascending(boolean b) {
		is_ascending = b;
	}
	public static void set_is_descending(boolean b) {
		is_descending = b;
	}
	public static void is_toRight(boolean b) {
		is_toRight = b;
	}
	public static void set_is_toLeft(boolean b) {
		is_toLeft = b;
	}
	
	class AttackAction extends AbstractAction {
		
		public void actionPerformed(ActionEvent e){
			if(isInTarget) {
				point++;
				t.changeTarget();
			}
		}
	}
}

class AscendingAction extends AbstractAction {
	
	boolean b;
	
	public AscendingAction (boolean b){
		this.b = b;
	}
	
	public void actionPerformed(ActionEvent e){
		GameMode.set_is_ascending(b);
	}
}

class DescendingAction extends AbstractAction {
	
	boolean b;
	
	public DescendingAction (boolean b){
		this.b = b;
	}
	
	public void actionPerformed(ActionEvent e){
		GameMode.set_is_descending(b);
	}
}

class ToRightAction extends AbstractAction {
	
	boolean b;
	
	public ToRightAction (boolean b){
		this.b = b;
	}
	
	public void actionPerformed(ActionEvent e){
		GameMode.is_toRight(b);
	}
}

class ToLeftAction extends AbstractAction {
	
	boolean b;
	
	public ToLeftAction (boolean b){
		this.b = b;
	}
	
	public void actionPerformed(ActionEvent e){
		GameMode.set_is_toLeft(b);
	}
}



