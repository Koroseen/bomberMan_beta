package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private boolean allow = true;
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void collide() {
        for (Enemy enemy : Game.entityList.getEnemies()) {
            if (this.intersects(enemy)) {
                if (enemy.isAlive()) {
                    enemy.setAlive(false);
                    new SoundManager("sound/eat.wav","eat");
                }
            }
        }

        if (this.intersects(Game.entityList.getBomberman())) {
            Game.entityList.getBomberman().setAlive(false);
        }
    }
    @Override
    public void update() {
        collide();
    }
}