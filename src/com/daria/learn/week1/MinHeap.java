package com.daria.learn.week1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

//Not done
public class MinHeap<T extends Comparable> implements Iterable<T>{

    private final ArrayList<T> heap;
    private final int currentSize;

    public MinHeap(int initialCapacity) {
        this.currentSize = 0;
        this.heap = new ArrayList<>(initialCapacity);
    }

    public MinHeap() {
        this(1);
    }

    public void add(T value) {
        heap.add(value);
        swim(heap.size() - 1);
    }

    public T pop() {
        if (heap.isEmpty()) throw new IllegalStateException();

        T min = heap.get(0);
        swap(1, heap.size() - 1);
        return min;
    }

    public T peek() {
        if (heap.isEmpty()) throw new IllegalStateException();
        return heap.get(0);
    }

    private void swim(int k) {
        while (k > 1 && isGreater(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void swap(int i, int k) {
        T temp = heap.get(i);
        heap.add(i, heap.get(k));
        heap.add(k, temp);
    }

    private boolean isGreater(int k1, int k2) {
        return heap.get(k1).compareTo(heap.get(k2)) > 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }
}
