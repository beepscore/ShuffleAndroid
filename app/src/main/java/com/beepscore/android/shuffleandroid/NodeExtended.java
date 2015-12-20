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

            if (null == this.didVisitLeft) {
                description.put("didVisitLeft", JSONObject.NULL);
            } else {
                description.put("didVisitLeft", this.didVisitLeft);
            }

            if (null == this.didVisitRight) {
                description.put("didVisitRight", JSONObject.NULL);
            } else {
                description.put("didVisitRight", this.didVisitRight);
            }

        } catch (JSONException e) {
            Log.d(LOG_TAG, "descriptionJSON error" + e.toString());
        }
        return description;
    }

}
