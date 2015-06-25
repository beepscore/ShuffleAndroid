package com.beepscore.android.shuffleandroid;


import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by stevebaker on 6/12/15.
 */
public class ShufflerTest extends TestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testIsLeafNode() {
        Shuffler shuffler = new Shuffler();

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(0);
        indexes.add(-1);

        Node joe = new Node("a", indexes, null, null);
        assertFalse(shuffler.isLeafNode(joe, "a", "b"));
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

        assertFalse(shuffler.isValidShuffleForEdgeCases("abc", "", ""));
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
        assertFalse(shuffler.isValidShuffle("a", null, "ab"));
        assertFalse(shuffler.isValidShuffle("ab", null, "abc"));
    }

    public void testIsValidShuffleSourceStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("", "", ""));
        assertTrue(shuffler.isValidShuffle("abc", "abc", ""));
        assertTrue(shuffler.isValidShuffle("abc", "", "abc"));

        assertFalse(shuffler.isValidShuffle("abc", "", ""));
    }

    public void testIsValidShuffle() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("ab", "a", "b"));
        assertTrue(shuffler.isValidShuffle("dabecf", "abc", "def"));
        assertTrue(shuffler.isValidShuffle("abcdefghijkl", "abcghi", "defjkl"));

        assertFalse(shuffler.isValidShuffle("abcdefghijkl", "abchgi", "defjkl"));
    }

    public void testIsValidShuffleLettersInCommon() {
        Shuffler shuffler = new Shuffler();

        // string0 and string1 contain letters in common
        assertTrue(shuffler.isValidShuffle("abca", "ac", "ba"));
        assertTrue(shuffler.isValidShuffle("acbbca", "abc", "cba"));
        assertTrue(shuffler.isValidShuffle("abaabza", "aba", "abza"));
        assertTrue(shuffler.isValidShuffle("This is a great day indeed!",
                "T reayde", "hisis a gt da ined!"));

        // expect false because strings are case sensitive
        assertFalse(shuffler.isValidShuffle("This is a great day indeed!",
                "t reayde", "hisis a gt da ined!"));
    }

    public void testIsValidShuffleUTF8() {
        Shuffler shuffler = new Shuffler();

        // Chinese from http://www.foxconn.com/index.html
        assertTrue(shuffler.isValidShuffle("公告本公司董事會通過104年第一季合併",
                "公司會0合", "告本公董事通過14年第一季併"));

        assertFalse(shuffler.isValidShuffle("公告本公司董事會通過104年第一季合併",
                "公司0合", "會告本公董事通過14年第一季併"));
    }

    //==========================================================================

    public void testIsValidShuffleDepthFirst() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleDepthFirst("ab", "a", "b"));
        assertTrue(shuffler.isValidShuffleDepthFirst("dabecf", "abc", "def"));
        assertTrue(shuffler.isValidShuffleDepthFirst("abcdefghijkl", "abcghi", "defjkl"));

        assertFalse(shuffler.isValidShuffleDepthFirst("abcdefghijkl", "abchgi", "defjkl"));
    }

    public void testIsValidShuffleDepthFirstLettersInCommon() {
        Shuffler shuffler = new Shuffler();

        // string0 and string1 contain letters in common
        assertTrue(shuffler.isValidShuffleDepthFirst("abca", "ac", "ba"));
        assertTrue(shuffler.isValidShuffleDepthFirst("acbbca", "abc", "cba"));
        assertTrue(shuffler.isValidShuffleDepthFirst("abaabza", "aba", "abza"));
        assertTrue(shuffler.isValidShuffleDepthFirst("This is a great day indeed!",
                "T reayde", "hisis a gt da ined!"));

        // expect false because strings are case sensitive
        assertFalse(shuffler.isValidShuffleDepthFirst("This is a great day indeed!",
                "t reayde", "hisis a gt da ined!"));
    }

    public void testIsValidShuffleDepthFirstUTF8() {
        Shuffler shuffler = new Shuffler();

        // Chinese from http://www.foxconn.com/index.html
        assertTrue(shuffler.isValidShuffleDepthFirst("公告本公司董事會通過104年第一季合併",
                "公司會0合", "告本公董事通過14年第一季併"));

        assertFalse(shuffler.isValidShuffleDepthFirst("公告本公司董事會通過104年第一季合併",
                "公司0合", "會告本公董事通過14年第一季併"));
    }
}
