package uet.oop.bomberman.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;

public class Menubutton {
    public static int myEnum = 1;
    public static Button newgame;
    public static Button setting;
    public static Button exit;
    public static Button goback;
    public static Button hard;
    public static Button easy;
    public static Button medium;
    public static Button resume;
    public static Button mainMenu;
    public static boolean newGame = true;
    public static boolean Setting = true;

    public Menubutton(Group root) {
        //sound setting
        SoundManager.running = new boolean[7];

        newgame = new Button("new game");
        newgame.setLayoutX(Settings.WIDTH / 2 - 85);
        newgame.setLayoutY(200);
        newgame.setMinSize(170, 40);
        newgame.setOnAction(actionEvent -> {
            newGame = false;
        });

        setting = new Button("settings");
        setting.setLayoutX(Settings.WIDTH / 2 - 85);
        setting.setLayoutY(250);
        setting.setMinSize(170, 40);
        setting.setOnAction(actionEvent -> {
            Setting = false;
        });

        exit = new Button("exit");
        exit.setLayoutX(Settings.WIDTH / 2 - 85);
        exit.setLayoutY(300);
        exit.setMinSize(170, 40);
        exit.setOnAction(Menubutton::alert);

        Image img = new Image("images/icon.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(40);
        view.setPreserveRatio(true);
        goback = new Button();
        goback.setStyle("-fx-background-color: transparent");
        goback.setGraphic(view);
        goback.setLayoutX(10);
        goback.setLayoutY(10);
        goback.setOnAction(actionEvent -> {
            if (Game.gamestate.equals("pause")) {
                if (!Setting) Setting = true;
                else Game.gamestate = "running";
            } else if (!newGame || !Setting) {
                newGame = true;
                Setting = true;
            }
        });

        easy = new Button("easy");
        easy.setLayoutX(Settings.WIDTH / 2 - 85);
        easy.setLayoutY(200);
        easy.setMinSize(170, 40);
        easy.setOnAction(actionEvent -> {
            LoadingScreen.startloadingScreen(root);
        });

        medium = new Button("medium");
        medium.setLayoutX(Settings.WIDTH / 2 - 85);
        medium.setLayoutY(250);
        medium.setMinSize(170, 40);
        medium.setOnAction(actionEvent -> {
            CreateMap.createMapLevel(2);
            Game.gamestate = "running";
            new SoundManager("sound/pacbaby.wav", "ingame");
            update();
            System.out.println("pressed");
            newGame = true;
            Setting = true;
        });

        hard = new Button("hard");
        hard.setLayoutX(Settings.WIDTH / 2 - 85);
        hard.setLayoutY(300);
        hard.setMinSize(170, 40);
        hard.setOnAction(actionEvent -> {
            CreateMap.createMapLevel(3);
            Game.gamestate = "running";
            new SoundManager("sound/pacbaby.wav", "ingame");
            update();
            System.out.println("pressed");
            newGame = true;
            Setting = true;
        });

        resume = new Button("RESUME");
        resume.setLayoutX(Settings.WIDTH / 2 - 85);
        resume.setLayoutY(200);
        resume.setMinSize(170, 40);
        resume.setOnAction(actionEvent -> {
            if (Game.gamestate.equals("pause")) {
                Game.gamestate = "running";
            } else if (Game.gamestate.equals("nextLevel")) {
                myEnum = 4;
                LoadingScreen.startloadingScreen(root);
            }
        });

        mainMenu = new Button("MAIN MENU");
        mainMenu.setLayoutX(Settings.WIDTH / 2 - 85);
        mainMenu.setLayoutY(300);
        mainMenu.setMinSize(170, 40);
        mainMenu.setOnAction(actionEvent -> {
            Game.gamestate = "startmenu";
            new SoundManager("sound/start.wav", "title");
        });

        setStyle();
        root.getChildren().addAll(newgame, setting, exit, resume, easy, hard, medium, mainMenu, goback);
    }

    public void setStyle() {
        newgame.setFont(Game.font);
        setting.setFont(Game.font);
        exit.setFont(Game.font);
        hard.setFont(Game.font);
        medium.setFont(Game.font);
        easy.setFont(Game.font);
        resume.setFont(Game.font);
        mainMenu.setFont(Game.font);

        stateUpdate(newgame);
        stateUpdate(setting);
        stateUpdate(exit);
        stateUpdate(easy);
        stateUpdate(hard);
        stateUpdate(medium);
        stateUpdate(resume);
        stateUpdate(mainMenu);
    }

    public static void stateUpdate(Button button) {
        button.setStyle(
                "-fx-padding: 8 15 15 15;" +
                        "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "-fx-background-radius: 50px;\n" +
                        "-fx-background-color: \n" +
                        "linear-gradient(from 0% 93% to 0% 100%, white 0%, blue 100%),\n" +
                        "white,\n" +
                        "blue,\n" +
                        "radial-gradient(center 50% 50%, radius 100%, whitesmoke, blue);\n" +
                        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n");
        // Thêm bóng đổ vào Button khi chuột di chuyển bên trên
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.setStyle(
                        "-fx-background-radius: 50px;\n" +
                                "-fx-border-radius: 50px;\n" +
                                "-fx-background-color: \n" +
                                "radial-gradient(center 50% 50%, radius 100%, whitesmoke, blue, red);\n" +
                                "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n");
            }
        });

