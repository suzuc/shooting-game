package view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import manager.GameModeManager;

public class TitleMode extends JPanel implements MouseListener{
	
	public TitleMode() {
		//マウス入力の有効化
		addMouseListener(this);
	}
	
	public void  paintComponent(Graphics g){
		super.paintComponent(g);
		
		Font font1 = new Font("タイトルフォント",Font.ITALIC,50);
		g.setFont(font1);
		
		drawStringCenter(g, "Click to start...", 200, 150);
	}
	
	public static void drawStringCenter(Graphics g,String text,int x,int y){
        FontMetrics fm = g.getFontMetrics();
	 Rectangle rectText = fm.getStringBounds(text, g).getBounds();
	 x=x-rectText.width/2;
	 y=y-rectText.height/2+fm.getMaxAscent();
		
        g.drawString(text, x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// マウスのボタンがクリックされた
		switch ( e.getButton() ) {
		case MouseEvent.BUTTON1:
			System.out.println("左クリック");
			GameModeManager.change(new GameMode());
			break;
		case MouseEvent.BUTTON2:
			System.out.println("ホイールクリック");
			break;
		case MouseEvent.BUTTON3:
			System.out.println("右クリック");
			break;
		}
		System.out.println("クリック座標"+e.getPoint());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}