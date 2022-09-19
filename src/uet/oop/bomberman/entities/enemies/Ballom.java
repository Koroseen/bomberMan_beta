package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

//import static uet.oop.bomberman.entities.Bomber.t;

public class Ballom extends Enemy {
    private int countCall = 0;
    public Ballom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Ballom(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);
    }

    void changeDir() {
        countCall++;
        if(countCall > 100) countCall = 0;
        switch (countCall % 4) {
            case 0:
                this.dir = enemyDir.UP;
                break;
            case 1:
                this.dir = enemyDir.RIGHT;
                break;
            case  2:
                this.dir = enemyDir.DOWN;
                break;
            case 3:
                this.dir = enemyDir.LEFT;
                break;
        }
    }

    public void goUp() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkCollision()) {
                this.y += 1;
                changeDir();
            }
        }
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 90).getFxImage());
    }

    public void goDown() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkCollision()) {
                this.y -= 1;
                changeDir();
            }
        }
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 90).getFxImage());
    }

    public void goLeft() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkCollision()) {
                this.x += 1;
                changeDir();
            }
        }
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 90).getFxImage());
    }

    public void goRight() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkCollision()) {
                this.x -= 1;
                changeDir();
            }
        }
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 90).getFxImage());
    }


    @Override
    public void update() {
        switch (this.dir) {
            case UP:
                goUp();
                break;
            case DOWN:
                goDown();
                break;
            case LEFT:
                goLeft();
                break;
            case RIGHT:
                goRight();
                break;
        }


    }
}
