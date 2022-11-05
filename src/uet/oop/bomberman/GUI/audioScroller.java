package uet.oop.bomberman.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import uet.oop.bomberman.audioSetting;

public class audioScroller {
    public static Slider slider;
    public static Label label;
    public static Label l;

    public static void slider(Group root) {
        // create label
        label = new Label("Select the volume");
        label.setTextFill(Color.WHITE);
        label.setLayoutX(200);
        label.setLayoutY(260);

        l = new Label(" ");
        l.setLayoutX(180);
        l.setLayoutY(320);

        // set the color of the text
        l.setTextFill(Color.WHITE);

        slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue((int) audioSetting.getMusicVolume());

        slider.setLayoutX(180);
        slider.setLayoutY(280);

        // enable TickLabels and Tick Marks
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setBlockIncrement(10);

        // Adding Listener to value property.
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>
                                        observable, Number oldValue, Number newValue) {
                audioSetting.setMusicVolume((double) newValue / 100);
                l.setText("volume: " + Math.round((double) newValue));
            }
        });
        root.getChildren().addAll(slider, label, l);
        slider.setVisible(false);
        label.setVisible(false);
        l.setVisible(false);
    }
}
