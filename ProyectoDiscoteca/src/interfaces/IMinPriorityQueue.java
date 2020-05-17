package interfaces;

import exception.GreaterKeyException;
import exception.HeapUnderFlowException;

public interface IMinPriorityQueue<V> {
	
	void insert(V element) throws GreaterKeyException, HeapUnderFlowException;
	V minimum() throws HeapUnderFlowException;
	V extract_min() throws HeapUnderFlowException;
	void decrease_key(int i, V value) throws GreaterKeyException, HeapUnderFlowException;
	void decrease_key(V originaValue, V newValue) throws GreaterKeyException, HeapUnderFlowException;
	boolean isEmpty();

}
