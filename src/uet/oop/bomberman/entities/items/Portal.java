package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.EntityList;

public class Portal extends Items {
    private boolean nextLevel=false;

    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        nextLevel = false;
    }

    public void setNextLevel(boolean level) {
        this.nextLevel = level;
    }

    @Override
    public void update() {
        if (this.contains(EntityList.bomberman) && EntityList.enemies.isEmpty()) {
            Game.gamestate = "nextLevel";
            new SoundManager("sound/win.wav","win");
        }
        if (nextLevel) {
            if (Game.getLevel() < 2) Game.setLevel(Game.getLevel() + 1);
            CreateMap.createMapLevel(Game.getLevel());
        }
    }
}