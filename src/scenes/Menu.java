package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import ui.Logo;
import ui.MyButton;
import ui.drawTileScreen;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private MyButton bPlaying, bEdit, bSettings, bQuit, bTutorial;
	private MyButton bLevelEditor; // what is this for ???
    private drawTileScreen TileScreen, TileScreen2;
    private AudioPlayer audioPlayer;
	
	public Menu(Game game) {
		super(game);
		game.getAudioPlayer().setVolume(game.getVolumeLevel());
		game.getAudioPlayer().playMusic(0);
		initButtons();
		
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;  
		int y = 300;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bEdit = new MyButton("Edit", x, y + yOffset, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
		bTutorial = new MyButton("Tutorial", x, y + yOffset * 3, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 4, w, h);
		bLevelEditor = new MyButton("Level", x, y + yOffset * 4, w, h);
		TileScreen = new drawTileScreen("TOWER DEFENSE", x, 200-yOffset*3/2, w, h);
		TileScreen2 = new drawTileScreen("TOWER DEFENSE", x+5, 200-yOffset*3/2+5, w, h);
		//logo = new Logo("res/khien.png", x, 300);//

	}

	@Override
	public void render(Graphics g) {
	    if (!game.getAudioPlayer().isPlaying())
	    	game.getAudioPlayer().playMusic(0);
	    g.setColor(new Color(70, 120, 70));
	    g.fillRect(0, 0, 640, 800);
	    drawButtons(g);
	}


	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bEdit.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
		bTutorial.draw(g);
		TileScreen2.draw2(g);
		TileScreen.draw(g);
		//logo.draw(g);//
	}

	@Override
	public void mouseClicked(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(LEVEL_SELECTION); 
		}else if (bEdit.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(EDIT2);
		}else if (bSettings.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			game.getAudioPlayer().playMusic(0);
			SetGameState(SETTINGS);
		}else if (bTutorial.getBounds().contains(x, y)) {
				game.getAudioPlayer().playMusicWO(1);
				SetGameState(TUTORIAL); 
	    }else if (bQuit.getBounds().contains(x, y)) {
        	game.getAudioPlayer().playMusicWO(1);
			System.exit(0);
        }
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bEdit.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);
		bTutorial.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMouseOver(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMouseOver(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMouseOver(true);
		else if (bTutorial.getBounds().contains(x, y))
			bTutorial.setMouseOver(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {  

		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMousePressed(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMousePressed(true);
		else if (bSettings.getBounds().contains(x, y)) 
			bSettings.setMousePressed(true);
		else if (bTutorial.getBounds().contains(x, y)) 
			bTutorial.setMousePressed(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMousePressed(true);

	}

	@Override 
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bEdit.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
		bTutorial.resetBooleans();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

}