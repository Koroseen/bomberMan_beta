package uet.oop.bomberman.GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import uet.oop.bomberman.CreateMap;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Settings;
import uet.oop.bomberman.SoundManager;

public class Menubutton {
    public static Button newgame;
    public static Button setting;
    public static Button exit;

    public Menubutton(Group root) {
        newgame = new Button("new game");
        newgame.setLayoutX(Settings.WIDTH / 2 - 85);
        newgame.setLayoutY(200);
        newgame.setMinSize(170, 40);
        newgame.setOnAction(actionEvent -> {
            CreateMap.createMapLevel(1);
            Game.gamestate = "running";
            new SoundManager("sound/pacbaby.wav", "ingame");
            newgame.setVisible(false);
            Game.startscreenView.setVisible(false);
            System.out.println("fafdssffa");
        });

        setting = new Button("setting");
        setting.setFont(Game.font);
        setting.setLayoutX(Settings.WIDTH / 2 - 85);
        setting.setLayoutY(250);
        setting.setMinSize(170, 40);
        setting.setOnAction(actionEvent -> {

        });

        exit = new Button("exit");
        exit.setLayoutX(Settings.WIDTH / 2 - 85);
        exit.setLayoutY(300);
        exit.setMinSize(170, 40);
        exit.setOnAction(actionEvent -> {

        });

        stateUpdate(newgame);
        stateUpdate(setting);
        stateUpdate(exit);

        setStyle();
        root.getChildren().addAll(newgame, setting, exit);
    }

    public void setStyle() {
        newgame.setFont(Game.font);
        setting.setFont(Game.font);
        exit.setFont(Game.font);

        newgame.setStyle(
                "-fx-padding: 8 15 15 15;" +
                        "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "-fx-background-radius: 50px;\n" +
                        "-fx-background-color: \n" +
                        "linear-gradient(from 0% 93% to 0% 100%, white 0%, blue 100%),\n" +
                        "white,\n" +
                        "blue,\n" +
                        "radial-gradient(center 50% 50%, radius 100%, whitesmoke, blue);\n" +
                        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n");
        setting.setStyle(
                "-fx-padding: 8 15 15 15;" +
                        "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "-fx-background-radius: 50px;\n" +
                        "-fx-background-color: \n" +
                        "linear-gradient(from 0% 93% to 0% 100%, white 0%, blue 100%),\n" +
                        "white,\n" +
                        "blue,\n" +
                        "radial-gradient(center 50% 50%, radius 100%, whitesmoke, blue);\n" +
                        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n");
        exit.setStyle(
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

    public void stateUpdate(Button button) {
        // Thêm bóng đổ vào Button khi chuột di chuyển bên trên
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.setStyle(
                        "-fx-background-radius: 50px;\n" +
                                "-fx-border-radius: 50px;\n" +
                                "-fx-background-color: \n" +
                                "linear-gradient(from 0% 93% to 0% 100%, white 0%, blue 100%),\n" +
                                "white,\n" +
                                "blue,\n" +
                                "radial-gradient(center 50% 50%, radius 100%, whitesmoke, blue);\n" +
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

    public void update() {

    }
}
