package com.beepscore.android.shuffleandroid;

/**
 * Created by stevebaker on 6/12/15.
 */
public class Node {

    String value = null;
    Integer index0 = null;
    Integer index1 = null;
    Node left = null;
    Node right = null;

    public Node(String value, Integer index0, Integer index1, Node left, Node right) {
        this.value = value;
        this.index0 = index0;
        this.index1 = index1;
        this.left = left;
        this.right = right;
    }

    public Node() {
        this(null, null, null, null, null);
    }

    @Override
    public String toString() {
        String description =  valueDescription(value) + ", "
                + indexDescription(index0) + ", "
                + indexDescription(index1) + ", "
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

    protected String indexDescription(Integer index) {
        String description = "";
        if (index == null) {
            description = description.concat("null");
        } else {
            description = description.concat(index.toString());
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
