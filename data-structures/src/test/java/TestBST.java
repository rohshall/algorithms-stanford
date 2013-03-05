import junit.framework.Assert;
import org.junit.Test;
import org.mcvly.algo.data.structures.trees.AbstractBST;
import org.mcvly.algo.data.structures.trees.Node;
import org.mcvly.algo.data.structures.trees.SimpleBST;

/**
 * User: RMalyona
 * Date: 01.03.13
 */
public class TestBST {
    @Test
    public void testInsert() {
        Node<Integer> root = new Node<Integer>(2);
        Node<Integer> left = new Node<Integer>(1);
        Node<Integer> right = new Node<Integer>(3);

        AbstractBST<Integer> simpleBST = new SimpleBST<Integer>(root);
        simpleBST.insert(left);
        simpleBST.insert(right);

        Assert.assertEquals(root, simpleBST.getRoot());
        Assert.assertEquals(left, simpleBST.getRoot().getLeftChild());
        Assert.assertEquals(right, simpleBST.getRoot().getRightChild());

        Assert.assertEquals(3, simpleBST.getRoot().getSize());
        Assert.assertEquals(1, simpleBST.search(1).getSize());
    }

    @Test
    public void testSearch() {
        AbstractBST<Integer> simpleBST = generateTree();

        Assert.assertEquals(new Node<Integer>(5), simpleBST.search(5));
        Assert.assertEquals(new Node<Integer>(1), simpleBST.search(1));
        Assert.assertEquals(new Node<Integer>(4), simpleBST.search(4));
    }

    @Test
    public void testMin() {
        AbstractBST<Integer> simpleBST = generateTree();

        Assert.assertEquals(new Node<Integer>(1), simpleBST.min());
    }

    @Test
    public void testMax() {
        AbstractBST<Integer> simpleBST = generateTree();

        Assert.assertEquals(new Node<Integer>(6), simpleBST.max());
    }

    @Test
    public void testPredecessor() {
        AbstractBST<Integer> simpleBST = generateTree();

        Assert.assertEquals(new Node<Integer>(2), simpleBST.predecessor(3));
        Assert.assertEquals(new Node<Integer>(5), simpleBST.predecessor(6));
        Assert.assertNull(simpleBST.predecessor(1));
    }

    @Test
    public void testTraverse() {
        AbstractBST<Integer> simpleBST = generateTree();
        Assert.assertEquals("123456", simpleBST.traverseInOrder());
    }

    @Test
    public void testDelete() {
        AbstractBST<Integer> simpleBST = generateTree();
        simpleBST.delete(simpleBST.search(1));
        Assert.assertNull(simpleBST.search(1));
        Assert.assertEquals(6, simpleBST.getRoot().getSize());

        simpleBST.delete(simpleBST.search(6));
        Assert.assertNull(simpleBST.search(6));
        Assert.assertEquals(5, simpleBST.getRoot().getSize());

        simpleBST.delete(simpleBST.search(3));
        Assert.assertNull(simpleBST.search(3));
        Assert.assertEquals(4, simpleBST.getRoot().getSize());
    }

    @Test
    public void testSwap() {
        AbstractBST<Integer> simpleBST = generateTree();
        // not easy case. 1 node has 1 child, another - 2
        Node<Integer> a = simpleBST.search(2);
        Node<Integer> b = simpleBST.search(5);
        simpleBST.swap(a,b);

        Assert.assertEquals(simpleBST.getRoot(), b.getParent());
        Assert.assertEquals(simpleBST.getRoot().getLeftChild(), b);
        Assert.assertEquals(simpleBST.getRoot(), a.getParent());
        Assert.assertEquals(simpleBST.getRoot().getRightChild(), a);

        Assert.assertEquals(new Node<Integer>(1), b.getLeftChild());
        Assert.assertNull(b.getRightChild());
        Assert.assertEquals(new Node<Integer>(4), a.getLeftChild());
        Assert.assertEquals(new Node<Integer>(6), a.getRightChild());

        // easy case. no children
        simpleBST = generateTree();
        a = simpleBST.search(1);
        b = simpleBST.search(7);
        simpleBST.swap(a,b);
        Assert.assertNull(a.getLeftChild());
        Assert.assertNull(a.getRightChild());
        Assert.assertNull(b.getLeftChild());
        Assert.assertNull(b.getRightChild());
        Assert.assertEquals(simpleBST.search(2), b.getParent());
        Assert.assertEquals(simpleBST.search(2).getLeftChild(), b);
        Assert.assertEquals(simpleBST.search(6), a.getParent());
        Assert.assertEquals(simpleBST.search(6).getRightChild(), a);

        // swap root
        simpleBST = generateTree();
        a = simpleBST.search(3);
        b = simpleBST.search(2);
        simpleBST.swap(a, b);
        Assert.assertNull(b.getParent());
        Assert.assertEquals(simpleBST.getRoot(), a.getParent());
        Assert.assertEquals(simpleBST.getRoot().getLeftChild(), a);

        Assert.assertNull(a.getRightChild());
        Assert.assertEquals(new Node<Integer>(1), a.getLeftChild());

        Assert.assertEquals(new Node<Integer>(5), b.getRightChild());
    }

    @Test
    public void testSelect() {
        AbstractBST<Integer> simpleBST = generateTree();
        Assert.assertEquals(new Node<Integer>(3), simpleBST.select(3));
        Assert.assertEquals(new Node<Integer>(2), simpleBST.select(2));
        Assert.assertEquals(new Node<Integer>(1), simpleBST.select(1));
        Assert.assertEquals(new Node<Integer>(4), simpleBST.select(4));
        Assert.assertEquals(new Node<Integer>(5), simpleBST.select(5));
        Assert.assertEquals(new Node<Integer>(6), simpleBST.select(6));
        Assert.assertEquals(new Node<Integer>(7), simpleBST.select(7));
    }

    @Test
    public void testRank() {
        AbstractBST<Integer> simpleBST = generateTree();
        Assert.assertEquals(2, simpleBST.rank(simpleBST.getRoot()));
        Assert.assertEquals(1, simpleBST.rank(simpleBST.search(2)));
        Assert.assertEquals(0, simpleBST.rank(simpleBST.search(1)));
        Assert.assertEquals(1, simpleBST.rank(simpleBST.search(5)));
        Assert.assertEquals(0, simpleBST.rank(simpleBST.search(4)));
        Assert.assertEquals(0, simpleBST.rank(simpleBST.search(6)));
        Assert.assertEquals(0, simpleBST.rank(simpleBST.search(7)));
    }

    private AbstractBST<Integer> generateTree() {
        Node<Integer> root = new Node<Integer>(3);
        Node<Integer> firstLeft = new Node<Integer>(2);
        Node<Integer> firstLeftLeft = new Node<Integer>(1);
        Node<Integer> firstRight = new Node<Integer>(5);
        Node<Integer> firstRightLeft = new Node<Integer>(4);
        Node<Integer> firstRightRight = new Node<Integer>(6);
        Node<Integer> secondRightRight = new Node<Integer>(7);

        AbstractBST<Integer> simpleBST = new SimpleBST<Integer>(root);
        simpleBST.insert(firstLeft);
        simpleBST.insert(firstRight);
        simpleBST.insert(firstLeftLeft);
        simpleBST.insert(firstRightLeft);
        simpleBST.insert(firstRightRight);
        simpleBST.insert(secondRightRight);
        return simpleBST;
    }
}
