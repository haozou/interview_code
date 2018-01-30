package com.hao.interview.dataStructure;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by hzou on 7/3/17.
 */
public class NestedList<T> implements Iterator<T> {

    protected Stack<NestedElementInterface<T>> stack;

    public NestedList(List<NestedElementInterface<T>> nestedElements) {
        stack = new Stack<>();
        for (int i = nestedElements.size() - 1; i >= 0; i--)
            stack.push(nestedElements.get(i));
    }


    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (stack.peek().isElement()) {
                return true;
            } else {
                NestedElementInterface<T> element = stack.pop();
                for (int i = element.getList().size() - 1; i >= 0; i--)
                    stack.push(element.getList().get(i));
            }
        }
        return false;
    }

    @Override
    public T next() {
        hasNext();
        return stack.pop().getElement();
    }

    @Override
    public void remove() {

    }
}
