package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private boolean allow = true;
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        for (int i = 0; i < EntityList.enemies.size(); i++) {
            if (Bomb.isFire() && this.intersects(EntityList.enemies.get(i))) {
                EntityList.enemies.get(i).setAlive(false);
                if (allow) {
                    Menu.Score += 100;
                    allow = false;
                    new SoundManager("sound/eat.wav","eat");
                }
            }
        }

        if (Bomb.isFire() && this.intersects(EntityList.bomberman)) {
            EntityList.bomberman.setImg( Sprite.player_dead1.getFxImage());
            EntityList.bomberman.setDieTime(true);
        }
    }
}