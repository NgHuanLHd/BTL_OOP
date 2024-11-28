package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpz.LoadSave;
import main.Game;
import ui.MyButton;

import static main.GameStates.*;


public class Tutorial extends GameScene implements SceneMethods {
	private MyButton bEnemy, bTower, bSpecialSkill,bHowToPlay, bNext;
	private MyButton bMenu, bLetsPlay;
	private BufferedImage bgrImg, buttonImg, displayArea, nextButtonImg, ingameImg;
	
	private static int general = 1, enemy = 2, tower = 3, skill = 4;
	private int section = general; // set the initial screen at "How to play" 
	private int page = 0; // set the initial page at
	public Tutorial(Game game) {
		super(game);
		initButtons();
		loadImgs();
	}

	private void loadImgs() {
		bgrImg = LoadSave.getSpriteAtlas("origbig");
		buttonImg = LoadSave.getSpriteAtlas("buttonImg");
		displayArea = LoadSave.getSpriteAtlas("Board");
		nextButtonImg = LoadSave.getSpriteAtlas("nextButton");
		ingameImg = LoadSave.getSpriteAtlas("ingame");
		
		
	}

	private void initButtons() {
		bEnemy = new MyButton("Enemy Wiki", 10, 150, 150, 45);
		bTower = new MyButton("Tower Wiki", 10, 220, 150, 45);
		bSpecialSkill = new MyButton("Special Skills", 10, 290, 150, 45);
		bHowToPlay = new MyButton("How to Play", 10, 360, 150, 45);
		bMenu = new MyButton("Back to Menu", 10, 720, 200, 45);
		bLetsPlay = new MyButton("Get Started!", 470, 720, 190, 45);
		bNext = new MyButton("Next", 213 + 393 - 150, 175 + 455 , 150, 45);


	}
	@Override
	public void render(Graphics g) {
		g.drawImage(bgrImg, 0, 0, null);
		// content displaying area 			
		g.drawImage(displayArea,180, 100, 450, 600, null);
		g.setColor(new Color(255, 255, 204)); 
		g.fillRect(213,175, 393, 455);
		g.setColor(Color.black);
		g.drawRect(213, 175, 393, 455); // table of contents
		
		g.drawRect(213, 175, 256, 256); // img
		g.drawRect(213 + 256, 175, 393 - 256, 256);
		
		displayInfo(g);
		// buttons
		drawButtons(g);
		
		// tutorial title
		g.drawImage(buttonImg, 180 + (450 - 326) / 2, 50, 326, 120, null);
		g.setColor(Color.white);
	    g.drawString("Tutorial", 242 + (326 - 96) / 2, 110); // Centered position - not completed placing
		
	}

	private void displayInfo(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		switch(section) {
		case 1: // general
			displayGeneralInfo(g);
			break;
		case 2: //enemy
			displayEnemyInfo(g);
			break;
		case 3: //tower
			displayTowerInfo(g);
			break;
		}
	}

	private void displayGeneralInfo(Graphics g) {
		g.drawImage(ingameImg, 213, 175, 393, 350, null);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("How to play: Buy and place towers to win, ez", 213 + 2, 393 + 160 + 2);
	}

	private void displayEnemyInfo(Graphics g) {
		
		//stats
		int yOffset = 2;
		int imageOffset = 32;
		g.drawString(helpz.Constants.Enemies.GetName(page),213 + 256 + 5, 175 + 25);
		g.drawString("Health: " + helpz.Constants.Enemies.GetStartHealth(page), 213 + 256 + 5, 175 + (20 + 5) * yOffset++);
		g.drawString("Speed: " + helpz.Constants.Enemies.GetSpeed(page), 213 + 256 + 5, 175 + (20 + 5) * yOffset++ + imageOffset + 15);
		g.drawString("Special ab: No" , 213 + 256 + 5, 175 + (20 + 5) * yOffset++ + imageOffset * 2 + 30);
		
		// detailed info
		g.setFont(new Font("Arial", Font.BOLD, 15));
		int textYOffset = 20; // Line spacing for description text
		int textX = 213 + 2; // X position for text
		int startY = 450; // Starting Y position for detailed info
		if (page == 0) {
			g.drawString("Orcs are tough and aggressive enemies with decent", textX, startY);
			g.drawString("HP and moderate speed. They rely on brute physical", textX, startY + textYOffset * 1);
			g.drawString("strength to overwhelm their opponents.", textX, startY + textYOffset * 2);
		} else if (page == 1) {
			g.drawString("Bats are swift and elusive enemies with high speed", textX, startY);
			g.drawString("but low health. Their greatest advantage is their", textX, startY + textYOffset * 1);
			g.drawString("ability to fly, so they can only be targeted by some", textX, startY + textYOffset * 2);
			g.drawString("special towers.", textX, startY + textYOffset * 3);
		} else if (page == 2) {
			g.drawString("Knights are heavily armored enemies with high HP", textX, startY);
			g.drawString("but low speed. Their resilience makes them tough to", textX, startY + textYOffset * 1);
			g.drawString("defeat, even though they move slowly across the", textX, startY + textYOffset * 2);
			g.drawString("battlefield.", textX, startY + textYOffset * 3);
		} else if (page == 3) {
			g.drawString("Wolves are swift and relentless enemies with extremely", textX, startY);
			g.drawString("high speed but low health. They rely on their numbers", textX, startY + textYOffset * 1);
			g.drawString("to overwhelm players and squeeze their defenses.", textX, startY + textYOffset * 2);
		}

		
	}

