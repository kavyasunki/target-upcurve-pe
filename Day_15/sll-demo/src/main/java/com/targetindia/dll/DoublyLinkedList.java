package com.targetindia.dll;

import com.targetindia.sll.SinglyLinkedList;

import java.util.NoSuchElementException;

public class DoublyLinkedList {



    class ListNode {
        ListNode prev;
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.prev = null; // this is the default behavior; no need to do this.
            this.next = null; // this is the default behavior; no need to do this.
        }

    }

    private ListNode head;
    private ListNode tail;
    private int length;


    public int size() {
        return length;
    }

    public void prepend(int data) {
        length++;
        var newNode = new ListNode(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void append(int data) {
        length++;
        var newNode = new ListNode(data);
        if (head == null) { // or tail==null
            head = tail = newNode;
            return;
        }

        newNode.prev = tail;
        tail = tail.next = newNode;
    }

    public void displayForward() {
        var curr = head;
        System.out.print("HEAD <-> ");
        while (curr != null) {
            System.out.print(curr.data);
            System.out.print(" <-> ");
            curr = curr.next;
        }
        System.out.println("TAIL");
    }


    public void displayBackward() {
        var curr = tail;
        System.out.print("TAIL <-> ");
        while (curr != null) {
            System.out.print(curr.data);
            System.out.print(" <-> ");
            curr = curr.prev;
        }
        System.out.println("HEAD");
    }

    public int deleteFirst() throws NoSuchElementException {
        if (head == null) { // or tail==null
            throw new NoSuchElementException("List is empty!");
        }

        length--;

        if (head == tail) {
            // only element in the list
            int data = head.data;
            head = tail = null;
            return data;
        }

        var nodeToDelete = head;
        head = head.next;
        head.prev = null;
        nodeToDelete.next = null;
        return nodeToDelete.data;
    }
    public int deleteLast() {
        if (head == null) { // or tail==null
            throw new NoSuchElementException("List is empty!");
        }

        length--;

        if (head == tail) {
            // only element in the list
            int data = head.data;
            head = tail = null;
            return data;
        }

        var nodeToDelete = tail;
        tail = tail.prev;
        tail.next = null;
        nodeToDelete.prev = null;
        return nodeToDelete.data;
    }
    public void insert(int index, int data) {

        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            this.prepend(data);
            return;
        }

        if (index == length) {
            this.append(data);
            return;
        }

        var curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        var newNode = new ListNode(data);
        newNode.next = curr.next;
        curr.next = newNode;
        length++;
    }
    public int deleteAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            return this.deleteFirst();
        }

        var curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        var nodeToDelete = curr.next;
        curr.next = nodeToDelete.next;
        nodeToDelete.next = null;
        length--;
        return nodeToDelete.data;
    }



}
