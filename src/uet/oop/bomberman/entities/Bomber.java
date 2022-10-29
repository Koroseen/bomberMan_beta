package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int trace = 0;
    private boolean dieTime;
    private boolean isAlive;
    private int speed = Sprite.SCALED_SIZE / 8;
    private int speedItemDuration;
    private boolean hasTouchedSpeedItem = false;
    private boolean once = false;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        isAlive = true;
        dieTime = false;
    }

    public void setDieTime(boolean dieTime) {
        this.dieTime = dieTime;
    }

    public boolean isDieTime() {
        return dieTime;
    }

    public void setSpeedItemDuration(int speedItemDuration) {
        this.speedItemDuration = speedItemDuration;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setHasTouchedSpeedItem(boolean hasTouchedSpeedItem) {
        this.hasTouchedSpeedItem = hasTouchedSpeedItem;
    }

    @Override
    public void update() {
        if (isAlive) {
            for (Bomb bomb : Game.entityList.getBombs()) {
                if (!this.intersects(bomb) && bomb.isAllow()) {
                    bomb.setAllow(false);
                }
            }
            if (speedItemDuration > 0) {
                speedItemDuration--;
                if (!once) {
                    new SoundManager("sound/eat.wav", "eat");
                    speed += 2;
                    once = true;
                }
            } else {
                if (once) {
                    hasTouchedSpeedItem = false;
                    once = false;
                    speed -= 2;
                }
            }
        } else {
            Game.gamestate = "gameover";
            SoundManager.updateSound();
            //Game.delaytime = 300;
        }
    }

    public void goUp() {
        this.y -= speed;
        animate = animate > 100 ? 0 : animate + 1;
        if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) {
            this.y += speed;
            if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 4) {
                this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
            }
            if (this.x % Sprite.SCALED_SIZE >= 3 * Sprite.SCALED_SIZE / 4) {
                this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE + 1);
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 15).getFxImage());
    }

    public void goDown() {
        this.y += speed;
        animate = animate > 100 ? 0 : animate + 1;
        if (checkWall() || checkBrick() || checkBomb() || checkTree() || checkBox()) {
            this.y -= speed;
            if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 4) {
                this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
            }
            if (this.x % Sprite.SCALED_SIZE >= 3 * Sprite.SCALED_SIZE / 4) {
                this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE + 1);
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 15).getFxImage());
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
            if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 4) {
                this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
            }
            if (this.y % Sprite.SCALED_SIZE >= 3 * Sprite.SCALED_SIZE / 4) {
                this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE + 1);
            }
        }
        trace += count;
        Game.moveCamera(count, 0);
        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 15).getFxImage());
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
            if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 4) {
                this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
            }
            if (this.y % Sprite.SCALED_SIZE >= 3 * Sprite.SCALED_SIZE / 4) {
                this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE + 1);
            }
        }
        trace += count;
        Game.moveCamera(count, 0);
        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 15).getFxImage());
    }

    public void setBomb() {
        int x_ = this.x / Sprite.SCALED_SIZE;
        int y_ = this.y / Sprite.SCALED_SIZE;

        if (Game.entityList.getBombs().isEmpty()) {
            Game.entityList.addBomb(new Bomb(x_, y_, Sprite.bomb.getFxImage()));
            CreateMap.setGrid(y_, x_, 'b');
        }
    }
}
