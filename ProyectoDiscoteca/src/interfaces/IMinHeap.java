package interfaces;

public interface IMinHeap<V> {
	
	void min_heapify(int i);
	void build_min_heap();
	void heapsort();
	int parent(int i);
	int left(int i);
	int right(int i);

}
