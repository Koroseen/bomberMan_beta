package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.util.Pair;
import uet.oop.bomberman.createMap;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private int slow = 0;

    public Oneal(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);
    }

    public void goUp() {
        System.out.println("u");
//        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.y += 1;
//            if(this.y % Sprite.SCALED_SIZE == 0) if(countCall % 2 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
    }

    public void goDown() {
//        countCall = countCall > 100 ? 0 : countCall + 1;
        System.out.println("d");
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.y -= 1;
//            if(this.y % Sprite.SCALED_SIZE == 0) if(countCall % 2 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60).getFxImage());
    }

    public void goLeft() {
        System.out.println("l");
//        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.x += 1;
//            if(this.x % Sprite.SCALED_SIZE == 0) if(countCall % 2 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60).getFxImage());
    }

    public void goRight() {
        System.out.println("r");
//        countCall = countCall > 100 ? 0 : countCall + 1;
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.x -= 1;
//            if(this.x % Sprite.SCALED_SIZE == 0) if(countCall % 2 == 0) changeDir();
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
    }

    //y row
    //x column
    public Pair<Integer, Integer> delta(int row, int col) {
        return A_Star.aStarSearch(
                createMap.grid,
                new Pair<>(this.y/Sprite.SCALED_SIZE, this.x/Sprite.SCALED_SIZE ),
                new Pair<>(row, col));
    }

    @Override
    public void update() {
        int destRow = EntityList.bomberman.getY()/32;
        int destCol = EntityList.bomberman.getX()/32;

        Pair<Integer, Integer> pair = delta(destRow, destCol);
        slow = slow > 100 ? 0 : slow + 1;
        if (this.y < pair.getKey() *32) {
            System.out.println(this.x/32 + " " + this.y/32);
            if(slow % 2 == 0) goDown();
        }
        if (this.y > pair.getKey() * 32 ) {
            System.out.println(this.x/32 + " " + this.y/32);
            if(slow % 2 == 0) goUp();
        }
        if (this.x <  pair.getValue() * 32 ) {
            System.out.println(this.x/32 + " " + this.y/32);
            if(slow % 2 == 0) goRight();
        }
        if (this.x > pair.getValue() * 32 ) {
            System.out.println(this.x/32 + " " + this.y/32);
            if(slow % 2 == 0) goLeft();
        }
        System.out.println("one "+ this.x/32 +" "+ this.y/32);
    }
}
