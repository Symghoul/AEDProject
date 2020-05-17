package graph;

public class Vertex<V> {

	private V value;
	private int label;
    private double distance;
    private byte color;
    private int predecessor;
    private int initialTime;
    private int finalTime;
	
	public Vertex(V v) {
		value = v;
        distance = -1;
        color = 0;
        predecessor = -1;
        initialTime = 0;
        finalTime = 0;
	}

	public V getValue() {
		
		return value;
	}

	public void setValue(V v) {
		
		value = v;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public double getDistance() {
		
		return distance;
	}

	public void setDistance(double d) {
		distance = d;
		
	}
	
	public byte getColor() {
		return color;
	}
	
	public void setColor(byte color) {
		this.color = color;
	}
	
	public int getPredecessor() {
		return predecessor;
	}
	
	public void setPredecessor(int predecessor) {
		this.predecessor = predecessor;
	}
	
	public int getInitialTime() {
		return initialTime;
	}
	
	public void setInitialTime(int initialTime) {
		this.initialTime = initialTime;
	}
	
	public int getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(int finalTime) {
		this.finalTime = finalTime;
	}

}
