package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Bomb;

public class FlameItem extends Item {

    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (this.intersects(Game.entityList.getBomberman())) {
            Bomb.increaseRadius();
            Game.entityList.getItems().remove(this);
        }
    }

}
