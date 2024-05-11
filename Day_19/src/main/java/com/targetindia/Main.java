package com.targetindia;
public class Main {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node bubbleSort(Node head) {
        if (head == null || head.next == null)
            return head;

        boolean swapped;
        Node current;
        Node tail = null;

        do {
            swapped = false;
            current = head;

            while (current.next != tail) {
                if (current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
            tail = current;
        } while (swapped);

        return head;
    }

    static Node selectionSort(Node head) {
        if (head == null || head.next == null)
            return head;

        Node current = head;

        while (current != null) {
            Node minNode = current;
            Node temp = current.next;

            while (temp != null) {
                if (temp.data < minNode.data)
                    minNode = temp;
                temp = temp.next;
            }

            int tempData = current.data;
            current.data = minNode.data;
            minNode.data = tempData;

            current = current.next;
        }

        return head;
    }

    static Node insertionSort(Node head) {
        if (head == null || head.next == null)
            return head;

        Node sorted = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            sorted = sortedInsert(sorted, current);
            current = next;
        }

        return sorted;
    }

    static Node sortedInsert(Node head, Node newNode) {
        if (head == null || head.data >= newNode.data) {
            newNode.next = head;
            return newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            return head;
        }
    }
    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(5);
        head.next.next = new Node(8);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(20);

        System.out.println("Original list:");
        printList(head);

        head = bubbleSort(head);
        System.out.println("After Bubble Sort:");
        printList(head);

        head = selectionSort(head);
        System.out.println("After Selection Sort:");
        printList(head);

        head = insertionSort(head);
        System.out.println("After Insertion Sort:");
        printList(head);
    }
}

