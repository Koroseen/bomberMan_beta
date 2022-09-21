package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Flame;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected int animate;
    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Rectangle2D getRect() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity entity) {
        return this.getRect().intersects(entity.getRect());
    }
    public boolean contains(Entity entity) {
        return this.getRect().contains(entity.getRect());
    }
    public boolean checkWall() {
        for (Wall wall : EntityList.walls) {
            if (this.intersects(wall)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBrick() {
        for(int i = 0; i < EntityList.bricks.size(); i++) {
            if(this.equals(EntityList.bricks.get(i))) {
                if (this instanceof Flame) {
                    Brick brick = EntityList.bricks.get(i);
                    brick.setBroken(true);
                }
                return true;
            }
        }
        return false;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}
