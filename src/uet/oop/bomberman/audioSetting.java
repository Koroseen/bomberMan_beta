package uet.oop.bomberman;

public class audioSetting {
    private static float musicVolume=1;

    public audioSetting() {
        musicVolume = 1;
    }

    public static float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }
}
