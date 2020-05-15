package linearStructures;

import interfaces.IQueue;

public class Queue<E> implements IQueue<E> {
	
	Node<E> first;
	Node<E> Last;
	
	public Queue() {
		first = null;
		Last = null;
	}
	
	@Override
	public void enqueue(E obj) {
		if(Last != null && first != null) {
			Node<E> ref= new Node<E>(obj);
			Last.addBefore(ref);
			ref.addNext(Last);
			Last = ref;
		}else if(first == null && Last == null) {
			first = new Node<E>(obj);
		}else if(Last == null){
			Node<E> ref= new Node<E>(obj);
			ref.addNext(first);
			Last = ref;
			first.addBefore(Last);
		}
		
	}

	@Override
	public E dequeue() {
		if(first == null && Last == null) {
			return null;
		}
		
		if(first != null) {
			E ref = (E) first.getInfo();
			first = first.before;
			return ref;
		}else if(Last.next == null) {
			E ref = Last.getInfo();
			Last = null;
			return ref;
		}
		
		return null;
		
	}

	public E peek() {
		if(first == null && Last == null) {
			return null;
		}
		
		if(first != null) {
			E ref = (E) first.getInfo();
			return ref;
		}else if(Last.next == null) {
			E ref = Last.getInfo();
			return ref;
		}
		
		return null;
	}

}
