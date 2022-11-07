package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

public class Item extends Entity {
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    private int existTime = 1000;

    @Override
    public void update() {
    }

    public void checkExist(Item item){
        if(existTime > 0){
            existTime--;
        } else{
            Game.entityList.getItems().remove(item);
        }
    }
}
