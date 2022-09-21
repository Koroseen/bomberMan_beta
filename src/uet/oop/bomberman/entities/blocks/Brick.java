package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;


public class Brick extends Entity {
    private boolean broken;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        broken = false;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    @Override
    public void update() {
        if (broken) {
            animate = animate > 100 ? 0 : animate + 1;
            setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 60).getFxImage());
            EntityList.grasses.add(new Grass(this.x / Sprite.SCALED_SIZE, this.y / Sprite.SCALED_SIZE, Sprite.grass.getFxImage()));
        }
    }

}
