package main;

public enum GameStates {

	PLAYING, MENU, SETTINGS, EDIT, GAME_OVER, LEVEL_SELECTION, EDIT2, WIN_GAME, TUTORIAL;

	public static GameStates gameState = MENU;

	public static void SetGameState(GameStates state) {
		GameStates.gameState = state;

	}
 
}
