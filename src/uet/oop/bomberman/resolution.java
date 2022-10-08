package uet.oop.bomberman;

import javafx.stage.Stage;

public class resolution {
    public static void setResolution(Stage stage) {
            Game.gameover.setX(0);
            Game.gameover.setY(0);
            Game.gameover.setFitHeight(Settings.HEIGHT);
            Game.gameover.setFitWidth(Settings.WIDTH);

            Game.homeScreen.setX(170);
            Game.homeScreen.setY(Settings.HEIGHT / 2 + 50);
            Game.homeScreen.setFitHeight(40);
            Game.homeScreen.setFitWidth(160);

            Game.continueGame.setX(170);
            Game.continueGame.setY(Settings.HEIGHT / 2);
            Game.continueGame.setFitHeight(40);
            Game.continueGame.setFitWidth(160);

            Game.startscreenView.setX(0);
            Game.startscreenView.setY(0);
            Game.startscreenView.setFitHeight(Settings.HEIGHT);
            Game.startscreenView.setFitWidth(Settings.WIDTH);

            Game.playbutton.setLayoutX(170);
            Game.playbutton.setLayoutY(210);
            Game.playbutton.setFitHeight(40);
            Game.playbutton.setFitWidth(160);

            Game.gamemodeHard.setLayoutX(170);
            Game.gamemodeHard.setLayoutY(360);
            Game.gamemodeHard.setFitHeight(40);
            Game.gamemodeHard.setFitWidth(160);

            Game.gamemodeMedium.setLayoutX(170);
            Game.gamemodeMedium.setLayoutY(310);
            Game.gamemodeMedium.setFitHeight(40);
            Game.gamemodeMedium.setFitWidth(160);

            Game.gamemodeEasy.setLayoutX(170);
            Game.gamemodeEasy.setLayoutY(260);
            Game.gamemodeEasy.setFitHeight(40);
            Game.gamemodeEasy.setFitWidth(160);

            Game.settings.setLayoutX(170);
            Game.settings.setLayoutY(260);
            Game.settings.setFitHeight(40);
            Game.settings.setFitWidth(160);
    }
}
