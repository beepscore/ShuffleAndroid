package com.beepscore.android.shuffleandroid;

/**
 * Created by stevebaker on 6/24/15.
 */
public class NodeExtended extends Node {

    Boolean isValidCandidate = null;

    public NodeExtended(String value, Integer index0, Integer index1,
                        NodeExtended left, NodeExtended right, Boolean isValidCandidate) {
        super(value, index0, index1, left, right);
        this.isValidCandidate = isValidCandidate;
    }

    public NodeExtended() {
        this(null, null, null, null, null, null);
    }

    @Override
    public String toString() {
        String description =  super.toString()
                + ", "
                + isValidCandidateDescription(isValidCandidate);
        return description;
    }

    protected String isValidCandidateDescription(Boolean isValidCandidate) {
        String description = "";
        if (isValidCandidate == null) {
            description = description.concat("null");
        } else {
            description = description.concat(isValidCandidate.toString());
        }
        return description;
    }

}
