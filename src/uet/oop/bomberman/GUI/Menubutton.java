package uet.oop.bomberman.GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;

public class Menubutton {
    public static Button newgame;
    public static Button setting;
    public static Button exit;
    public static Button hard;
    public static Button easy;
    public static Button medium;
    public static Button resume;
    public static Button mainMenu;
    public static boolean newGame = true;
    public static boolean Setting = true;

    public Menubutton(Group root) {
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
        exit.setOnAction(actionEvent -> {

        });

        easy = new Button("easy");
        easy.setLayoutX(Settings.WIDTH / 2 - 85);
        easy.setLayoutY(200);
        easy.setMinSize(170, 40);
        easy.setOnAction(actionEvent -> {
            CreateMap.createMapLevel(1);
            Game.gamestate = "running";
            new SoundManager("sound/pacbaby.wav", "ingame");
            update();
            System.out.println("pressed");
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
        });

        resume = new Button("exit");
        resume.setLayoutX(Settings.WIDTH / 2 - 85);
        resume.setLayoutY(240);
        resume.setMinSize(170, 40);
        resume.setOnAction(actionEvent -> {

        });

        mainMenu = new Button("fdsafs");
        mainMenu.setLayoutX(Settings.WIDTH / 2 - 85);
        mainMenu.setLayoutY(300);
        mainMenu.setMinSize(170, 40);
        mainMenu.setOnAction(actionEvent -> {
            Game.gamestate = "startmenu";
        });

        setStyle();
        root.getChildren().addAll(newgame, setting, exit, resume, easy, hard, medium, mainMenu);
    }

    public void setStyle() {
        newgame.setFont(Game.font);
        setting.setFont(Game.font);
        exit.setFont(Game.font);
        hard.setFont(Game.font);
        medium.setFont(Game.font);
        easy.setFont(Game.font);

        setOriginalStyle(newgame);
        setOriginalStyle(setting);
        setOriginalStyle(exit);
        setOriginalStyle(hard);
        setOriginalStyle(easy);
        setOriginalStyle(medium);
        setOriginalStyle(resume);
        setOriginalStyle(mainMenu);

        stateUpdate(newgame);
        stateUpdate(setting);
        stateUpdate(exit);
        stateUpdate(hard);
        stateUpdate(easy);
        stateUpdate(medium);
        stateUpdate(resume);
        stateUpdate(mainMenu);
    }

    public void setOriginalStyle(Button button) {
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

    public static void stateUpdate(Button button) {
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

    public static void update() {
        if (Game.gamestate.equals("startmenu")) {
            resume.setVisible(false);
            mainMenu.setVisible(false);
            if (!newGame) {
                setting.setVisible(false);
                exit.setVisible(false);
                hard.setVisible(true);
                easy.setVisible(true);
                medium.setVisible(true);
            } else if (!Setting) {
                newgame.setVisible(false);
                setting.setVisible(false);
                exit.setVisible(false);
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
                audioScroller.slider.setVisible(false);
                audioScroller.label.setVisible(false);
                audioScroller.l.setVisible(false);
            }
        } else if (Game.gamestate.equals("running")) {
            Game.startscreenView.setVisible(false);
            easy.setVisible(false);
            medium.setVisible(false);
            hard.setVisible(false);
            newgame.setVisible(false);
            setting.setVisible(false);
            exit.setVisible(false);
            mainMenu.setVisible(false);
            resume.setVisible(false);
        } else if (Game.gamestate.equals("pause")) {
            mainMenu.setVisible(true);
            resume.setVisible(true);
        }
    }
}
