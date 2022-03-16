
package sched;

import java.util.Stack;

public class PriorityQueue<E extends Comparable<E>> {
    private Node root;
    private Stack<Node> stack;

    @Override
    public String toString() {
        return "PriorityQueue [root=" + root + "]";
    }

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
        root = insert(newNode, this.root);
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

    private Node merge(Node lhs, Node rhs) {
        if (lhs.right == null) {
            lhs.right = rhs;
            stack.push(lhs);
            return lhs;
        } else if (lhs.right.compareTo(rhs) < 0) {
            lhs.right = merge(lhs.right, rhs);
            stack.push(lhs.right);
        } else {
            lhs.right = merge(rhs, lhs.right);
            stack.push(lhs.right);
        }
        return null;
    }

    private Node insert(Node value, Node start) {
        if (value == null) {
            return start;
        } else if (start == null) {
            return value;
        }
        if (value.compareTo(start) < 0) {
            return merge(value, start);
        } else {
            return merge(start, value);
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
    }
}
