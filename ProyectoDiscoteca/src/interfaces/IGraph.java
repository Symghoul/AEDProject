package interfaces;

import java.util.ArrayList;

import exception.GreaterKeyException;
import exception.HeapUnderFlowException;
import exception.UnderflowException;
import exception.VertexNotAdjacentException;
import graph.Edge;
import graph.Vertex;
import graph.VertexL;

public interface IGraph<V> {
	
	Vertex<V> searchVertex(V e);
	boolean isAdjacent(int vertex1, int vertex2) throws VertexNotAdjacentException;
	boolean isEmpty();
	Edge edgeLabel(int vertex1, int vertex2) throws VertexNotAdjacentException; 
    void insertVertex(V value);
    void insertEdge( int origin1, int destiny1, double conection);
    void deleteVertex(int labelVertex);
    void deleteEdge( int labelVertex1, int labelVertex2) throws VertexNotAdjacentException;
    ArrayList<Integer> BFS(int labelVertexStart) throws UnderflowException;
    ArrayList<ArrayList<Integer>> DFS();
//    ArrayList<Integer> DFS(int startPosition);
    ArrayList<Integer> Prim(int labelVertexStart) throws GreaterKeyException, HeapUnderFlowException;
    ArrayList<Edge> Kruskal() throws HeapUnderFlowException;
    Object[] Dijsktra(int labelVertexStart) throws IndexOutOfBoundsException, VertexNotAdjacentException;
    double[][] Floyd_Warshal();
}