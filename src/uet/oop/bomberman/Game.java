package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.GUI.audioScroller;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;

import static javafx.stage.StageStyle.*;
import static uet.oop.bomberman.entities.EntityList.bomberman;

public class Game extends Application {
    ImageView nextlevel;
    static ImageView gameover;
    static ImageView startscreenView;
    static ImageView continueGame;
    static ImageView homeScreen;
    static ImageView playbutton;
    static ImageView settings;
    static ImageView gamemodeHard;
    static ImageView gamemodeEasy;
    static ImageView gamemodeMedium;

    private static int level = 1;
    public static String gamestate = " ";
    private static GraphicsContext gc;
    private static Canvas canvas;
    public static long time = 120;
    public static long delaytime = 100;
    public boolean checkplay = true;
    public boolean checksetting = true;

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
    public void start(Stage stage) throws Exception {
        stage = new Stage(DECORATED);
        stage.setTitle("BomberMan");
        Image icon = new Image("images/icon.png");
        stage.getIcons().add(icon);

        // Tao Canvas
        canvas = new Canvas(Settings.WIDTH, Settings.HEIGHT);
        canvas.setLayoutY(30);
        gc = canvas.getGraphicsContext2D();

        Image playButton = new Image("images/New game Button.png");
        Image startscreen = new Image("images/author1.png");
        Image Gamemode = new Image("images/author1.png");
        Image setting = new Image("images/Settings button.png");
        Image continuegame = new Image("images/Continue Button.png");
        Image homescreen = new Image("images/Menu Button.png");
        Image gameOver = new Image("images/gameover.png");
        Image nextLevel = new Image("images/levelup.png");

        nextlevel = new ImageView(nextLevel);
        nextlevel.setX(0);
        nextlevel.setY(0);
        nextlevel.setFitHeight(Settings.HEIGHT);
        nextlevel.setFitWidth(Settings.WIDTH);

        gameover = new ImageView(gameOver);

        homeScreen = new ImageView(homescreen);

        continueGame = new ImageView(continuegame);

        startscreenView = new ImageView(startscreen);

        playbutton = new ImageView(playButton);

        //gamemode
        gamemodeHard = new ImageView(Gamemode);

        gamemodeMedium = new ImageView(Gamemode);

        gamemodeEasy = new ImageView(Gamemode);

        //setting
        settings = new ImageView(setting);

        resolution.setResolution(stage);
        //effect
        Glow glow = new Glow();
        glow.setLevel(0.9);
        playbutton.setEffect(glow);
        settings.setEffect(glow);
        homeScreen.setEffect(glow);
        continueGame.setEffect(glow);

        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);

        //Menu.createMenu(root);
        Menu.createMenu(root);
        root.getChildren().add(startscreenView);
        root.getChildren().add(playbutton);
        root.getChildren().add(gamemodeHard);
        root.getChildren().add(gamemodeEasy);
        root.getChildren().add(gamemodeMedium);
        root.getChildren().add(settings);
        root.getChildren().add(continueGame);
        root.getChildren().add(homeScreen);
        root.getChildren().add(gameover);
        root.getChildren().add(nextlevel);

        nextlevel.setVisible(false);
        gameover.setVisible(false);
        homeScreen.setVisible(false);
        continueGame.setVisible(false);
        gamemodeEasy.setVisible(false);
        gamemodeMedium.setVisible(false);
        gamemodeHard.setVisible(false);

        audioScroller.slider(root);
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
        scene.setFill(Color.WHITE);

//        CreateMap.createMapLevel(level);

        new SoundManager("sound/start.wav", "title");
        SoundManager.updateSound();

        gamestate = "startmenu";
        AnimationTimer timer = new AnimationTimer() {
            long start = System.currentTimeMillis();
            long before = 0;
            int glowtime = 0;

            @Override
            public void handle(long l) {
                glowtime++;
                if (glowtime % 50 == 0) {
                    if (glow.getLevel() == 0.9) {
                        glow.setLevel(0.2);
                    } else {
                        glow.setLevel(0.9);
                    }
                }
                    if (gamestate.equals("startmenu")) {
                        SoundManager.updateSound();
                        showMenu();
                    }
                    if (gamestate.equals("running")) {
                        render();
                        update();
                        long end = (System.currentTimeMillis() - start) / 1000;
                        if (end - before == 1) Game.time--;
                        before = end;
                    }
                    if (gamestate.equals("pause")) {
                        startscreenView.setVisible(true);
                        continueGame.setVisible(true);
                        homeScreen.setVisible(true);
                    }
                    if (gamestate.equals("gameover")) {
                        //show gameover trong muc images + time
                        gameover.setVisible(true);
                        homeScreen.setVisible(true);
                        //reset game hoac show menu
                        if (delaytime > 0) {
                            delaytime--;
                            System.out.println(delaytime);
                        } else {
                            delaytime = 100;
                            gamestate = "startmenu";
                        }
                    }
                    if (gamestate.equals("nextLevel")) {
                        //tao level
                        nextlevel.setVisible(true);
                        SoundManager.updateSound();
                        if (delaytime > 0) delaytime--;
                        else {
                            delaytime = 100;
                            nextlevel.setVisible(false);
                            level++;
                            CreateMap.createMapLevel(level);
                            gamestate = "running";
                        }
                    }
                }
            }

            ;

        timer.start();

