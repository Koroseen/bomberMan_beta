package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ballom extends Enemy {
    private int countCall = 0;
    private int slow = 0;

    public Ballom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Ballom(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);
    }

    void changeDir() {
        Random rd = new Random();
        int n = rd.nextInt(4);
        switch (n) {
            case 0:
                this.dir = enemyDir.UP;
                break;
            case 1:
                this.dir = enemyDir.RIGHT;
                break;
            case 2:
                this.dir = enemyDir.DOWN;
                break;
            case 3:
                this.dir = enemyDir.LEFT;
                break;
        }
    }

    public void goUp() {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick()) this.y += 1;
            if(this.y % Sprite.SCALED_SIZE == 0) if(countCall % 3 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage());
    }

    public void goDown() {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick()) this.y -= 1;
            if(this.y % Sprite.SCALED_SIZE == 0) if(countCall % 3 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60).getFxImage());
    }

    public void goLeft() {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick()) this.x += 1;
            if(this.x % Sprite.SCALED_SIZE == 0) if(countCall % 3 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage());
    }

    public void goRight() {
        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick()) this.x -= 1;
            if(this.x % Sprite.SCALED_SIZE == 0) if(countCall % 3 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60).getFxImage());
    }

    @Override
    public void update() {
        if(isAlive) {
            slow = slow > 100 ? 0 : slow + 1;
            switch (this.dir) {
                case UP:
                    if(slow % 3 == 0) goUp();
                    break;
                case DOWN:
                    if(slow % 3 == 0) goDown();
                    break;
                case LEFT:
                    if(slow % 3 == 0) goLeft();
                    break;
                case RIGHT:
                    if(slow % 3 == 0) goRight();
                    break;
            }
        } else {
            this.img = Sprite.balloom_dead.getFxImage();
        }
    }
}
