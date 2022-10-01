package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;

public class Settings {
    //Canvas setting
    public static final int MAX_ROW = 15;
    public static final int MAX_COL = 29;
    public static final int WIDTH = MAX_COL * Sprite.SCALED_SIZE;
    public static final int HEIGHT = MAX_ROW * Sprite.SCALED_SIZE+30;
}
