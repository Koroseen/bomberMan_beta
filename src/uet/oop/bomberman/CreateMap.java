package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.entities.items.Portal;
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

    public static void importData(int stage) throws IOException {
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
        EntityList.clearList();
        //import tileset to array
        try {
            importData(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //map render
        for (int i = 0; i < Settings.WORLD_HEIGHT / Sprite.SCALED_SIZE; i++) {
            for (int j = 0; j < Settings.WORLD_WIDTH / Sprite.SCALED_SIZE; j++) {
                EntityList.grasses.add((new Grass(j, i, Sprite.grass.getFxImage())));
                switch (grid[i][j]) {
                    case 'p':
                        EntityList.bomberman = new Bomber(j, i, Sprite.player_down.getFxImage());
                        break;
                    case '#':
                        EntityList.walls.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        EntityList.bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case '1':
                        EntityList.enemies.add(new Ballom(j, i, Sprite.balloom_left1.getFxImage(), 1, 0, Enemy.enemyDir.UP));
                        break;
                    case '2':
                        EntityList.enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage(), 1, 100, Enemy.enemyDir.UP));
                        break;
                    case 'x':
                        EntityList.portal = new Portal(j, i, Sprite.portal.getFxImage());
                        EntityList.bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                }
            }
        }
    }
}
