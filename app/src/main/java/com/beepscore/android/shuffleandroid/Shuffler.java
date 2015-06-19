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

    /**
     * Checks several edge cases such as arguments null or empty strings
     * Uses Boolean (true, false, null) instead of boolean (true, false)
     * @param shuffledString
     * @param string0
     * @param string1
     * @return true if shuffledString is a valid shuffle of string0 and string1.
     * return false if shuffledString is not a valid shuffle of string0 and string1.
     * return null if method can't tell if shuffledString is a valid shuffle of string0 and string1.
     */
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
                && StringUtils.isStringNullOrEmpty(string0)
                && StringUtils.isStringNullOrEmpty(string1)) {
            return false;
        }

        if (StringUtils.isStringNullOrEmpty(string0)
                && shuffledString.equals(string1)) {
            return true;
        }

        if (StringUtils.isStringNullOrEmpty(string1)
                && shuffledString.equals(string0)) {
            return true;
        }
        return null;
    }

    protected boolean isNodeIndex0AtEndOfString(Node node, String string) {
        if (StringUtils.isStringNullOrEmpty(string)) {
            return true;
        }
        if (node.index0 == string.length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isNodeIndex1AtEndOfString(Node node, String string) {
        if (StringUtils.isStringNullOrEmpty(string)) {
            return true;
        }
        if (node.index1 == string.length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param node may not be null.
     * @param string0 may be null or empty "".
     * @param string1 may be null or empty "".
     * @return true if node index0 and index1 are at end of string0 and string1
     */
    protected boolean isLeafNode(Node node, String string0, String string1) {

        if (StringUtils.isStringNullOrEmpty(string0)
                && StringUtils.isStringNullOrEmpty(string1)) {
            return true;
        }

        if (StringUtils.isStringNullOrEmpty(string0)) {
            if (node.index1 == string1.length() - 1) {
                return true;
            } else {
                return false;
            }
        }

        if (StringUtils.isStringNullOrEmpty(string1)) {
            if (node.index0 == string0.length() - 1) {
                return true;
            } else {
                return false;
            }
        }

        // string0 and string1 are non-empty

        if (isNodeIndex0AtEndOfString(node, string0)
                && isNodeIndex1AtEndOfString(node, string1)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isASolution(Node node, String shuffledString, String string0, String string1) {
        if (isLeafNode(node, string0, string1)
                && isNodeValueEqualToValue(node, shuffledString)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Traverses binary tree breadth first.
     * Uses a queue instead of recursion to reduce risk of call stack overflow.
     * @param shuffledString a potential shuffle of string0 and string1
     * @param string0 a source string
     * @param string1 a source string
     * @return true if shuffledString is a valid shuffle of string0 and string1
     */
    public boolean isValidShuffle(String shuffledString,
                                  String string0, String string1) {

        Boolean edgeCaseResult = isValidShuffleForEdgeCases(shuffledString, string0, string1);
        if (edgeCaseResult != null) {
            // isValidShuffleForEdgeCases() was able to determine if shuffle is valid
            return edgeCaseResult;
        }

        // LinkedList implements fifo Queue, Dequeue
        // http://stackoverflow.com/questions/12179887/android-queue-vs-stack
        Queue<Node> queue = new LinkedList<Node>();

        addRootNodeToQueue(queue);

        while (!queue.isEmpty()) {

            Node node = queue.remove();
            //Log.d("breadth-first", node.toString());
            this.nodesSearched.add(node.value);

            if (isLeafNode(node, string0, string1)) {
                // node is a terminal node
                if (isASolution(node, shuffledString, string0, string1)) {
                    return true;
                } else {
                    // skip to next iteration, next node in queue
                    continue;
                }
            }

            String shuffledStringStart = shuffledString.substring(0, node.value.length());
            if (isNodeValueEqualToValue(node, shuffledStringStart)) {
                // path to this node is a valid candidate, so add sub-branches
                addLeftNodeToNodeAndQueue(string0, queue, node);
                addRightNodeToNodeAndQueue(string1, queue, node);
            }
        }

        // didn't find a solution
        return false;
    }

    private void addRootNodeToQueue(Queue<Node> queue) {
        // this index value signifies node has no letters from that source
        // e.g. if node.index0 == -1, node.value contains no letters from string0
        final int INDEX_BEFORE_SOURCE_START = -1;

        // root node has empty value and no letters from either source string
        Node root = new Node("", INDEX_BEFORE_SOURCE_START, INDEX_BEFORE_SOURCE_START,
                null, null);
        queue.add(root);
    }

    private void addLeftNodeToNodeAndQueue(String string0, Queue<Node> queue, Node node) {
        if ((string0 != null)
                && (node.index0 < string0.length())) {
            String string0AtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(string0, node.index0 + 1);
            String nodeLeftValue = node.value.concat(string0AtIndex);
            node.left = new Node(nodeLeftValue, node.index0 + 1, node.index1, null, null);
            queue.add(node.left);
        }
    }

    private void addRightNodeToNodeAndQueue(String string1, Queue<Node> queue, Node node) {
        if ((string1 != null)
                && (node.index1 < string1.length())) {
            String string1AtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(string1, node.index1 + 1);
            String nodeRightValue = node.value.concat(string1AtIndex);
            node.right = new Node(nodeRightValue, node.index0, node.index1 + 1, null, null);
            queue.add(node.right);
        }
    }

}
