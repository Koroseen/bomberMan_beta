package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;

public class Ballom extends Enemy{

    public Ballom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Ballom(int xUnit, int yUnit, Image img, int speed, int chaseRad, enemyDir dir) {
        super(xUnit, yUnit, img, speed, chaseRad, dir);
    }

    @Override
    public void update() {

    }
}
