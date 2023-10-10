package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int modCount;
    private ForwardLinked.Node<T> head;
    private int size;

    public void add(T value) {
        size++;
        modCount++;
        ForwardLinked.Node<T> node = head;
        ForwardLinked.Node<T> newNode = new ForwardLinked.Node<>(value, null);
        if (node == null) {
            head = newNode;
        } else {
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T element = head.item;
        ForwardLinked.Node<T> buf = head;
        head = head.next;
        buf.item = null;
        buf.next = null;
        size--;
        modCount++;
        return element;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            ForwardLinked.Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Коллекция был изменена");
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.item;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T item;
        ForwardLinked.Node<T> next;

        Node(T element, ForwardLinked.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}