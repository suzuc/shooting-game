package view;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DynamicGameWindow extends JFrame implements Runnable{
	private Thread th = null;
	private double sleepAddTime;
	private int fps=60;
	
	public DynamicGameWindow(String title, int width, int height) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setFps(fps);
		
		
	}
	
	public synchronized void change(JPanel p) {
		getContentPane().removeAll();
		Insets inset = getInsets();
		p.setBounds(-inset.left,-inset.top,getWidth(),getHeight());
		add(p);
		validate();
		repaint();
	}
	public synchronized void startGameLoop(){
		if ( th == null ) {
			th = new Thread(this);
			th.start();
		}
	}
	public synchronized void stopGameLoop(){
		if ( th != null ) {
			th = null;
		}
	}
	@Override
	public void run(){
		double nextTime = System.currentTimeMillis() + sleepAddTime;
		while(th != null){
			try{
				long res = (long)nextTime - System.currentTimeMillis();
				if ( res < 0 ) res = 0;
				Thread.sleep(res);
				repaint();
				nextTime += sleepAddTime;
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	public void setFps(int fps){
		if ( fps < 10 || fps > 60 ) {
			throw new IllegalArgumentException("fpsの設定は10～60の間で指定してください。");
		}
		this.fps = fps;
		sleepAddTime = 1000.0 / fps;
	}
}
