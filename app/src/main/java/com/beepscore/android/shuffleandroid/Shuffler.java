package com.beepscore.android.shuffleandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by stevebaker on 6/12/15.
 */
public class Shuffler {

    /** Use more general interface List instead of restricting to type ArrayList
     * http://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java#2279059
     */
    public List<String> nodesSearched = new ArrayList<String>();

    private boolean isNodeValueEqualToValue(Node node, String value) {
        if ((value == null) && (node.value == null)) {
            return true;
        }

        if ((value != null) && (node.value != null)
                && (node.value.equals(value))) {
            // First two conditionals check both values are non null.
            // They are objects and so are safe to compare via third conditional equals()
            return true;
        }
        return false;
    }

    /** Search a binary tree for a node with value.
     * Tree nodes are non-null.
     * Search is breadth first.
     * Search is "pre-order"- checks node before subtrees.
     * Search checks node, then left subtree, then right subtree.
     * @param root starting node of binary tree.
     * @param value may be null.
     * @return first node found with value.
     * return null if not found
     * return null if root is null
     * return null if value is null
     */
    public Node nodeInTreeWithValueBreadthFirst(Node root, String value) {

        if ((root == null) || (value == null)) {
            return null;
        }

        // LinkedList implements Queue, Dequeue
        // http://stackoverflow.com/questions/12179887/android-queue-vs-stack
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(root);

        while (!queue.isEmpty()) {

            Node node = queue.remove();
            //Log.d("breadth-first", node.toString());
            this.nodesSearched.add(node.value);

            if (isNodeValueEqualToValue(node, value)) {
                return node;
            }

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
        // didn't find a match
        return null;
    }
}
