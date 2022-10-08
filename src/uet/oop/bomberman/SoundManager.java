package uet.oop.bomberman;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl;

//import javax.sound.sampled.*;

public class SoundManager {
    public static Clip ingame;
    public static Clip eat;
    public static Clip win;
    public static Clip title_screen;
    public static Clip bomb_explosion;
    public static Clip just_died;
    public float gainAmount = 0.0f;

    public static boolean isSoundDied;
    public static boolean isSoundTitle;
    private static boolean isSoundComplete;

    public SoundManager(String name, String sound) {
        try {
            URL url = this.getClass().getClassLoader().getResource(name);

            assert url != null;
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            if (sound.equals("title")) {
                title_screen = AudioSystem.getClip();
                title_screen.open(audioIn);
                title_screen.start();
                title_screen.loop(10);
                FloatControl volume = (FloatControl) title_screen.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volume.getMaximum() - volume.getMinimum();
                volume.setValue(gainAmount*range + volume.getMinimum());
            }
            if (sound.equals("eat")) {
                eat = AudioSystem.getClip();
                eat.open(audioIn);
                eat.start();
            }
            if (sound.equals("ingame")) {
                ingame = AudioSystem.getClip();
                ingame.open(audioIn);
                ingame.start();
                ingame.loop(10);
                FloatControl volume = (FloatControl) ingame.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volume.getMaximum() - volume.getMinimum();
                volume.setValue(gainAmount*range + volume.getMinimum());
            }
            if (sound.equals("win")) {
                win = AudioSystem.getClip();
                win.open(audioIn);
                win.start();
            }
            if (sound.equals("explosion")) {
                bomb_explosion = AudioSystem.getClip();
                bomb_explosion.open(audioIn);
                bomb_explosion.start();
                FloatControl volume = (FloatControl) bomb_explosion.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volume.getMaximum() - volume.getMinimum();
                volume.setValue(gainAmount*range + volume.getMinimum());
            }
            if (sound.equals("just_died")) {
                just_died = AudioSystem.getClip();
                just_died.open(audioIn);
                just_died.start();
                FloatControl volume = (FloatControl) just_died.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volume.getMaximum() - volume.getMinimum();
                volume.setValue(gainAmount*range + volume.getMinimum());
            }
            if (sound.equals("default")) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volume.getMaximum() - volume.getMinimum();
                volume.setValue(gainAmount*range + volume.getMinimum());
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (Game.gamestate.equals("running")) {
            title_screen.stop();
            ingame.start();
        }
        else if (Game.gamestate.equals("pause")){
            ingame.stop();
        }
        if (Game.gamestate.equals("startmenu")) {
            title_screen.start();
        }
        if(Game.gamestate.equals("gameover")){
            ingame.stop();
            new SoundManager("sound/just_died.wav","just_died");
        }
        if(Game.gamestate.equals("nextLevel")){
            ingame.stop();
        }
        FloatControl gainControl = (FloatControl) title_screen.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(audioSetting.getMusicVolume()));
        /*
        if (!player.isLife()) {
            title_screen.close();
            bomb_explosion.close();
            if (!isSoundDied) {
                new SoundManager("sound/just_died.wav", "just_died");
                isSoundDied = true;
            }
        }
        if (wait) {
            title_screen.close();
            bomb_explosion.close();
            if (!isSoundComplete) {
                new SoundManager("sound/win.wav", "default");
                isSoundComplete = true;
            }
        }
         */
    }
}

