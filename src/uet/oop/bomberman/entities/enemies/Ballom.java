package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class Ballom extends Enemy {
    private int countCall = 0;
    private int slow = 0;

    public Ballom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Ballom(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);
    }

    @Override
    public void setAlive(boolean res) {
        this.isAlive = res;
    }

    @Override
    public void update() {
        if (isAlive) {
            checkPlayer();
            slow = slow > 100 ? 0 : slow + 1;
            countCall = countCall > 100 ? 0 : countCall + 1;
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
            this.img = Sprite.balloom_dead.getFxImage();
            deadTime--;
            if (deadTime == 0) {
                Game.entityList.getEnemies().remove(this);
                Menu.addScore(100);
            }
        }
    }
}