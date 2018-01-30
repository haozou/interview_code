package com.hao.interview.stockOutliers.list;

import java.util.Iterator;

/**
 * Created by hzou on 10/24/17.
 */
public interface List<E> extends Iterable<E> {
    /**
     * adds data to the end of the List.
     * @param data
     */
    void add(E data);

    /**
     * adds the data parameter to the position in the LinkedList, with the first position being 0.
     * @param pos
     * @param data
     */
    void add (int pos, E data);

    /**
     * removes the Object stored at position pos.
     * @param pos
     */
    void remove (int pos);

    /**
     * returns the Object stored at position pos.
     * @param pos
     * @return
     */
    E get (int pos);

    /**
     * returns the size (number of Object instances) of the List.
     * @return
     */
    int size ();

    /**
     * returns a Iterator instance, as defined below.
     * @return
     */
    Iterator<E> iterator ();
}
