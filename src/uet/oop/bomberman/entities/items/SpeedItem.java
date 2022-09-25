package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.EntityList;

public class SpeedItem extends Items {

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (this.intersects(EntityList.bomberman)) {
            EntityList.bomberman.setHasTouchedSpeedItem(true);
            EntityList.bomberman.setSpeedItemDuration(1000);
            EntityList.items.remove(this);
        }
    }

}
