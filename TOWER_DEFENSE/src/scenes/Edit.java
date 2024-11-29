package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Edit extends GameScene implements SceneMethods {

	private MyButton bEditing, bYourLevel, bBack;
 
	public Edit(Game game) {
		super(game); 
		initButtons(); 
 
	}

	private void initButtons() {
		bEditing = new MyButton("Editing", 280, 100, 100, 30);
		bYourLevel = new MyButton("Your Level", 280, 150, 100, 30);
		bBack = new MyButton("<Back" , 2, 2, 100, 30);
	}
 
	@Override
	public void render(Graphics g) {
		/* g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 640); */
		drawButtons(g);
	}
 
	private void drawButtons(Graphics g) {
		bEditing.draw(g);
		bYourLevel.draw(g);
		bBack.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bEditing.getBounds().contains(x, y))
			SetGameState(EDIT);
		else if (bYourLevel.getBounds().contains(x, y))
		    SetGameState(EDIT);
		else if (bBack.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bEditing.setMouseOver(false);
		bYourLevel.setMouseOver(false);
		bBack.setMouseOver(false);
		
		if (bEditing.getBounds().contains(x, y))
			bEditing.setMouseOver(true);
		else if (bYourLevel.getBounds().contains(x, y))
			bYourLevel.setMouseOver(true);
		else if (bBack.getBounds().contains(x, y))
			bBack.setMouseOver(true);
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bEditing.getBounds().contains(x, y))
			bEditing.setMousePressed(true);
		else if (bYourLevel.getBounds().contains(x, y))
			bYourLevel.setMousePressed(true);
		else if (bBack.getBounds().contains(x, y))
			bBack.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bYourLevel.resetBooleans();
		bYourLevel.resetBooleans();
		bBack.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
