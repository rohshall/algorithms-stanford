package org.mcvly.algo.data.structures.trees;

/**
 * User: RMalyona
 * Date: 01.03.13
 */
public interface BST<T extends Comparable<T>> {
    void insert(Node<T> node);
    void delete(Node<T> node);
    Node<T> search(T key);
    Node<T> select(int i);
    Node<T> min();
    Node<T> max();
    Node<T> predecessor(T key);
    int rank(Node<T> node);
    String traverseInOrder();
}
