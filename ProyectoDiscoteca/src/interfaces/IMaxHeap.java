package interfaces;

public interface IMaxHeap<V extends Comparable<V>> {

	void max_heapify(int i);
	void build_max_heap();
	void heapsort();
	int parent(int i);
	int left(int i);
	int right(int i);
	
}
