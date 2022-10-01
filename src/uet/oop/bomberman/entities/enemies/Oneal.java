package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private int slow = 0;
    private boolean chase;
    public Oneal(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);

    }

    public void goUp() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.y += 1;
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
    }

    public void goDown() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.y -= 1;
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60).getFxImage());
    }

    public void goLeft() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.x += 1;
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60).getFxImage());
    }

    public void goRight() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            animate = animate > 100 ? 0 : animate + 1;
            if (checkWall() || checkBrick() || checkBomb()) this.x -= 1;
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage());
    }

    public Pair<Integer, Integer> delta(int row, int col) {
        return A_Star.aStarSearch(
                CreateMap.getGrid(),
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
            if(slow % 4 == 0) goDown();
        }
        if (this.y > pair.getKey() * 32 ) {
            if(slow % 4 == 0) goUp();
        }
        if (this.x <  pair.getValue() * 32 ) {
            if(slow % 4 == 0) goRight();
        }
        if (this.x > pair.getValue() * 32 ) {
            if(slow % 4 == 0) goLeft();
        }
    }
}
