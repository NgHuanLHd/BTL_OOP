package scenes;

import static main.GameStates.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MyButton;

public class Settings extends GameScene implements SceneMethods {

    private MyButton bBack;
    private OptionsWindow optionsWindow;
    public Settings(Game game) {
        super(game);
        initButtons();
        optionsWindow = new OptionsWindow(game); // Khởi tạo cửa sổ Options
    }

    private void initButtons() {
        bBack = new MyButton("Back", 0, 0, 100, 30);
    }


    @Override
    public void render(Graphics g) {
    	
        g.setColor(new Color(70, 120, 70));
        g.fillRect(0, 0, 640, 800);
        

        bBack.draw(g);
        optionsWindow.render(g);
    
        /*drawSlider(g);

        // Hiển thị mức âm lượng (0% - 100%)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Volume: " + (int) (volumeLevel * 100) + "%", sliderX, sliderY - 20);*/
        
   
    }
  

    @Override
    public void mouseClicked(int x, int y) {
        if (bBack.getBounds().contains(x, y)) {
            game.getAudioPlayer().playMusicWO(1);
            game.getAudioPlayer().stopMusic();
            SetGameState(MENU); // Quay lại menu khi nhấn nút "Back"
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bBack.setMouseOver(false);
        if (bBack.getBounds().contains(x, y))
            bBack.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bBack.getBounds().contains(x, y))
            bBack.setMousePressed(true);
        
        optionsWindow.mousePressed(x, y);
       
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (bBack.getBounds().contains(x, y) && bBack.isMousePressed()) {
            SetGameState(MENU); // Quay lại menu
        }
        bBack.resetBooleans();
        
        optionsWindow.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        optionsWindow.mouseDragged( x, y);
    }
}





/*package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMenu;
	public Settings(Game game) {
		super(game); 
		initButtons(); 
 
	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 280, 100, 100, 30);
	}
 
	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}
 
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}*/
