package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static int loop = 0;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 20;
    private GraphicsContext gc;
    private Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        //createMap();
        createMap.createMapLevel(1);

        Entity bomberman = new Bomber(1, 18, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W) {
                ((Bomber) bomberman).goUp();
            } else if (event.getCode() == KeyCode.S) {
                ((Bomber) bomberman).goDown();
            } else if (event.getCode() == KeyCode.A) {
                ((Bomber) bomberman).goLeft();
            } else if (event.getCode() == KeyCode.D) {
                ((Bomber) bomberman).goRight();
            } else {
                ((Bomber) bomberman).placeBomb();
            }
        });
    }

    public void update() {
        EntityList.walls.forEach(Wall::update);
        EntityList.grasses.forEach(Grass::update);
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityList.walls.forEach(g -> g.render(gc));
        EntityList.grasses.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
