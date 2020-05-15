package interfaces;

import java.util.ArrayList;

import exception.GreaterKeyException;
import exception.HeapUnderFlowException;
import exception.ThereIsNotAnEdgeException;
import exception.UnderflowException;
import graph.EdgeL;

public interface IGraph<V extends Comparable<V>, E extends Comparable<E>> {
    void insertVertex(V value);
    void insertEdge( int position1, int position2 , E conection);
    void deleteVertex(int positionVertex);
    void deleteEdge( int position1, int position2, E conection) throws ThereIsNotAnEdgeException;
    void deleteAllEdge( int position1, int position2) throws ThereIsNotAnEdgeException;
    ArrayList<Integer> BFS(int startPosition) throws UnderflowException;
    ArrayList<Integer> DFS(int startPosition);
    ArrayList<ArrayList<Integer>> DFS();
    ArrayList<Integer> Prim(int startPosition) throws GreaterKeyException, HeapUnderFlowException;
    ArrayList<EdgeL<V, E>> Kruskal() throws HeapUnderFlowException;
    Object[] Dijsktra(int startPosition);
    double[][] Floyd_Warshal();
}