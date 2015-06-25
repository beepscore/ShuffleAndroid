package com.beepscore.android.shuffleandroid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Deque;

/**
 * Created by stevebaker on 6/12/15.
 */
public class Shuffler {

    /** Use more general interface List instead of restricting to type ArrayList
     * http://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java#2279059
     */
    public List<String> nodesSearched = null;

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
        if (node.indexes.get(0) == string.length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isNodeIndex1AtEndOfString(Node node, String string) {
        if (StringUtils.isStringNullOrEmpty(string)) {
            return true;
        }
        if (node.indexes.get(1) == string.length() - 1) {
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
     * @return true if node indexes.get(0) and indexes.get(1) are at end of string0 and string1
     */
    protected boolean isLeafNode(Node node, String string0, String string1) {

        if (StringUtils.isStringNullOrEmpty(string0)
                && StringUtils.isStringNullOrEmpty(string1)) {
            return true;
        }

        if (StringUtils.isStringNullOrEmpty(string0)) {
            if (node.indexes.get(1) == string1.length() - 1) {
                return true;
            } else {
                return false;
            }
        }

        if (StringUtils.isStringNullOrEmpty(string1)) {
            if (node.indexes.get(0) == string0.length() - 1) {
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

    //==========================================================================

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

        nodesSearched = new ArrayList<String>();

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
        // index values signifies node has no letters from that source string
        // e.g. if node.indexes.get(0) == -1, node.value contains no letters from string0
        final Integer INDEX_BEFORE_SOURCE_START = -1;
        ArrayList<Integer>indexesBeforeSourceStart = new ArrayList<Integer>();
        indexesBeforeSourceStart.add(INDEX_BEFORE_SOURCE_START);
        indexesBeforeSourceStart.add(INDEX_BEFORE_SOURCE_START);

        // root node has empty value and no letters from either source string
        Node root = new Node("", indexesBeforeSourceStart, null, null);
        queue.add(root);
    }

    private void addLeftNodeToNodeAndQueue(String string0, Queue<Node> queue, Node node) {
        if ((string0 != null)
                && (node.indexes.get(0) < string0.length())) {
            String string0AtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(string0, node.indexes.get(0) + 1);
            String nodeLeftValue = node.value.concat(string0AtIndex);

            ArrayList<Integer>indexes = new ArrayList<Integer>();
            indexes.add(node.indexes.get(0) + 1);
            indexes.add(node.indexes.get(1));

            node.left = new Node(nodeLeftValue, indexes, null, null);
            queue.add(node.left);
        }
    }

    private void addRightNodeToNodeAndQueue(String string1, Queue<Node> queue, Node node) {
        if ((string1 != null)
                && (node.indexes.get(1) < string1.length())) {
            String string1AtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(string1, node.indexes.get(1) + 1);
            String nodeRightValue = node.value.concat(string1AtIndex);

            ArrayList<Integer>indexes = new ArrayList<Integer>();
            indexes.add(node.indexes.get(0));
            indexes.add(node.indexes.get(1) + 1);

            node.right = new Node(nodeRightValue, indexes, null, null);
            queue.add(node.right);
        }
    }

    //==========================================================================

    /**
     * Traverses binary tree depth first.
     * Uses a stack instead of recursion to reduce risk of call stack overflow.
     * @param shuffledString a potential shuffle of string0 and string1
     * @param string0 a source string
     * @param string1 a source string
     * @return true if shuffledString is a valid shuffle of string0 and string1
     * http://www.cis.upenn.edu/~matuszek/cit594-2012/Pages/backtracking.html
     */
    public boolean isValidShuffleDepthFirst(String shuffledString,
                                            String string0, String string1) {

        Boolean edgeCaseResult = isValidShuffleForEdgeCases(shuffledString, string0, string1);
        if (edgeCaseResult != null) {
            // isValidShuffleForEdgeCases() was able to determine if shuffle is valid
            return edgeCaseResult;
        }

        nodesSearched = new ArrayList<String>();

        // LinkedList class implements Deque interface for lifo stack
        // http://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
        // http://docs.oracle.com/javase/7/docs/api/java/util/Stack.html
        // http://stackoverflow.com/questions/12179887/android-stack-vs-stack
        Deque<NodeExtended> stack = new LinkedList<NodeExtended>();

        addRootNodeToStack(stack);

        while (!stack.isEmpty()) {

            NodeExtended node = stack.peek();
            this.nodesSearched.add(node.value);


            ////////////////////////
            // TODO: after implement only push if valid, can eliminate this check
            String shuffledStringStart = shuffledString.substring(0, node.value.length());
            if (!isNodeValueEqualToValue(node, shuffledStringStart)) {
                // this node is not a valid candidate, so discard it
                stack.pop();
                continue;
            }
            ////////////////////////


            if (isLeafNode(node, string0, string1)) {
                if (isASolution(node, shuffledString, string0, string1)) {
                    return true;
                } else {
                    stack.pop();
                    continue;
                }
            }

            else {
                if (node.hasUnvisitedChildren()) {
                    if (!node.didVisitLeft) {
                        node.didVisitLeft = true;
                        addLeftNodeToNodeAndStack(string0, stack, node);
                    }
                    else if (!node.didVisitRight) {
                        node.didVisitRight = true;
                        addRightNodeToNodeAndStack(string1, stack, node);
                    }
                }
                else {
                    // visited all children, didn't find a solution under this node
                    stack.pop();
                    continue;
                }
            }
        }
        // didn't find a solution
        return false;
    }

    private void addRootNodeToStack(Deque<NodeExtended> stack) {
        // this index value signifies node has no letters from that source
        // e.g. if node.indexes.get(0) == -1, node.value contains no letters from string0
        final Integer INDEX_BEFORE_SOURCE_START = -1;
        ArrayList<Integer>indexesBeforeSourceStart = new ArrayList<Integer>();
        indexesBeforeSourceStart.add(INDEX_BEFORE_SOURCE_START);
        indexesBeforeSourceStart.add(INDEX_BEFORE_SOURCE_START);

        // root node has empty value and no letters from either source string
        NodeExtended root = new NodeExtended("", indexesBeforeSourceStart,
                null, null, false, false);
        stack.push(root);
    }

    // TODO: only push if valid
    private void addLeftNodeToNodeAndStack(String string0, Deque<NodeExtended> stack, NodeExtended node) {
        if ((string0 != null)
                && (node.indexes.get(0) < string0.length())) {
            String string0AtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(string0, node.indexes.get(0) + 1);
            String leftNodeValue = node.value.concat(string0AtIndex);

            ArrayList<Integer>indexes = new ArrayList<Integer>();
            indexes.add(node.indexes.get(0) + 1);
            indexes.add(node.indexes.get(1));

            NodeExtended leftNode = new NodeExtended(leftNodeValue, indexes,
                    null, null, false, false);
            node.left = leftNode;
            stack.push(leftNode);
        }
    }

    // TODO: only push if valid
    private void addRightNodeToNodeAndStack(String string1, Deque<NodeExtended> stack, NodeExtended node) {
        if ((string1 != null)
                && (node.indexes.get(1) < string1.length())) {
            String string1AtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(string1, node.indexes.get(1) + 1);
            String rightNodeValue = node.value.concat(string1AtIndex);

            ArrayList<Integer>indexes = new ArrayList<Integer>();
            indexes.add(node.indexes.get(0));
            indexes.add(node.indexes.get(1) + 1);

            NodeExtended rightNode = new NodeExtended(rightNodeValue, indexes,
                    null, null, false, false);
            node.right = rightNode;
            stack.push(rightNode);
        }
    }

}
