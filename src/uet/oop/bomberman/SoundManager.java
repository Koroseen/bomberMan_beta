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
    public static boolean[] running;

    public SoundManager(String name, String sound) {
        try {
            URL url = this.getClass().getClassLoader().getResource(name);

            assert url != null;
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            if (sound.equals("title")) {
                running[1] = true;
                title_screen = AudioSystem.getClip();
                title_screen.open(audioIn);
                title_screen.start();
                title_screen.loop(10);
                volumeConstrol(title_screen);
            }
            if (sound.equals("eat")) {
                running[2] = true;
                eat = AudioSystem.getClip();
                eat.open(audioIn);
                eat.start();
                volumeConstrol(eat);
            }
            if (sound.equals("ingame")) {
                running[3] = true;
                ingame = AudioSystem.getClip();
                ingame.open(audioIn);
                ingame.start();
                ingame.loop(10);
                volumeConstrol(ingame);
            }
            if (sound.equals("win")) {
                running[4] = true;
                win = AudioSystem.getClip();
                win.open(audioIn);
                win.start();
                volumeConstrol(win);
            }
            if (sound.equals("explosion")) {
                running[5] = true;
                bomb_explosion = AudioSystem.getClip();
                bomb_explosion.open(audioIn);
                bomb_explosion.start();
                volumeConstrol(bomb_explosion);
            }
            if (sound.equals("just_died")) {
                running[6] = true;
                just_died = AudioSystem.getClip();
                just_died.open(audioIn);
                just_died.start();
                volumeConstrol(just_died);
            }
            if (sound.equals("default")) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volume.getMaximum() - volume.getMinimum();
                volume.setValue(gainAmount * range + volume.getMinimum());
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (Game.gamestate.equals("running")) {
            title_screen.stop();
            ingame.start();
        } else if (Game.gamestate.equals("pause")) {
            ingame.stop();
        }
        if (Game.gamestate.equals("startmenu")) {
            title_screen.start();
        }
        if (Game.gamestate.equals("gameover")) {
            ingame.stop();
            new SoundManager("sound/just_died.wav", "just_died");
        }
        if (Game.gamestate.equals("nextLevel")) {
            ingame.stop();
        }
        if (running[1]) {
            volumeConstrol(title_screen);
        }
        if (running[3]) {
            volumeConstrol(ingame);
        }
        if (running[6]) {
            volumeConstrol(just_died);
        }
        if (running[5]) {
            volumeConstrol(bomb_explosion);
        }
        if (running[2]) {
            volumeConstrol(eat);
        }
        if (running[4]) {
            volumeConstrol(win);
        }
    }

    public static void volumeConstrol(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(audioSetting.getMusicVolume()));
    }
}

