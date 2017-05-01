package com.hao.interview.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by hzou on 4/19/17.
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger val = stack.peek();
            if (val.isInteger()) {
                return true;
            } else {
                stack.pop();
                for (int i = val.getList().size() - 1; i >= 0; i--) {
                    stack.push(val.getList().get(i));
                }
            }
        }
        return false;
    }
    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return stack.pop().getInteger();
    }


    @Override
    public void remove() {
    }

}
