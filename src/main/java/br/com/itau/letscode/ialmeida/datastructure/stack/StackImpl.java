package br.com.itau.letscode.ialmeida.datastructure.stack;

public class StackImpl<T> implements Stack<T> {

    private final T[] elements;
    private int last;

    public StackImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Stack size must be bigger than zero.");
        }
        this.elements = (T[]) new Object[size];
        this.last = -1;
    }

    @Override
    public void push(T data) {
        if (isFull()) {
            throw new IllegalArgumentException("Stack is full.");
        }
        this.elements[++this.last] = data;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty.");
        }
        return this.elements[this.last--];
    }

    @Override
    public T top() {
        return isEmpty() ? null : this.elements[this.last];
    }

    @Override
    public int size() {
        return this.elements.length;
    }

    @Override
    public boolean isEmpty() {
        return this.last == -1;
    }

    @Override
    public boolean isFull() {
        return this.last + 1 == this.size();
    }
}
