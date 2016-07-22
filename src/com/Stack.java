package com;

import java.util.Arrays;

/**
 * Created by Ricardo on 22/06/2016.
 */
public class Stack<E> extends java.util.Stack<E> implements IStack<E>,IOriginator<E> {

    private java.util.Stack<StackMemento<E>> undoMementos;
    private java.util.Stack<StackMemento<E>> redoMementos;

    public Stack() {
        undoMementos = new java.util.Stack<>();
        redoMementos = new java.util.Stack<>();
    }

    @Override
    public E top() {
        return this.lastElement();
    }

    @Override
    public void undo() {
        if(!undoMementos.isEmpty()) {
            redoMementos.push(saveStateToMemento());
            getStateFromMemento(undoMementos.pop());
        }
    }

    @Override
    public void redo() {
        if(!redoMementos.isEmpty()) {
            undoMementos.push(saveStateToMemento());
            getStateFromMemento(redoMementos.pop());
        }
    }

    @Override
    public E push(E item) {
        redoMementos.clear();
        undoMementos.push(saveStateToMemento());
        return super.push(item);
    }

    @Override
    public synchronized E pop() {
        undoMementos.push(saveStateToMemento());
        return super.pop();
    }

    public void getStateFromMemento(java.util.Stack<E> stack){
        this.clear();
        this.addAll(stack);
    }

    public StackMemento<E> saveStateToMemento(){
        return new StackMemento<E>(this);
    }

    public void printState() {
        System.out.println("S=" + Arrays.toString(this.toArray())
                + " U=" + Arrays.toString(undoMementos.toArray())
                + " R=" + Arrays.toString(redoMementos.toArray()));
    }

}
