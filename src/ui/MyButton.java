	package ui;
	
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.FontFormatException;
	import java.awt.Graphics;
	import java.awt.Rectangle;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import java.io.InputStream;
	
	public class MyButton {
	
		public int x, y, width, height, id;
		private String text;
		private Rectangle bounds;
		private Font customFont;
		private boolean mouseOver, mousePressed;
		private BufferedImage buttonImage;
	
		public MyButton(String text, int x, int y, int width, int height) {
			this.text = text;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.id = -1; 
			
			try {
				InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
				customFont = Font.createFont(Font.TRUETYPE_FONT, is);
				is = getClass().getResourceAsStream("/font/Prusia Bold.ttf");
			}catch(FontFormatException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
	
			}
	
			initBounds();
		}
	
		public MyButton(String text, int x, int y, int width, int height, int id) {
			this.text = text;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.id = id;
			
			
				
	
			initBounds();
		}
		
		// testing - not working
		public MyButton(String text, int x, int y, int width, int height, BufferedImage buttonImg) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.buttonImage = buttonImg;
			
			try {
				InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
				customFont = Font.createFont(Font.TRUETYPE_FONT, is);
				is = getClass().getResourceAsStream("/font/Prusia Bold.ttf");
			}catch(FontFormatException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
	
			}
			
			initBounds();
		}
	
		private void initBounds() {
			this.bounds = new Rectangle(x, y, width, height);
		}
	
		public void draw(Graphics g) {
			// Body
		//	drawBody(g);//
			// Border
			//drawBorder(g);//
	
			// Text
			if (buttonImage != null)
				g.drawImage(buttonImage, x, y, width, height, null);
			drawText(g);
		}
	
		/*private void drawBorder(Graphics g) {
	
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
			if (mousePressed) {
				g.drawRect(x + 1, y + 1, width - 2, height - 2);
				g.drawRect(x + 2, y + 2, width - 4, height - 4);
			}
	
		}*/
	
		private void drawBody(Graphics g) {
			if (mouseOver)
				g.setColor(Color.gray);
			else
				g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
	
		}
	
		private void drawText(Graphics g) {
			customFont = customFont.deriveFont(Font.BOLD, 30f);// in đậm
		    g.setFont(customFont); 
		    
			int w = g.getFontMetrics().stringWidth(text);
			int h = g.getFontMetrics().getHeight();
			
			g.setColor(Color.WHITE);
			g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2 -2 );
			
			if (mousePressed) {
				g.drawRect(x + 1, y + 1, width - 2, height - 2);
				g.drawRect(x + 2, y + 2, width - 4, height - 4);
			}
			
			if (mouseOver) {
				g.drawString(">",x , y + h/2 + height/2 - 2 ); // x + 10
				g.setColor(Color.gray);
			}else {
				g.setColor(Color.BLACK);}
	
		} 
		
	
		public void resetBooleans() {
			this.mouseOver = false;
			this.mousePressed = false;
		}
	
		public void setText(String text) {
			this.text = text;
		}
	
		public void setMousePressed(boolean mousePressed) {
			this.mousePressed = mousePressed;
		}
	
		public void setMouseOver(boolean mouseOver) {
			this.mouseOver = mouseOver;
		}
	
		public boolean isMouseOver() {
			return mouseOver;
		}
	
		public boolean isMousePressed() {
			return mousePressed;
		}
	
		public Rectangle getBounds() {
			return bounds;
		}
	
		public int getId() {
			return id;
		}
	
	}
