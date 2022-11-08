package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.SoundManager;

public class Portal extends Item {

    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (this.contains(Game.entityList.getBomberman()) && Game.entityList.getEnemies().isEmpty()) {
            Game.gamestate = "nextLevel";
            if (Game.getLevel() < 3) Game.setLevel(Game.getLevel() + 1);
            Game.reset(-Game.entityList.getBomberman().getTrace(), 0);
            new SoundManager("sound/win.wav", "win");
        }
    }
}