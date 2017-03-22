package com.hao.interview.imageProcess;

/**
 * Created by hzou on 3/21/17.
 */
public class ImageDriver {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: ImageDriver <images_directory>");
            System.exit(1);
        }

        // input image directory
        String dir = args[0];
        System.out.println("input images directory: " + dir);


        String noPhotoBombOutput = "./nophotobomb.jpg";
        String zoomedOutput = "./zoomed.jpg";

        // imageManipulator instance creation
        ImageManipulatorInterface imageManipulator = new ImageManipulator();

        // remove the photobomb
        imageManipulator.removePhotobomb(dir, noPhotoBombOutput);

        // zoom the picture to the middle
        imageManipulator.zoomMiddle(noPhotoBombOutput, zoomedOutput);
    }
}
