package scenes;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import ui.MyButton;

public class LevelSelection extends GameScene implements SceneMethods {

	private MyButton bLv1, bLv2, bLv3, bLvEdit, bBack;
	private AudioPlayer audioPlayer;
	
    public LevelSelection(Game game) {
        super(game);
        initButtons();
 
    }
 
    private void initButtons() {
 
        int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2; 
		int y = 150;
		int yOffset = 100; 

        bLv1 = new MyButton("Level 1" , x, y + yOffset , w, h);
        bLv2 = new MyButton("Level 2" , x, y + yOffset*2 , w, h);
        bLv3 = new MyButton("Level 3" , x, y + yOffset*3 , w, h);
        bLvEdit = new MyButton("Your Level" , x, y + yOffset*4 , w, h);
        bBack = new MyButton("<Back" , 2, 2, 100, 30);
       
    }

    @Override 
    public void render(Graphics g) {
    	g.setColor(new Color(70, 120, 70));
    	g.fillRect(0, 0, 640, 800);
    	drawButtons(g); 
    }
    
    private void drawButtons(Graphics g) {
		bLv1.draw(g);
		bLv2.draw(g);
		bLv3.draw(g);
		//bLvEdit.draw(g);
		bBack.draw(g);
		
	}

    @Override
    public void mouseClicked(int x, int y) {
    	if (bLv1.getBounds().contains(x, y)) {
			game.getAudioPlayer().stopMusic();
			game.getAudioPlayer().playMusicWO(1);
			game.getAudioPlayer().playMusic(2);
			SetGameState(PLAYING);
    	} else if (bLv2.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(PLAYING);
		} else if (bLv3.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(PLAYING);
		} else if (bLvEdit.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(PLAYING);
		} else if (bBack.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(MENU);
		}
    }

	@Override
    public void mouseMoved(int x, int y) {
		bLv1.setMouseOver(false);
		bLv2.setMouseOver(false);
		bLv3.setMouseOver(false);
		bLvEdit.setMouseOver(false);
		bBack.setMouseOver(false);
		
		if (bLv1.getBounds().contains(x, y))
			bLv1.setMouseOver(true);
		else if (bLv2.getBounds().contains(x, y))
			bLv2.setMouseOver(true);
		else if (bLv3.getBounds().contains(x, y)) 
			bLv3.setMouseOver(true);
		else if (bLvEdit.getBounds().contains(x, y)) 
			bLvEdit.setMouseOver(true);
		else if (bBack.getBounds().contains(x, y))
			bBack.setMouseOver(true);
    }
 
    @Override
    public void mousePressed(int x, int y) {
    	if (bLv1.getBounds().contains(x, y)) 
			bLv1.setMousePressed(true);
		else if (bLv2.getBounds().contains(x, y))
			bLv2.setMousePressed(true);
		else if (bLv3.getBounds().contains(x, y))
			bLv3.setMousePressed(true);
		else if (bLvEdit.getBounds().contains(x, y)) 
			bLvEdit.setMousePressed(true);
		else if (bBack.getBounds().contains(x, y))
			bBack.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
    	resetButtons(); 
    }

    private void resetButtons() {
		bLv1.resetBooleans();
		bLv2.resetBooleans();
		bLv3.resetBooleans();
		bBack.resetBooleans();
		bLvEdit.resetBooleans();
	}

	@Override
    public void mouseDragged(int x, int y) {
      
   }
} 
