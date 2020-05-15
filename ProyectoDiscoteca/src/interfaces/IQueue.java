package interfaces;

import exception.UnderflowException;

public interface IQueue<T> {
	
	public void enqueue(T data);
	public T dequeue() throws UnderflowException;

}
