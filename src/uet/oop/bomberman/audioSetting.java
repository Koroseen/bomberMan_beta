package uet.oop.bomberman;

public class audioSetting {
    private static double musicVolume=0.0f;

    public static double getMusicVolume() {
        return musicVolume;
    }

    public static void setMusicVolume(double musicVolume1) {
        musicVolume = musicVolume1;
    }
}
