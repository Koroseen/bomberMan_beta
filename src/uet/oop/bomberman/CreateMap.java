package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.*;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.enemies.Kondoria;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.Set;

public class CreateMap {
    private static char[][] grid;

    public static void setGrid(int row, int col, char c) {
        CreateMap.grid[row][col] = c;
    }

    public static char[][] getGrid() {
        return grid;
    }

    private static void importData(int stage) throws IOException {
        String path = "res/levels/level" + stage + ".txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = bufferedReader.readLine();
        String[] tmp = line.split(" ");
        Settings.WORLD_HEIGHT = Integer.parseInt(tmp[1]) * Sprite.SCALED_SIZE;
        Settings.WORLD_WIDTH = Integer.parseInt(tmp[2]) * Sprite.SCALED_SIZE;
        grid = new char[Settings.WORLD_HEIGHT / Sprite.SCALED_SIZE][Settings.WORLD_WIDTH / Sprite.SCALED_SIZE];
        for (int i = 0; i < Settings.WORLD_HEIGHT / Sprite.SCALED_SIZE; i++) {
            line = bufferedReader.readLine();
            for (int j = 0; j < Settings.WORLD_WIDTH / Sprite.SCALED_SIZE; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        bufferedReader.close();
    }

    public static void createMapLevel(int level) {
        Game.entityList.clearList();
        //import tileset to array
        try {
            importData(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //map render
        for (int i = 0; i < Settings.WORLD_HEIGHT / Sprite.SCALED_SIZE; i++) {
            for (int j = 0; j < Settings.WORLD_WIDTH / Sprite.SCALED_SIZE; j++) {
                Game.entityList.addGrasses((new Grass(j, i, Sprite.grass.getFxImage())));
                switch (grid[i][j]) {
                    case 'p':
                        Game.entityList.setBomberman(new Bomber(j, i, Sprite.player_down.getFxImage()));
                        break;
                    case '#':
                        Game.entityList.addWalls((new Wall(j, i, Sprite.wall.getFxImage())));
                        break;
                    case '*':
                        //randomly create a block between box, tree and brick
                        int x = (int) (Math.random() * 3) + 1;
                        if(x == 1) {
                            Game.entityList.addBricks((new Brick(j, i, Sprite.brick.getFxImage())));
                            break;
                        }else if(x == 2){
                            Game.entityList.addTrees((new Tree(j, i, Sprite.tree.getFxImage())));
                            break;
                        }else if(x == 3){
                            Game.entityList.addBoxs((new Box(j, i, Sprite.box.getFxImage())));
                            break;
                        }
                    case '1':
                        Game.entityList.addEnemies((new Ballom(j, i, Sprite.balloom_left1.getFxImage(), 1, 0, Enemy.enemyDir.UP)));
                        break;
                    case '2':
                        Game.entityList.addEnemies((new Oneal(j, i, Sprite.oneal_left1.getFxImage(), 1, 100, Enemy.enemyDir.UP)));
                        break;
                    case '3':
                        Game.entityList.addTrees((new Tree(j, i, Sprite.tree.getFxImage())));
                        Game.entityList.addEnemies((new Kondoria(j, i, Sprite.kondoria_left1.getFxImage(), 1, 200, Enemy.enemyDir.UP)));
                        break;
                    case 'x':
                        Game.entityList.setPortal(new Portal(j, i, Sprite.portal.getFxImage()));
                        Game.entityList.addBricks((new Brick(j, i, Sprite.brick.getFxImage())));
                        break;
                    case 's':
                        Game.entityList.addItems(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        Game.entityList.addBricks(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                }
            }
        }
    }
}
