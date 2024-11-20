package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import helpz.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import managers.TileManager;
import scenes.AudioPlayer;
import scenes.Edit;
import scenes.Editing;
import scenes.GameOver;
import scenes.LevelSelection;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;
import scenes.WinGame;

public class Game extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	// Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	public Settings settings;
	private Editing editing;
	private GameOver gameOver;	
	private WinGame winGame;
	private LevelSelection levelSelection;
	private Edit edit;   // ấn vào edit -> chuyển tới editing và Your Level
	private TileManager tileManager;
	
	private float volumeLevel = 0.5f; // Giá trị âm lượng mặc định
	private AudioPlayer audioPlayer;

	public Game() {
		
		audioPlayer = new AudioPlayer();
		audioPlayer.setVolume(volumeLevel); // Áp dụng âm lượng mặc định khi khởi tạo
		
		initClasses();
		createDefaultLevel();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300, 0);
		setResizable(false);
		setTitle("TOWER DEFENSE PROMAX");
		
		add(gameScreen);
		pack();
		setVisible(true);

	}

	private void createDefaultLevel() {
		int[] arr = new int[400];
		for (int i = 0; i < arr.length; i++)
			arr[i] = 0;

		LoadSave.CreateLevel("new_level", arr);
 
	}

	private void initClasses() {
		
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
		gameOver = new GameOver(this);
		levelSelection = new LevelSelection(this);
		edit = new Edit(this);
		winGame = new WinGame(this);
 
	}
	
	public void initInputs() {
	    this.addKeyListener(new KeyboardListener(this)); 
	    this.setFocusable(true); 
	}

 
	private void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void updateGame() {
		switch (GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break; 
		case LEVEL_SELECTION:
            break;	
		case SETTINGS:
		    break;
		case EDIT2:
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
				updates++;
			}

//			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
//				frames = 0;
//				updates = 0;
//				lastTimeCheck = System.currentTimeMillis();
//			}

		}

	}

	// Getters and setters
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

	public Editing getEditor() {
		return editing;
	}

	public GameOver getGameOver() {
		return gameOver;
	}
	
	public WinGame getWinGame() {
		return winGame;
	}
	
	public LevelSelection getLevelSelection() {
		return levelSelection;
	}
	
	public Edit getEdit() {
		return edit;
	}

	public TileManager getTileManager() {
		return tileManager;
	}
	
	public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
	
	public float getVolumeLevel() {
        return volumeLevel;
    }

    public void setVolumeLevel(float volumeLevel) {
        this.volumeLevel = volumeLevel;
        audioPlayer.setVolume(volumeLevel); // Cập nhật âm lượng toàn bộ game
    }
	
	public void stopMusicInScene() {
        audioPlayer.stopMusic();
    }
	
	public void toggleFullscreen(boolean enable) {
        // Logic để bật/tắt toàn màn hình
        if (enable) {
            System.out.println("Fullscreen enabled");
        } else {
            System.out.println("Fullscreen disabled");
        }
    }

	public void setCurrentLevel(int i) {
		// TODO Auto-generated method stub
		
	}

}