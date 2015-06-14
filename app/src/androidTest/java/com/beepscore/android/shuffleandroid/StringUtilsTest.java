package com.beepscore.android.shuffleandroid;

import junit.framework.TestCase;

/**
 * Created by stevebaker on 6/12/15.
 */
public class StringUtilsTest extends TestCase {

    public void testGetSafeSubstringInclusive() {
        assertEquals("a", StringUtils.getSafeSubstringInclusive("a", 0, 0));
        assertEquals("a", StringUtils.getSafeSubstringInclusive("abc", 0, 0));
        assertEquals("b", StringUtils.getSafeSubstringInclusive("abc", 1, 1));
        assertEquals("ab", StringUtils.getSafeSubstringInclusive("abc", 0, 1));
        assertEquals(" d", StringUtils.getSafeSubstringInclusive("ab d", 2, 3));
    }

    public void testGetSafeSubstringLengthOneAtIndex() {
        assertEquals("a", StringUtils.getSafeSubstringLengthOneAtIndex("a", 0));
        assertEquals("a", StringUtils.getSafeSubstringLengthOneAtIndex("abc", 0));
        assertEquals("b", StringUtils.getSafeSubstringLengthOneAtIndex("abc", 1));
        assertEquals(" ", StringUtils.getSafeSubstringLengthOneAtIndex("ab d", 2));
        assertEquals("d", StringUtils.getSafeSubstringLengthOneAtIndex("ab d", 3));
    }

    public void testGetSafeSubstringLengthOneAtIndexStringNull() {
        assertEquals("", StringUtils.getSafeSubstringLengthOneAtIndex(null, 0));
    }

    public void testGetSafeSubstringLengthOneAtIndexStringEmpty() {
        assertEquals("", StringUtils.getSafeSubstringLengthOneAtIndex("", 0));
    }

    public void testGetSafeSubstringLengthOneAtIndexOutOfBounds() {
        assertEquals("", StringUtils.getSafeSubstringLengthOneAtIndex("abc", -1));
        assertEquals("", StringUtils.getSafeSubstringLengthOneAtIndex("abc", 3));
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
