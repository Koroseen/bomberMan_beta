package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Tree extends Entity {
    private boolean broken;

    public Tree(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        broken = false;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    @Override
    public void update() {
        if (broken) {
            CreateMap.setGrid(this.y / Sprite.SCALED_SIZE, this.x / Sprite.SCALED_SIZE, ' ');
            if (Bomb.count == 300) Game.entityList.getTrees().remove(this);
        }
    }
}
