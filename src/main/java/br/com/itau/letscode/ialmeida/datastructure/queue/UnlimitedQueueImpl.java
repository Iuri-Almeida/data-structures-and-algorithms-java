package br.com.itau.letscode.ialmeida.datastructure.queue;


import br.com.itau.letscode.ialmeida.datastructure.node.Node;

public class UnlimitedQueueImpl<T> implements UnlimitedQueue<T> {
    
    private Node<T> head;
    private Node<T> tail;
    private int length;
    
    public UnlimitedQueueImpl() {
    }

    @Override
    public void enqueue(T data) {
        Node<T> node = new Node<>(data);

        if (this.isEmpty()) {
            this.head = node;
            this.tail = this.head;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }

        this.length++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }

        Node<T> oldHead = this.head;
        this.head = this.head.getNext();

        this.length--;

        return oldHead.getData();
    }

    @Override
    public T front() {
        return this.isEmpty() ? null : this.head.getData();
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

}
