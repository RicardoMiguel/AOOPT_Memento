package com;

/**
 * Created by Ricardo on 22/06/2016.
 */
public class StackMemento<E> extends java.util.Stack<E>{

    public StackMemento(java.util.Stack<E> original) {
        addAll(original);
    }
}
