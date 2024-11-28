package main;

import java.awt.Graphics;

public class Render {

	private Game game;

	public Render(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().render(g);
			break; 
		case PLAYING:
			game.getPlaying().render(g);
			break;
		case SETTINGS:
			game.getSettings().render(g);
			break;
		case EDIT:
			game.getEditor().render(g);
			break;
		case GAME_OVER:
			game.getGameOver().render(g);
			break;
		case LEVEL_SELECTION:
			game.getLevelSelection().render(g);
			break;
		case EDIT2:
			game.getEdit().render(g);	
		case WIN_GAME:
			game.getWinGame().render(g);
			break;
		case TUTORIAL:
			game.getTutorial().render(g);
		default:
			break;
		}	
	}


}