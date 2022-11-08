package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.Game;
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
            checkPlayer();
            int destRow = Game.entityList.getBomberman().getY() / 32;
            int destCol = Game.entityList.getBomberman().getX() / 32;

            Pair<Integer, Integer> pair = delta(destRow, destCol);

            if ((pair.getKey() == -1 && pair.getValue() == -1) ||
                    Math.sqrt(Math.pow(Game.entityList.getBomberman().getX() - this.x, 2)
                            + Math.pow(Game.entityList.getBomberman().getY() - this.y, 2)) > chaseRad) {

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
            } else {
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
            }
        } else {
            this.img = Sprite.oneal_dead.getFxImage();
            deadTime--;
            if (deadTime == 0) {
                Game.entityList.getEnemies().remove(this);
                Menu.addScore(150);
            }
        }
    }
}