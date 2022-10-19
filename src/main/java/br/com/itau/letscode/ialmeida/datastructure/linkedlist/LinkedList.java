package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

public interface LinkedList<T> {

    void prepend(T data);

    void append(T data);

    void insert(T data, int index);

    T popLeft();

    T pop();

    void print();

    int size();

    boolean isEmpty();

}
