package scenes;

import objects.PathPoint;

public class LevelEditor {
    private int[][] levelData;
    private PathPoint start;
    private PathPoint end;

    public LevelEditor(int[][] levelData, PathPoint start, PathPoint end) {
        this.levelData = levelData;
        this.start = start;
        this.end = end;
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public PathPoint getStartPoint() {
        return start;
    }

    public PathPoint getEndPoint() {
        return end;
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public void setStartPoint(PathPoint start) {
        this.start = start;
    }

    public void setEndPoint(PathPoint end) {
        this.end = end;
    }
}
