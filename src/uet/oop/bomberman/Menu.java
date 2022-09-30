package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Menu {
    private static ImageView statusGame;
    public static Text level, bomb, time;
    public static int bombNumber = 20;
    public static long timer=0;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(100);
        level.setY(22);
//        bomb = new Text("Bombs: 20");
//        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        bomb.setFill(Color.WHITE);
//        bomb.setX(512);
//        bomb.setY(20);
        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(200);
        time.setY(22);

        Image newGame = new Image("images/pause.png");
        Image playGame = new Image("images/resume.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(20);
        statusGame.setY(0);
        statusGame.setFitHeight(32);
        statusGame.setFitWidth(32);

        Pane pane = new Pane();
        pane.getChildren().addAll(level, time, statusGame);
        pane.setMinSize(Settings.WIDTH, 30);
        pane.setStyle("-fx-background-color: #427235");

        root.getChildren().add(pane);
    }
    public static void updateMenu() {

        time.setText("Times: "+Game.time++/60);
    }
}