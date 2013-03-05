package org.mcvly.algo.data.structures.trees;

/**
 * User: RMalyona
 * Time: 18:10
 */
public class Node<T extends Comparable<T>> implements Comparable<T> {
    private T key;
    int size;
    private Node<T> parent;
    private Node<T> leftChild, rightChild;

    public Node(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node<T> child) {
        this.leftChild = child;
    }

    public void setRightChild(Node<T> child) {
        this.rightChild = child;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int x) {
        this.size = x;
    }

    @Override
    public int compareTo(T o) {
        return key.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return !(key != null ? !key.equals(node.key) : node.key != null);
    }

    @Override
    public int hashCode() {

        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
