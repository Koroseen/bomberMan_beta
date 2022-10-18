package uet.oop.bomberman.GUI;

import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;

public class Menu {
    private static ImageView statusGame;
    public static Text level, bomb, time, score;
    public static int bombNumber = 20;
    public static int Score = 0;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(100);
        level.setY(20);
//        bomb = new Text("Bombs: 20");
//        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        bomb.setFill(Color.WHITE);
//        bomb.setX(512);
//        bomb.setY(20);
        time = new Text();
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(200);
        time.setY(20);

        score = new Text("score: 0");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        score.setFill(Color.WHITE);
        score.setX(300);
        score.setY(20);

        Glow glow = new Glow(0.9);
        score.setEffect(glow);
        time.setEffect(glow);

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
        pane.getChildren().addAll(level, time, statusGame, score);
        pane.setMinSize(Settings.WIDTH, 30);
        pane.setStyle("-fx-background-color: #427235");
        root.getChildren().add(pane);
    }
    public static void createButton(Group root){
        new Menubutton(root);
    }
    public static void updateMenu() {
        score.setText("Score: " + Score);
        time.setText("Times: " + Game.time);
    }
}