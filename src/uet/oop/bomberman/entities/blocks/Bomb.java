package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.EntityList.bomberman;

public class Bomb extends Entity {
    private static int count;
    private static boolean fire = false;
    private static int x;
    private static int y;
    private static int upLimit = 0;
    private static int downLimit = 0;
    private static int leftLimit = 0;
    private static int rightLimit = 0;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        count = 0;
    }

    public static boolean isFire() {return fire;}
    public static void setBomb() {
        x = bomberman.getX() / Sprite.SCALED_SIZE;
        y = bomberman.getY() / Sprite.SCALED_SIZE;

        if(bomberman.bombs.isEmpty()) {
            bomberman.bombs.add(new Bomb(x, y, Sprite.bomb.getFxImage()));
            calcLimit();
        }
    }

    public static void calcLimit() {
        EntityList.flames.add(new Flame(x, y, Sprite.bomb_exploded2.getFxImage()));

        Flame flame = new Flame(x, y - upLimit - 1, Sprite.explosion_vertical_top_last2.getFxImage());
        boolean b = flame.checkWall() | flame.checkBrick();
        while (upLimit < 1 && !b) {
            upLimit++;
            EntityList.flames.add(flame);
            flame = new Flame(x, y - upLimit, Sprite.explosion_vertical_top_last2.getFxImage());
            b = flame.checkWall() | flame.checkBrick();
        }

        flame = new Flame(x, y + downLimit + 1, Sprite.explosion_vertical_down_last2.getFxImage());
        b = flame.checkWall() | flame.checkBrick();
        while (downLimit < 1 && !b) {
            downLimit++;
            EntityList.flames.add(flame);
            flame = new Flame(x, y + downLimit, Sprite.explosion_vertical_down_last2.getFxImage());
            b = flame.checkWall() | flame.checkBrick();
        }

        flame = new Flame(x - leftLimit - 1, y, Sprite.explosion_horizontal_left_last2.getFxImage());
        b = flame.checkWall() | flame.checkBrick();
        while (leftLimit < 1 && !b) {
            leftLimit++;
            EntityList.flames.add(flame);
            flame = new Flame(x - leftLimit, y, Sprite.explosion_horizontal_left_last2.getFxImage());
            b = flame.checkWall() | flame.checkBrick();
        }

        flame = new Flame(x + rightLimit + 1, y, Sprite.explosion_horizontal_right_last2.getFxImage());
        b = flame.checkWall() | flame.checkBrick();
        while (rightLimit < 1 && !b) {
            rightLimit++;
            EntityList.flames.add(flame);
            flame = new Flame(x + rightLimit, y, Sprite.explosion_horizontal_right_last2.getFxImage());
            b = flame.checkWall() | flame.checkBrick();
        }
    }

    @Override
    public void update() {
        int timeOut = 200;
        if(count < timeOut) {
            count++;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, count, 90).getFxImage());
        }
        else if (count < 250){
            count++;
            fire = true;
        }
        else {
            EntityList.flames.clear();
            bomberman.bombs.clear();
            EntityList.removeBrick();
            fire = false;
            upLimit = downLimit = leftLimit = rightLimit = 0;
        }
    }
}
