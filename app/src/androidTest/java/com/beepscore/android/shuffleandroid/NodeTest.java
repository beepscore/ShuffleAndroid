package com.beepscore.android.shuffleandroid;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by stevebaker on 6/12/15.
 */
public class NodeTest extends TestCase {

    public void testNodePropertiesNull() {
        Node node = new Node();
        assertNotNull(node);

        assertNull(node.value);
        assertNull(node.indexes);
        assertNull(node.children.get(0));
        assertNull(node.children.get(1));
    }

    public void testNodeToStringPropertiesNull() {
        Node node = new Node();
        String expected = "null, indexes: null, children: [null, null]";
        assertEquals(expected, node.toString());
    }

    public void testNodeProperties() {
        Node joe = new Node();

        String testValue = "Joe";
        joe.value = testValue;
        assertEquals(testValue, joe.value);

        String expectedDescription = "Joe, indexes: null, children: [null, null]";
        assertEquals(expectedDescription, joe.toString());

        Node larry = new Node();
        ((ArrayList<Node>)joe.children).set(0, larry);
        larry.value = "Larry";
        assertEquals(larry, joe.children.get(0));

        expectedDescription = "Joe, indexes: null, children: [Larry, null]";
        assertEquals(expectedDescription, joe.toString());

        Node rick = new Node();
        ((ArrayList<Node>)joe.children).set(1, rick);
        assertEquals(rick, joe.children.get(1));

        expectedDescription = "Joe, indexes: null, children: [Larry, null]";
        assertEquals(expectedDescription, joe.toString());

        rick.value = "Rick";
        expectedDescription = "Joe, indexes: null, children: [Larry, Rick]";
        assertEquals(expectedDescription, joe.toString());
    }

    public void testConstructor() {
        String value = "Joe";
        Node larry = new Node();
        Node rick = new Node();
        ArrayList<Node> children = new ArrayList<Node>();
        children.add(larry);
        children.add(rick);
        Node joe = new Node(value, null, children);

        assertNotNull(joe);
        assertEquals(value, joe.value);
        assertEquals(larry, joe.children.get(0));
        assertEquals(rick, joe.children.get(1));
    }
}
