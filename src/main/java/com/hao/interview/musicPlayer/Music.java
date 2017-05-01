package com.hao.interview.musicPlayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by hzou on 5/1/17.
 */
public class Music implements Comparable<Music>{


    protected String path;

    protected String artist;

    protected String album;

    protected String title;

    protected Player player;

    public Music(){}

    public Music(String path, String artist, String album, String title) {
        this.path = path;
        this.artist = artist;
        this.album = album;
        this.title = title;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return String.format("Title: %s (Artist: %s, Album: %s)", title, artist, album);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer() {
        try {
            player = new Player(new FileInputStream(path));
        } catch (JavaLayerException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int compareTo(Music o) {
        return this.title.compareTo(o.getTitle());
    }
}

