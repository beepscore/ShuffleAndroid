package com.beepscore.android.shuffleandroid;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stevebaker on 6/24/15.
 */

/**
 * NodeExtended adds properties to help traverse a tree of nodes
 * by remembering which children have been visited.
 */
public class NodeExtended extends Node {

    public final String LOG_TAG = NodeExtended.class.getSimpleName();

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
        return this.descriptionJSON().toString();
    }

    protected String didVisitLeftDescription(Boolean didVisitLeft) {
        if (didVisitLeft == null) {
            return "null";
        } else {
            return this.didVisitLeft.toString();
        }
    }

    protected String didVisitRightDescription(Boolean didVisitRight) {
        if (didVisitRight == null) {
            return "null";
        } else {
            return this.didVisitRight.toString();
        }
    }

    public Boolean didVisitAllChildren() {
        if (didVisitLeft && didVisitRight) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return a partial description of the node
     */
    protected JSONObject descriptionJSON() {

        JSONObject description = super.descriptionJSON();
        try {
            description.put("didVisitLeft", this.didVisitLeftDescription(this.didVisitLeft));
            description.put("didVisitRight", this.didVisitRightDescription(this.didVisitRight));
        } catch (JSONException e) {
            Log.d(LOG_TAG, "descriptionJSON error" + e.toString());
        }
        return description;
    }

}
