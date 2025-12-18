package task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RingBuffer<T> {
    private static class Node<E> {
        E value;
        Node<E> next;
        Node(E value) { this.value = value; }
    }

    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    private final Node<T>[] nodes;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public RingBuffer(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity <= 0");
        this.capacity = capacity;
        this.nodes = (Node<T>[]) new Node[capacity];

        Node<T> first = new Node<>(null);
        nodes[0] = first;
        Node<T> prev = first;

        for (int i = 1; i < capacity; i++) {
            Node<T> n = new Node<>(null);
            nodes[i] = n;
            prev.next = n;
            prev = n;
        }
        prev.next = first;
    }

    public void put(T item) throws InterruptedException {
        if (item == null) throw new IllegalArgumentException("null item");
        lock.lock();
        try {
            while (size == capacity) notFull.await();

            nodes[tail].value = item;
            tail = (tail + 1) % capacity;
            size++;

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) notEmpty.await();

            T item = nodes[head].value;
            nodes[head].value = null;
            head = (head + 1) % capacity;
            size--;

            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
