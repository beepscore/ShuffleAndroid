package com.beepscore.android.shuffleandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevebaker on 6/12/15.
 */
public class Node {

    String value = null;
    ArrayList<Integer> indexes = null;
    ArrayList<? extends Node> children = null;

    public Node(String value, ArrayList<Integer> indexes,
                ArrayList<? extends Node> children) {

        this.value = value;

        if (indexes == null) {
            ArrayList<Integer> indexesNull = new ArrayList<Integer>();
            indexesNull.add(null);
            indexesNull.add(null);
            this.indexes = indexesNull;
        } else {
            this.indexes = indexes;
        }

        if (children == null) {
            ArrayList<Node> childrenNull = new ArrayList<Node>();
            childrenNull.add(null);
            childrenNull.add(null);
            this.children = childrenNull;
        } else {
            this.children = children;
        }
    }

    public Node() {
        // call to this must be first statement in constructor
        this(null, null, null);
    }

    @Override
    public String toString() {
        String description =  valueDescription(value) + ", "
                + indexesDescription(indexes) + ", "
                + childrenDescription(children);
        return description;
    }

    protected String valueDescription(String aValue) {
        String description = "";
        if (aValue == null) {
            description = description.concat("null");
        } else {
            description = description.concat(aValue);
        }
        return description;
    }

    protected String indexesDescription(ArrayList<Integer> indexes) {
        String description = "indexes: ";
        if (indexes == null) {
            description = description.concat("null");
        } else {
            description = description.concat(indexes.toString());
        }
        return description;
    }

    protected String childrenDescription(ArrayList<? extends Node> children) {
        String description = "children: ";
        if (children == null) {
            description = description.concat("null");
        } else {
            description = description + "[";
            if ((children.get(0) == null)
                || (children.get(0).value == null)) {
                description = description + "null";
            } else {
                description = description + children.get(0).value;
            }

            description = description.concat(", ");

            if ((children.get(1) == null)
                    || (children.get(1).value == null)) {
                description = description + "null";
            } else {
                description = description + children.get(1).value;
            }
            description = description.concat("]");
        }
        return description;
    }

    /**
     *
     * @param childPosition e.g. "left", "right", "child0"
     * @param child
     * @return
     */
    // TODO: Consider convert childPosition into an enum or similar
    protected String childDescription(String childPosition, Node child) {
        String description = childPosition;
        if (child == null) {
            description = description.concat(": null");
        } else {
            description = description.concat(".value: ");
            if (child.value == null) {
                description = description.concat("null");
            } else {
                description = description.concat(child.value);
            }
        }
        return description;
    }
}
