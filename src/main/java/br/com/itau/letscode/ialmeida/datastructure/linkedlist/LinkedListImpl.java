package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

import br.com.itau.letscode.ialmeida.datastructure.node.Node;

import java.util.Optional;

public class LinkedListImpl<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public LinkedListImpl(T value) {
        this.head = new Node<>(value);
        this.tail = this.head;
        this.length = 1;
    }

    @Override
    public void prepend(T data) {
        Node<T> node = new Node<>(data);
        node.setNext(this.head);
        this.head = node;
        this.length++;
    }

    @Override
    public void append(T data) {
        Node<T> node = new Node<>(data);
        this.tail.setNext(node);
        this.tail = node;
        this.length++;
    }

    @Override
    public void insert(T data, int index) {
        if (index < 0 || index >= this.size()) {
            throw new IllegalArgumentException("Can't find index = " + index);
        }

        if (index == 0) {
            this.prepend(data);
            return;
        }

        Node<T> newNode = new Node<>(data);
        Node<T> node = this.traverseToIndex(index - 1);
        Node<T> nextNode = node.getNext();

        node.setNext(newNode);
        newNode.setNext(nextNode);

        this.length++;
    }

    @Override
    public T popLeft() {
        if (this.length == 1) {
            throw new RuntimeException("You can't delete the head");
        }
        Node<T> oldHead = this.head;
        this.head = this.head.getNext();
        this.length--;
        return oldHead.getData();
    }

    @Override
    public T pop() {
        if (this.length == 1) {
            throw new RuntimeException("You can't delete the head");
        }
        Node<T> node = this.traverseToIndex(this.length - 2);
        Node<T> oldTail = node.getNext();
        node.setNext(null);
        this.tail = node;
        this.length--;
        return oldTail.getData();
    }

    @Override
    public void print() {
        Node<T> node = this.head;
        while (Optional.ofNullable(node).isPresent()) {
            System.out.print(node + " --> ");
            node = node.getNext();
        }
        System.out.println("null");
    }

    @Override
    public int size() {
        return this.length;
    }

    private Node<T> traverseToIndex(int index) {
        int i = 0;
        Node<T> node = this.head;
        while (i != index) {
            node = node.getNext();
            i++;
        }
        return node;
    }

}
