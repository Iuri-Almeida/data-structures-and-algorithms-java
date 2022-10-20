package br.com.itau.letscode.ialmeida.datastructure.queue;

public interface UnlimitedQueue<T> {

    void enqueue(T data);

    T dequeue();

    T front();

    int size();

    boolean isEmpty();
}
