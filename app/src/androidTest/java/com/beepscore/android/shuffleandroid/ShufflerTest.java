package com.beepscore.android.shuffleandroid;


import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevebaker on 6/12/15.
 */
public class ShufflerTest extends TestCase {

    Node start = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // reference tree graph
        // https://en.wikipedia.org/wiki/Tree_traversal
        Node nodeC = new Node("C", null, null);
        Node nodeE = new Node("E", null, null);
        Node nodeD = new Node("D", nodeC, nodeE);

        Node nodeA = new Node("A", null, null);
        Node nodeB = new Node("B", nodeA, nodeD);

        Node nodeH = new Node("H", null, null);
        Node nodeI = new Node("I", nodeH, null);
        Node nodeG = new Node("G", null, nodeI);

        Node nodeF = new Node("F", nodeB, nodeG);

        start = nodeF;
    }

    // ************************************************************************

    public void testNodeInTreeWithValueBreadthFirstValueTreeNull() {
        Shuffler shuffler = new Shuffler();
        String value = "A";
        assertNull(shuffler.nodeInTreeWithValueBreadthFirst(null, value));
    }

    public void testNodeInTreeWithValueBreadthFirstValueNull() {
        Shuffler shuffler = new Shuffler();

        Node actual = shuffler.nodeInTreeWithValueBreadthFirst(start, null);
        assertNull(actual);
    }

    public void testNodeInTreeWithValueBreadthFirst() {
        Shuffler shuffler = new Shuffler();

        String value = "I";
        Node actual = shuffler.nodeInTreeWithValueBreadthFirst(start, value);
        assertEquals(value, actual.value);
    }

    public void testNodeInTreeWithValueBreadthFirstValueNotInTree() {
        Shuffler shuffler = new Shuffler();

        String value = "X";
        Node actual = shuffler.nodeInTreeWithValueBreadthFirst(start, value);
        assertNull(actual);

        List<String> expected = new ArrayList<>();
        expected.add("F");
        expected.add("B");
        expected.add("G");
        expected.add("A");
        expected.add("D");
        expected.add("I");
        expected.add("C");
        expected.add("E");
        expected.add("H");
        assertEquals(expected, shuffler.nodesSearched);
    }
}
