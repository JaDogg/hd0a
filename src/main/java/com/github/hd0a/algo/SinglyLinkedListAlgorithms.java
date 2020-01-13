package com.github.hd0a.algo;


/**
 * Day 16 - LinkedList sortedMerge
 */
//https://practice.geeksforgeeks.org/problems/merge-two-sorted-linked-lists/1
public class SinglyLinkedListAlgorithms {
    public static class Node {
        private static int sid = 0;
        int data;
        int id;
        Node next;

        Node(int d) {
            data = d;
            next = null;
            id = sid++;
        }

        @Override
        public String toString() {
            return String.format("%d -> %s", data, String.valueOf(next));
        }
    }

    public static Node fromString(String values) {
        if (values == null || values.isBlank()) return null;
        String[] nodeElements = values.split(" ");
        Node head = new Node(-1);
        Node current = head;
        for (String element : nodeElements) {
            int data = Integer.parseInt(element);
            current.next = new Node(data);
            current = current.next;
        }
        return head.next;
    }

    public static Node findHalf(Node head) {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        int middlePos = (size + 1) / 2;
        current = head;
        for (int i = 0; i < middlePos; i++) {
            current = current.next;
        }
        return current;
    }

    public static Node reverse(Node head) {
        if (head == null) return null;
        // WHY:
        // Reverse of single element is itself
        if (head.next == null) return head;
        Node holder = head;
        Node prev = null;
        while (holder != null) {
            Node temp = holder.next;
            holder.next = prev;
            prev = holder;
            holder = temp;
        }
        return prev;
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return false;
        if (head.next.next == null) return head.data == head.next.data;
        boolean palindrome = true;

        Node half = findHalf(head);
        Node reversedHalf = reverse(half);

        Node fromHead = head;
        Node fromReversedHalf = reversedHalf;

        while (fromReversedHalf != null) {
            if (fromHead.data != fromReversedHalf.data) {
                palindrome = false;
            }
            fromReversedHalf = fromReversedHalf.next;
            fromHead = fromHead.next;
        }

        // now we fix things
        // for even number of elements
        if (fromHead.next == half) {
            fromHead.next = reverse(reversedHalf);
        } else {
            // for odd number of elements
            fromHead.next.next = reverse(reversedHalf);
        }

        return palindrome;
    }

    // TODO Merge sort a linked list
    public static Node sortedMerge(Node headA, Node headB) {
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
