package org.mcvly.algo.data.structures.trees;

/**
 * User: RMalyona
 * Date: 01.03.13
 */
public class AbstractBST<T extends Comparable<T>> implements BST<T> {
    private Node<T> root;

    public AbstractBST(Node<T> root) {
        this.root = root;
        root.setSize(1);
    }

    public Node<T> getRoot() {
        return root;
    }

    @Override
    public void insert(Node<T> node) {
        Node<T> insertAfter = root;
        Node<T> insertInto = root;
        boolean left = false;
        node.setSize(1);
        while (insertInto != null) {
            if (insertInto.compareTo(node.getKey()) > 0) { //insert into left subtree
                insertAfter = insertInto;
                insertInto = insertAfter.getLeftChild();
                left = true;
            } else { // insert into right subtree
                insertAfter = insertInto;
                insertInto = insertAfter.getRightChild();
                left = false;
            }
        }
        insertInto = node;
        insertInto.setParent(insertAfter);
        if (left) {
            insertAfter.setLeftChild(node);
        } else {
            insertAfter.setRightChild(node);
        }
        updateParentsSize(node, 1);
    }

    private void updateParentsSize(Node<T> node, int value) {
        while (node.getParent() != null) {
            node.getParent().setSize(node.getParent().getSize() + value);
            node = node.getParent();
        }
    }

    @Override
    public void delete(Node<T> node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.equals(node.getParent().getLeftChild())) {
                node.getParent().setLeftChild(null);
            } else if (node.equals(node.getParent().getRightChild())) {
                node.getParent().setRightChild(null);
            } else if (node.equals(root)) {
                root = null;
            }
            updateParentsSize(node, -1);
            node.setParent(null);
        }  else if (node.getLeftChild() == null && node.getRightChild() != null ||
                node.getLeftChild() != null && node.getRightChild() == null) {
            updateParentsSize(node, -1);
            if (node.getLeftChild() != null) {
                if (node.getParent() != null) {
                    if (node.equals(node.getParent().getLeftChild())) {
                        node.getParent().setLeftChild(node.getLeftChild());
                    } else {
                        node.getParent().setRightChild(node.getLeftChild());
                    }
                } else {
                    root = node.getLeftChild();
                }
                node.getLeftChild().setParent(node.getParent());
                node.setLeftChild(null);
                node.setParent(null);
            } else {
                if (node.getParent() != null) {
                    if (node.equals(node.getParent().getLeftChild())) {
                        node.getParent().setLeftChild(node.getRightChild());
                    } else {
                        node.getParent().setRightChild(node.getRightChild());
                    }
                } else {
                    root = node.getRightChild();
                }
                node.getRightChild().setParent(node.getParent());
                node.setRightChild(null);
                node.setParent(null);
            }
        } else {
            Node<T> predecessor = predecessor(node.getKey()); // doesn't have right child
            if (predecessor.getRightChild() != null) {
                throw new RuntimeException("Error in delete");
            }
            swap(node, predecessor);
            delete(node);
        }
    }

    @Override
    public Node<T> search(T key) {
        Node<T> currentNode = root;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode;
            }
            if (currentNode.compareTo(key) > 0) { // left subtree
                currentNode = currentNode.getLeftChild();
            } else { // right subtree
                currentNode = currentNode.getRightChild();
            }
        }
        return null;
    }

    @Override
    public Node<T> select(int i) {
        return select(root, i);
    }

    private Node<T> select(Node<T> node, int i) {
        if (node == null) {
            return null;
        } else if (node.getSize() == 1 && i ==1) {
            return node;
        } else if (node.getSize() >= 2) {
            int a = node.getLeftChild() == null ? 0 : node.getLeftChild().getSize();
            if (a == i-1) {
                return node;
            } else if (a >= i) {
                return select(node.getLeftChild(), i);
            } else {
                return select(node.getRightChild(), i-a-1);
            }
        }
        return null;
    }

    @Override
    public Node<T> min() {
        Node<T> result = null, tempNode = root;
        while (tempNode != null) {
            result = tempNode;
            tempNode = tempNode.getLeftChild();
        }
        return result;
    }

    @Override
    public Node<T> max() {
        Node<T> result = null, tempNode = root;
        while (tempNode != null) {
            result = tempNode;
            tempNode = tempNode.getRightChild();
        }
        return result;
    }

    @Override
    public Node<T> predecessor(T key) {
        Node<T> node = search(key);
        if (node == null) {
            return null;
        }
        Node<T> result = null;
        if (node.getLeftChild() != null) { // max of left subtree
            Node<T> tempNode = node.getLeftChild();
            while (tempNode != null) {
                result = tempNode;
                tempNode = tempNode.getRightChild();
            }
            return result;
        } else { // traverse up until get key less than k
            Node<T> parent = node.getParent();
            while (parent != null) {
                if (parent.compareTo(key) < 0) {
                    return parent;
                }
                parent = parent.getParent();
            }
            return null;
        }
    }

    /**
     * number of keyes <= given value
     */
    @Override
    public int rank(Node<T> node) {
        return node.getLeftChild() == null ? 0 : node.getLeftChild().getSize();
    }

    @Override
    public String traverseInOrder() {
        return traverse(root);
    }

    private String traverse(Node<T> node) {
        if (node != null) {
            return traverse(node.getLeftChild()) + node.getKey() + traverse(node.getRightChild());
        }
        return "";
    }

    public void swap(Node<T> a, Node<T> b) {
        // 1. parent
        Node<T> temp;

        if (b.getParent() != null) {
            if (b.equals(b.getParent().getLeftChild())) {
                b.getParent().setLeftChild(a);
            } else if (b.equals(b.getParent().getRightChild())) {
                b.getParent().setRightChild(a);
            }
        } else {
            root = a;
        }

        if (a.getParent() != null) {
            if (a.equals(a.getParent().getLeftChild())) { // a's parent
                a.getParent().setLeftChild(b);
            } else if(a.equals(a.getParent().getRightChild())) {
                a.getParent().setRightChild(b);
            }
        } else {
            root = b;
        }

        if (a.equals(b.getParent())) {
            b.setParent(a.getParent());
            a.setParent(b);
        } else if (b.equals(a.getParent())) {
            a.setParent(b.getParent());
            b.setParent(a);
        } else {
            temp = a.getParent();
            a.setParent(b.getParent());
            b.setParent(temp);
        }

        // 2. children
        temp = a.getLeftChild();
        a.setLeftChild(b.getLeftChild());
        b.setLeftChild(temp);
        temp = a.getRightChild();
        a.setRightChild(b.getRightChild());
        b.setRightChild(temp);

        int t = a.getSize();
        a.setSize(b.getSize());
        b.setSize(t);
    }
}
