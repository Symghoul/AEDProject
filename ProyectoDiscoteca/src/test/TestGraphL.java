package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import exception.ThereIsNotAnEdgeException;
import exception.UnderflowException;
import exception.VertexNotAdjacentException;
import graph.GraphL;
import graph.VertexL;
import graph.Edge;


class TestGraphL {

	private GraphL<Integer> directedGraph;
    private GraphL<String> directedGraph2;

    private void setupScene1(){
        directedGraph = new GraphL<>(true, true, new VertexL<Integer>(1));
    }

    private void setupScene2(){
        setupScene1();
        directedGraph.insertVertex(2); 
        directedGraph.insertEdge(1, 0, 10); 
        directedGraph.insertVertex(3); 
    }

    private void setupScene3(){
        setupScene2();
        directedGraph.insertEdge(1, 0, 40);
    }

    private void setupScene4(){
        setupScene2();
        directedGraph.insertEdge(1, 1, 20); 
    }

    private void setupScene5(){
        setupScene3();
        directedGraph.insertEdge(2, 1, 30); 
    }

    private void setupScene6(){
        setupScene2();
        directedGraph.insertEdge(2, 2, 100); 
    }

    private void setupScene7(){
        setupScene6();
        directedGraph.insertEdge(2, 2, 200); // Inserts another edge from (3) to (3)
    }

    /**
     * A multigraph with a cycle.
     */
    private void setupScene8(){
        setupScene2();
        directedGraph.insertEdge(2, 1, 50); // From (3) to (2)
        directedGraph.insertEdge(1, 0, 40); // From (2) to (1)
        directedGraph.insertEdge(1,1,30); // From (2) to (2)
        directedGraph.insertEdge(1, 2, 20); // From (2) to (3)
    }


    /**
     * A disconnected simple graph
     */
    private void setupScene9(){
        setupScene1();
        for(int i = 2; i <= 9; i++){
            directedGraph.insertVertex(i);
        }
        directedGraph.insertEdge(0, 1, 10);
        directedGraph.insertEdge(0, 2, 23);
        directedGraph.insertEdge(0, 3, 15);
        directedGraph.insertEdge(1, 0, 18);
        directedGraph.insertEdge(2, 5, 40);
        directedGraph.insertEdge(3, 4, 12);
        directedGraph.insertEdge(4, 5, 28);
        directedGraph.insertEdge(6, 7, 30);
        directedGraph.insertEdge(7, 8, 39);
        directedGraph.insertEdge(8, 6, 20);
    }

    /**
     * A connected simple graph
     */
    private void setupScene10(){
        setupScene1();
        for (int i = 2; i <= 5; i++){
            directedGraph.insertVertex(i);
        }
        directedGraph.insertEdge(0, 1,10);
        directedGraph.insertEdge(1, 2,20);
        directedGraph.insertEdge(2, 3,30);
        directedGraph.insertEdge(3, 4,40);
        directedGraph.insertEdge(4, 0,50);
    }

    /**
     * A second connected simple graph
     */
    private void setupScene11(){
        setupScene1();
        for(int i = 2; i <= 6; i++ )
            directedGraph.insertVertex(i);
	        directedGraph.insertEdge(0, 1, 10);
	        directedGraph.insertEdge(0, 2, 23);
	        directedGraph.insertEdge(0, 3, 15);
	        directedGraph.insertEdge(2, 5, 40);
	        directedGraph.insertEdge(4, 5, 28);
	        directedGraph.insertEdge(3, 4, 12);
    }

    /**
     * Simple connected graph for Dijkstra.
     */
    private void setupScene12(){
        directedGraph2 = new GraphL<>(true, true);
        directedGraph2.insertVertex("A");
        directedGraph2.insertVertex("B");
        directedGraph2.insertVertex("C");
        directedGraph2.insertVertex("D");
        directedGraph2.insertVertex("E");
        directedGraph2.insertVertex("F");
        directedGraph2.insertEdge(0, 1, 3);
        directedGraph2.insertEdge(0, 2, 2);
        directedGraph2.insertEdge(1, 3, 1);
        directedGraph2.insertEdge(2, 1, 1);
        directedGraph2.insertEdge(2, 4, 5);
        directedGraph2.insertEdge(4, 1, 1);
        directedGraph2.insertEdge(4, 5, 3);
        directedGraph2.insertEdge(3, 4, 2);
        directedGraph2.insertEdge(3, 5, 2);
    }

