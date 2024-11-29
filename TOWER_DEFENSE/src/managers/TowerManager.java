package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

public class TowerManager {

	private Playing playing;
	private BufferedImage[] towerImgs;
	private BufferedImage backGroundTowerImg;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;

	public TowerManager(Playing playing) {
		this.playing = playing;
		loadTowerImgs();
	} 
	
	private void loadTowerImgs() {
	    int tiersPerTower = 3; // Mỗi loại tháp có 3 cấp độ
	    int towerTypes = 3;    // Có 3 loại tháp: Cannon, Mg, Missile Launch

	    towerImgs = new BufferedImage[towerTypes * tiersPerTower];

	    // Tải hình ảnh cho Cannon
	    towerImgs[0 * tiersPerTower + 0] = LoadSave.getImageFromFile("Cannon/Cannon.png"); // Cannon Tier 1
	    towerImgs[0 * tiersPerTower + 1] = LoadSave.getImageFromFile("Cannon/Cannon2.png"); // Cannon Tier 2
	    towerImgs[0 * tiersPerTower + 2] = LoadSave.getImageFromFile("Cannon/Cannon3.png"); // Cannon Tier 3

	    // Tải hình ảnh cho Mg
	    towerImgs[1 * tiersPerTower + 0] = LoadSave.getImageFromFile("Mg/Mg.png"); // Mg Tier 1
	    towerImgs[1 * tiersPerTower + 1] = LoadSave.getImageFromFile("Mg/Mg2.png"); // Mg Tier 2
	    towerImgs[1 * tiersPerTower + 2] = LoadSave.getImageFromFile("Mg/Mg3.png"); // Mg Tier 3

	    // Tải hình ảnh cho Missile Launch
	    towerImgs[2 * tiersPerTower + 0] = LoadSave.getImageFromFile("Missile/MissileLaunch.png"); // Missile Tier 1
	    towerImgs[2 * tiersPerTower + 1] = LoadSave.getImageFromFile("Missile/MissileLaunch2.png"); // Missile Tier 2
	    towerImgs[2 * tiersPerTower + 2] = LoadSave.getImageFromFile("Missile/MissileLaunch3.png"); // Missile Tier 3

	    // Tải hình ảnh nền
	    backGroundTowerImg = LoadSave.getImageFromFile("Cannon/backGroundTower.png");
	}

	
	/*private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[3];
		for (int i = 1; i < 3; i++) {
			towerImgs[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);}
		towerImgs[0] = LoadSave.getImageFromFile("Cannon/Cannon.png");
		backGroundTowerImg = LoadSave.getImageFromFile("Cannon/backGroundTower.png");
	}*/

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
	}

	public void removeTower(Tower displayedTower) {
		for (int i = 0; i < towers.size(); i++)
			if (towers.get(i).getId() == displayedTower.getId())
				towers.remove(i);
	}

	public void upgradeTower(Tower displayedTower) {
		for (Tower t : towers)
			if (t.getId() == displayedTower.getId())
				t.upgradeTower();
	}

	public void update() {
		for (Tower t : towers) {
	        t.update(); // Cập nhật trạng thái của tháp

	        Enemy closestEnemy = getClosestEnemyInRange(t);
	        if (closestEnemy != null) {
	            double targetAngle = calculateAngleToTarget(
	                t.getX(), 
	                t.getY(), 
	                closestEnemy.getX(), 
	                closestEnemy.getY()
	            );

	            // Cập nhật góc mượt mà
	            t.setAngleSmooth(targetAngle);
	        }

	        attackEnemyIfClose(t); // Kiểm tra và bắn quái vật nếu cần
	    }
	}
	
	private double calculateAngleToTarget(float towerX, float towerY, float enemyX, float enemyY) {
	    float deltaX = enemyX - towerX;
	    float deltaY = enemyY - towerY;
	    return Math.atan2(deltaY, deltaX); // Trả về góc theo radian
	}
	
	private Enemy getClosestEnemyInRange(Tower tower) {
	    Enemy closest = null;
	    double closestDistance = Double.MAX_VALUE;

	    for (Enemy e : playing.getEnemyManger().getEnemies()) {
	        if (e.isAlive()) {
	            double distance = helpz.Utilz.GetHypoDistance(tower.getX(), tower.getY(), e.getX(), e.getY());
	            if (distance < closestDistance && distance <= tower.getRange()) {
	                closestDistance = distance;
	                closest = e;
	            }
	        }
	    }
	    return closest;
	}

	private void attackEnemyIfClose(Tower t) {
		for (Enemy e : playing.getEnemyManger().getEnemies()) {
			if (e.isAlive())
				if (isEnemyInRange(t, e)) {
					if (t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
				}
				
			}
		}

	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		return range < t.getRange();
	}
	
	public void draw(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;

	    for (Tower t : towers) {
	        int towerType = t.getTowerType();
	        int towerTier = t.getTier();

	        // Lấy chỉ số trong mảng 1 chiều
	        int imgIndex = getTowerImgIndex(towerType, towerTier);
	        BufferedImage towerImg = towerImgs[imgIndex];

	        double angle = t.getAngle(); // Lấy góc của tháp
	        int centerX = t.getX() + towerImg.getWidth() / 2;
	        int centerY = t.getY() + towerImg.getHeight() / 2;

	        // Vẽ bệ đỡ trước khi vẽ tháp
	        g.drawImage(backGroundTowerImg, t.getX(), t.getY(), null);

	        // Lưu trạng thái gốc
	        g2d.rotate(angle, centerX, centerY);

	        // Vẽ hình ảnh với góc xoay
	        g2d.drawImage(towerImg, t.getX(), t.getY(), null);

	        // Phục hồi trạng thái gốc
	        g2d.rotate(-angle, centerX, centerY);
	    }
	}
	
	private int getTowerImgIndex(int towerType, int tier) {
	    int tiersPerTower = 3; // Số cấp độ mỗi loại tháp có
	    return towerType * tiersPerTower + (tier - 1);
	}

	public Tower getTowerAt(int x, int y) {
		for (Tower t : towers)
			if (t.getX() == x)
				if (t.getY() == y)
					return t;
		return null;
	}

	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}

	public void reset() {
		towers.clear();
		towerAmount = 0;
	}

}
