package Sound;

import java.io.File;
import javax.sound.sampled.*;
public class ThemeSong {

    public ThemeSong(String location) {
        try {
            File musicPath = new File(location);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
