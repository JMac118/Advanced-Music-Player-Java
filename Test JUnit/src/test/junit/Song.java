/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.junit;

/**
 *
 * @author Jursh
 */
public class Song {
    private String songName;
    private String uri;
    
    public Song(String filename, String path){
        songName = filename;
        uri = path;
    }
    /**
     * @return the songName
     */
    public String getSongName() {
        return songName;
    }

    /**
     * @param songName the songName to set
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    
}
