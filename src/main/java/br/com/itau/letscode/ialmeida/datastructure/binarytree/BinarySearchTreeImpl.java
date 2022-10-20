package br.com.itau.letscode.ialmeida.datastructure.binarytree;

import br.com.itau.letscode.ialmeida.datastructure.node.TreeNode;
import br.com.itau.letscode.ialmeida.datastructure.queue.UnlimitedQueue;
import br.com.itau.letscode.ialmeida.datastructure.queue.UnlimitedQueueImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private TreeNode<T> root;
    private int length;

    public BinarySearchTreeImpl(T value) {
        this.root = new TreeNode<>(value);
        this.length = 1;
    }

    @Override
    public void insert(T data) {
        this.insert(this.root, data);
        this.length++;
    }

    private void insert(TreeNode<T> curNode, T data) {
        TreeNode<T> node = new TreeNode<>(data);

        while (true) {
            if (node.getData().compareTo(curNode.getData()) > 0) {
                if (Optional.ofNullable(curNode.getRight()).isEmpty()) {
                    curNode.setRight(node);
                    return;
                }
                curNode = curNode.getRight();
            } else if (node.getData().compareTo(curNode.getData()) < 0) {
                if (Optional.ofNullable(curNode.getLeft()).isEmpty()) {
                    curNode.setLeft(node);
                    return;
                }
                curNode = curNode.getLeft();
            } else {
                throw new IllegalArgumentException("No duplicated values allowed.");
            }
        }
    }

    @Override
    public void insertRecursive(T data) {
        this.insertRecursive(this.root, data);
        this.length++;
    }

    private void insertRecursive(TreeNode<T> curNode, T data) {
        TreeNode<T> node = new TreeNode<>(data);

        if (node.getData().compareTo(curNode.getData()) > 0) {
            if (Optional.ofNullable(curNode.getRight()).isEmpty()) {
                curNode.setRight(node);
            } else {
                this.insertRecursive(curNode.getRight(), data);
            }
        } else if (node.getData().compareTo(curNode.getData()) < 0) {
            if (Optional.ofNullable(curNode.getLeft()).isEmpty()) {
                curNode.setLeft(node);
            } else {
                this.insertRecursive(curNode.getLeft(), data);
            }
        } else {
            throw new IllegalArgumentException("No duplicated values allowed.");
        }
    }

    @Override
    public T remove(T data) {
        if (this.size() == 1) {
            throw new RuntimeException("Tree has only one element.");
        }

        TreeNode<T> curNode = this.root;
        TreeNode<T> prevNode = curNode;

        while (Optional.ofNullable(curNode).isPresent()) {
            if (data.compareTo(curNode.getData()) > 0) {
                prevNode = curNode;
                curNode = curNode.getRight();
            } else if (data.compareTo(curNode.getData()) < 0) {
                prevNode = curNode;
                curNode = curNode.getLeft();
            } else {
                if (curNode.getData().compareTo(prevNode.getData()) > 0) {  // right child
                    if (Optional.ofNullable(curNode.getRight()).isPresent()) {  // with right child
                        TreeNode<T> rightNode = curNode.getRight();

                        while (Optional.ofNullable(rightNode.getLeft()).isPresent() && Optional.ofNullable(rightNode.getLeft().getLeft()).isPresent()) {
                            rightNode = rightNode.getLeft();
                        }

                        if (Optional.ofNullable(rightNode.getLeft()).isEmpty()) {  // right child with no left child
                            prevNode.setRight(rightNode);
                            rightNode.setLeft(curNode.getLeft());
                        } else {  // right child with left child
                            prevNode.setRight(rightNode.getLeft());
                            rightNode.getLeft().setLeft(curNode.getLeft());
                            rightNode.setLeft(rightNode.getLeft().getRight());
                            prevNode.getRight().setRight(curNode.getRight());
                        }
                    } else {  // no right child
                        prevNode.setRight(curNode.getLeft());
                    }
                } else if (curNode.getData().compareTo(prevNode.getData()) < 0) {  // left child
                    if (Optional.ofNullable(curNode.getRight()).isPresent()) {  // with right child
                        TreeNode<T> rightNode = curNode.getRight();

                        while (Optional.ofNullable(rightNode.getLeft()).isPresent() && Optional.ofNullable(rightNode.getLeft().getLeft()).isPresent()) {
                            rightNode = rightNode.getLeft();
                        }

                        if (Optional.ofNullable(rightNode.getLeft()).isEmpty()) {  // right child with no left child
                            prevNode.setLeft(rightNode);
                            rightNode.setLeft(curNode.getLeft());
                        } else {  // right child with left child
                            prevNode.setLeft(rightNode.getLeft());
                            rightNode.getLeft().setLeft(curNode.getLeft());
                            rightNode.setLeft(rightNode.getLeft().getRight());
                            prevNode.getLeft().setRight(curNode.getRight());
                        }
                    } else {  // no right child
                        prevNode.setLeft(curNode.getLeft());
                    }
                } else {  // root node
                    if (Optional.ofNullable(curNode.getRight()).isPresent()) {  // with right child
                        TreeNode<T> rightNode = curNode.getRight();

                        while (Optional.ofNullable(rightNode.getLeft()).isPresent() && Optional.ofNullable(rightNode.getLeft().getLeft()).isPresent()) {
                            rightNode = rightNode.getLeft();
                        }

                        if (Optional.ofNullable(rightNode.getLeft()).isEmpty()) {  // right child with no left child
                            rightNode.setLeft(curNode.getLeft());
                            this.root = rightNode;
                        } else {  // right child with left child
                            rightNode.getLeft().setLeft(curNode.getLeft());
                            TreeNode<T> aux = rightNode.getLeft().getRight();
                            rightNode.getLeft().setRight(curNode.getRight());
                            this.root = rightNode.getLeft();
                            rightNode.setLeft(aux);
                        }
                    } else {  // no right child
                        this.root = curNode.getLeft();
                    }
                }
                this.length--;
                return curNode.getData();
            }
        }
        throw new RuntimeException("Value = " + data + " not found.");
    }

    @Override
    public boolean exists(T data) {
        return Optional.ofNullable(this.search(data)).isPresent();
    }

    @Override
    public TreeNode<T> retrieve(T data) {
        return this.search(data);
    }

    private TreeNode<T> search(T data) {
        TreeNode<T> curNode = this.root;

        while (Optional.ofNullable(curNode).isPresent()) {
            if (data.compareTo(curNode.getData()) > 0) {
                curNode = curNode.getRight();
            } else if (data.compareTo(curNode.getData()) < 0) {
                curNode = curNode.getLeft();
            } else {
                return curNode;
            }
        }
        return null;
    }

    @Override
    public void preOrder() {
        List<TreeNode<T>> nodes = new ArrayList<>();
        System.out.println(this.preOrder(this.root, nodes));
    }

    private List<TreeNode<T>> preOrder(TreeNode<T> curNode, List<TreeNode<T>> nodes) {
        nodes.add(curNode);

        if (Optional.ofNullable(curNode.getLeft()).isPresent()) {
            this.preOrder(curNode.getLeft(), nodes);
        }

        if (Optional.ofNullable(curNode.getRight()).isPresent()) {
            this.preOrder(curNode.getRight(), nodes);
        }

        return nodes;
    }

    @Override
    public void inOrder() {
        List<TreeNode<T>> nodes = new ArrayList<>();
        System.out.println(this.inOrder(this.root, nodes));
    }

    private List<TreeNode<T>> inOrder(TreeNode<T> curNode, List<TreeNode<T>> nodes) {
        if (Optional.ofNullable(curNode.getLeft()).isPresent()) {
            this.inOrder(curNode.getLeft(), nodes);
        }

        nodes.add(curNode);

        if (Optional.ofNullable(curNode.getRight()).isPresent()) {
            this.inOrder(curNode.getRight(), nodes);
        }

        return nodes;
    }

    @Override
    public void posOrder() {
        List<TreeNode<T>> nodes = new ArrayList<>();
        System.out.println(this.posOrder(this.root, nodes));
    }

    private List<TreeNode<T>> posOrder(TreeNode<T> curNode, List<TreeNode<T>> nodes) {
        if (Optional.ofNullable(curNode.getLeft()).isPresent()) {
            this.posOrder(curNode.getLeft(), nodes);
        }

        if (Optional.ofNullable(curNode.getRight()).isPresent()) {
            this.posOrder(curNode.getRight(), nodes);
        }

        nodes.add(curNode);

        return nodes;
    }

    @Override
    public void bfs() {
        TreeNode<T> curNode = this.root;
        List<TreeNode<T>> nodes = new ArrayList<>();
        UnlimitedQueue<TreeNode<T>> queue = new UnlimitedQueueImpl<>();

        queue.enqueue(curNode);

        while (queue.size() > 0) {
            curNode = queue.dequeue();
            nodes.add(curNode);

            if (Optional.ofNullable(curNode.getLeft()).isPresent()) {
                queue.enqueue(curNode.getLeft());
            }

            if (Optional.ofNullable(curNode.getRight()).isPresent()) {
                queue.enqueue(curNode.getRight());
            }
        }

        System.out.println(nodes);
    }

    @Override
    public void bfsRecursive() {
        UnlimitedQueue<TreeNode<T>> queue = new UnlimitedQueueImpl<>();
        List<TreeNode<T>> nodes = new ArrayList<>();

        queue.enqueue(this.root);
        System.out.println(this.bfsRecursive(queue, nodes));
    }

    private List<TreeNode<T>> bfsRecursive(UnlimitedQueue<TreeNode<T>> queue, List<TreeNode<T>> nodes) {
        if (queue.size() == 0) {
            return nodes;
        }

        TreeNode<T> curNode = queue.dequeue();
        nodes.add(curNode);

        if (Optional.ofNullable(curNode.getLeft()).isPresent()) {
            queue.enqueue(curNode.getLeft());
        }

        if (Optional.ofNullable(curNode.getRight()).isPresent()) {
            queue.enqueue(curNode.getRight());
        }

        return this.bfsRecursive(queue, nodes);
    }

    @Override
    public int size() {
        return this.length;
    }

}
