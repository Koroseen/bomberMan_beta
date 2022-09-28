package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private int slow = 0;
    public Oneal(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);
        this.chaseRad = 300;
    }

    @Override
    public void setAlive(boolean res) {
        this.isAlive = res;
    }

    public Pair<Integer, Integer> delta(int row, int col) {
        return A_Star.aStarSearch(
                CreateMap.getGrid(),
                new Pair<>(this.y / Sprite.SCALED_SIZE, this.x / Sprite.SCALED_SIZE),
                new Pair<>(row, col));
    }

    @Override
    public void update() {
        if (isAlive) {
            if (EntityList.bomberman.getX() + chaseRad > this.x && EntityList.bomberman.getY() + chaseRad > this.y) {
                int destRow = EntityList.bomberman.getY() / 32;
                int destCol = EntityList.bomberman.getX() / 32;

                Pair<Integer, Integer> pair = delta(destRow, destCol);

                slow = slow > 100 ? 0 : slow + 1;

                if (this.y < pair.getKey() * 32) {
                    if (slow % 2 == 0) goDown(this);
                }
                if (this.y > pair.getKey() * 32) {
                    if (slow % 2 == 0) goUp(this);
                }
                if (this.x < pair.getValue() * 32) {
                    if (slow % 2 == 0) goRight(this);
                }
                if (this.x > pair.getValue() * 32) {
                    if (slow % 2 == 0) goLeft(this);
                }
            } else {
                slow = slow > 100 ? 0 : slow + 1;
                switch (this.dir) {
                    case UP:
                        if (slow % 3 == 0) goUp(this);
                        break;
                    case DOWN:
                        if (slow % 3 == 0) goDown(this);
                        break;
                    case LEFT:
                        if (slow % 3 == 0) goLeft(this);
                        break;
                    case RIGHT:
                        if (slow % 3 == 0) goRight(this);
                        break;
                }
            }
        } else {
            this.img = Sprite.oneal_dead.getFxImage();
            deadTime--;
            if (deadTime == 0) EntityList.enemies.remove(this);
        }
    }
}
