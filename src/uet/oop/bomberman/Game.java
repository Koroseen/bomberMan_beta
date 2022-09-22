package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Enemy;

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
            } else if (event.getCode() == KeyCode.SPACE && bomberman.bombs.isEmpty()){
                bomberman.placeBomb();
            }
        });
    }

    public void update() {
        EntityList.walls.forEach(Wall::update);
        if(Bomb.isFire()) EntityList.bricks.forEach(Brick::update);
        for(int i = 0; i < bomberman.bombs.size(); i++) bomberman.bombs.get(i).update();
        EntityList.enemies.forEach(Enemy::update);
        bomberman.update();
    }

    public void render(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityList.grasses.forEach(g -> g.render(gc));
        EntityList.walls.forEach(g -> g.render(gc));
        EntityList.bricks.forEach(g -> g.render(gc));
        EntityList.enemies.forEach(g -> g.render(gc));
        for(int i = 0; i < bomberman.bombs.size(); i++) bomberman.bombs.get(i).render(gc);
        for (int i = 0; i < EntityList.flames.size() && Bomb.isFire(); i++) EntityList.flames.get(i).render(gc);
        bomberman.render(gc);

    }
}
