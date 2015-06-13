package com.beepscore.android.shuffleandroid;

import junit.framework.TestCase;

/**
 * Created by stevebaker on 6/12/15.
 */
public class StringUtilsTest extends TestCase {

    public void testGetSubstringInclusive() {
        assertEquals("a", StringUtils.getSubstringInclusive("a", 0, 0));
        assertEquals("a", StringUtils.getSubstringInclusive("abc", 0, 0));
        assertEquals("b", StringUtils.getSubstringInclusive("abc", 1, 1));
        assertEquals("ab", StringUtils.getSubstringInclusive("abc", 0, 1));
        assertEquals(" d", StringUtils.getSubstringInclusive("ab d", 2, 3));
    }

    public void testGetSubstringLengthOneAtIndex() {
        assertEquals("a", StringUtils.getSubstringLengthOneAtIndex("a", 0));
        assertEquals("a", StringUtils.getSubstringLengthOneAtIndex("abc", 0));
        assertEquals("b", StringUtils.getSubstringLengthOneAtIndex("abc", 1));
        assertEquals(" ", StringUtils.getSubstringLengthOneAtIndex("ab d", 2));
        assertEquals("d", StringUtils.getSubstringLengthOneAtIndex("ab d", 3));
    }

    public void testGetSubstringLengthOneAtIndexStringNull() {
        assertEquals("", StringUtils.getSubstringLengthOneAtIndex(null, 0));
    }

    public void testGetSubstringLengthOneAtIndexStringEmpty() {
        assertEquals("", StringUtils.getSubstringLengthOneAtIndex("", 0));
    }

    public void testGetSubstringLengthOneAtIndexOutOfBounds() {
        assertEquals("", StringUtils.getSubstringLengthOneAtIndex("abc", -1));
        assertEquals("", StringUtils.getSubstringLengthOneAtIndex("abc", 3));
    }

    public void testIsStringNullOrEmpty() {
        assertTrue(StringUtils.isStringNullOrEmpty(null));
        assertTrue(StringUtils.isStringNullOrEmpty(""));
        
        assertFalse(StringUtils.isStringNullOrEmpty("B"));
    }

    public void testStringByRemovingLettersInString() {
        assertEquals("b", StringUtils.stringByRemovingLettersInString("ab", "a"));
        assertEquals("a", StringUtils.stringByRemovingLettersInString("ab", "b"));
        assertEquals("", StringUtils.stringByRemovingLettersInString("ab", "ab"));

        // can't remove "b" from "a"
        assertEquals("a", StringUtils.stringByRemovingLettersInString("a", "b"));
    }

}
