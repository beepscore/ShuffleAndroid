package com.beepscore.android.shuffleandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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

    protected Boolean isValidShuffleForEdgeCases(String shuffledString, String string0, String string1) {

        if (shuffledString == null) {
            if ((string0 == null) && (string1 == null)) {
                return true;
            } else {
                return false;
            }
        }

        if ("".equals(shuffledString)) {
            if (("".equals(string0)) && ("".equals(string1))) {
                return true;
            } else {
                return false;
            }
        }

        if ((shuffledString != null)
                && (string0 == null)
                && (string1 == null)) {
            return false;
        }

        if (((string0 == null) || (string0.length() == 0))
                && shuffledString.equals(string1)) {
            return true;
        }

        if (((string1 == null) || (string1.length() == 0))
                && shuffledString.equals(string0)) {
            return true;
        }
        return null;
    }

    public boolean isValidShuffle(String shuffledString,
                                  String string0, String string1) {

        Boolean edgeCaseResult = isValidShuffleForEdgeCases(shuffledString, string0, string1);
        if (edgeCaseResult != null) {
            return edgeCaseResult;
        }

        // TODO: Fix me
        // LinkedList implements Queue, Dequeue
        // http://stackoverflow.com/questions/12179887/android-queue-vs-stack
        Queue<Node> queue = new LinkedList<Node>();

        Node root = new Node("", null, null);
        queue.add(root);

        int indexString0 = 0;
        int indexString1 = 0;

        while (!queue.isEmpty()) {

            Node node = queue.remove();
            //Log.d("breadth-first", node.toString());
            this.nodesSearched.add(node.value);

            if (isNodeValueEqualToValue(node, shuffledString)) {
                return true;
            }

            if ((string0 != null)
                    && (indexString0 < string0.length())) {
                String string0AtIndex = StringUtils.getSubstringLengthOneAtIndex(string0, indexString0);
                String nodeLeftValue = node.value.concat(string0AtIndex);
                node.left = new Node(nodeLeftValue, null, null);
                queue.add(node.left);
                indexString0 += 1;
            }

            if ((string1 != null)
                    && (indexString1 < string1.length())) {
                String string1AtIndex = StringUtils.getSubstringLengthOneAtIndex(string1, indexString1);
                String nodeRightValue = node.value.concat(string1AtIndex);
                node.right = new Node(nodeRightValue, null, null);
                queue.add(node.right);
                indexString1 += 1;
            }

        }

        // didn't find a solution
        return false;
    }
    /** Potential Alternative solution.
     *  would need to add code to handle edge cases
     */
    public boolean isValidShuffle2(String shuffledString,
                                   String string0, String string1) {

        Boolean edgeCaseResult = isValidShuffleForEdgeCases(shuffledString, string0, string1);
        if (edgeCaseResult != null) {
            return edgeCaseResult;
        }

        String shuffledStringAfterRemovingString0 = StringUtils.stringByRemovingLettersInString(shuffledString, string0);
        String shuffledStringAfterRemovingString1 = StringUtils.stringByRemovingLettersInString(shuffledString, string1);

        if (shuffledStringAfterRemovingString0.equals(string1)
                && (shuffledStringAfterRemovingString1.equals(string0))) {
            return true;
        } else {
            return false;
        }
    }

}
