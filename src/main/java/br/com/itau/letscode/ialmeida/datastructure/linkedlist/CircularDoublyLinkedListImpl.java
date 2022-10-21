package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

import br.com.itau.letscode.ialmeida.datastructure.node.DoublyNode;

import java.util.Optional;

public class CircularDoublyLinkedListImpl<T> implements CircularDoublyLinkedList<T> {

    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int length;

    public CircularDoublyLinkedListImpl(T data) {
        this.head = new DoublyNode<>(data);
        this.tail = this.head;
        this.length = 1;
    }

    @Override
    public void insert(T data) {
        if (this.exists(data)) {
            throw new RuntimeException("No duplicated values allowed.");
        }
        DoublyNode<T> node = new DoublyNode<>(data);

        this.head.setPrev(node);
        node.setNext(this.head);

        node.setPrev(this.tail);
        this.tail.setNext(node);

        this.tail = node;

        this.length++;
    }

    @Override
    public T remove(T data) {
        if (this.size() == 1) {
            throw new RuntimeException("You can't remove the last element.");
        }

        DoublyNode<T> removedNode = this.traverseToData(data);

        removedNode.getPrev().setNext(removedNode.getNext());
        removedNode.getNext().setPrev(removedNode.getPrev());

        if (removedNode.equals(this.head)) {
            this.head = this.head.getNext();
        }

        if (removedNode.equals(this.tail)) {
            this.tail = this.tail.getPrev();
        }

        this.length--;

        return removedNode.getData();
    }

    private DoublyNode<T> traverseToData(T data) {
        DoublyNode<T> node = this.head;
        while (!node.equals(this.tail) && !node.getData().equals(data)) {
            node = node.getNext();
        }

        if (node.getData().equals(data)) {
            return node;
        }

        throw new RuntimeException("Couldn't find data = " + data);
    }

    @Override
    public boolean exists(T data) {
        return Optional.ofNullable(this.search(data)).isPresent();
    }

    private DoublyNode<T> search(T data) {
        DoublyNode<T> curNode = this.head;

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
        DoublyNode<T> node = this.head;
        while (!node.equals(this.tail)) {
            System.out.print(node + " <- -> ");
            node = node.getNext();
        }
        System.out.println(this.tail);
    }

    @Override
    public void reverse() {
        DoublyNode<T> node = this.tail;
        while (!node.equals(this.head)) {
            System.out.print(node + " <- -> ");
            node = node.getPrev();
        }
        System.out.println(this.head);
    }
}
