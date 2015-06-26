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
     * @param sourceStrings an array. Count == 2.
     * @return true if shuffledString is a valid shuffle of source strings.
     * return false if shuffledString is not a valid shuffle of source strings.
     * return null if method can't tell if shuffledString is a valid shuffle of source strings.
     */
    protected Boolean isValidShuffleForEdgeCases(String shuffledString, ArrayList<String>sourceStrings) {

        if (shuffledString == null) {
            if ((sourceStrings.get(0) == null) && (sourceStrings.get(1) == null)) {
                return true;
            } else {
                return false;
            }
        }

        if ("".equals(shuffledString)) {
            if (("".equals(sourceStrings.get(0))) && ("".equals(sourceStrings.get(1)))) {
                return true;
            } else {
                return false;
            }
        }

        if ((shuffledString != null)
                && StringUtils.isStringNullOrEmpty(sourceStrings.get(0))
                && StringUtils.isStringNullOrEmpty(sourceStrings.get(1))) {
            return false;
        }

        if (StringUtils.isStringNullOrEmpty(sourceStrings.get(0))
                && shuffledString.equals(sourceStrings.get(1))) {
            return true;
        }

        if (StringUtils.isStringNullOrEmpty(sourceStrings.get(1))
                && shuffledString.equals(sourceStrings.get(0))) {
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
     * @param sourceStrings Each element may be null or empty "".
     * @return true if node indexes.get(0) and indexes.get(1) are at end of
     * sourceString.get(0) and sourceString.get(1)
     */
    protected boolean isLeafNode(Node node, ArrayList<String>sourceStrings) {

        if (StringUtils.isStringNullOrEmpty(sourceStrings.get(0))
                && StringUtils.isStringNullOrEmpty(sourceStrings.get(1))) {
            return true;
        }

        if (StringUtils.isStringNullOrEmpty(sourceStrings.get(0))) {
            if (node.indexes.get(1) == sourceStrings.get(1).length() - 1) {
                return true;
            } else {
                return false;
            }
        }

        if (StringUtils.isStringNullOrEmpty(sourceStrings.get(1))) {
            if (node.indexes.get(0) == sourceStrings.get(0).length() - 1) {
                return true;
            } else {
                return false;
            }
        }

        // string0 and string1 are non-empty

        if (isNodeIndex0AtEndOfString(node, sourceStrings.get(0))
                && isNodeIndex1AtEndOfString(node, sourceStrings.get(1))) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isASolution(Node node, String shuffledString, ArrayList<String>sourceStrings) {
        if (isLeafNode(node, sourceStrings)
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
     * @param shuffledString a potential shuffle of source strings
     * @param sourceStrings an array of source strings. Currently assumes count = 2
     * @return true if shuffledString is a valid shuffle of source strings.
     */
    public boolean isValidShuffle(String shuffledString,
                                  ArrayList<String>sourceStrings) {

        Boolean edgeCaseResult = isValidShuffleForEdgeCases(shuffledString, sourceStrings);
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

            if (isLeafNode(node, sourceStrings)) {
                // node is a terminal node
                if (isASolution(node, shuffledString, sourceStrings)) {
                    return true;
                } else {
                    // skip to next iteration, next node in queue
                    continue;
                }
            }

            String shuffledStringStart = shuffledString.substring(0, node.value.length());
            if (isNodeValueEqualToValue(node, shuffledStringStart)) {
                // path to this node is a valid candidate, so add sub-branches
                addChildNodeAtIndexToNodeAndQueue(sourceStrings, 0, node, queue);
                addChildNodeAtIndexToNodeAndQueue(sourceStrings, 1, node, queue);
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
        Node root = new Node("", indexesBeforeSourceStart, null);
        queue.add(root);
    }

    /**
     * @param sourceStrings
     * @param childIndex 0 to add left child, 1 to add right child
     * @param queue
     * @param node Node that child node will be added to
     */
    private void addChildNodeAtIndexToNodeAndQueue(ArrayList<String>sourceStrings,
                                                   Integer childIndex,
                                                   Node node, Queue<Node> queue) {
        Integer otherChildIndex = 0;
        if (childIndex == 0) {
            otherChildIndex = 1;
        }

        if ((sourceStrings.get(childIndex) != null)
                && (node.indexes.get(childIndex) < sourceStrings.get(childIndex).length())) {
            String childStringAtIndex = StringUtils.getSafeSubstringLengthOneAtIndex(sourceStrings.get(childIndex),
                    node.indexes.get(childIndex) + 1);
            String childNodeValue = node.value.concat(childStringAtIndex);

            ArrayList<Integer>childNodeIndexes = new ArrayList<Integer>();
            if (childIndex == 0) {
                childNodeIndexes.add(node.indexes.get(childIndex) + 1);
                childNodeIndexes.add(node.indexes.get(otherChildIndex));
            }
            else {
                childNodeIndexes.add(node.indexes.get(otherChildIndex));
                childNodeIndexes.add(node.indexes.get(childIndex) + 1);
            }

            Node childNode = new Node(childNodeValue, childNodeIndexes, null);
            ((ArrayList<Node>)node.children).set(childIndex, childNode);
            queue.add(childNode);
        }
    }

    //==========================================================================

    /**
     * Traverses binary tree depth first.
     * Uses a stack instead of recursion to reduce risk of call stack overflow.
     * @param shuffledString a potential shuffle of string0 and string1
     * @param sourceStrings an array of source strings. Currently assumes count = 2
     * @return true if shuffledString is a valid shuffle of source strings
     * http://www.cis.upenn.edu/~matuszek/cit594-2012/Pages/backtracking.html
     */
    public boolean isValidShuffleDepthFirst(String shuffledString,
                                            ArrayList<String>sourceStrings) {

        Boolean edgeCaseResult = isValidShuffleForEdgeCases(shuffledString, sourceStrings);
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


            if (isLeafNode(node, sourceStrings)) {
                if (isASolution(node, shuffledString, sourceStrings)) {
                    return true;
                } else {
                    stack.pop();
                    continue;
                }
            }

            else {
                if (node.didVisitAllChildren()) {
                    // didn't find a solution under this node
                    stack.pop();
                    continue;
                }
                else {
                    if (!node.didVisitLeft) {
                        node.didVisitLeft = true;
                        addLeftNodeToNodeAndStack(sourceStrings.get(0), stack, node);
                    }
                    else if (!node.didVisitRight) {
                        node.didVisitRight = true;
                        addRightNodeToNodeAndStack(sourceStrings.get(1), stack, node);
                    }
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

        ArrayList<NodeExtended> childrenNull = new ArrayList<NodeExtended>();
        childrenNull.add(null);
        childrenNull.add(null);

        // root node has empty value and no letters from either source string
        NodeExtended root = new NodeExtended("", indexesBeforeSourceStart,
                childrenNull, false, false);
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
                    null, false, false);
            ((ArrayList<NodeExtended>)node.children).set(0, leftNode);
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
                    null, false, false);
            ((ArrayList<NodeExtended>)node.children).set(1, rightNode);
            stack.push(rightNode);
        }
    }

}
