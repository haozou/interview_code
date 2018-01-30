package com.hao.interview.dataStructure;

import java.util.List;

/**
 * Created by hzou on 7/3/17.
 */
public class NestedElement<T> implements NestedElementInterface<T> {

    protected T element;
    protected List<NestedElementInterface<T>> elements;
    public NestedElement(T element) {
        this.element = element;
    }

    public NestedElement(List<NestedElementInterface<T>> elements) {
        this.elements = elements;
    }
    @Override
    public boolean isElement() {
        return this.element != null;
    }

    @Override
    public T getElement() {
        return this.element;
    }

    @Override
    public List<NestedElementInterface<T>> getList() {
        if (this.isElement()) return null;
        return this.elements;
    }

}
