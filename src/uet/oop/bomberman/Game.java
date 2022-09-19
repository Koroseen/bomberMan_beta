package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;

import static uet.oop.bomberman.entities.EntityList.bomberman;

public class Game extends Application {
    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Tao Canvas
        canvas = new Canvas(Settings.WIDTH, Settings.HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();

        Image image = new Image(new File("res/levels/background.png").toURI().toString());
        ImageView imageView = new ImageView(image);

        root.getChildren().add(imageView);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        createMap.createMapLevel(1);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                bomberman.goUp();
            } else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                bomberman.goDown();
            } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                bomberman.goLeft();
            } else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                bomberman.goRight();
            } else if (event.getCode() == KeyCode.SPACE){
                bomberman.placeBomb();
            }
        });
    }

    public void update() {
        EntityList.walls.forEach(Entity::update);
        EntityList.bricks.forEach(Entity::update);
        EntityList.bomberman.bombs.forEach(Bomb::update);
        EntityList.enemies.forEach(Enemy::update);
        EntityList.bomberman.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityList.walls.forEach(g -> g.render(gc));
        EntityList.bricks.forEach(g -> g.render(gc));
        EntityList.enemies.forEach(g -> g.render(gc));
        EntityList.bomberman.bombs.forEach(g -> g.render(gc));
        EntityList.bomberman.render(gc);

    }
}
