package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Enemy extends Entity {
    protected int speed;
    protected boolean isAlive = true;
    protected int chaseRad;
    public enum enemyDir {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    protected enemyDir dir;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Enemy(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img);
        this.speed = speed;
        this.chaseRad = chaseRad;
        this.dir = dir;
    }
}
