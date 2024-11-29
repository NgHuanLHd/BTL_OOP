package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class drawTileScreen {

	public int x, y, width, height, id;
	private String text;
	private Font customFont;
	private Color color;

	public drawTileScreen(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		// Tải phông chữ tùy chỉnh
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			customFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Prusia Bold.ttf");
		}catch(FontFormatException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();

		}
			
		
	}

	public void draw(Graphics g) {
		drawText(g);
	}
	
	private void drawText(Graphics g) {
		customFont = customFont.deriveFont(Font.BOLD, 100f);// in đậm
	    g.setFont(customFont);

	    int w = g.getFontMetrics().stringWidth(text); // Chiều rộng chữ
	    int h = g.getFontMetrics().getHeight();      // Chiều cao chữ

	    g.setColor(Color.WHITE);

	    // Tính toán vị trí vẽ chữ để giữ trung tâm:
	    int drawX = x + (width - w) / 2;   // Căn giữa theo chiều ngang
	    int drawY = y + (height + h) / 2;  

	    g.drawString(text, drawX, drawY);
	}
	
	public void draw2(Graphics g) {
		drawText2(g);
	}

	private void drawText2(Graphics g) {
		customFont = customFont.deriveFont(Font.BOLD, 100f);// in đậm
	    g.setFont(customFont);

	    int w = g.getFontMetrics().stringWidth(text); // Chiều rộng chữ
	    int h = g.getFontMetrics().getHeight();      // Chiều cao chữ

	    g.setColor(Color.GRAY);

	    // Tính toán vị trí vẽ chữ để giữ trung tâm:
	    int drawX = x + (width - w) / 2;   // Căn giữa theo chiều ngang
	    int drawY = y + (height + h) / 2;  

	    g.drawString(text, drawX, drawY);		
	}

	public void setColor(Color color) {
		this.color = color;		
	}



}