    /**
     * A multigraph for Dijkstra.
     */
    private void setupScene13(){
        directedGraph2 = new GraphL<>(true, true);
        directedGraph2.insertVertex("A");
        directedGraph2.insertVertex("B");
        directedGraph2.insertVertex("C");
        directedGraph2.insertVertex("D");
        directedGraph2.insertVertex("E");
        directedGraph2.insertVertex("F");
        directedGraph2.insertEdge(0, 1, 3);
        directedGraph2.insertEdge(0, 2, 1);
        directedGraph2.insertEdge(0, 2, 2);
        directedGraph2.insertEdge(0, 3, 2);
        directedGraph2.insertEdge(0, 3, 2);
        directedGraph2.insertEdge(1, 3, 4);
        directedGraph2.insertEdge(1, 4, 1);
        directedGraph2.insertEdge(4, 1, 1);
        directedGraph2.insertEdge(3, 4, 3);
        directedGraph2.insertEdge(3, 4, 2);
        directedGraph2.insertEdge(3, 4, 1);
        directedGraph2.insertEdge(3, 5, 7);
        directedGraph2.insertEdge(4, 5, 1);
    }

    @Test
    void constructorMethodTest(){
        setupScene1();

        assertTrue(directedGraph.isDirected()); // Tests if the graph is directed
        assertTrue(directedGraph.isWeighted()); // Tests if the graph is weighted
        assertEquals(1, directedGraph.getNumberOfVertices()); // Tests the number of vertices in the graph
        assertEquals(0, directedGraph.getNumberOfEdges()); // Tests the number of edges in the graph
    }

    @Test
    void insertVertexTest(){
        setupScene1();
        directedGraph.insertVertex(2);

        assertEquals(2, directedGraph.getNumberOfVertices());
        assertEquals(1, directedGraph.searchVertex(0));
        assertEquals(0, directedGraph.searchVertex(0)); // Adjacent vertices to (1).
        assertEquals(2, directedGraph.searchVertex(0));
        assertEquals(0, directedGraph.searchVertex(0)); // Adjacent vertices to (2).
    }


    @Test
    void deleteVertexTest(){
        setupScene8();

        assertEquals(3, directedGraph.getNumberOfVertices()); 

        try{
            directedGraph.deleteVertex(3);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }
        setupScene2();

        assertEquals(3, directedGraph.getNumberOfVertices()); 
        directedGraph.deleteVertex(2);
        assertTrue(directedGraph.getVertex().size() == 2); 

        setupScene2();

        assertEquals(3, directedGraph.getNumberOfVertices());
        directedGraph.deleteVertex(1);
        assertEquals(2, directedGraph.getNumberOfVertices()); 
        
        setupScene2();

        assertEquals(3, directedGraph.getNumberOfVertices()); 
        assertEquals(1, directedGraph.getVertex().get(1)); 

        directedGraph.deleteVertex(0);

        assertEquals(2, directedGraph.getNumberOfVertices()); 
        assertEquals(0, directedGraph.getVertex().get(1)); 

        setupScene8();

        assertEquals(3, directedGraph.getNumberOfVertices()); 
        assertEquals(5, directedGraph.getNumberOfEdges()); 
       
        directedGraph.deleteVertex(1);

        assertEquals(0, directedGraph.getNumberOfEdges()); 
        assertEquals(2, directedGraph.getNumberOfVertices()); 
    }

