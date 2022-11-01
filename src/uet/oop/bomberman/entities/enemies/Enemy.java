package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int speed;
    protected boolean isAlive = true;
    protected int chaseRad;
    protected int countCall = 0;
    protected int deadTime = 60;

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

    public abstract void setAlive(boolean res);
    public void changeDir(Enemy enemy) {
        Random rd = new Random();
        int n = rd.nextInt(4);
        switch (n) {
            case 0:
                enemy.dir = enemyDir.UP;
                break;
            case 1:
                enemy.dir = enemyDir.RIGHT;
                break;
            case 2:
                enemy.dir = enemyDir.DOWN;
                break;
            case 3:
                enemy.dir = enemyDir.LEFT;
                break;
        }
    }
    public void goUp(Enemy enemy) {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= enemy.speed; ++i) {
            enemy.y -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) enemy.y += 1;
            if (enemy.y % Sprite.SCALED_SIZE == 0 && countCall % 2 == 0) changeDir(enemy);
        }
        if (enemy instanceof Ballom) {
            setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage());
        } else if (enemy instanceof Oneal) {
            setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
        }
    }

    public void goDown(Enemy enemy) {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= enemy.speed; ++i) {
            enemy.y += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()|| checkTree() || checkBox()) enemy.y -= 1;
            if (enemy.y % Sprite.SCALED_SIZE == 0 && countCall % 2 == 0) changeDir(enemy);
        }
        if (enemy instanceof Ballom) {
            setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage());
        } else if (enemy instanceof Oneal) {
            setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
        }
    }

    public void goLeft(Enemy enemy) {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= enemy.speed; ++i) {
            enemy.x -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()|| checkTree() || checkBox()) enemy.x += 1;
            if (enemy.x % Sprite.SCALED_SIZE == 0 && countCall % 2 == 0) changeDir(enemy);
        }
        if (enemy instanceof Ballom) {
            setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage());
        } else if (enemy instanceof Oneal) {
            setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
        }
    }

    public void goRight(Enemy enemy) {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= enemy.speed; ++i) {
            enemy.x += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()|| checkTree() || checkBox()) enemy.x -= 1;
            if (enemy.x % Sprite.SCALED_SIZE == 0 && countCall % 2 == 0) changeDir(enemy);
        }
        if (enemy instanceof Ballom) {
            setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage());
        } else if (enemy instanceof Oneal) {
            setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
        }
    }
}