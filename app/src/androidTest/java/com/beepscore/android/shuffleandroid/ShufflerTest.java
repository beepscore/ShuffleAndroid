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

    public ArrayList<String>sourceStringsFromStrings(String string0, String string1) {
        ArrayList<String>strings = new ArrayList<>();
        strings.add(string0);
        strings.add(string1);
        return strings;
    }

    public void testIsLeafNode() {
        Shuffler shuffler = new Shuffler();

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(0);
        indexes.add(-1);

        Node joe = new Node("a", indexes, null);

        assertFalse(shuffler.isLeafNode(joe, sourceStringsFromStrings("a", "b")));
    }

    //==========================================================================

    public void testIsValidShuffleForEdgeCasesShuffledStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleForEdgeCases(null, sourceStringsFromStrings(null, null)));

        assertFalse(shuffler.isValidShuffleForEdgeCases(null, sourceStringsFromStrings("a", null)));
        assertFalse(shuffler.isValidShuffleForEdgeCases(null, sourceStringsFromStrings(null, "b")));
        assertFalse(shuffler.isValidShuffleForEdgeCases(null, sourceStringsFromStrings("a", "b")));
    }

    public void testIsValidShuffleForEdgeCasesShuffledStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertFalse(shuffler.isValidShuffleForEdgeCases("", sourceStringsFromStrings(null, null)));
        assertFalse(shuffler.isValidShuffleForEdgeCases("", sourceStringsFromStrings("a", null)));
        assertFalse(shuffler.isValidShuffleForEdgeCases("", sourceStringsFromStrings(null, "xy")));
        assertFalse(shuffler.isValidShuffleForEdgeCases("", sourceStringsFromStrings("a", "b")));
    }

    public void testIsValidShuffleForEdgeCasesSourceStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings("abc", null)));
        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings(null, "abc")));
        assertFalse(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings(null, null)));

        assertNull(shuffler.isValidShuffleForEdgeCases("a", sourceStringsFromStrings(null, "ab")));
        assertNull(shuffler.isValidShuffleForEdgeCases("ab", sourceStringsFromStrings(null, "abc")));
        assertNull(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings(null, "ab")));
    }

    public void testIsValidShuffleForEdgeCasesSourceStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleForEdgeCases("", sourceStringsFromStrings("", "")));
        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings("abc", "")));
        assertTrue(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings("", "abc")));

        assertFalse(shuffler.isValidShuffleForEdgeCases("abc", sourceStringsFromStrings("", "")));
    }

    //==========================================================================

    public void testIsValidShuffleShuffledStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle(null, sourceStringsFromStrings(null, null)));
        assertFalse(shuffler.isValidShuffle(null, sourceStringsFromStrings("a", null)));
        assertFalse(shuffler.isValidShuffle(null, sourceStringsFromStrings(null, "b")));
        assertFalse(shuffler.isValidShuffle(null, sourceStringsFromStrings("a", "b")));
    }

    public void testIsValidShuffleShuffledStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertFalse(shuffler.isValidShuffle("", sourceStringsFromStrings(null, null)));
        assertFalse(shuffler.isValidShuffle("", sourceStringsFromStrings("a", null)));

        assertFalse(shuffler.isValidShuffle("", sourceStringsFromStrings(null, "xy")));
        assertFalse(shuffler.isValidShuffle("", sourceStringsFromStrings("a", "b")));
    }

    public void testIsValidShuffleSourceStringNull() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("abc", sourceStringsFromStrings("abc", null)));
        assertTrue(shuffler.isValidShuffle("abc", sourceStringsFromStrings(null, "abc")));

        assertFalse(shuffler.isValidShuffle("abc", sourceStringsFromStrings(null, null)));
        assertFalse(shuffler.isValidShuffle("abc", sourceStringsFromStrings(null, "ab")));
        assertFalse(shuffler.isValidShuffle("a", sourceStringsFromStrings(null, "ab")));
        assertFalse(shuffler.isValidShuffle("ab", sourceStringsFromStrings(null, "abc")));
    }

    public void testIsValidShuffleSourceStringEmpty() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("", sourceStringsFromStrings("", "")));
        assertTrue(shuffler.isValidShuffle("abc", sourceStringsFromStrings("abc", "")));
        assertTrue(shuffler.isValidShuffle("abc", sourceStringsFromStrings("", "abc")));

        assertFalse(shuffler.isValidShuffle("abc", sourceStringsFromStrings("", "")));
    }

    public void testIsValidShuffle() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffle("ab", sourceStringsFromStrings("a", "b")));
        assertTrue(shuffler.isValidShuffle("dabecf", sourceStringsFromStrings("abc", "def")));
        assertTrue(shuffler.isValidShuffle("abcdefghijkl", sourceStringsFromStrings("abcghi", "defjkl")));

        assertFalse(shuffler.isValidShuffle("abcdefghijkl", sourceStringsFromStrings("abchgi", "defjkl")));
    }

    public void testIsValidShuffleLettersInCommon() {
        Shuffler shuffler = new Shuffler();

        // sourceStrings 0 and 1 contain letters in common
        assertTrue(shuffler.isValidShuffle("abca", sourceStringsFromStrings("ac", "ba")));
        assertTrue(shuffler.isValidShuffle("acbbca", sourceStringsFromStrings("abc", "cba")));
        assertTrue(shuffler.isValidShuffle("abaabza", sourceStringsFromStrings("aba", "abza")));
        assertTrue(shuffler.isValidShuffle("This is a great day indeed!",
                sourceStringsFromStrings("T reayde", "hisis a gt da ined!")));

        // expect false because strings are case sensitive
        assertFalse(shuffler.isValidShuffle("This is a great day indeed!",
                sourceStringsFromStrings("t reayde", "hisis a gt da ined!")));
    }

    public void testIsValidShuffleUTF8() {
        Shuffler shuffler = new Shuffler();

        // Chinese from http://www.foxconn.com/index.html
        assertTrue(shuffler.isValidShuffle("公告本公司董事會通過104年第一季合併",
                sourceStringsFromStrings("公司會0合", "告本公董事通過14年第一季併")));

        assertFalse(shuffler.isValidShuffle("公告本公司董事會通過104年第一季合併",
                sourceStringsFromStrings("公司0合", "會告本公董事通過14年第一季併")));
    }

    //==========================================================================

    public void testIsValidShuffleDepthFirst() {
        Shuffler shuffler = new Shuffler();

        assertTrue(shuffler.isValidShuffleDepthFirst("ab", sourceStringsFromStrings("a", "b")));
        assertTrue(shuffler.isValidShuffleDepthFirst("dabecf", sourceStringsFromStrings("abc", "def")));
        assertTrue(shuffler.isValidShuffleDepthFirst("abcdefghijkl", sourceStringsFromStrings("abcghi", "defjkl")));

        assertFalse(shuffler.isValidShuffleDepthFirst("abcdefghijkl", sourceStringsFromStrings("abchgi", "defjkl")));
    }

    public void testIsValidShuffleDepthFirstLettersInCommon() {
        Shuffler shuffler = new Shuffler();

        // sourceStrings 0 and 1 contain letters in common
        assertTrue(shuffler.isValidShuffleDepthFirst("abca", sourceStringsFromStrings("ac", "ba")));
        assertTrue(shuffler.isValidShuffleDepthFirst("acbbca", sourceStringsFromStrings("abc", "cba")));
        assertTrue(shuffler.isValidShuffleDepthFirst("abaabza", sourceStringsFromStrings("aba", "abza")));
        assertTrue(shuffler.isValidShuffleDepthFirst("This is a great day indeed!",
                sourceStringsFromStrings("T reayde", "hisis a gt da ined!")));

        // expect false because strings are case sensitive
        assertFalse(shuffler.isValidShuffleDepthFirst("This is a great day indeed!",
                sourceStringsFromStrings("t reayde", "hisis a gt da ined!")));
    }

    public void testIsValidShuffleDepthFirstUTF8() {
        Shuffler shuffler = new Shuffler();

        // Chinese from http://www.foxconn.com/index.html
        assertTrue(shuffler.isValidShuffleDepthFirst("公告本公司董事會通過104年第一季合併",
                sourceStringsFromStrings("公司會0合", "告本公董事通過14年第一季併")));

        assertFalse(shuffler.isValidShuffleDepthFirst("公告本公司董事會通過104年第一季合併",
                sourceStringsFromStrings("公司0合", "會告本公董事通過14年第一季併")));
    }

}
