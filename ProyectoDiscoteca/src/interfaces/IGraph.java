package interfaces;

import java.util.ArrayList;

import exception.GreaterKeyException;
import exception.HeapUnderFlowException;
import exception.UnderflowException;
import exception.VertexNotAdjacentException;
import graph.Edge;
import graph.VertexL;

public interface IGraph<V> {
	
	VertexL<V> searchVertex(int e);
	boolean isAdjacent(VertexL<V> vertex1, VertexL<V> vertex2) throws VertexNotAdjacentException;
	boolean isEmpty();
	Edge edgeLabel(VertexL<V> vertex1, VertexL<V> vertex2) throws VertexNotAdjacentException; 
    void insertVertex(V value);
    void insertEdge( VertexL<V> origin, VertexL<V> destiny, double conection);
    void deleteVertex(int labelVertex);
    void deleteEdge( int labelVertex1, int labelVertex2) throws VertexNotAdjacentException;
    ArrayList<Integer> BFS(int labelVertexStart) throws UnderflowException;
    ArrayList<ArrayList<Integer>> DFS(int labelVertexStart);
    ArrayList<Integer> Prim(int labelVertexStart) throws GreaterKeyException, HeapUnderFlowException;
    ArrayList<Edge> Kruskal() throws HeapUnderFlowException;
    Object[] Dijsktra(int labelVertexStart);
    double[][] Floyd_Warshal();
}