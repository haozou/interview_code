package com.hao.interview.dataStructure;

import java.util.List;

/**
 * Created by hzou on 7/3/17.
 */
public interface NestedElementInterface<T> {
    public boolean isElement();
    public T getElement();
    public List<NestedElementInterface<T>> getList();

}
