package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Flame;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    public static List<Wall> walls = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Grass> grasses = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Flame> flames = new ArrayList<>();

    public static Bomber bomberman = new Bomber(11, 11, Sprite.player_right.getFxImage());

    public static void removeBrick() {
        for (int i = 0; i < EntityList.bricks.size(); i++) {
            if (EntityList.bricks.get(i).isBroken()) {
                EntityList.bricks.remove(i);
            }
        }
    }
    public static void clearList() {
        walls.clear();
        bricks.clear();
    }
}
