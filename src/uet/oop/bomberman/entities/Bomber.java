package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.EntityList.bomberman;

public class Bomber extends Entity {

    private final boolean isAlive = true;
    private final int speed = Sprite.SCALED_SIZE / 8;
    public List<Bomb> bombs = new ArrayList<>();

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {}
    public void goUp() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkWall() || checkBrick()) {
                this.y += 1;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 60).getFxImage());
    }

    public void goDown() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkWall() || checkBrick()) {
                this.y -= 1;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 60).getFxImage());
    }

    public void goLeft() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkWall() || checkBrick()) {
                this.x += 1;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 60).getFxImage());
    }

    public void goRight() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            if(animate > 100) {
                animate = 0;
            }
            animate++;
            if (checkWall() || checkBrick()) {
                this.x -= 1;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 60).getFxImage());
    }

    public void placeBomb() {
        Bomb.setBomb();
    }
}
