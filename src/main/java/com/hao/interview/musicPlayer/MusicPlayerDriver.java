package com.hao.interview.musicPlayer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by hzou on 5/1/17.
 */
public class MusicPlayerDriver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: MusicPlayerDriver <inputPath>");
            System.exit(1);
        }
        String inputPath = args[0];
        run(inputPath);
    }

    public static void run(String inputPath) {
        MusicPlayerInterface musicPlayer = new MusicPlayer(inputPath);
        musicPlayer.loadMusics();
        Scanner scanner;
        while (true) {
            musicPlayer.showMenu();
            System.out.println("input your option: (1 ~ 5):");
            try {
                scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                switch (option) {
                    case 1: {
                        musicPlayer.listAllMP3MusicByTitle();
                        break;
                    }
                    case 2: {
                        musicPlayer.listAllMP3MusicByArtist();
                        break;
                    }
                    case 3: {
                        while (true) {
                            try {
                                musicPlayer.listAllMP3MusicByTitle();
                                System.out.println("--------------------------");
                                System.out.println("input your music number:");
                                int musicNumber = scanner.nextInt();
                                musicPlayer.play(musicNumber);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("!!!!!Invalid Input!!!!!!");
                            } catch (NoMusicException e) {
                                System.out.println("Invalid music number!");
                            }

                        }
                        break;
                    }
                    case 4: {
                        musicPlayer.stopPlayBack();
                        break;
                    }
                    case 5: {
                        System.exit(0);
                        break;
                    }
                    default: {
                        throw new Exception("No such Option!");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("!!!!!Invalid Input!!!!!!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
