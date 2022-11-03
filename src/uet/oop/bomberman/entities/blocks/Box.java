package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Box extends Entity {
    private boolean broken;
    private int cnt = 0;
    public Box(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        broken = false;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    @Override
    public void update() {
        if (broken) {
            cnt++;
            CreateMap.setGrid(this.y / Sprite.SCALED_SIZE, this.x / Sprite.SCALED_SIZE, ' ');
            if (cnt == 50) Game.entityList.getBoxs().remove(this);
        }
    }
}
