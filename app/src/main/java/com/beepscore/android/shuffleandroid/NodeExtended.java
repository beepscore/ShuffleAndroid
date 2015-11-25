package com.beepscore.android.shuffleandroid;

import java.util.ArrayList;

/**
 * Created by stevebaker on 6/24/15.
 *
 * NodeExtended adds properties to help traverse a tree of nodes
 * by remembering which children have been visited.
 *
 */
public class NodeExtended extends Node {

    Boolean didVisitLeft = null;
    Boolean didVisitRight = null;

    public NodeExtended(String value,
                        ArrayList<Integer> indexes,
                        ArrayList<NodeExtended> children,
                        Boolean didVisitLeft, Boolean didVisitRight) {

        super(value, indexes, children);

        if (children == null) {
            ArrayList<NodeExtended> childrenNull = new ArrayList<NodeExtended>();
            childrenNull.add(null);
            childrenNull.add(null);
            this.children = childrenNull;
        }
        this.didVisitLeft = didVisitLeft;
        this.didVisitRight = didVisitRight;
    }

    public NodeExtended() {
        this(null, null, null, null, null);
        ArrayList<NodeExtended> childrenNull = new ArrayList<NodeExtended>();
        childrenNull.add(null);
        childrenNull.add(null);
        this.children = childrenNull;
    }

    @Override
    public String toString() {
        String description =  super.toString()
                + ", "
                + didVisitLeftDescription(didVisitLeft)
                + ", "
                + didVisitRightDescription(didVisitRight);
        return description;
    }

    protected String didVisitLeftDescription(Boolean didVisitLeft) {
        String description = "";
        if (didVisitLeft == null) {
            description = description.concat("null");
        } else {
            description = description.concat(didVisitLeft.toString());
        }
        return description;
    }

    protected String didVisitRightDescription(Boolean didVisitRight) {
        String description = "";
        if (didVisitRight == null) {
            description = description.concat("null");
        } else {
            description = description.concat(didVisitRight.toString());
        }
        return description;
    }

    public Boolean didVisitAllChildren() {
        if (didVisitLeft && didVisitRight) {
            return true;
        } else {
            return false;
        }
    }

}
