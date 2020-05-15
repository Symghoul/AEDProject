package interfaces;

import exception.HeapUnderFlowException;
import exception.SmallerKeyException;

public interface IMaxPriorityQueue<V extends Comparable<V>> {

	void insert(V element) throws SmallerKeyException, HeapUnderFlowException;
	V maximum() throws HeapUnderFlowException;
	V extract_max() throws HeapUnderFlowException;
	void increase_key(int i, V value) throws SmallerKeyException, HeapUnderFlowException;
	boolean isEmpty();
	
}
