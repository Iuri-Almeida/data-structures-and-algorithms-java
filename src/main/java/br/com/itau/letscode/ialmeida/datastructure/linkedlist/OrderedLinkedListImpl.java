package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

import br.com.itau.letscode.ialmeida.datastructure.node.Node;

import java.util.Optional;

public class OrderedLinkedListImpl<T extends Comparable<T>> implements OrderedLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public OrderedLinkedListImpl(T data) {
        this.head = new Node<>(data);
        this.tail = this.head;
        this.length = 1;
    }

    @Override
    public void insert(T data) {
        if (data.compareTo(this.head.getData()) <= 0) {
            this.prepend(data);
            return;
        }

        if (data.compareTo(this.tail.getData()) >= 0) {
            this.append(data);
            return;
        }

        Node<T> curNode = this.head;
        Node<T> newNode = new Node<>(data);

        while (newNode.getData().compareTo(curNode.getNext().getData()) > 0) {
            curNode = curNode.getNext();
        }

        newNode.setNext(curNode.getNext());
        curNode.setNext(newNode);

        this.length++;
    }

    private void prepend(T data) {
        Node<T> node = new Node<>(data);
        node.setNext(this.head);
        this.head = node;
        this.length++;
    }

    public void append(T data) {
        Node<T> node = new Node<>(data);
        this.tail.setNext(node);
        this.tail = node;
        this.length++;
    }

    @Override
    public T remove(T data) {
        if (this.size() == 1) {
            throw new RuntimeException("You can't delete the head");
        }

        if (data.equals(this.head.getData())) {
            return this.popLeft();
        }

        Node<T> prevNode = this.traverseToDataBefore(data);
        Node<T> removedNode = prevNode.getNext();
        prevNode.setNext(removedNode.getNext());

        this.length--;

        return removedNode.getData();
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

    private Node<T> traverseToDataBefore(T data) {
        Node<T> node = this.head;
        while (Optional.ofNullable(node.getNext()).isPresent() && !node.getNext().getData().equals(data)) {
            node = node.getNext();
        }

        if (Optional.ofNullable(node.getNext()).isEmpty()) {
            throw new RuntimeException("Couldn't find data = " + data);
        }

        return node;
    }

    private T popLeft() {
        Node<T> oldHead = this.head;
        this.head = this.head.getNext();
        this.length--;
        return oldHead.getData();
    }

}
