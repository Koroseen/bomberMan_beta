package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private boolean up, down, left, right, set;
    public static EntityList entityList = new EntityList();
    public static ImageView nextLevel;
    public static ImageView startscreenView;
    public static ImageView gameOver;
    public static ImageView win;

    private static int level = 1;
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

            nextLevel = new ImageView(new Image("images/levelup.png"));
            nextLevel.setX(0);
            nextLevel.setY(0);
            nextLevel.setFitHeight(Settings.HEIGHT);
            nextLevel.setFitWidth(Settings.WIDTH);

            startscreenView = new ImageView(new Image("images/author1.png"));
            startscreenView.setX(0);
            startscreenView.setY(0);
            startscreenView.setFitHeight(Settings.HEIGHT);
            startscreenView.setFitWidth(Settings.WIDTH);

            gameOver = new ImageView(new Image("images/gameover.png"));
            gameOver.setX(0);
            gameOver.setY(0);
            gameOver.setFitHeight(Settings.HEIGHT);
            gameOver.setFitWidth(Settings.WIDTH);

            // Tao root container
            Group root = new Group();
            Menu.createMenu(root);
            root.getChildren().add(canvas);
            root.getChildren().add(startscreenView);
            root.getChildren().add(nextLevel);
            root.getChildren().add(gameOver);
            Menu.createButton(root);
            audioScroller.slider(root);

            Scene scene = new Scene(root, 500, 500);

            //scene for win
            Text click = new Text("Click any button to continue");
            Font font = Font.loadFont("file:res/font/test.ttf", 20);
            click.setFont(font);
            click.setFill(Color.WHITE);
            click.setX(75);
            click.setY(400);

            Group rootWin = new Group();
            rootWin.getChildren().addAll(Menu.info, click);
            Scene win = new Scene(rootWin, 500, 500);
            win.setFill(Color.BLACK);
            final int[] effect = {0};

            stage.setScene(win);
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
                        update();
                        render();
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
                        delaytime--;
                        if (delaytime > 0 && delaytime < 70) {
                            gameOver.setVisible(true);
                        } else if (delaytime == 0) {
                            gamestate = "startmenu";
                            delaytime = 100;
                            Menubutton.update();
                        }
                    }
                    if (gamestate.equals("nextLevel")) {
                        //tao level
                        SoundManager.updateSound();
                        Menubutton.update();
                    }
                    if (gamestate.equals("win")) {
                        delaytime--;
                        if (delaytime > 30 && delaytime < 90) {
                            stage.setScene(win);
                            click.setVisible(false);
                            Menu.info.setText("You Won!!!");
                        } else {
                            click.setVisible(true);
                            Menu.info.setText("Your Score is " + Menu.getScore());
                            Menu.info.setX(150);
                            effect[0]++;
                            if (effect[0] > 30 && effect[0] < 60) {
                                Menu.info.setVisible(false);
                            } else if (effect[0] == 60) {
                                Menu.info.setVisible(true);
                                effect[0] = 0;
                            }
                        }
                    }
                }
            };

            timer.start();

            // update bomberman position
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP:
                        up = true;
                        break;
                    case DOWN:
                        down = true;
                        break;
                    case LEFT:
                        left = true;
                        break;
                    case RIGHT:
                        right = true;
                        break;
                    case SPACE:
                        entityList.getBomberman().setBomb();
                        break;
                    case ESCAPE:
                        if (gamestate.equals("running")) gamestate = "pause";
                        else if (gamestate.equals("pause")) {
                            if (!Menubutton.Setting) Menubutton.Setting = true;
                            else gamestate = "running";
                        }
                        break;
                    case C:
                        for (Enemy enemy : Game.entityList.getEnemies()) enemy.setAlive(false);
                }
            });
            scene.setOnKeyReleased(event -> {
                switch (event.getCode()) {
                    case UP:
                        up = false;
                        entityList.getBomberman().setImg(Sprite.player_down.getFxImage());
                        break;
                    case DOWN:
                        down = false;
                        entityList.getBomberman().setImg(Sprite.player_down.getFxImage());
                        break;
                    case LEFT:
                        left = false;
                        entityList.getBomberman().setImg(Sprite.player_left.getFxImage());
                        break;
                    case RIGHT:
                        right = false;
                        entityList.getBomberman().setImg(Sprite.player_right.getFxImage());
                        break;
                    case SPACE:
                        set = false;
                        break;
                }
            });

            //keypress for win scene
            win.setOnKeyPressed(keyEvent -> {
                delaytime = 100;
                Game.reset(-Game.entityList.getBomberman().getTrace(), 0);
                Menu.setScore(0);
                gamestate = "startmenu";
                stage.setScene(scene);
            });
            AnimationTimer count = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (up) entityList.getBomberman().goUp();
                    if (down) entityList.getBomberman().goDown();
                    if (left) entityList.getBomberman().goLeft();
                    if (right) entityList.getBomberman().goRight();
                }
            };
            count.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {
        Menubutton.update();
        SoundManager.updateSound();
        Menu.updateMenu();
        for (Wall wall : entityList.getWalls()) wall.update();
        for (int i = 0; i < entityList.getBombs().size(); i++) entityList.getBombs().get(i).update();
        entityList.getPortal().update();
        entityList.getBomberman().update();
        for (int i = 0; i < entityList.getBricks().size(); i++) entityList.getBricks().get(i).update();
        for (int i = 0; i < entityList.getTrees().size(); i++) entityList.getTrees().get(i).update();
        for (int i = 0; i < entityList.getBoxs().size(); i++) entityList.getBoxs().get(i).update();
        for (int i = 0; i < entityList.getItems().size(); i++) entityList.getItems().get(i).update();
        for (int i = 0; i < entityList.getEnemies().size(); i++) entityList.getEnemies().get(i).update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Grass grass : entityList.getGrasses()) grass.render(gc);
        for (Wall wall : entityList.getWalls()) wall.render(gc);
        entityList.getPortal().render(gc);
        for (Box box : entityList.getBoxs()) box.render(gc);
        for (Bomb bomb : entityList.getBombs()) {
            bomb.render(gc);
            if (bomb.isFire()) {
                bomb.explode();
                for (Flame flame : entityList.getFlames()) flame.render(gc);
            }
        }
        entityList.getBomberman().render(gc);
        for (Item item : entityList.getItems()) item.render(gc);
        for (Brick brick : entityList.getBricks()) brick.render(gc);
        for (Enemy enemy : entityList.getEnemies()) enemy.render(gc);
        for (Tree tree : entityList.getTrees()) tree.render(gc);
        entityList.getBomberman().render(gc);

    }

    public static void moveCamera(int x, int y) {
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.translate(-x, -y);
    }

    public static void reset(int x, int y) {
        gc.translate(-x, -y);
    }
}