    @Test
    void deleteEdgeTest() throws ThereIsNotAnEdgeException, VertexNotAdjacentException {
        ArrayList<Edge> edges;
        setupScene8();

        try{
            directedGraph.deleteEdge(1, 0);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        setupScene2();

        directedGraph.deleteEdge(1, 0);
        assertEquals(0, directedGraph.searchVertex(1));
        assertEquals(0, directedGraph.getNumberOfEdges());

        setupScene3();

        assertEquals(2, directedGraph.searchVertex(1));

        directedGraph.deleteEdge(1, 0);
        assertEquals(1, directedGraph.searchVertex(1));
        assertEquals(1, directedGraph.getNumberOfEdges());

        edges = directedGraph.getEdges(1, 0);
        assertEquals(1, edges.size());

        assertEquals(0, directedGraph.searchVertex(0));


        setupScene4();

        assertEquals(2, directedGraph.searchVertex(0));
        assertEquals(2, directedGraph.getNumberOfEdges());

        edges = directedGraph.getEdges(1, 1);
        assertEquals(1, edges.size());

        directedGraph.deleteEdge(1, 1);

        assertEquals(1, directedGraph.getVertex().get(1));
        assertEquals(1, directedGraph.getNumberOfEdges());

        edges = directedGraph.getEdges(1, 0);

        assertEquals(directedGraph.getVertex().get(0).getValue(), edges.get(0).getDestinationVertex().getValue());
        assertEquals(directedGraph.getVertex().get(1).getValue(), edges.get(0).getOriginVertex().getValue());
        assertEquals(10, edges.get(0).getWeight());
    }

    private void printBFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = directedGraph.getVertex().get(j).getPredecessor();
            System.out.println("Valor: " + directedGraph.getVertex().get(j).getValue());
            System.out.println("Color: " + directedGraph.getVertex().get(j).getColor());
            System.out.println("Distancia desde el vértice " + directedGraph.getVertex().get(tree.get(0)).getValue() + ": " + directedGraph.getVertex().get(j).getDistance());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : directedGraph.getVertex().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    private void printDFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = directedGraph.getVertex().get(j).getPredecessor();
            System.out.println("Valor: " + directedGraph.getVertex().get(j).getValue());
            System.out.println("Color: " + directedGraph.getVertex().get(j).getColor());
            System.out.println("Tiempo inicial: " + directedGraph.getVertex().get(j).getInitialTime());
            System.out.println("Tiempo final: " + directedGraph.getVertex().get(j).getFinalTime());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : directedGraph.getVertex().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    @Test
    void BFSWithStartPositionTest() throws UnderflowException {
        ArrayList<Integer> tree = null;
        // Case 1: A graph with one vertex.
        setupScene1();
        tree = directedGraph.BFS(0);
        assertEquals(1, tree.size());
        assertEquals(directedGraph.getVertex().get(0), directedGraph.getVertex().get(tree.get(0)));

        // Case 2: A disconnected graph of n vertices.
        setupScene9();
        tree = directedGraph.BFS(0);
        assertEquals(6, tree.size());
        assertEquals(1, directedGraph.searchVertex(0));
        assertEquals(0, directedGraph.getVertex().get(tree.get(0)).getDistance());
        assertEquals(-1, directedGraph.getVertex().get(tree.get(0)).getPredecessor());
        assertEquals(2, directedGraph.searchVertex(1));
        assertEquals(1, directedGraph.getVertex().get(tree.get(1)).getDistance());
        assertEquals(0, directedGraph.getVertex().get(tree.get(1)).getPredecessor());
        assertEquals(3, directedGraph.searchVertex(2));
        assertEquals(1, directedGraph.getVertex().get(tree.get(2)).getDistance());
        assertEquals(0, directedGraph.getVertex().get(tree.get(2)).getPredecessor());
        assertEquals(4, directedGraph.searchVertex(3));
        assertEquals(1, directedGraph.getVertex().get(tree.get(3)).getDistance());
        assertEquals(0, directedGraph.getVertex().get(tree.get(3)).getPredecessor());
        assertEquals(6, directedGraph.searchVertex(4));
        assertEquals(2, directedGraph.getVertex().get(tree.get(4)).getDistance());
        assertEquals(2, directedGraph.getVertex().get(tree.get(4)).getPredecessor());
        assertEquals(5, directedGraph.searchVertex(5));
        assertEquals(2, directedGraph.getVertex().get(tree.get(5)).getDistance());
        assertEquals(3, directedGraph.getVertex().get(tree.get(5)).getPredecessor());

        tree = directedGraph.BFS(6);
        assertEquals(3, tree.size());
        assertEquals(7, directedGraph.searchVertex(0));
        assertEquals(0, directedGraph.getVertex().get(tree.get(0)).getDistance());
        assertEquals(-1, directedGraph.getVertex().get(tree.get(0)).getPredecessor());
        assertEquals(8, directedGraph.searchVertex(1));
        assertEquals(1, directedGraph.getVertex().get(tree.get(1)).getDistance());
        assertEquals(6, directedGraph.getVertex().get(tree.get(1)).getPredecessor());
        assertEquals(9, directedGraph.searchVertex(2));
        assertEquals(2, directedGraph.getVertex().get(tree.get(2)).getDistance());
        assertEquals(7, directedGraph.getVertex().get(tree.get(2)).getPredecessor());

        // Case 3: A connected graph with n vertices.
        setupScene10();
        tree = directedGraph.BFS(0);
        assertEquals(5, tree.size());

        // Case 4: A graph with a cycle.
        setupScene8();
        System.out.println("\n------------------------------ BFS with start position ------------------------------");
        System.out.println("--------- Starting from (3) ---------");
        tree = directedGraph.BFS(2);
        assertEquals(3, tree.size());
        printBFSTree(tree);
        System.out.println("--------- Starting from (2) ---------");
        tree = directedGraph.BFS(1);
        assertEquals(3, tree.size());
        printBFSTree(tree);
    }


    @Test
    void DFSWithInitialVertexTest(){
        ArrayList<ArrayList<Integer>> tree;
        // Case 1: A graph with one vertex. 
        setupScene1();
        tree = directedGraph.DFS();
        assertEquals(1, tree.size());

        setupScene11();
        tree = directedGraph.DFS();
        assertEquals(2, tree.size());
     
        // Case 3: A disconnected graph of n vertices.
        setupScene9();
        tree = directedGraph.DFS();
        assertEquals(6, tree.size());
        System.out.println("\n------------------------------ DFS with start position ------------------------------");
        System.out.println("---------- Case 3: A disconnected graph of n vertices ---------");
        System.out.println("--------First tree-------");

        // Case 4: A graph with a cycle.
        setupScene8();
        System.out.println("---------- Case 4: A graph with a cycle. ---------");
        System.out.println("--------- Starting from (3) ---------");
        assertEquals(3, tree.size());
        System.out.println("--------- Starting from (2) ---------");
        tree = directedGraph.DFS();
        assertEquals(3, tree.size());
    }

    @Test
    void DFSWithoutStartPositionTest(){
        ArrayList<ArrayList<Integer>> forest;
        // Case 1: A graph with one vertex.
        setupScene1();
        forest = directedGraph.DFS();
        assertEquals(1, forest.size());
        assertEquals(1, forest.get(0).size());

        
        setupScene11();
        forest = directedGraph.DFS();
        assertEquals(1, forest.size());
        assertEquals(6, forest.get(0).size());
        System.out.println("\n------------------------------ DFS without start position ------------------------------");
        System.out.println("---------- Case 2: A connected graph of n vertices ---------");
        printDFSTree(forest.get(0));

        // Case 3: A disconnected graph of n vertices.
        setupScene9();
        forest = directedGraph.DFS();
        assertEquals(2, forest.size());
        assertEquals(6, forest.get(0).size());
        assertEquals(3, forest.get(1).size());
        System.out.println("===================CASE 3: DISCONNECTED GRAPH===================");
        System.out.println("--------First tree-------");
        printDFSTree(forest.get(0));
        System.out.println("\n--------Second tree-------");
        printDFSTree(forest.get(1));
    }

    @Test
    void DijkstraTest() throws IndexOutOfBoundsException, VertexNotAdjacentException{
        Object[] a;
        double[] dist;
        int[] pred;

        // Case 1: A graph with one vertex
        setupScene1();
        a = directedGraph.Dijsktra(0);
        dist = (double[])a[0];
        pred = (int[])a[1];
        assertEquals(1, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(1, pred.length);
        assertEquals(-1, pred[0]);

        // Case 2: A simple connected graph
        setupScene12();
        a = directedGraph2.Dijsktra(0);
        dist = (double[])a[0];
        pred = (int[])a[1];

        assertEquals(6, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(4, dist[3]);
        assertEquals(6, dist[4]);
        assertEquals(6, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(0, pred[1]);
        assertEquals(0, pred[2]);
        assertEquals(1, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(3, pred[5]);

        a = directedGraph2.Dijsktra(3);
        dist = (double[])a[0];
        pred = (int[])a[1];

        assertEquals(6, dist.length);
        assertEquals(Integer.MAX_VALUE, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(Integer.MAX_VALUE, dist[2]);
        assertEquals(0, dist[3]);
        assertEquals(2, dist[4]);
        assertEquals(2, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(4, pred[1]);
        assertEquals(-1, pred[2]);
        assertEquals(-1, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(3, pred[5]);

        // Case 3: A connected multigraph
        setupScene13();
        a = directedGraph2.Dijsktra(0);
        dist = (double[])a[0];
        pred = (int[])a[1];

        assertEquals(6, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(1, dist[2]);
        assertEquals(2, dist[3]);
        assertEquals(3, dist[4]);
        assertEquals(4, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(0, pred[1]);
        assertEquals(0, pred[2]);
        assertEquals(0, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(4, pred[5]);

        setupScene1();
        try{
            a = directedGraph.Dijsktra(90);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }
    }
}


