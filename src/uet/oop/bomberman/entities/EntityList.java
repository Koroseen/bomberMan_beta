package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.blocks.*;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.items.Portal;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    private List<Wall> walls = new ArrayList<>();
    private List<Brick> bricks = new ArrayList<>();
    private List<Box> boxs = new ArrayList<>();
    private List<Tree> trees = new ArrayList<>();
    private List<Grass> grasses = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    private List<Item> items = new ArrayList<>();

    private List<Bomb> bombs = new ArrayList<>();

    private List<Flame> flames = new ArrayList<>();

    private Portal portal;

    private Bomber bomberman;

    public List<Wall> getWalls() {
        return walls;
    }

    public void addWalls(Wall wall) {
        walls.add(wall);
    }


    public List<Brick> getBricks() {
        return bricks;
    }

    public void addBricks(Brick brick) {
        bricks.add(brick);
    }

    public List<Box> getBoxs() {
        return boxs;
    }
    public void addBoxs(Box box) {
        boxs.add(box);
    }

    public List<Tree> getTrees() {
        return trees;
    }
    public void addTrees(Tree tree) {
        trees.add(tree);
    }

    public List<Grass> getGrasses() {
        return grasses;
    }

    public void addGrasses(Grass grass) {
        grasses.add(grass);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }


    public List<Item> getItems() {
        return items;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public List<Flame> getFlames() {
        return flames;
    }

    public void addFlame(Flame flame) {
        flames.add(flame);
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void setBombs(List<Bomb> bombs) {
        this.bombs = bombs;
    }

    public Portal getPortal() {
        return portal;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }

    public Bomber getBomberman() {
        return bomberman;
    }

    public void setBomberman(Bomber bomberman) {
        this.bomberman = bomberman;
    }

    public void clearList() {
        walls.clear();
        bricks.clear();
        enemies.clear();
        items.clear();
        grasses.clear();
    }
}
