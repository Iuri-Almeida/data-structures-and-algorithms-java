package br.com.itau.letscode.ialmeida.datastructure.queue;

public class QueueImpl<T> implements Queue<T> {

    private final T[] elements;
    private int first;
    private int last;

    public QueueImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Queue size must be bigger than zero.");
        }
        this.elements = (T[]) new Object[size];
        this.first = -1;
        this.last = -1;
    }

    @Override
    public void enqueue(T data) {
        if (isFull()) {
            throw new IllegalArgumentException("Queue is full.");
        }
        if (this.first == -1) {
            this.first = 0;
        }
        this.updateRearValue();
        this.elements[this.last] = data;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        T element = this.elements[this.first];
        this.elements[this.first] = null;
        this.updateFrontValue();
        return element;
    }

    @Override
    public T front() {
        return isEmpty() ? null : this.elements[this.first];
    }

    @Override
    public int size() {
        return this.elements.length;
    }

    @Override
    public boolean isEmpty() {
        return this.first == -1 && this.last == -1;
    }

    @Override
    public boolean isFull() {
        return !this.isEmpty() && (this.last == this.first - 1 || this.last == this.first + (this.size() - 1));
    }

    private void updateRearValue() {
        if (this.last + 1 == this.size()) {
            this.last = 0;
        } else {
            this.last++;
        }
    }

    private void updateFrontValue() {
        if (this.first == this.last) {
            this.first = -1;
            this.last = -1;
        } else if (this.first + 1 == this.size()) {
            this.first = 0;
        } else {
            this.first++;
        }
    }
}