	private void displayTowerInfo(Graphics g) {
		int yOffset = 2;
		int imageOffset = 32;
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(helpz.Constants.Towers.GetName(page),213 + 256 + 5, 175 + 25);
		g.drawString("Level 1: ", 213 + 256 + 5, 175 + (20 + 5) * yOffset++);
		g.drawString("Level 2: ", 213 + 256 + 5, 175 + (20 + 5) * yOffset++ + imageOffset + 15);
		g.drawString("Level 3: ", 213 + 256 + 5, 175 + (20 + 5) * yOffset++ + imageOffset * 2 + 30);
		
		//level 1
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("Damage:" + helpz.Constants.Towers.GetStartDmg(page) , 213 + 256 + 5, 175 + (20 + 5) * 3 - 10);
		g.drawString("Range:" + helpz.Constants.Towers.GetDefaultRange(page) , 213 + 256 + 5, 175 + (20 + 5) * 3 + 3);
		g.drawString("Attack Interval:" + helpz.Constants.Towers.GetDefaultCooldown(page) , 213 + 256 + 5, 175 + (20 + 5) * 3 + 16);
		
		//detailed info
		g.setFont(new Font("Arial", Font.BOLD, 15));
		int textYOffset = 20; // Line spacing for description text
		int textX = 213 + 2; // X position for text
		int startY = 450; // Starting Y position for detailed info
		if (page == 1) {
			g.drawString("The Archer Tower is a balanced defense with average", textX, startY);
			g.drawString("damage, attack speed, and range. It is low in cost and", textX, startY + textYOffset * 1);
			g.drawString("can target both ground and air enemies, making it", textX, startY + textYOffset * 2);
			g.drawString("a versatile choice for early defense.", textX, startY + textYOffset * 3);
		} else if (page == 0) {
			g.drawString("The Cannon Tower deals high damage with a slow attack", textX, startY);
			g.drawString("interval and a wide range. Its ability to hit multiple", textX, startY + textYOffset * 1);
			g.drawString("enemies at once with AoE damage makes it effective", textX, startY + textYOffset * 2);
			g.drawString("against large groups of ground enemies, but it can only", textX, startY + textYOffset * 3);
			g.drawString("target ground units.", textX, startY + textYOffset * 4);
		} else if (page == 2) {
			g.drawString("The Wizard Tower doesn't deal damage but slows", textX, startY);
			g.drawString("enemies with its powerful attack interval. With an", textX, startY + textYOffset * 1);
			g.drawString("average range, it can target both ground and air enemies.", textX, startY + textYOffset * 2);
			g.drawString("Its main role is to disrupt enemy movement and make", textX, startY + textYOffset * 3);
			g.drawString("them easier targets for other towers.", textX, startY + textYOffset * 4);
		}
		
		
	}

	private void drawButtons(Graphics g) {
		bEnemy.draw(g);
		bTower.draw(g);
		bSpecialSkill.draw(g);
		bHowToPlay.draw(g);
		bMenu.draw(g);
		bLetsPlay.draw(g);
		bNext.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(MENU);
		} else if (bLetsPlay.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			SetGameState(LEVEL_SELECTION);
		} else if (bTower.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			section = tower;
			page = 0;
		} else if (bEnemy.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			section = enemy;
			page = 0;
		} else if (bSpecialSkill.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			section = skill;
			page = 0;
		} else if (bHowToPlay.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			section = general;
		}  else if (bNext.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			if (section != 1)
				if ((section == tower && page < 2) || ((section == enemy) && page < 3))
					page++; // flip the page
				else
					page = 0;
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bHowToPlay.setMouseOver(false);
		bTower.setMouseOver(false);
		bEnemy.setMouseOver(false);
		bLetsPlay.setMouseOver(false);
		bMenu.setMouseOver(false);
		bSpecialSkill.setMouseOver(false);

		if (bHowToPlay.getBounds().contains(x, y))
			bHowToPlay.setMouseOver(true);
		else if (bTower.getBounds().contains(x, y))
			bTower.setMouseOver(true);
		else if (bEnemy.getBounds().contains(x, y))
			bEnemy.setMouseOver(true);
		else if (bLetsPlay.getBounds().contains(x, y))
			bLetsPlay.setMouseOver(true);
		else if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bSpecialSkill.getBounds().contains(x, y))
			bSpecialSkill.setMouseOver(true);
		else if (bNext.getBounds().contains(x, y))
			bNext.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bHowToPlay.getBounds().contains(x, y))
			bHowToPlay.setMousePressed(true);
		else if (bTower.getBounds().contains(x, y))
			bTower.setMousePressed(true);
		else if (bEnemy.getBounds().contains(x, y)) 
			bEnemy.setMousePressed(true);
		else if (bLetsPlay.getBounds().contains(x, y)) 
			bLetsPlay.setMousePressed(true);
		else if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bSpecialSkill.getBounds().contains(x, y))
			bSpecialSkill.setMousePressed(true);
		else if (bNext.getBounds().contains(x, y))
			bNext.setMousePressed(true);
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bHowToPlay.resetBooleans();
		bMenu.resetBooleans();
		bLetsPlay.resetBooleans();
		bSpecialSkill.resetBooleans();
		bTower.resetBooleans();
		bEnemy.resetBooleans();
		bNext.resetBooleans();
		
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		
		
	}

}
