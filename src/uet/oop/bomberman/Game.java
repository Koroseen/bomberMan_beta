package uet.oop.bomberman;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.entities.EntityList.bomberman;

public class Game extends Application {
    private static String gamestate=" ";
    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("BomberMan");
        // Tao Canvas
        canvas = new Canvas(Settings.WIDTH, Settings.HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        scene.setFill(Color.WHITE);

        CreateMap.createMapLevel(2);

        //Menu.createMenu(root);

        gamestate="running";
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(gamestate.equals("startmenu")){
                    showMenu(root);
                }
                if(gamestate.equals("running")) {
                    render();
                    update();
                }
            }
        };
        timer.start();

        scene.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("UP")) {
                bomberman.goUp();
            } else if (event.getCode().toString().equals("DOWN")) {
                bomberman.goDown();
            } else if (event.getCode().toString().equals("LEFT")) {
                bomberman.goLeft();
            } else if (event.getCode().toString().equals("RIGHT")) {
                bomberman.goRight();
            } else if (event.getCode().toString().equals("SPACE") && bomberman.bombs.isEmpty()) {
                bomberman.placeBomb();
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals("LEFT")) {
                bomberman.setImg(Sprite.player_left.getFxImage());
            } else if (event.getCode().toString().equals("UP")){
                bomberman.setImg(Sprite.player_up.getFxImage());
            } else if (event.getCode().toString().equals("RIGHT")) {
                bomberman.setImg(Sprite.player_right.getFxImage());
            } else if (event.getCode().toString().equals("DOWN")){
                bomberman.setImg(Sprite.player_down.getFxImage());
            }
        });
    }

    public void update() {
        EntityList.walls.forEach(Wall::update);
        if (Bomb.isFire()) EntityList.bricks.forEach(Brick::update);
        for (int i = 0; i < EntityList.items.size(); i++) EntityList.items.get(i).update();
        for (int i = 0; i < bomberman.bombs.size(); i++) bomberman.bombs.get(i).update();
        bomberman.update();
        EntityList.enemies.forEach(Enemy::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityList.grasses.forEach(g -> g.render(gc));
        EntityList.walls.forEach(g -> g.render(gc));
        EntityList.bricks.forEach(g -> g.render(gc));
        for (int i = 0; i < EntityList.items.size(); i++) EntityList.items.get(i).render(gc);
        EntityList.enemies.forEach(g -> g.render(gc));
        for (int i = 0; i < bomberman.bombs.size(); i++) bomberman.bombs.get(i).render(gc);
        for (int i = 0; i < EntityList.flames.size() && Bomb.isFire(); i++) EntityList.flames.get(i).render(gc);
        bomberman.render(gc);
    }

    public void showMenu(Group root){
    }
}

