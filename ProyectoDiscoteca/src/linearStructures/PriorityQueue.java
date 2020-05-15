package linearStructures;

import interfaces.IQueue;

public class PriorityQueue<K extends Comparable<K>,V> implements IQueue<K> {
	Heap<K> elements;

	
	public PriorityQueue (boolean Type) {
		elements = new Heap<>(Type);
	}

	@Override
	public void enqueue(K obj) {
		elements.add(obj);
	}

	@Override
	public K dequeue() {
		return elements.obtain();
	}

	public K peek() {
		return elements.getTop();
	}
}

