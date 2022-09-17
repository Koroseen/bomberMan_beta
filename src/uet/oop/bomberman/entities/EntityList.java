package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    public static List<Wall> walls = new ArrayList<>();
    public static List<Grass> grasses = new ArrayList<>();

    public static void clearList() {
        walls.clear();
        grasses.clear();
    }
}
