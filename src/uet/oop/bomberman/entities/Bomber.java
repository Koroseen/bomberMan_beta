package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int trace = 0;
    private boolean isAlive;
    private int speed = 1;
    private final int bomblimit = 3;
    private int buffItem = 0;
    private int speedUpDuration = 500;
    private boolean speedUpTouched = false;
    private int flameDuration = 3000;
    private boolean flameTouched = false;
    private int bombs = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        isAlive = true;
    }
    public void addBomb() {
        bombs = bombs < bomblimit ? bombs + 1 : bombs;
    }
    public int getBombs() {return bombs;}
    public void setAlive(boolean b) {this.isAlive = b;}

    public int getTrace() {
        return trace;
    }

    public void setSpeedUpTouched(boolean b) {this.speedUpTouched = b;}
    public void setFlameTouched(boolean b) {this.flameTouched = b;}

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
                flameTouched = false;
                Bomb.setRadius(1);
                flameDuration = 3000;
            }
        } else {
            if (Menu.getLives() > 0) {
                Menu.setLives(Menu.getLives() - 1);
                isAlive = true;
            }
            if (Menu.getLives() == 0) {
                Game.gamestate = "gameover";
                SoundManager.updateSound();
//                Game.reset(-Game.entityList.getBomberman().getTrace(), 0);
//                Menu.setScore(0);
                //Game.delaytime = 300;
            }
            Bomb.setRadius(1);
//            this.bomblimit = 1;
            setImg(Sprite.player_dead1.getFxImage());
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
        if (bombs > 0) {
            Game.entityList.addBomb(new Bomb(x_, y_, Sprite.bomb.getFxImage()));
            bombs--;
        }
    }
}
