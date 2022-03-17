
package sched;

import java.util.ArrayList;
import java.util.Stack;

public class PriorityQueue<E extends Comparable<E>> {
    private Node root;
    private Stack<Node> stack;

    PriorityQueue() {
        this.root = null;
        this.stack = new Stack<Node>();
    }

    PriorityQueue(E value) {
        this.root = new Node(value);
        this.stack = new Stack<Node>();
    }

    public void enqueue(E value) {
        // ! DONE
        Node newNode = new Node(value);
        if (this.root == null) {
            this.root = newNode;
            return;
        }
        root = insert(this.root, newNode);
        balance();
    }

    public E dequeue() {
        // ! DONE
        E returnVal = this.root.value;
        this.root = insert(this.root.left, this.root.right);
        return returnVal;
    }

    public boolean isEmpty() {
        // ! DONE
        if (this.root == null) {
            return true;
        } else {
            return false;
        }
    }

    public E peakNextValue() {
        if (this.root == null) {
            return null;
        }
        return this.root.value;
    }

    private Node merge(Node lhs, Node rhs) {
        if (lhs.right == null) {
            lhs.right = rhs;
            stack.push(lhs);
            return lhs;
        } else if (lhs.right.compareTo(rhs) < 0) {
            lhs.right = merge(lhs.right, rhs);
        } else {
            lhs.right = merge(rhs, lhs.right);
        }
        return lhs;
    }

    private Node insert(Node first, Node second) {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }
        if (first.compareTo(second) < 0) {
            return merge(first, second);
        } else {
            return merge(second, first);
        }
    }

    private void balance() {
        while (!stack.isEmpty()) {
            Node test = this.stack.pop();
            if (depthToNull(test.left) < depthToNull(test.right)) {
                // If the right side is heavier swap the children
                Node temp = test.right;
                test.right = test.left;
                test.left = temp;
            }
        }
    }

    private Integer depthToNull(Node value) {
        if (value == null) {
            return 0;
        }
        return depthToNull(value, 1);
    }

    private Integer depthToNull(Node value, Integer pos) {
        if (value.left == null || value.right == null) {
            return pos;
        }
        return Math.min(depthToNull(value.left, pos + 1), depthToNull(value.right, pos + 1));
    }

    @Override
    public String toString() {
        return "PriorityQueue [root=" + root + "]";
    }

    private class Node {
        private E value;
        private Node left;
        private Node right;

        Node(E value) {
            this.value = value;
        }

        public int compareTo(Node rhs) {
            return this.value.compareTo(rhs.value);
        }

        @Override
        public String toString() {
            return "value: " + value;
        }
    }
}
