package uet.oop.bomberman;

import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;

public class createMap {
    public static void createMapLevel(int level) throws Exception {
        EntityList.clearList();
        switch (level) {
            case 1:
            //import tileset to array
            String path = "res/levels/level" + level + ".txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            char[][] arr = new char[Settings.MAX_ROW][Settings.MAX_COL];
            for (int i = 0; i < Settings.MAX_ROW; i++) {
                line = bufferedReader.readLine();
                for (int j = 0; j < Settings.MAX_COL; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }

            bufferedReader.close();

            //map render
            for (int i = 0; i < Settings.MAX_ROW; i++) {
                for (int j = 0; j < Settings.MAX_COL; j++) {
                    switch (arr[i][j]) {
                        case '6':
                            EntityList.walls.add(new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        case '8':
                            EntityList.bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        default:
                            break;
                    }
                }
            }

            EntityList.enemies.add(new Ballom(15, 10, Sprite.balloom_left1.getFxImage(), 1, 5, Enemy.enemyDir.UP));
            break;

            case 2:
                break;
        }
    }
}
