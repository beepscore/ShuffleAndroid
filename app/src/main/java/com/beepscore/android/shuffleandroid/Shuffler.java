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

    protected int lengthOfSource(String string) {
        if (string == null) {
            return 0;
        } else {
            return string.length();
        }
    }

    protected int lengthOfSources(String string0, String string1) {
        return lengthOfSource(string0) + lengthOfSource(string1);
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

        // TODO: Fix me
        // LinkedList implements Queue, Dequeue
        // http://stackoverflow.com/questions/12179887/android-queue-vs-stack
        Queue<Node> queue = new LinkedList<Node>();

        final int LENGTH_OF_SOURCES = lengthOfSources(string0, string1);

        Node root = new Node("", 0, 0, null, null);
        queue.add(root);

        while (!queue.isEmpty()) {

            Node node = queue.remove();
            //Log.d("fooby", node.toString());
            this.nodesSearched.add(node.value);

            if ((isNodeValueEqualToValue(node, shuffledString))
                    && (node.value.length() == LENGTH_OF_SOURCES)) {
                return true;
            }

            //String shuffledStringStart = StringUtils.getSubstringInclusive(shuffledString, 0, node.value.length());
            String shuffledStringStart = shuffledString.substring(0, node.value.length());
            if (node.value.equals(shuffledStringStart)) {
                // candidate is potentially valid

                if ((string0 != null)
                        && (node.index0 < string0.length())) {
                    String string0AtIndex = StringUtils.getSubstringLengthOneAtIndex(string0, node.index0);
                    String nodeLeftValue = node.value.concat(string0AtIndex);
                    node.left = new Node(nodeLeftValue, node.index0 + 1, node.index1, null, null);
                    queue.add(node.left);
                }

                if ((string1 != null)
                        && (node.index1 < string1.length())) {
                    String string1AtIndex = StringUtils.getSubstringLengthOneAtIndex(string1, node.index1);
                    String nodeRightValue = node.value.concat(string1AtIndex);
                    node.right = new Node(nodeRightValue, node.index0, node.index1 + 1, null, null);
                    queue.add(node.right);
                }
            }
        }

        // didn't find a solution
        return false;
    }

    /** Potential alternative solution.
     *  Currently can return incorrect result if string0 and string1 have letters in common.
     *  Would need to add code to handle edge cases.
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
