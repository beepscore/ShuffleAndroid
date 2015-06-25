package com.beepscore.android.shuffleandroid;

/**
 * Created by stevebaker on 6/24/15.
 */
public class NodeExtended extends Node {

    Boolean didVisitLeft = null;
    Boolean didVisitRight = null;

    public NodeExtended(String value, Integer index0, Integer index1,
                        NodeExtended left, NodeExtended right,
                        Boolean didVisitLeft, Boolean didVisitRight) {
        super(value, index0, index1, left, right);
        this.didVisitLeft = didVisitLeft;
        this.didVisitRight = didVisitRight;
    }

    public NodeExtended() {
        this(null, null, null, null, null, null, null);
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

    public Boolean hasUnvisitedChildren() {
        if (!didVisitLeft || !didVisitRight) {
            return true;
        } else {
            return false;
        }
    }

}
