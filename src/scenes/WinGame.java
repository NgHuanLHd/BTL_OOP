package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class WinGame extends GameScene implements SceneMethods {

	private MyButton bReplay, bMenu, bNextLv;

	public WinGame(Game game) {
		super(game);
		initButtons(); 
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 300;
		int yOffset = 100;

		bMenu = new MyButton("Menu", x, y, w, h);
		bReplay = new MyButton("Replay", x, y + yOffset, w, h);
		bNextLv = new MyButton("Next Level", x, y + 2*yOffset, w, h);

	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(new Color(70, 120, 70));
	    g.fillRect(0, 0, 640, 800);
		// game over text
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		g.setColor(Color.red);
		g.drawString("Game Win!", 180, 80);

		// buttons
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		bMenu.draw(g);
		bReplay.draw(g);
		bNextLv.draw(g);
	}

	private void replayGame() {
		// reset everything
		resetAll();

		// change state to playing 
		SetGameState(PLAYING);

	}

	private void resetAll() {
		game.getPlaying().resetEverything();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
			resetAll();
		} else if (bReplay.getBounds().contains(x, y)) {
			replayGame();
		} else if (bNextLv.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);
		bNextLv.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
	    } else if (bReplay.getBounds().contains(x, y)) {
	    	bReplay.setMouseOver(true);
	    } else if (bNextLv.getBounds().contains(x, y)) {
	    	bNextLv.setMouseOver(true);
	    }  
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
	    } else if (bReplay.getBounds().contains(x, y)) {
	    	bReplay.setMousePressed(true);
	    } else if (bNextLv.getBounds().contains(x, y)) {
	    	bNextLv.setMousePressed(true);
	    }

	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bReplay.resetBooleans();
		bNextLv.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

}
