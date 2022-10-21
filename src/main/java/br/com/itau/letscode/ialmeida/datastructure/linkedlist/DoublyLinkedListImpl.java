package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

import br.com.itau.letscode.ialmeida.datastructure.node.DoublyNode;
import br.com.itau.letscode.ialmeida.datastructure.node.Node;

import java.util.Optional;

public class DoublyLinkedListImpl<T> implements DoublyLinkedList<T> {

    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int length;

    public DoublyLinkedListImpl(T data) {
        this.head = new DoublyNode<>(data);
        this.tail = this.head;
        this.length = 1;
    }

    @Override
    public void prepend(T data) {
        DoublyNode<T> node = new DoublyNode<>(data);
        node.setNext(this.head);
        this.head.setPrev(node);
        this.head = node;
        this.length++;
    }

    @Override
    public void append(T data) {
        DoublyNode<T> node = new DoublyNode<>(data);
        this.tail.setNext(node);
        node.setPrev(this.tail);
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

        DoublyNode<T> newNode = new DoublyNode<>(data);
        DoublyNode<T> curNode = this.traverseToIndex(index);

        curNode.getPrev().setNext(newNode);
        newNode.setPrev(curNode.getPrev());

        curNode.setPrev(newNode);
        newNode.setNext(curNode);

        this.length++;
    }

    @Override
    public T popLeft() {
        if (this.size() == 1) {
            throw new RuntimeException("You can't delete the head");
        }

        DoublyNode<T> oldHead = this.head;

        oldHead.getNext().setPrev(null);
        this.head = oldHead.getNext();
        oldHead.setNext(null);

        this.length--;

        return oldHead.getData();
    }

    @Override
    public T pop() {
        if (this.size() == 1) {
            throw new RuntimeException("You can't delete the head");
        }

        DoublyNode<T> oldTail = this.tail;

        oldTail.getPrev().setNext(null);
        this.tail = oldTail.getPrev();
        oldTail.setPrev(null);

        this.length--;

        return oldTail.getData();
    }

    @Override
    public void print() {
        DoublyNode<T> node = this.head;
        while (Optional.ofNullable(node).isPresent()) {
            System.out.print(node + " <- -> ");
            node = node.getNext();
        }
        System.out.println("null");
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public void reverse() {
        DoublyNode<T> node = this.tail;
        while (Optional.ofNullable(node).isPresent()) {
            System.out.print(node + " <- -> ");
            node = node.getPrev();
        }
        System.out.println("null");
    }

    private DoublyNode<T> traverseToIndex(int index) {
        int i = 0;
        DoublyNode<T> node = this.head;
        while (i != index) {
            node = node.getNext();
            i++;
        }
        return node;
    }
}
