package managers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scenes.Playing;

public class WaveManager {

	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();
	private int enemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private int waveTickLimit = 60 * 15;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;
	
	private int firstWaveDelay = 60 * 15; 
	private int firstWaveCountdown = firstWaveDelay;
	private boolean isFirstWave = true;
	
	private float preparationTime = 1f; // Thời gian chuẩn bị ban đầu 
	private boolean isPreparationActive = true; // Trạng thái của tgian cbi



	public WaveManager(Playing playing) {
		this.playing = playing;
		createWaves();
	}

	public void update() {
		
		if (isPreparationActive) {
	        if (preparationTime > 0) {
	            preparationTime -= 1.0f / 60.0f; // Giảm mỗi frame (nếu game chạy ở 60 FPS)
	        } else {
	            isPreparationActive = false; // Ngừng thời gian chuẩn bị
	            isFirstWave = false; // Bắt đầu wave 1
	        }
	    }
		
		if (isFirstWave) {
	        if (firstWaveCountdown > 0) {
	            firstWaveCountdown--;
	            return; // Kết thúc sớm nếu còn thời gian chờ
	        } else {
	            isFirstWave = false; // Kết thúc thời gian chờ, bắt đầu wave
	        }
	    }
		
		if (enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++;

		if (waveStartTimer) {
			waveTick++;
			if (waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}

	}

	public void increaseWaveIndex() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}

	public boolean isWaveTimerOver() { 

		return waveTickTimerOver;
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public int getNextEnemy() {
		 if (isFirstWave && firstWaveCountdown > 0) {
		        return -1; // đảm bảo không có kẻ địch nào được sinh ra
		    }
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}

	private void createWaves() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0))));
	/*	waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1, 2)))); */
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public boolean isTimeForNewEnemy() {
		if (isFirstWave && firstWaveCountdown > 0) {
	        return false;
	    }
		return enemySpawnTick >= enemySpawnTickLimit;
	}

	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

	public boolean isPreparationActive() {
	    return isPreparationActive;
	}

	public float getPreparationTime() {
	    return preparationTime;
	}

	
	public void reset() {
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
		isFirstWave = true;
	    firstWaveCountdown = firstWaveDelay;
	}

}
