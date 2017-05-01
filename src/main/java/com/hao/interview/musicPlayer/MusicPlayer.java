package com.hao.interview.musicPlayer;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hzou on 5/1/17.
 */
public class MusicPlayer implements MusicPlayerInterface {
    private String path;
    private List<Music> musics = new ArrayList<>();
    private List<Music> musicsSortByArtist = new ArrayList<>();
    private int playMusicId = -1;

    public MusicPlayer() {
    }

    public MusicPlayer(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void showMenu() {
        System.out.println("********************************************");
        System.out.println("Music Player:");
        System.out.println("1. list all MP3s sorted by song title.");
        System.out.println("2. list all MP3s sorted by artist title.");
        System.out.println("3. play music.");
        System.out.println("4. stop playback.");
        System.out.println("5. exit.");
        System.out.println("********************************************");

    }

    @Override
    public void loadMusics() {
        loadMusics(new File(path));
        Collections.sort(musics);
        musicsSortByArtist = new ArrayList<>(musics);
        Collections.sort(musicsSortByArtist, new Comparator<Music>() {
            @Override
            public int compare(Music o1, Music o2) {
                return o1.getArtist().compareTo(o2.getArtist());
            }
        });
    }

    private void loadMusics(File file) {
        if (file == null || !file.exists()) return;
        try {
            if (file.isFile()) {
                String absolutePath = file.getAbsolutePath();
                if (absolutePath.endsWith(".mp3")) {
                    AudioFile f = AudioFileIO.read(new File(absolutePath));
                    Tag tag = f.getTag();
                    musics.add(new MP3Music(absolutePath,
                            tag.getFirst(FieldKey.ARTIST),
                            tag.getFirst(FieldKey.ALBUM),
                            tag.getFirst(FieldKey.TITLE)));
                }
            } else {
                for (File child : file.listFiles()) {
                    loadMusics(child);
                }
            }
        } catch (IOException e) {
        } catch (CannotReadException e) {
        } catch (ReadOnlyFileException e) {
        } catch (TagException e) {
        } catch (InvalidAudioFrameException e) {
        }

    }

    @Override
    public void listAllMP3MusicByTitle() {
        int i = 1;
        for (Music music : musics) {
            System.out.println(i++ + ". " + music);
        }
    }

    @Override
    public void listAllMP3MusicByArtist() {
        int i = 1;
        for (Music music : musicsSortByArtist) {
            System.out.println(i++ + ". " + music);
        }
    }

    @Override
    public void play(final int musicNumber) throws NoMusicException {
        if (musicNumber > musics.size() || musicNumber < 1) throw new NoMusicException("No Such Music Exception");
        final Music music = musics.get(musicNumber - 1);
        this.playMusicId = musicNumber;
        music.setPlayer();
        //create a new thread to start playback
        //the run method will not be called until the thread is started
        //if the play method is not called from a thread the the rest of the program
        //will block until the entire song has played
        Thread t = new Thread() {
            public void run() {
                try {
                    music.getPlayer().play();
                    if (music.getPlayer().isComplete()) {
                        playMusicId = -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        //start the thread
        //only when start is called does the run method of the thread get called
        t.start();
    }

    @Override
    public void stopPlayBack() {
        if (playMusicId != -1) {
            musics.get(playMusicId - 1).getPlayer().close();
            try {
                play(playMusicId);
            } catch (NoMusicException e) {
                e.printStackTrace();
            }
        }
    }
}
