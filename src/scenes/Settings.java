package scenes;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MyButton;

public class Settings extends GameScene implements SceneMethods {

    private MyButton bBack;
    private int sliderX, sliderY, sliderWidth, sliderHeight;
    private int knobX, knobY, knobWidth, knobHeight;
    private boolean draggingKnob = false; // Cờ để kiểm tra xem người dùng có kéo nút không
    private float volumeLevel = 0.5f; // Mức âm lượng mặc định (0.0 - 1.0)

    public Settings(Game game) {
        super(game);
        initButtons();
        initSlider();
    }

    private void initButtons() {
        bBack = new MyButton("Back", 0, 0, 100, 30);
    }

    private void initSlider() {
        sliderX = 150;
        sliderY = 300;
        sliderWidth = 300;
        sliderHeight = 5;

        knobWidth = 20;
        knobHeight = 20;
        knobX = sliderX + (int) (volumeLevel * sliderWidth) - knobWidth / 2;
        knobY = sliderY - knobHeight / 2;

        //Đặt âm lượng ban đầu
        if (game.getAudioPlayer() != null) {
            game.getAudioPlayer().playMusic(0);    // Bắt đầu phát nhạc
            game.getAudioPlayer().setVolume(volumeLevel); // Đặt âm lượng mặc định
        }

    }


    @Override
    public void render(Graphics g) {
    	
        g.setColor(new Color(70, 120, 70));
        g.fillRect(0, 0, 640, 800);

        bBack.draw(g);
        
        drawSlider(g);

     // Hiển thị mức âm lượng (0% - 100%)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Volume: " + (int) (volumeLevel * 100) + "%", sliderX, sliderY - 20);
        
        
    }

    private void drawSlider(Graphics g) {
        // Đường dẫn thanh trượt
        g.setColor(Color.GRAY);
        g.fillRect(sliderX, sliderY - sliderHeight / 2, sliderWidth, sliderHeight);

        // Phần đã được chọn
        g.setColor(Color.BLACK);
        g.fillRect(sliderX, sliderY - sliderHeight / 2, (int) (volumeLevel * sliderWidth), sliderHeight);

        // Nút kéo
        g.setColor(Color.WHITE);
        g.fillOval(knobX, knobY, knobWidth, knobHeight);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bBack.getBounds().contains(x, y)) {
			game.getAudioPlayer().playMusicWO(1);
			game.getAudioPlayer().stopMusic();
        	SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
    	bBack.setMouseOver(false);
    	if (bBack.getBounds().contains(x, y))
			bBack.setMouseOver(true);    }

    @Override
    public void mousePressed(int x, int y) {
        if (isKnobClicked(x, y)) {
            draggingKnob = true;
        } else if (bBack.getBounds().contains(x, y))
			bBack.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (bBack.getBounds().contains(x, y) && bBack.isMousePressed()) {
            // Quay lại màn hình Menu
           SetGameState(MENU);
        }
        bBack.resetBooleans();
        draggingKnob = false; // Ngắt trạng thái kéo
    }


    @Override
    public void mouseDragged(int x, int y) {
    	if (draggingKnob) {
            // Cập nhật vị trí nút kéo
            knobX = Math.max(sliderX, Math.min(x - knobWidth / 2, sliderX + sliderWidth - knobWidth));
            volumeLevel = (float) (knobX - sliderX) / sliderWidth;

            // Cập nhật âm lượng
            if (game.getAudioPlayer() != null) {
            	game.setVolumeLevel(volumeLevel);
            }
            
            game.getAudioPlayer().setVolume(volumeLevel);
        }
    }


    
    private void resetButtons() {
		bBack.resetBooleans();
	}

    private boolean isKnobClicked(int x, int y) {
        return x >= knobX && x <= knobX + knobWidth && y >= knobY && y <= knobY + knobHeight;
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