        scene.setOnKeyPressed(event ->

            {
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

        scene.setOnKeyReleased(event ->

            {
                if (event.getCode().toString().equals("LEFT")) {
                    bomberman.setImg(Sprite.player_left.getFxImage());
                } else if (event.getCode().toString().equals("UP")) {
                    bomberman.setImg(Sprite.player_up.getFxImage());
                } else if (event.getCode().toString().equals("RIGHT")) {
                    bomberman.setImg(Sprite.player_right.getFxImage());
                } else if (event.getCode().toString().equals("DOWN")) {
                    bomberman.setImg(Sprite.player_down.getFxImage());
                } else if (event.getCode().toString().equals("C")) {
                    for (int i = 0; i < EntityList.enemies.size(); i++) EntityList.enemies.get(i).setAlive(false);
                }
            });

        playbutton.setOnMouseClicked(event ->

            {
                //render game mode va chon
                if (checkplay) {
                    glow.setLevel(0.2);
                    playbutton.setVisible(false);

                    Glow glow1 = new Glow(0.9);
                    gamemodeMedium.setEffect(glow1);
                    gamemodeEasy.setEffect(glow1);
                    gamemodeHard.setEffect(glow1);
                    gamemodeEasy.setVisible(true);
                    gamemodeMedium.setVisible(true);
                    gamemodeHard.setVisible(true);
                    settings.setVisible(false);
                    checkplay = false;
                } else {
                    gamemodeEasy.setVisible(false);
                    gamemodeMedium.setVisible(false);
                    gamemodeHard.setVisible(false);
                    settings.setVisible(true);
                    checkplay = true;
                }
            });

            //chon gamemode
        gamemodeHard.setOnMouseClicked(event ->

            {
                checkplay = true;
                hideMenu();
                gamestate = "running";
                glow.setLevel(0.9);
            });

        gamemodeMedium.setOnMouseClicked(event ->

            {
                checkplay = true;
                hideMenu();
                CreateMap.createMapLevel(2);
                gamestate = "running";
                glow.setLevel(0.9);
            });

        gamemodeEasy.setOnMouseClicked(event ->

            {
                checkplay = true;
                hideMenu();
                CreateMap.createMapLevel(1);
                gamestate = "running";
            });

        continueGame.setOnMouseClicked(event ->
            {
                startscreenView.setVisible(false);
                continueGame.setVisible(false);
                homeScreen.setVisible(false);
                gamestate = "running";
            });

        homeScreen.setOnMouseClicked(event ->
            {
                showMenu();
                gamestate = "startmenu";
                checkplay = true;
            });

        settings.setOnMouseClicked(Event ->

            {
                if (checksetting) {
                    settings.setLayoutY(210);
                    playbutton.setVisible(false);
                    checksetting = false;
                    audioScroller.slider.setVisible(true);
                    audioScroller.label.setVisible(true);
                    audioScroller.l.setVisible(true);
                } else {
                    settings.setLayoutY(260);
                    playbutton.setVisible(true);
                    checksetting = true;
                    audioScroller.slider.setVisible(false);
                    audioScroller.label.setVisible(false);
                    audioScroller.l.setVisible(false);
                }
            });
        }

        public void update () {
            SoundManager.updateSound();
            Menu.updateMenu();
            EntityList.walls.forEach(Wall::update);
            if (Bomb.isFire()) EntityList.bricks.forEach(Brick::update);
            for (int i = 0; i < EntityList.items.size(); i++) EntityList.items.get(i).update();
            for (int i = 0; i < bomberman.bombs.size(); i++) bomberman.bombs.get(i).update();
            for (int i = 0; i < EntityList.flames.size(); i++) EntityList.flames.get(i).update();
            for (int i = 0; i < EntityList.enemies.size(); i++) EntityList.enemies.get(i).update();
            EntityList.portal.update();
            bomberman.update();
        }

        public void render () {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            EntityList.grasses.forEach(g -> g.render(gc));
            EntityList.walls.forEach(g -> g.render(gc));
            EntityList.portal.render(gc);
            EntityList.bricks.forEach(g -> g.render(gc));
            for (int i = 0; i < EntityList.items.size(); i++) EntityList.items.get(i).render(gc);
            for (int i = 0; i < EntityList.enemies.size(); i++) EntityList.enemies.get(i).render(gc);
            for (int i = 0; i < bomberman.bombs.size(); i++) bomberman.bombs.get(i).render(gc);
            for (int i = 0; i < EntityList.flames.size() && Bomb.isFire(); i++) EntityList.flames.get(i).render(gc);
            bomberman.render(gc);
        }

        public void showMenu () {
            startscreenView.setVisible(true);
            if (checksetting) {
                playbutton.setVisible(true);
            }
            if (checkplay) {
                settings.setVisible(true);
            }
            continueGame.setVisible(false);
            homeScreen.setVisible(false);
            gameover.setVisible(false);
        }

        public void hideMenu () {
            startscreenView.setVisible(false);
            playbutton.setVisible(false);
            settings.setVisible(false);
            gamemodeEasy.setVisible(false);
            gamemodeMedium.setVisible(false);
            gamemodeHard.setVisible(false);
            SoundManager.updateSound();
            new SoundManager("sound/pacbaby.wav", "ingame");
        }

        public static void moveCamera ( int x, int y){
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.translate(-x, -y);
        }
    }
