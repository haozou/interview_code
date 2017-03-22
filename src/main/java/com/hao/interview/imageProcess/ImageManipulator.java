package com.hao.interview.imageProcess;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hzou on 3/21/17.
 */
public class ImageManipulator implements ImageManipulatorInterface {

    public ImageManipulator() {

    }

    /**
     * Takes into a directory of images and remove the bomb in the photo and save it into disk
     * @param directory
     * @param outputfile
     */
    @Override
    public void removePhotobomb(String directory, String outputfile) {
        File[] files = (new File(directory)).listFiles();

        List<Picture> pictures = loadPictures(files);
        if (pictures == null || pictures.size() <= 0) {
            System.err.println("no image files found in " + directory);
            System.exit(2);
        }
        int width = pictures.get(0).getWidth();
        int height = pictures.get(0).getHeight();
        Picture output = new Picture(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Pixel pixel = computeMedianPixel(pictures, i, j);
                output.setPixel(i, j, pixel);
            }
        }
        output.store(outputfile);
    }

    /**
     * zoom the input picture to the middle
     * @param inputfile
     * @param outputfile
     */
    @Override
    public void zoomMiddle(String inputfile, String outputfile) {
        Picture picture = new Picture(inputfile);
        int width = picture.getWidth();
        int height = picture.getHeight();
        Picture output = new Picture(width, height);

        int pivotWidth = width / 2;
        int pivotHeight = height / 2;

        int factorX = width / 4;
        int factorY = height / 4;

        int startHeight = height / 4;
        int startWidth = width / 4;
        int endHeight = height - startHeight;
        int endWidth = width - startWidth;
        for (int i = startHeight; i < endHeight; i++) {
            for (int j = startWidth; j < endWidth; j++) {
                // top left
                if (j < pivotWidth && i < pivotHeight) {
                    int x = j - factorX + (j - startWidth), y = i - factorY + (i - startHeight);
                    output.setPixel(x, y, picture.getPixel(j, i));
                    output.setPixel(x + 1, y, picture.getPixel(j, i));
                    output.setPixel(x, y + 1, picture.getPixel(j, i));
                    output.setPixel(x + 1, y + 1, picture.getPixel(j, i));
                    // bottom left
                } else if (j < pivotWidth && i >= pivotHeight) {
                    int x = j - factorX + (j - startWidth), y = i + factorY - (endHeight - i - 1);
                    output.setPixel(x, y, picture.getPixel(j, i));
                    output.setPixel(x + 1, y, picture.getPixel(j, i));
                    output.setPixel(x, y - 1, picture.getPixel(j, i));
                    output.setPixel(x + 1, y - 1, picture.getPixel(j, i));
                    // top right
                } else if (j >= pivotWidth && i < pivotHeight) {
                    int x = j + factorX - (endWidth - j - 1), y = i - factorY + (i - startHeight);
                    output.setPixel(x, y, picture.getPixel(j, i));
                    output.setPixel(x - 1, y, picture.getPixel(j, i));
                    output.setPixel(x, y + 1, picture.getPixel(j, i));
                    output.setPixel(x - 1, y + 1, picture.getPixel(j, i));
                    // bottom right
                } else if (j >= pivotWidth && i >= pivotHeight) {
                    int x = j + factorX - (endWidth - j - 1), y = i + factorY - (endHeight - i - 1);
                    output.setPixel(x, y, picture.getPixel(j, i));
                    output.setPixel(x, y - 1, picture.getPixel(j, i));
                    output.setPixel(x - 1, y, picture.getPixel(j, i));
                    output.setPixel(x - 1, y - 1, picture.getPixel(j, i));
                }
            }
        }

        output.store(outputfile);

    }

    private List<Picture> loadPictures(File[] files) {
        if (files == null) return null;
        List<Picture> pictures = new ArrayList<>();
        for (File file : files) {
            if (!file.getName().endsWith(".jpg")) continue;
            Picture picture = new Picture(file.getPath());
            pictures.add(picture);
        }
        return pictures;
    }

    private Pixel computeMedianPixel(List<Picture> pictures, int x, int y) {
        int[] red = new int[pictures.size()];
        int[] green = new int[pictures.size()];
        int[] blue = new int[pictures.size()];
        for (int i = 0; i < pictures.size(); i++) {
            try {
                red[i] = pictures.get(i).getPixel(x, y).getRed();
                green[i] = pictures.get(i).getPixel(x, y).getGreen();
                blue[i] = pictures.get(i).getPixel(x, y).getBlue();
            } catch (Exception e) {
                // ignore the outOfBoundException which means ignore the bad pictures
                continue;
            }
        }
        Arrays.sort(red);
        Arrays.sort(green);
        Arrays.sort(blue);
        Pixel pixel = new Pixel(
                getMediaValue(red),
                getMediaValue(green),
                getMediaValue(blue));
        return pixel;
    }

    private int getMediaValue(int[] nums) {
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2;
        } else {
            return nums[nums.length / 2];
        }
    }


    // example enlarge an array to the middle
    public int[][] enlarge(int[][] array) {
        int width = array[0].length;
        int height = array.length;
        int[][] result = new int[height][width];
        int pivotWidth = width / 2;
        int pivotHeight = height / 2;
        int factorX = width / 4;
        int factorY = height / 4;

        int startHeight = height / 4;
        int startWidth = width / 4;
        int endHeight = height - startHeight;
        int endWidth = width - startWidth;

        for (int i = startHeight; i < endHeight; i++) {
            for (int j = startWidth; j < endWidth; j++) {
                if (j < pivotWidth && i < pivotHeight) {
                    int x = j - factorX + (j - startWidth), y = i - factorY + (i - startHeight);
                    result[y][x] = array[i][j];
                    result[y][x + 1] = array[i][j];
                    result[y + 1][x] = array[i][j];
                    result[y + 1][x + 1] = array[i][j];
                } else if (j < pivotWidth && i >= pivotHeight) {
                    int x = j - factorX + (j - startWidth), y = i + factorY - (endHeight - i - 1);
                    result[y][x] = array[i][j];
                    result[y][x + 1] = array[i][j];
                    result[y - 1][x] = array[i][j];
                    result[y - 1][x + 1] = array[i][j];
                } else if (j >= pivotWidth && i < pivotHeight) {
                    int x = j + factorX - (endWidth - j - 1), y = i - factorY + (i - startHeight);
                    result[y][x] = array[i][j];
                    result[y][x - 1] = array[i][j];
                    result[y + 1][x] = array[i][j];
                    result[y + 1][x - 1] = array[i][j];
                } else if (j >= pivotWidth && i >= pivotHeight) {
                    int x = j + factorX - (endWidth - j - 1), y = i + factorY - (endHeight - i - 1);
                    result[y][x] = array[i][j];
                    result[y - 1][x] = array[i][j];
                    result[y][x - 1] = array[i][j];
                    result[y - 1][x - 1] = array[i][j];
                }
            }
        }
        return result;
    }

}
