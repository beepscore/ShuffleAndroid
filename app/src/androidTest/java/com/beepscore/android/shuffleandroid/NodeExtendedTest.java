package com.beepscore.android.shuffleandroid;

import junit.framework.TestCase;

/**
 * Created by stevebaker on 6/24/15.
 */
public class NodeExtendedTest extends TestCase {

    public void testNodePropertiesNull() {
        NodeExtended node = new NodeExtended();
        assertNotNull(node);

        assertNull(node.value);
        assertNull(node.index0);
        assertNull(node.index1);
        assertNull(node.left);
        assertNull(node.right);
        assertNull(node.isValidCandidate);
    }

    public void testNodeExtendedToStringPropertiesNull() {
        NodeExtended node = new NodeExtended();
        String expected = "null, null, null, left: null, right: null, null";
        assertEquals(expected, node.toString());
    }

    public void testNodeExtendedProperties() {
        NodeExtended joe = new NodeExtended();

        String testValue = "Joe";
        joe.value = testValue;
        assertEquals(testValue, joe.value);

        String expectedDescription = "Joe, null, null, left: null, right: null, null";
        assertEquals(expectedDescription, joe.toString());

        NodeExtended larry = new NodeExtended();
        joe.left = larry;
        larry.value = "Larry";
        assertEquals(larry, joe.left);

        expectedDescription = "Joe, null, null, left.value: Larry, right: null, null";
        assertEquals(expectedDescription, joe.toString());

        NodeExtended rick = new NodeExtended();
        joe.right = rick;
        assertEquals(rick, joe.right);

        expectedDescription = "Joe, null, null, left.value: Larry, right.value: null, null";
        assertEquals(expectedDescription, joe.toString());

        rick.value = "Rick";
        expectedDescription = "Joe, null, null, left.value: Larry, right.value: Rick, null";
        assertEquals(expectedDescription, joe.toString());
    }

    public void testConstructor() {
        String value = "Joe";
        NodeExtended larry = new NodeExtended();
        NodeExtended rick = new NodeExtended();
        NodeExtended joe = new NodeExtended(value, null, null, larry, rick, true);

        assertNotNull(joe);
        assertEquals(value, joe.value);
        assertEquals(larry, joe.left);
        assertEquals(rick, joe.right);
        assertTrue(joe.isValidCandidate);
    }
}
