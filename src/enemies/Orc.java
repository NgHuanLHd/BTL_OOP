package enemies;

import static helpz.Constants.Enemies.ORC;

import managers.EnemyManager;

public class Orc extends Enemy {
	private int currentFrame = 0;
	private int animationTick = 0;
	private static final int FRAME_DELAY = 10;
	public Orc(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, ORC,em);
	}

	public void updateAnimationFrame() {
        animationTick++;
        if (animationTick >= FRAME_DELAY) {
            animationTick = 0;
            currentFrame = (currentFrame + 1) %  32; // Cycle through 6 frames
        }
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

}
