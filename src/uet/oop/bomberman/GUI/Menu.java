package uet.oop.bomberman.GUI;

import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;

public class Menu {
    public static Text lives;
    private static ImageView statusGame;
    public static Text level, bomb, time, score;
    private static int Score = 0;
    private static int Lives = 3;
    public static Font font = Font.loadFont("file:res/font/BOMBERMA.TTF", 14);
    
    public static void addScore(int score) {Score += score;}
    public static void setScore (int score) {Score = score;}
    public static void createMenu(Group root) {

        level = new Text();
        level.setFont(font);

        level.setFill(Color.WHITE);
        level.setX(100);
        level.setY(20);

        bomb = new Text();
        bomb.setFont(font);
        bomb.setFill(Color.WHITE);
        bomb.setX(10);
        bomb.setY(20);

        time = new Text();
        time.setFont(font);
        time.setFill(Color.WHITE);
        time.setX(200);
        time.setY(20);

        score = new Text();
        score.setFont(font);
        score.setFill(Color.WHITE);
        score.setX(300);
        score.setY(20);

        lives = new Text("lives: 3");
        lives.setFont(font);
        lives.setFill(Color.WHITE);
        lives.setX(400);
        lives.setY(20);

        Glow glow = new Glow(0.9);
        score.setEffect(glow);
        time.setEffect(glow);
        bomb.setEffect(glow);
        lives.setEffect(glow);

        Image newGame = new Image("images/pause.png");
        Image playGame = new Image("images/resume.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(0);
        statusGame.setY(0);
        statusGame.setFitHeight(28);
        statusGame.setFitWidth(28);

        statusGame.setOnMouseClicked(event -> {
            System.out.println("CLICKED");
            if (Game.gamestate.equals("running")) {
                Game.gamestate = "pause";
                SoundManager.updateSound();
                statusGame.setImage(newGame);
            } else {
                Game.gamestate = "running";
                statusGame.setImage(playGame);
            }
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(level, time, statusGame, score, bomb, lives);
        pane.setMinSize(Settings.WIDTH, 30);
        pane.setStyle("-fx-background-color: #427235");
        root.getChildren().add(pane);
        LoadingScreen.createLoadingScreen(root);
    }

    public static void createButton(Group root) {
        new Menubutton(root);
    }

    public static int getLives() {
        return Lives;
    }

    public static void setLives(int live) {
        Menu.Lives = live;
    }

    public static void updateMenu() {
        level.setText("Level: " + Game.getLevel());
        score.setText("Score: " + Score);
        time.setText("Times: " + Game.time);
        bomb.setText("Bombs: " + Game.entityList.getBomberman().getBombs());
        lives.setText("Lives: " + Lives);
    }
}