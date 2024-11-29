package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Logo {
    private BufferedImage image;
    private int x, y;

    public Logo(String filePath, int x, int y) {
        this.x = x;
        this.y = y;

        try {
            System.out.println("Đang thử tải tệp từ đường dẫn: " + filePath);
            image = ImageIO.read(getClass().getResource(filePath));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Không thể tải hình ảnh từ: " + filePath);
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, null);
        } else {
            g.setColor(Color.RED);
            g.drawString("Không thể hiển thị hình ảnh!", x, y);
        }
    }
}

