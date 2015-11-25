package com.beepscore.android.shuffleandroid;

import java.util.ArrayList;

/**
 * Created by stevebaker on 6/12/15.
 */

/**
 * Provides a node that can be used to build a tree data structure.
 */
public class Node {

    /**
     * A value
     * When nodes are connected in a tree, we may search the tree to look for a value.
     * We may sort or order the nodes in the tree based on each node's value.
     * For example sort the nodes into a binary search tree or into a heap.
     * https://en.wikipedia.org/wiki/Binary_tree
     * https://en.wikipedia.org/wiki/Binary_heap
     * https://en.wikipedia.org/wiki/Binary_search_tree
     */
    String value = null;

    /**
     * An array of indexes
     * For example, when shuffling 2 strings,
     * index(0) can represent the current position within string0
     * index(1) can represent the current position within string1
     */
    ArrayList<Integer> indexes = null;

    /**
     * An array of this node's children
     * For example, when building a binary tree, children.size() can be 2
     * child(0) can reference the left child, or can be null
     * child(1) can reference the right child, or can be null
     */
    ArrayList<? extends Node> children = null;

    /**
     *
     * @param value
     * @param indexes
     * @param children
     */
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
