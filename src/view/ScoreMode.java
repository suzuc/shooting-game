package view;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ScoreMode extends JPanel{
	
	public void  paintComponent(Graphics g){
		super.paintComponent(g);
		Font font1 = new Font("タイトルフォント",Font.ITALIC,50);
		g.setFont(font1);
		
		g.drawString("おしまい", 200, 150);
	}

}