        // Loại bỏ bóng đổ khi chuột thoát ra khỏi Button.
        button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.setStyle(
                        "-fx-padding: 8 15 15 15;" +
                                "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                                "-fx-background-radius: 50px;\n" +
                                "-fx-background-color: \n" +
                                "linear-gradient(from 0% 93% to 0% 100%, white 0%, blue 100%),\n" +
                                "white,\n" +
                                "blue,\n" +
                                "radial-gradient(center 50% 50%, radius 100%, whitesmoke, blue);\n" +
                                "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n");
            }
        });
    }

    public static void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Do you want to save before exiting?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        }
    }

    public static void update() {
        switch (Game.gamestate) {
            case "startmenu":
                Game.startscreenView.setVisible(true);
                Game.nextLevel.setVisible(false);
                Game.gameOver.setVisible(false);
                resume.setVisible(false);
                mainMenu.setVisible(false);
                if (!newGame) {
                    setting.setVisible(false);
                    exit.setVisible(false);
                    hard.setVisible(true);
                    easy.setVisible(true);
                    medium.setVisible(true);
                    goback.setVisible(true);
                } else if (!Setting) {
                    newgame.setVisible(false);
                    setting.setVisible(false);
                    exit.setVisible(false);
                    goback.setVisible(true);
                    audioScroller.slider.setVisible(true);
                    audioScroller.label.setVisible(true);
                    audioScroller.l.setVisible(true);
                } else {
                    newgame.setVisible(true);
                    setting.setVisible(true);
                    exit.setVisible(true);
                    hard.setVisible(false);
                    easy.setVisible(false);
                    medium.setVisible(false);
                    goback.setVisible(false);
                    audioScroller.slider.setVisible(false);
                    audioScroller.label.setVisible(false);
                    audioScroller.l.setVisible(false);
                }
                break;
            case "running":
                Game.startscreenView.setVisible(false);
                Game.nextLevel.setVisible(false);
                Game.gameOver.setVisible(false);
                easy.setVisible(false);
                medium.setVisible(false);
                hard.setVisible(false);
                newgame.setVisible(false);
                setting.setVisible(false);
                exit.setVisible(false);
                mainMenu.setVisible(false);
                resume.setVisible(false);
                goback.setVisible(false);
                LoadingScreen.loader.setVisible(false);
                break;
            case "pause":
                resume.setText("RESUME");
                mainMenu.setVisible(true);
                resume.setVisible(true);
                setting.setVisible(true);
                goback.setVisible(true);
                audioScroller.slider.setVisible(false);
                audioScroller.label.setVisible(false);
                audioScroller.l.setVisible(false);
                if(!Setting){
                    mainMenu.setVisible(false);
                    resume.setVisible(false);
                    setting.setVisible(false);
                    audioScroller.slider.setVisible(true);
                    audioScroller.label.setVisible(true);
                    audioScroller.l.setVisible(true);
                }
                break;
            case "nextLevel":
                resume.setText("NEXT LEVEL");
                resume.setVisible(true);
                mainMenu.setVisible(true);
                Game.nextLevel.setVisible(true);
                break;
        }
    }
}
