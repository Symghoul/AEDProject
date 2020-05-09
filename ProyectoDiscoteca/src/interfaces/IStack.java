package interfaces;

public interface IStack<A> {
	
	public void push(A a);
	
	public A pop() throws Exception;
	
	public A top() throws Exception;
	
	public boolean isEmpty();
		
	public void removeAll();
	
	public int size();

}
