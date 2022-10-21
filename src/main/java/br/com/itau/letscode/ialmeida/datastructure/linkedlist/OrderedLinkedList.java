package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

public interface OrderedLinkedList<T extends Comparable<T>> {

    void insert(T data);

    T remove(T data);

    void print();

    int size();
}
