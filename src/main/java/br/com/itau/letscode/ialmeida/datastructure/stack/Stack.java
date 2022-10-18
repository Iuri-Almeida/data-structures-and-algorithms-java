package br.com.itau.letscode.ialmeida.datastructure.stack;

public interface Stack<T> {

    void push(T data);

    T pop();

    T top();

    int size();

    boolean isEmpty();

    boolean isFull();
}
