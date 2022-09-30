package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.EntityList;

public class Portal extends Items{
    private boolean nextLevel;
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        nextLevel = false;
    }

    @Override
    public void update() {
        if (this.intersects(EntityList.bomberman) && EntityList.enemies.isEmpty()) {
            nextLevel = true;
        }
        if (nextLevel) {
            if (Game.getCurLevel() < 2) Game.setCurLevel(Game.getCurLevel() + 1);
            CreateMap.createMapLevel(Game.getCurLevel());
        }
    }
}
