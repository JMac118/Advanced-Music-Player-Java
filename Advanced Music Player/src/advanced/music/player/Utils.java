/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanced.music.player;

/**
 *
 * @author Jursh
 */
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Utils {

    public static void playSound(Song current){
        Media m = new Media("file:///" + current.getUri().replace('\\', '/'));
        MediaPlayer player = new MediaPlayer(m);
        player.play();
    }

}