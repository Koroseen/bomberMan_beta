package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        checkExist(this);
        if (this.intersects(Game.entityList.getBomberman())) {
            Game.entityList.getBomberman().setSpeedUpTouched(true);
            Game.entityList.getItems().remove(this);
        }
    }
}
