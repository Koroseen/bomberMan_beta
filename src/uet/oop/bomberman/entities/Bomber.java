package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int trace = 0;
    private boolean isAlive;
    private int speed = 1;
    private int bomblimit = 1;
    private int buffItem = 0;

    private int speedUpDuration = 500;
    private boolean speedUpTouched = false;
    private int flameDuration = 500;
    private boolean flameTouched = false;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        isAlive = true;
    }
    public void setAlive(boolean b) {this.isAlive = b;}

    public int getTrace() {
        return trace;
    }

    public void setSpeedUpTouched(boolean b) {this.speedUpTouched = b;}
    public void setFlameTouched(boolean b) {this.flameTouched = b;}
    public void increaseBomb() {
        this.bomblimit++;
    }

    public int getBomblimit() {
        return bomblimit;
    }

    public void setBomblimit(int bomblimit) {
        this.bomblimit = bomblimit;
    }

    public void increaseBuffItem() {
        this.buffItem++;
    }

    public int getBuffItem() {
        return buffItem;
    }

    @Override
    public void update() {
        if (isAlive) {
            for (Bomb bomb : Game.entityList.getBombs()) {
                if (!this.intersects(bomb) && bomb.isAllow()) {
                    bomb.setAllow(false);
                }
            }
            if (speedUpDuration > 0 && speedUpTouched) {
                this.speed = 2;
                speedUpDuration--;
            } else {
                speedUpTouched = false;
                this.speed = 1;
                speedUpDuration = 500;
            }

            if (flameDuration > 0 && flameTouched) {
                Bomb.setRadius(2);
                flameDuration--;
            } else {
                Bomb.setRadius(1);
                flameDuration = 500;
            }
        } else {
            Bomb.setRadius(1);
            this.bomblimit = 1;
            setImg(Sprite.player_dead1.getFxImage());
            Game.gamestate = "gameover";
            SoundManager.updateSound();
        }
    }

    public void goUp() {
        this.y -= speed;
        animate = animate > 100 ? 0 : animate + 1;
        if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) {
            this.y += speed;
        }
        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 60).getFxImage());
    }

    public void goDown() {
        this.y += speed;
        animate = animate > 100 ? 0 : animate + 1;
        if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) {
            this.y -= speed;
        }
        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 60).getFxImage());
    }

    public void goLeft() {
        int count = 0;
        if (this.x - trace > Settings.WIDTH / 2 || this.x <= Settings.WIDTH / 2) this.x -= speed;
        else {
            count -= speed;
            this.x -= speed;
        }
        animate = animate > 100 ? 0 : animate + 1;
        if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) {
            if (this.x - trace > Settings.WIDTH / 2 || this.x <= Settings.WIDTH / 2) this.x += speed;
            else {
                count += speed;
                this.x += speed;
            }
        }
        trace += count;
        Game.moveCamera(count, 0);
        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 60).getFxImage());
    }

    public void goRight() {
        int count = 0;
        if (this.x - trace < Settings.WIDTH / 2 || this.x >= Settings.WORLD_WIDTH - Settings.WIDTH / 2) this.x += speed;
        else {
            count += speed;
            this.x += speed;
        }
        animate = animate > 100 ? 0 : animate + 1;
        if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) {
            if (this.x - trace < Settings.WIDTH / 2 || this.x >= Settings.WORLD_WIDTH - Settings.WIDTH / 2)
                this.x -= speed;
            else {
                count -= speed;
                this.x -= speed;
            }
        }
        trace += count;
        Game.moveCamera(count, 0);
        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 60).getFxImage());
    }

    public void setBomb() {
        int x_ = this.x / Sprite.SCALED_SIZE;
        int y_ = this.y / Sprite.SCALED_SIZE;
        if (Game.entityList.getBombs().size() < Game.entityList.getBomberman().getBomblimit()) {
            Game.entityList.addBomb(new Bomb(x_, y_, Sprite.bomb.getFxImage()));
            CreateMap.setGrid(y_, x_, 'b');
        }
    }
}
