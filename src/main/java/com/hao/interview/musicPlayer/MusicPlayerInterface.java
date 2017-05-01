package com.hao.interview.musicPlayer;

import java.util.List;

/**
 * Created by hzou on 5/1/17.
 */
public interface MusicPlayerInterface {

    void showMenu();

    void loadMusics();

    void listAllMP3MusicByTitle();

    void listAllMP3MusicByArtist();

    void play(int musicNumber) throws NoMusicException;

    void stopPlayBack();
}
