package uet.oop.bomberman;

import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;

public class CreateMap {
    private static char[][] grid = new char[Settings.MAX_ROW][Settings.MAX_COL];

    public static void setGrid(int row, int col, char c) {
        CreateMap.grid[row][col] = c;
    }

    public static char[][] getGrid() {
        return grid;
    }

    public static void importData(char[][] arr, int stage) throws IOException {
        String path = "res/levels/level" + stage + ".txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        line = bufferedReader.readLine();

        for (int i = 0; i < Settings.MAX_ROW; i++) {
            line = bufferedReader.readLine();
            for (int j = 0; j < Settings.MAX_COL; j++) {
                arr[i][j] = line.charAt(j);
            }
        }
        bufferedReader.close();
    }

    public static void createMapLevel(int level) throws IOException {
        EntityList.clearList();
        //import tileset to array
        importData(grid, level);
        //map render
        for (int i = 0; i < Settings.MAX_ROW; i++) {
            for (int j = 0; j < Settings.MAX_COL; j++) {
                EntityList.grasses.add((new Grass(j, i, Sprite.grass.getFxImage())));
                switch (grid[i][j]) {
                    case '*':
                        EntityList.walls.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '#':
                        EntityList.bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case '1':
                        EntityList.enemies.add(new Ballom(j, i, Sprite.balloom_left1.getFxImage(), 1, 0, Enemy.enemyDir.DOWN));
                        break;
                    case '2':
                        EntityList.enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage(), 1, 100, Enemy.enemyDir.UP));
                        break;
                }
            }
        }
    }
}
