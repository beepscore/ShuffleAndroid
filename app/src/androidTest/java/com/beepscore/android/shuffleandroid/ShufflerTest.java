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
        Node nodeC = new Node("C", null, null, null, null);
        Node nodeE = new Node("E", null, null, null, null);
        Node nodeD = new Node("D", null, null, nodeC, nodeE);

        Node nodeA = new Node("A", null, null, null, null);
        Node nodeB = new Node("B", null, null, nodeA, nodeD);

        Node nodeH = new Node("H", null, null, null, null);
        Node nodeI = new Node("I", null, null, nodeH, null);
        Node nodeG = new Node("G", null, null, null, nodeI);

        Node nodeF = new Node("F", null, null, nodeB, nodeG);

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

    //==========================================================================

    public void testIsValidShuffleForEdgeCasesShuffledStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleForEdgeCases(null, null, null));

        assertFalse(shuffler.isValidShuffleForEdgeCases(null, "a", null));
        assertFalse(shuffler.isValidShuffleForEdgeCases(null, null, "b"));
        assertFalse(shuffler.isValidShuffleForEdgeCases(null, "a", "b"));
    }

    public void testIsValidShuffleForEdgeCasesShuffledStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertFalse(shuffler.isValidShuffleForEdgeCases("", null, null));
        assertFalse(shuffler.isValidShuffleForEdgeCases("", "a", null));
        assertFalse(shuffler.isValidShuffleForEdgeCases("", null, "xy"));
        assertFalse(shuffler.isValidShuffleForEdgeCases("", "a", "b"));
    }

    public void testIsValidShuffleForEdgeCasesSourceStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", "abc", null));
        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", null, "abc"));

        assertFalse(shuffler.isValidShuffleForEdgeCases("abc", null, null));

        assertNull(shuffler.isValidShuffleForEdgeCases("a", null, "ab"));
        assertNull(shuffler.isValidShuffleForEdgeCases("ab", null, "abc"));
        assertNull(shuffler.isValidShuffleForEdgeCases("abc", null, "ab"));
    }

    public void testIsValidShuffleForEdgeCasesSourceStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleForEdgeCases("", "", ""));
        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", "abc", ""));
        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", "", "abc"));

        assertNull(shuffler.isValidShuffleForEdgeCases("abc", "", ""));
    }

    //==========================================================================

    public void testIsValidShuffleShuffledStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle(null, null, null));

        assertFalse(shuffler.isValidShuffle(null, "a", null));
        assertFalse(shuffler.isValidShuffle(null, null, "b"));
        assertFalse(shuffler.isValidShuffle(null, "a", "b"));
    }

    public void testIsValidShuffleShuffledStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertFalse(shuffler.isValidShuffle("", null, null));
        assertFalse(shuffler.isValidShuffle("", "a", null));
        assertFalse(shuffler.isValidShuffle("", null, "xy"));
        assertFalse(shuffler.isValidShuffle("", "a", "b"));
    }

    public void testIsValidShuffleSourceStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("abc", "abc", null));
        assertTrue(shuffler.isValidShuffle("abc", null, "abc"));

        assertFalse(shuffler.isValidShuffle("abc", null, null));

        assertFalse(shuffler.isValidShuffle("abc", null, "ab"));

        // TODO fixme
        //assertFalse(shuffler.isValidShuffle("a", null, "ab"));
        //assertFalse(shuffler.isValidShuffle("ab", null, "abc"));
    }

    public void testIsValidShuffleSourceStringEmpty() {
        Shuffler shuffler = new Shuffler();

        // TODO fixme
        //assertTrue(shuffler.isValidShuffle("", "", ""));

        assertTrue(shuffler.isValidShuffle("abc", "abc", ""));
        assertTrue(shuffler.isValidShuffle("abc", "", "abc"));

        assertFalse(shuffler.isValidShuffle("abc", "", ""));
    }

    public void testIsValidShuffle() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("ab", "a", "b"));
        assertTrue(shuffler.isValidShuffle("dabecf", "abc", "def"));
        assertTrue(shuffler.isValidShuffle("abcdefghijkl", "abcghi", "defjkl"));
    }

    public void testIsValidShuffleLettersInCommon() {
        Shuffler shuffler = new Shuffler();

        // string0 and string1 contain letters in common
        assertTrue(shuffler.isValidShuffle("abca", "ac", "ba"));
        assertTrue(shuffler.isValidShuffle("acbbca", "abc", "cba"));
    }

    //==========================================================================

    public void testIsValidShuffle2ShuffledStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle2(null, null, null));

        assertFalse(shuffler.isValidShuffle2(null, "a", null));
        assertFalse(shuffler.isValidShuffle2(null, null, "b"));
        assertFalse(shuffler.isValidShuffle2(null, "a", "b"));
    }

    public void testIsValidShuffle2ShuffledStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertFalse(shuffler.isValidShuffle2("", null, null));
        assertFalse(shuffler.isValidShuffle2("", "a", null));
        assertFalse(shuffler.isValidShuffle2("", null, "xy"));
        assertFalse(shuffler.isValidShuffle2("", "a", "b"));
    }

    public void testIsValidShuffle2SourceStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle2("abc", "abc", null));
        assertTrue(shuffler.isValidShuffle2("abc", null, "abc"));

        assertFalse(shuffler.isValidShuffle2("abc", null, null));

        assertFalse(shuffler.isValidShuffle2("a", null, "ab"));
        assertFalse(shuffler.isValidShuffle2("ab", null, "abc"));

        assertFalse(shuffler.isValidShuffle2("abc", null, "ab"));
    }

    public void testIsValidShuffle2SourceStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle2("", "", ""));
        assertTrue(shuffler.isValidShuffle2("abc", "abc", ""));
        assertTrue(shuffler.isValidShuffle2("abc", "", "abc"));

        assertFalse(shuffler.isValidShuffle2("abc", "", ""));
    }

    public void testIsValidShuffle2() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle2("dabecf", "abc", "def"));
        assertTrue(shuffler.isValidShuffle2("abcdefghijkl", "abcghi", "defjkl"));
    }

    public void testIsValidShuffle2LettersInCommon() {
        Shuffler shuffler = new Shuffler();

        // string0 and string1 contain letters in common
        // TODO fixme
        // assertTrue(shuffler.isValidShuffle2("abca", "ac", "ba"));
        //assertTrue(shuffler.isValidShuffle2("acbbca", "abc", "cba"));
    }
}
