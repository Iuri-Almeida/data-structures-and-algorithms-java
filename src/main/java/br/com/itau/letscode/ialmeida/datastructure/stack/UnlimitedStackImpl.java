package br.com.itau.letscode.ialmeida.datastructure.stack;

import java.util.ArrayList;
import java.util.List;

public class UnlimitedStackImpl<T> implements UnlimitedStack<T> {

    private final List<T> elements;

    public UnlimitedStackImpl() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void push(T data) {
        this.elements.add(data);
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return this.elements.remove(this.elements.size() - 1);
    }

    @Override
    public T top() {
        return this.isEmpty() ? null : this.elements.get(this.size() - 1);
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }
}
