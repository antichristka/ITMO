package Kate.lab6.binTree;

import java.util.NoSuchElementException;

/***
 * Класс бинарного дерева
 */
public class BinaryTree {
    public Node root; // корень дерева

    public BinaryTree() {
        this.root = null;
    }

    public void add(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                } else {
                    current = current.right;
                }
            }
        }
    }

    public void printTree(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            printTree(node.left);
            printTree(node.right);
        }
    }

}