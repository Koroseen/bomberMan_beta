package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomb extends Entity {
    public static int count;
    private boolean fire = false;
    private int radius = 1;
    private boolean allow;


    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        Bomb.count = 0;
        allow = true;
        fire = false;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean x) {
        allow = x;
    }


    public void explode() {
        int c = this.x / Sprite.SCALED_SIZE;
        int r = this.y / Sprite.SCALED_SIZE;

        Game.entityList.addFlame(new Flame(c, r, Sprite.bomb_exploded2.getFxImage()));

        for (int i = 1; i <= this.radius; i++) {
            Flame flame = new Flame(c + i, r, Sprite.explosion_horizontal_right_last2.getFxImage());
            boolean bool = flame.checkBrick() | flame.checkWall();
            if (!flame.checkBrick() && !flame.checkWall() && !flame.checkBox() && !flame.checkTree()) {
                Game.entityList.addFlame(flame);
            }

            flame = new Flame(c - i, r, Sprite.explosion_horizontal_left_last2.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall() && !flame.checkBox() && !flame.checkTree()) {
                Game.entityList.addFlame(flame);
            }

            flame = new Flame(c, r + i, Sprite.explosion_vertical_down_last2.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall() && !flame.checkBox() && !flame.checkTree()) {
                Game.entityList.addFlame(flame);
            }

            flame = new Flame(c, r - i, Sprite.explosion_vertical_top_last2.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall() && !flame.checkBox() && !flame.checkTree()) {
                Game.entityList.addFlame(flame);
            }
        }
    }


    @Override
    public void update() {
        int timeOut = 250;
        int row = this.y / Sprite.SCALED_SIZE;
        int col = this.x / Sprite.SCALED_SIZE;
        if (count < timeOut) {
            count++;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, count, 90).getFxImage());
        } else if (count < 300) {
            count++;
            fire = true;
            for (Flame flame : Game.entityList.getFlames()) flame.update();
            if (CreateMap.getGrid()[row][col] != ' ') CreateMap.setGrid(row, col, ' ');
        } else {
            Game.entityList.setBombs(new ArrayList<>());
            Game.entityList.getFlames().clear();
            fire = false;
        }
        if (count == 250) new SoundManager("sound/boom.wav", "explosion");
    }
}
