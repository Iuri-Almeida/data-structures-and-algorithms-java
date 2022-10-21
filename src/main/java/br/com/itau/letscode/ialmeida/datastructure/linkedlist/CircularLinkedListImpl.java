package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

import br.com.itau.letscode.ialmeida.datastructure.node.Node;

import java.util.Optional;

public class CircularLinkedListImpl<T> implements CircularLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public CircularLinkedListImpl(T data) {
        this.head = new Node<>(data);
        this.tail = this.head;
        this.tail.setNext(this.head);
        this.length = 1;
    }

    @Override
    public void insert(T data) {
        if (this.exists(data)) {
            throw new RuntimeException("No duplicated values allowed.");
        }
        Node<T> node = new Node<>(data);
        node.setNext(this.head);
        this.tail.setNext(node);
        this.tail = node;
        this.length++;
    }

    @Override
    public T remove(T data) {
        if (this.size() == 1) {
            throw new RuntimeException("You can't remove the last element.");
        }

        Node<T> prevNode = this.traverseToDataBefore(data);
        Node<T> removedNode = prevNode.getNext();
        prevNode.setNext(removedNode.getNext());

        if (removedNode.equals(this.head)) {
            this.head = prevNode.getNext();
        }

        if (removedNode.equals(this.tail)) {
            this.tail = prevNode;
        }

        this.length--;

        return removedNode.getData();
    }

    private Node<T> traverseToDataBefore(T data) {
        Node<T> node = this.head;
        while (!node.equals(this.tail) && !node.getNext().getData().equals(data)) {
            node = node.getNext();
        }

        if (node.getNext().getData().equals(data)) {
            return node;
        }

        throw new RuntimeException("Couldn't find data = " + data);
    }

    @Override
    public boolean exists(T data) {
        return Optional.ofNullable(this.search(data)).isPresent();
    }

    private Node<T> search(T data) {
        Node<T> curNode = this.head;

        while (!curNode.equals(this.tail)) {
            if (data.equals(curNode.getData())) {
                return curNode;
            }
            curNode = curNode.getNext();
        }
        return data.equals(curNode.getData()) ? curNode : null;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public void print() {
        Node<T> node = this.head;
        while (!node.equals(this.tail)) {
            System.out.print(node + " --> ");
            node = node.getNext();
        }
        System.out.println(this.tail);
    }
}
