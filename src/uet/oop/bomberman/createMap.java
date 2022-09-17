package uet.oop.bomberman;

import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;

public class createMap {
    public static void createMapLevel(int level) throws IOException {
        EntityList.clearList();

        //import tileset to array
        String path = "res/levels/level"+ level + ".txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        char[][] arr = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];
        for(int i = 0; i < BombermanGame.HEIGHT; i++) {
            line = bufferedReader.readLine();
            for(int j = 0; j < BombermanGame.WIDTH; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        bufferedReader.close();

        //map render
        for(int i = 0; i < BombermanGame.HEIGHT; i++) {
            for(int j = 0; j < BombermanGame.WIDTH; j++) {
                if(arr[i][j] == '6') {
                    EntityList.walls.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else if (arr[i][j] == '7') {
                    EntityList.grasses.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
        }
    }
}
