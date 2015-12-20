package com.beepscore.android.shuffleandroid;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by stevebaker on 6/24/15.
 */
public class NodeExtendedTest extends TestCase {

    public final String LOG_TAG = NodeExtendedTest.class.getSimpleName();

    public void testNodePropertiesNull() {
        NodeExtended node = new NodeExtended();
        assertNotNull(node);

        assertNull(node.value);
        assertNull(node.indexes.get(0));
        assertNull(node.indexes.get(1));
        assertNull(node.children.get(0));
        assertNull(node.children.get(1));
        assertNull(node.didVisitLeft);
        assertNull(node.didVisitRight);
    }

    public void testNodeExtendedToStringPropertiesNull() {
        NodeExtended node = new NodeExtended();
        String expected = "{\"value\":null,\"indexes\":\"[null, null]\",\"children\":\"[null, null]\",\"didVisitLeft\":null,\"didVisitRight\":null}";
        assertEquals(expected, node.toString());
    }

    public void testNodeExtendedProperties() {
        NodeExtended joe = new NodeExtended();

        String testValue = "Joe";
        joe.value = testValue;
        assertEquals(testValue, joe.value);

        String expectedDescription = "{\"value\":\"Joe\",\"indexes\":\"[null, null]\",\"children\":\"[null, null]\",\"didVisitLeft\":null,\"didVisitRight\":null}";
        assertEquals(expectedDescription, joe.toString());

        NodeExtended larry = new NodeExtended();
        ((ArrayList<NodeExtended>)joe.children).set(0, larry);
        larry.value = "Larry";
        assertEquals(larry, joe.children.get(0));

        expectedDescription = "{\"value\":\"Joe\",\"indexes\":\"[null, null]\",\"children\":\"[Larry, null]\",\"didVisitLeft\":null,\"didVisitRight\":null}";
        assertEquals(expectedDescription, joe.toString());

        NodeExtended rick = new NodeExtended();
        ((ArrayList<NodeExtended>)joe.children).set(1, rick);
        assertEquals(rick, joe.children.get(1));

        expectedDescription = "{\"value\":\"Joe\",\"indexes\":\"[null, null]\",\"children\":\"[Larry, null]\",\"didVisitLeft\":null,\"didVisitRight\":null}";
        assertEquals(expectedDescription, joe.toString());

        rick.value = "Rick";
        expectedDescription = "{\"value\":\"Joe\",\"indexes\":\"[null, null]\",\"children\":\"[Larry, Rick]\",\"didVisitLeft\":null,\"didVisitRight\":null}";
        assertEquals(expectedDescription, joe.toString());

        joe.didVisitLeft = true;
        expectedDescription = "{\"value\":\"Joe\",\"indexes\":\"[null, null]\",\"children\":\"[Larry, Rick]\",\"didVisitLeft\":true,\"didVisitRight\":null}";
        assertEquals(expectedDescription, joe.toString());

        joe.didVisitRight = false;
        expectedDescription = "{\"value\":\"Joe\",\"indexes\":\"[null, null]\",\"children\":\"[Larry, Rick]\",\"didVisitLeft\":true,\"didVisitRight\":false}";
        assertEquals(expectedDescription, joe.toString());
    }

    public void testConstructor() {
        String value = "Joe";
        NodeExtended larry = new NodeExtended();
        NodeExtended rick = new NodeExtended();
        ArrayList<NodeExtended> children = new ArrayList<NodeExtended>();
        children.add(larry);
        children.add(rick);
        NodeExtended joe = new NodeExtended(value, null, children, true, false);

        assertNotNull(joe);
        assertEquals(value, joe.value);
        assertEquals(larry, joe.children.get(0));
        assertEquals(rick, joe.children.get(1));
        assertTrue(joe.didVisitLeft);
        assertFalse(joe.didVisitRight);
    }
}
