package edu.ucsd.cse110.lab2;

public class MyQueue {
    private int[] elements;
    private int front;
    private int rear;
    private int size;
    
    public MyQueue(int capacity) {
        elements = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == elements.length;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(int element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
            // // copies elements into double sized array
            // int[] newArr = new int[size * 2];
            // for (int i = 0; i < size; i++) {
            //     newArr[i] = elements[i];
            // }
            // // brand new elements
            // elements = newArr;
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }
    
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int element = elements[front];
        front = (front + 1) % elements.length;
        size--;
        return element;
    }
    
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[front];
    }
}