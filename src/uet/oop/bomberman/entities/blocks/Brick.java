package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.EntityList.bomberman;


public class Brick extends Entity {
    private boolean broken;
    private int happen = 0;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        broken = false;
        happen = 0;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    @Override
    public void update() {
        if(broken) {
            if(happen < 150) {
                happen++;
                setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, happen, 60).getFxImage());
            }

        }
    }
}
