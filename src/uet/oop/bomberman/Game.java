package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.GUI.Menubutton;
import uet.oop.bomberman.GUI.audioScroller;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.*;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;

public class Game extends Application {
    public static EntityList entityList = new EntityList();
    public static ImageView nextLevel;
    public static ImageView startscreenView;

    private static int level = 2;
    public static String gamestate = " ";
    private static GraphicsContext gc;
    private static Canvas canvas;

    public static long time = 0;
    public static Font font = Font.loadFont("file:res/font/BOMBERMA.TTF", 19);
    public static long delaytime = 100;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lv) {
        level = lv;
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("BomberMan");
            Image icon = new Image("images/icon.png");
            stage.getIcons().add(icon);

            // Tao Canvas
            canvas = new Canvas(Settings.WIDTH, Settings.HEIGHT);
            canvas.setLayoutY(30);
            gc = canvas.getGraphicsContext2D();

            Image startscreen = new Image("images/author1.png");
            Image nextlevel = new Image("images/levelup.png");

            nextLevel = new ImageView(nextlevel);
            nextLevel.setX(0);
            nextLevel.setY(0);
            nextLevel.setFitHeight(Settings.HEIGHT);
            nextLevel.setFitWidth(Settings.WIDTH);

            startscreenView = new ImageView(startscreen);
            startscreenView.setX(0);
            startscreenView.setY(0);
            startscreenView.setFitHeight(Settings.HEIGHT);
            startscreenView.setFitWidth(Settings.WIDTH);

            // Tao root container
            Group root = new Group();
            Menu.createMenu(root);
            root.getChildren().add(canvas);
            root.getChildren().add(startscreenView);
            root.getChildren().add(nextLevel);
            Menu.createButton(root);
            audioScroller.slider(root);

            Scene scene = new Scene(root, 500, 500);
            stage.setScene(scene);
            stage.show();
            scene.setFill(Color.WHITE);
            new SoundManager("sound/start.wav", "title");
            SoundManager.updateSound();

            gamestate = "startmenu";
            AnimationTimer timer = new AnimationTimer() {
                final long start = System.currentTimeMillis();
                long before = 0;

                @Override
                public void handle(long l) {
                    if (gamestate.equals("startmenu")) {
                        SoundManager.updateSound();
                        Menubutton.update();
                    }

                    if (gamestate.equals("running")) {
                        render();
                        update();
                        long end = (System.currentTimeMillis() - start) / 1000;
                        if (end - before >= 1) Game.time++;
                        before = end;
                    }
                    if (gamestate.equals("pause")) {
                        startscreenView.setVisible(true);
                        Menubutton.update();
                        SoundManager.updateSound();
                    }

                    if (gamestate.equals("gameover")) {
                        //show gameover trong muc images + time
                        //reset game hoac show menu
                        if (delaytime > 0) {
                            delaytime--;
                            System.out.println(delaytime);
                        } else {
                            delaytime = 100;
                            gamestate = "startmenu";
                            Menubutton.update();
                        }
                    }
                    if (gamestate.equals("nextLevel")) {
                        //tao level
                        SoundManager.updateSound();
                        Menubutton.update();
                    }
                }
            };

            timer.start();

            // update bomberman position
            scene.setOnKeyPressed(event -> {
                if (event.getCode().toString().equals("ESCAPE")) {
                    if (gamestate.equals("running")) {
                        gamestate = "pause";
                    } else if (gamestate.equals("pause")) {
                        gamestate = "running";
                    }
                }
                if (gamestate.equals("running")) {
                    System.out.println("PressButton"+Game.time);
                    if (event.getCode().toString().equals("UP") ||
                            event.getCode().toString().equals("W")) {
                        entityList.getBomberman().goUp();
                    } else if (event.getCode().toString().equals("DOWN") ||
                            event.getCode().toString().equals("S")) {
                        entityList.getBomberman().goDown();
                    } else if (event.getCode().toString().equals("LEFT") ||
                            event.getCode().toString().equals("A")) {
                        entityList.getBomberman().goLeft();
                    } else if (event.getCode().toString().equals("RIGHT") ||
                            event.getCode().toString().equals("D")) {
                        entityList.getBomberman().goRight();
                    } else if (event.getCode().toString().equals("SPACE") && entityList.getBombs().isEmpty()) {
                        entityList.getBomberman().setBomb();
                    } else if (event.getCode().toString().equals("C")) {
                        for (int i = 0; i < entityList.getEnemies().size(); i++)
                            entityList.getEnemies().get(i).setAlive(false);
                    }
                }
            });
            //update bomberman sprites
            scene.setOnKeyReleased(event -> {
                if (event.getCode().toString().equals("LEFT")) {
                    entityList.getBomberman().setImg(Sprite.player_left.getFxImage());
                } else if (event.getCode().toString().equals("UP")) {
                    entityList.getBomberman().setImg(Sprite.player_up.getFxImage());
                } else if (event.getCode().toString().equals("RIGHT")) {
                    entityList.getBomberman().setImg(Sprite.player_right.getFxImage());
                } else if (event.getCode().toString().equals("DOWN")) {
                    entityList.getBomberman().setImg(Sprite.player_down.getFxImage());
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {
        Menubutton.update();
        SoundManager.updateSound();
        Menu.updateMenu();
        for (Wall wall : entityList.getWalls()) wall.update();
        for (Bomb bomb : entityList.getBombs()) bomb.update();
        for (int i = 0; i < entityList.getBricks().size(); i++) entityList.getBricks().get(i).update();
        for (int i = 0; i < entityList.getItems().size(); i++) entityList.getItems().get(i).update();
        for (int i = 0; i < entityList.getEnemies().size(); i++) entityList.getEnemies().get(i).update();
        entityList.getPortal().update();
        entityList.getBomberman().update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Grass grass : entityList.getGrasses()) grass.render(gc);
        for (Wall wall : entityList.getWalls()) wall.render(gc);
        for (Bomb bomb : entityList.getBombs()) {
            bomb.render(gc);
            if (bomb.isFire()) {
                bomb.explode();
                for (Flame flame : entityList.getFlames()) flame.render(gc);
            }
        }
        for (Item item : entityList.getItems()) item.render(gc);
        entityList.getPortal().render(gc);
        for (Brick brick : entityList.getBricks()) brick.render(gc);
        for (Enemy enemy : entityList.getEnemies()) enemy.render(gc);
        entityList.getBomberman().render(gc);
    }


    public static void moveCamera(int x, int y) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.translate(-x, -y);
    }

}
