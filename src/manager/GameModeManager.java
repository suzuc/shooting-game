package manager;

import view.DynamicGameWindow;
import view.GameMode;
import view.ScoreMode;
import view.TitleMode;

public class GameModeManager {
	
	static DynamicGameWindow gw = new DynamicGameWindow("(^^♪",400,300);
	
	public static void start() {
		gw.setVisible(true);
		gw.change(new TitleMode());
		gw.startGameLoop();
	}
	
	public static void change(GameMode gm) {
		gw.change(gm);
	}
	
	public static void change2(ScoreMode sm) {
		gw.change(sm);
	}
}
