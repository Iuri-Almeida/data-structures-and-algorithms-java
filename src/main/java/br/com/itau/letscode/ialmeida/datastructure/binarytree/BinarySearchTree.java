package br.com.itau.letscode.ialmeida.datastructure.binarytree;

import br.com.itau.letscode.ialmeida.datastructure.node.TreeNode;

public interface BinarySearchTree<T extends Comparable<T>> {


    void insert(T data);

    void insertRecursive(T data);

    T remove(T data);

    boolean exists(T data);

    TreeNode<T> retrieve(T data);

    void preOrder();

    void inOrder();

    void posOrder();

    void bfs();

    void bfsRecursive();

    int size();
}
