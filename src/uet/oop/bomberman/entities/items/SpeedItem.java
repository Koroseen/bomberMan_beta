package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.EntityList;

public class SpeedItem extends Item {

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (this.intersects(Game.entityList.getBomberman())) {
            Game.entityList.getBomberman().setHasTouchedSpeedItem(true);
            Game.entityList.getBomberman().setSpeedItemDuration(1000);
            Game.entityList.getItems().remove(this);
        }
    }

}
