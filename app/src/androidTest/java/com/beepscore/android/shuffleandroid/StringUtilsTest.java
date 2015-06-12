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

}
