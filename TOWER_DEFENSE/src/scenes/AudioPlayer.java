package scenes;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AudioPlayer {
    Clip clip;
    private URL soundURL[] = new URL[30];
    
    public AudioPlayer() {
       soundURL[0] = getClass().getResource("/sound/gameMusicWAV.wav");
       soundURL[1] = getClass().getResource("/sound/selectWAV.wav");
       soundURL[2] = getClass().getResource("/sound/MusicInGame.wav");
    	
    	
    }

    
    public void setFile(int i) {
        try {
            if (clip != null && clip.isOpen()) {
                clip.close(); // Giải phóng clip cũ trước khi mở cái mới
            }
            if (soundURL[i] == null) {
                System.out.println("Sound file not found for index: " + i);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            System.err.println("Error loading sound file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void setVolume(float volume) {
        try {
            if (clip != null && clip.isOpen()) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log10(Math.max(volume, 0.01)) * 20); // Chuyển từ tỉ lệ tuyến tính sang dB
                gainControl.setValue(dB); // Đặt âm lượng
            } else {
                System.out.println("Clip is null or not open for volume adjustment.");
            }
        } catch (Exception e) {
            System.err.println("Error setting volume: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void playMusic(int i) {
    	setFile(i);
    	play();
    	loop();
    }
    
    public void playMusicWO(int i) {
    	setFile(i);
    	play();
    }
    
    public void stopMusic() {
    	stop();
    }
   
    public void play() {
        clip.start();
    }
    
    public void loop() {
    	 clip.loop(Clip.LOOP_CONTINUOUSLY); 
    }

    public void stop() {
    	if (clip != null && clip.isRunning()) {
            clip.stop();  // Dừng nhạc nếu clip đang chạy
        } else {
            System.out.println("No music is currently playing or clip is null.");
        }
    }
  
    public boolean isPlaying() {
        return clip.isRunning();
    }


    public float getCurrentVolume() {
        try {
            if (clip != null && clip.isOpen()) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = gainControl.getValue(); // Giá trị dB hiện tại
                return (float) Math.pow(10, dB / 20); // Chuyển dB ngược lại về tỉ lệ
            }
        } catch (Exception e) {
            System.err.println("Error getting volume: " + e.getMessage());
        }
        return 0;
    }

    
    
}

