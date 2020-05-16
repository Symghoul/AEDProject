package auxiliaryStructures;

import java.util.ArrayList;

import exception.*;
import interfaces.IMinPriorityQueue;

public class MinPriorityQueue<V extends Comparable<V>> extends MinHeap<V> implements IMinPriorityQueue<V> {

    public MinPriorityQueue(ArrayList<V> heap, int max_size){
        super(heap, max_size);
        build_min_heap();
    }

    
    public void insert(V element) throws GreaterKeyException, HeapUnderFlowException {
        heap_size++;
        heap.add(element);
        decrease_key(heap_size - 1, element);
    }

    
    public V minimum() throws HeapUnderFlowException {
        return get(0);
    }

    
    public V extract_min() throws HeapUnderFlowException {
        if(isEmpty())
            throw new HeapUnderFlowException();
        V min = heap.set(0, heap.get(heap_size - 1));
        heap.remove(heap_size - 1);
        heap_size--;
        min_heapify(0);
        return min;
    }

    
    public void decrease_key(int i, V value) throws GreaterKeyException, HeapUnderFlowException {
        if(value.compareTo(get(i)) > 0)
            throw new GreaterKeyException();
        heap.set(i, value);
        for(; i >= 1 && get(parent(i)).compareTo(get(i)) > 0; i = parent(i))
            exchange(i, parent(i));
    }

    
    public void decrease_key(V originalValue, V newValue) throws GreaterKeyException, HeapUnderFlowException {
        int i = heap.indexOf(originalValue);
        if(newValue.compareTo(get(i)) > 0)
            throw new GreaterKeyException();
        heap.set(i, newValue);
        for(; i >= 1 && get(parent(i)).compareTo(get(i)) > 0; i = parent(i))
            exchange(i, parent(i));
    }

    
    public boolean isEmpty() {
        return heap_size == 0;
    }
}