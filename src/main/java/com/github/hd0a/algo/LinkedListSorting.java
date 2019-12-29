package com.github.hd0a.algo;


/**
 * Day 16 - LinkedList sortedMerge
 */
//https://practice.geeksforgeeks.org/problems/merge-two-sorted-linked-lists/1
public class LinkedListSorting {
    public static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public Node sortedMerge(Node headA, Node headB) {
        Node root = new Node(-1);
        Node holder = root;
        if (headA == null) return headB;
        if (headB == null) return headA;
        while (headA != null && headB != null) {
            if (headA.data <= headB.data) {
                holder.next = headA;
                holder = headA;
                headA = headA.next;
            } else {
                holder.next = headB;
                holder = headB;
                headB = headB.next;
            }
        }
        if (headA != null) {
            holder.next = headA;
        }
        if (headB != null) {
            holder.next = headB;
        }
        return root.next;
    }
}
