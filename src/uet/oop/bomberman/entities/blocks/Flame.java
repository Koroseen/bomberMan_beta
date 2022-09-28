package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;

public class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        for (int i = 0; i < EntityList.enemies.size(); i++) {
            if (Bomb.isFire() && this.intersects(EntityList.enemies.get(i))) {
                EntityList.enemies.get(i).setAlive(false);
            }
        }
    }
}
