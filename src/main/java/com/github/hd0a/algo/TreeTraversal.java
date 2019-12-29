package com.github.hd0a.algo;

/**
 * Day 09 - Binary tree traversal
 * all these orders depend on where we access current node
 * preOrder -> N L R
 * inOrder -> L N R
 * postOrder -> L R N
 */
public class TreeTraversal {
    public static void preOrderTraverse(SimpleTree tree) {
        if (tree == null) {
            return;
        }
        System.out.printf("%d ", tree.value);
        preOrderTraverse(tree.leftChild);
        preOrderTraverse(tree.rightChild);
    }

    public static void inOrderTraverse(SimpleTree tree) {
        if (tree == null) {
            return;
        }
        inOrderTraverse(tree.leftChild);
        System.out.printf("%d ", tree.value);
        inOrderTraverse(tree.rightChild);
    }

    public static void postOrderTraverse(SimpleTree tree) {
        if (tree == null) {
            return;
        }
        postOrderTraverse(tree.leftChild);
        postOrderTraverse(tree.rightChild);
        System.out.printf("%d ", tree.value);
    }

    public static void main(String[] args) {
        SimpleTree tree = new SimpleTree(1);
        tree.leftChild = new SimpleTree(2);
        tree.rightChild = new SimpleTree(3);
        tree.leftChild.leftChild = new SimpleTree(4);
        tree.leftChild.rightChild = new SimpleTree(5);
        tree.rightChild.leftChild = new SimpleTree(6);

        System.out.println("InOrder::");
        inOrderTraverse(tree);
        System.out.println();

        System.out.println("PreOrder::");
        preOrderTraverse(tree);
        System.out.println();

        System.out.println("PostOrder::");
        postOrderTraverse(tree);
        System.out.println();
    }
}
