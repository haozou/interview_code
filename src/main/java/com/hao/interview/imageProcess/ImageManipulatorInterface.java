package com.hao.interview.imageProcess;


/**
 * Created by hzou on 3/21/17.
 */
public interface ImageManipulatorInterface {


    public void removePhotobomb(String directory, String outputfile);
    public void zoomMiddle(String inputfile, String outputfile);
}