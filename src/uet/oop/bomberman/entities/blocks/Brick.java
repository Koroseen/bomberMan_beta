package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ConcurrentModificationException;


public class Brick extends Entity {
    private boolean broken;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        broken = false;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    @Override
    public void update() {
        if (broken) {
            animate = animate > 100 ? 0 : animate + 1;
            setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 40).getFxImage());
            CreateMap.setGrid(this.y / Sprite.SCALED_SIZE, this.x / Sprite.SCALED_SIZE, ' ');
            if (Bomb.count == 300) Game.entityList.getBricks().remove(this);
        }
    }

}
