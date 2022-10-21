package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

public interface CircularLinkedList<T> {

    void insert(T data);

    T remove(T data);

    boolean exists(T data);

    int size();

    void print();
}
