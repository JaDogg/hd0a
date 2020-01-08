package com.github.hd0a.algo;

import java.util.Stack;

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

    public static void preOrderTraverseStack(SimpleTree tree) {
        Stack<SimpleTree> nodeStack = new Stack<>();
        if (tree == null) {
            return;
        }
        nodeStack.push(tree);
        while (!nodeStack.isEmpty()) {
            SimpleTree current = nodeStack.pop();
            System.out.printf("%d ", current.value);
            if (current.rightChild != null) nodeStack.push(current.rightChild);
            if (current.leftChild != null) nodeStack.push(current.leftChild);
        }
    }

    public static void inOrderTraverse(SimpleTree tree) {
        if (tree == null) {
            return;
        }
        inOrderTraverse(tree.leftChild);
        System.out.printf("%d ", tree.value);
        inOrderTraverse(tree.rightChild);
    }

    public static void inOrderTraverseStack(SimpleTree tree) {
        if (tree == null) return;
        Stack<SimpleTree> nodeStack = new Stack<>();
        SimpleTree current = tree;

        while (current != null || !nodeStack.isEmpty()) {
            while (current != null) {
                nodeStack.push(current);
                current = current.leftChild;
            }
            current = nodeStack.pop();
            System.out.printf("%d ", current.value);
            current = current.rightChild;
        }
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
        System.out.print("Recursive   :");
        inOrderTraverse(tree);
        System.out.println();
        System.out.print("Stack based :");
        inOrderTraverseStack(tree);
        System.out.println();
        System.out.println();

        System.out.println("PreOrder::");
        System.out.print("Recursive   :");
        preOrderTraverse(tree);
        System.out.println();
        System.out.print("Stack based :");
        preOrderTraverseStack(tree);
        System.out.println();
        System.out.println();

        System.out.println("PostOrder::");
        postOrderTraverse(tree);
        System.out.println();
    }
}
