package com.beepscore.android.shuffleandroid;

/**
 * Created by stevebaker on 6/12/15.
 */
public class Node {

    String value = null;
    Node left = null;
    Node right = null;

    public Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node() {
        this(null, null, null);
    }

    @Override
    public String toString() {
        String description =  valueDescription(value) + ", "
                + leftDescription(left) + ", "
                + rightDescription(right);
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

    protected String rightDescription(Node child) {
        return childDescription("right", child);
    }

    protected String leftDescription(Node child) {
        return childDescription("left", child);
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
