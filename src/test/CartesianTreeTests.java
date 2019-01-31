import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianTreeTests {
    CartesianTree<Integer> cartesianTree = new CartesianTree<Integer>();

    @Test
    void addAndContainsTest() {
        cartesianTree.add(1);
        cartesianTree.add(2);
        cartesianTree.add(3);
        assertTrue(cartesianTree.contains(1));
        assertFalse(cartesianTree.contains(5));
        assertTrue(cartesianTree.contains(2));
        assertTrue(cartesianTree.contains(3));
    }

    @Test
    void removeTest() {
        cartesianTree.add(23);
        cartesianTree.add(11);
        cartesianTree.add(46);
        cartesianTree.remove(11);
        assertFalse(cartesianTree.contains(11));
    }
}