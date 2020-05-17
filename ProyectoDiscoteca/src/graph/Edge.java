package graph;

import interfaces.IEdge;

public class Edge implements IEdge{

	private int label;
	private Vertex<?> vOrigin;
	private Vertex<?> vDestination;
	private double weight;
	
	public Edge(Vertex<?> v1, Vertex<?> v2, double w) {
		
		vOrigin = v1;
		vDestination = v2;
		weight = w; 
	}
	
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	@Override
	public Vertex<?> getOriginVertex() {
		
		return vOrigin;
	}

	@Override
	public void setOriginVertex(Vertex<?> u) {
		vOrigin = u;
		
	}

	@Override
	public Vertex<?> getDestinationVertex() {
		
		return vDestination;
	}
	
	@Override
	public void setDestinationVertex(Vertex<?> toVertex) {
		vDestination = toVertex;
	}

	@Override
	public double getWeight() {
		
		return weight;
	}

	@Override
	public void setWeight(double w) {

		weight = w;
		
	}
}
