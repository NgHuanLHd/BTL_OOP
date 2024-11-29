package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.BasicStroke;

import main.Game;
import ui.MyButton;

public class OptionsWindow {

    private Game game;
    private int sliderX, sliderY, sliderWidth, sliderHeight;
    private int knobX, knobY, knobWidth, knobHeight;
    private boolean draggingKnob = false; // Cờ để kiểm tra xem người dùng có kéo nút không
    private float volumeLevel = 0.5f; // Mức âm lượng mặc định (0.0 - 1.0)

    
    public OptionsWindow(Game game) {
        this.game = game;
        initSlider();
    }

    private void initSlider() {
        sliderX = 340; // 
        sliderY = 230; // 
        sliderWidth = 150;
        sliderHeight = 14;

        knobWidth = 15;
        knobHeight = 26;
  
        knobX = sliderX + (int) (volumeLevel * sliderWidth) - knobWidth / 2;//
        knobY = sliderY - knobHeight / 2;

        // Đặt âm lượng ban đầu
        if (game.getAudioPlayer() != null) {
            game.getAudioPlayer().playMusic(0);    // Bắt đầu phát nhạc
            game.getAudioPlayer().setVolume(volumeLevel); // Đặt âm lượng mặc định
        }
    }

    public void render(Graphics g) {
    	
    	
        // Ép kiểu Graphics thành Graphics2D để sử dụng các tính năng vẽ nâng cao
        Graphics2D g2d = (Graphics2D) g;  
        

        g2d.setColor(new Color(0, 0, 0, 150)); 
        g2d.fillRoundRect(120, 100, 400, 600, 35, 35);  
        
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(120, 100, 400, 600, 35, 35);  
        
        // Vẽ thanh trượt điều chỉnh âm lượng
        drawSlider(g);

        // Hiển thị mức âm lượng (0% - 100%)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Volume: " + (int) (volumeLevel * 100) + "%", sliderX, sliderY - 20);
        
       
    }


	private void drawSlider(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("MUSIC", 200, 240);
		
        g.setColor(Color.GRAY);
        g.fillRect(sliderX, sliderY - sliderHeight / 2, sliderWidth, sliderHeight);

        g.setColor(Color.WHITE);
        g.fillRect(sliderX, sliderY - sliderHeight / 2, (int) (volumeLevel * sliderWidth), sliderHeight);

        g.setColor(Color.WHITE);
        g.fillRect(knobX, knobY, knobWidth, knobHeight);
    }

    public void mousePressed(int x, int y) {
        if (isKnobClicked(x, y)) {
            draggingKnob = true;  // Bắt đầu kéo nút trượt
        }
    }

    public void mouseReleased(int x, int y) {
        draggingKnob = false;  // Kết thúc kéo
    }

    public void mouseDragged(int x, int y) {
        if (draggingKnob) {
            knobX = Math.max(sliderX, Math.min(x - knobWidth / 2, sliderX + sliderWidth - knobWidth));
            //volumeLevel = (float) (knobX - sliderX) / sliderWidth;  // Cập nhật mức âm lượng
            volumeLevel = (float) (knobX - sliderX) / (sliderWidth - knobWidth);


            if (game.getAudioPlayer() != null) {
                game.getAudioPlayer().setVolume(volumeLevel);
            }
        }
    }


    // Kiểm tra xem nút kéo có được nhấn hay không
    private boolean isKnobClicked(int x, int y) {
        return x >= knobX && x <= knobX + knobWidth && y >= knobY && y <= knobY + knobHeight;
    }
}
