package interfaces;

import graph.Vertex;

public interface IEdge {

	public Vertex<?> getOriginVertex();

    public void setOriginVertex(Vertex<?> u);

    public Vertex<?> getDestinationVertex();

    public void setDestinationVertex(Vertex<?> toVertex);

    public double getWeight();

    public void setWeight(double weight);
}
