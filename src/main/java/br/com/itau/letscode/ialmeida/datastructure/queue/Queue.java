package br.com.itau.letscode.ialmeida.datastructure.queue;

public interface Queue<T> {

    void enqueue(T data);

    T dequeue();

    T front();

    int size();

    boolean isEmpty();

    boolean isFull();
}
