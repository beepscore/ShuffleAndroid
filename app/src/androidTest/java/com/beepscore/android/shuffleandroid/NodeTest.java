package com.beepscore.android.shuffleandroid;

import junit.framework.TestCase;

/**
 * Created by stevebaker on 6/12/15.
 */
public class NodeTest extends TestCase {

    public void testNodePropertiesNull() {
        Node node = new Node();
        assertNotNull(node);

        assertNull(node.value);
        assertNull(node.indexes);
        assertNull(node.left);
        assertNull(node.right);
    }

    public void testNodeToStringPropertiesNull() {
        Node node = new Node();
        String expected = "null, null, left: null, right: null";
        assertEquals(expected, node.toString());
    }

    public void testNodeProperties() {
        Node joe = new Node();

        String testValue = "Joe";
        joe.value = testValue;
        assertEquals(testValue, joe.value);

        String expectedDescription = "Joe, null, left: null, right: null";
        assertEquals(expectedDescription, joe.toString());

        Node larry = new Node();
        joe.left = larry;
        larry.value = "Larry";
        assertEquals(larry, joe.left);

        expectedDescription = "Joe, null, left.value: Larry, right: null";
        assertEquals(expectedDescription, joe.toString());

        Node rick = new Node();
        joe.right = rick;
        assertEquals(rick, joe.right);

        expectedDescription = "Joe, null, left.value: Larry, right.value: null";
        assertEquals(expectedDescription, joe.toString());

        rick.value = "Rick";
        expectedDescription = "Joe, null, left.value: Larry, right.value: Rick";
        assertEquals(expectedDescription, joe.toString());
    }

    public void testConstructor() {
        String value = "Joe";
        Node larry = new Node();
        Node rick = new Node();
        Node joe = new Node(value, null, larry, rick);

        assertNotNull(joe);
        assertEquals(value, joe.value);
        assertEquals(larry, joe.left);
        assertEquals(rick, joe.right);
    }
}
