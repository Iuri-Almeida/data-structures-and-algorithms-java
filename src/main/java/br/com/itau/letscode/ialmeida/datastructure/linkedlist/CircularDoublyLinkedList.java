package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

public interface CircularDoublyLinkedList<T> {

    void insert(T data);

    T remove(T data);

    boolean exists(T data);

    int size();

    void print();

    void reverse();

}
