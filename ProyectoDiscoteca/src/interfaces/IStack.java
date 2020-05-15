package interfaces;

public interface IStack<E> {
	public void push(E obj);
	public E pop();
	public E peek();
}
