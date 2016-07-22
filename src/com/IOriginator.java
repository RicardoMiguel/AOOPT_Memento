package com;

/**
 * Created by Ricardo on 22/06/2016.
 */
public interface IOriginator<E> {
    public void getStateFromMemento(java.util.Stack<E> stack);
    public StackMemento<E> saveStateToMemento();

}
