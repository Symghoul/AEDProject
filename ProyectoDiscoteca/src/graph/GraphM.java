package graph;


import java.util.*;

import auxiliaryStructures.DisjointSet;
import exception.HeapUnderFlowException;
import exception.VertexNotAdjacentException;
import interfaces.IGraph;

public class GraphM<V> implements IGraph<V>{
	
    private ArrayList<VertexM<V>> elementsReference;
    private int nVertex;
    private boolean weighted;
    private  boolean directed;
    private Edge matrixAdyacency[][];
    private boolean visitedG[];

    public GraphM(ArrayList<VertexM<V>> eR, boolean w, boolean d) {
        elementsReference = eR;
        weighted = w;
        directed = d;
        nVertex = elementsReference.size();
        matrixAdyacency = new Edge[200][200];
    }

    public GraphM(boolean w, boolean d) {
        elementsReference = new ArrayList<>();
        weighted = w;
        directed = d;
        matrixAdyacency = new Edge[200][200];
        nVertex = 0;

    }
    
    public int getnVertex() {
    	return nVertex;
    }
    
    public void setnVertex(int nVertex) {
    	this.nVertex = nVertex;
    }
    
    public boolean isWeighted() {
    	return weighted;
    }
    
    public void setWeighted(boolean weighted) {
    	this.weighted = weighted;
    }
    
    public boolean isDirected() {
    	return directed;
    }
    
    public void setDirected(boolean directed) {
    	this.directed = directed;
    }
    
    public ArrayList<VertexM<V>> getElementsReference() {
    	return elementsReference;
    }
    
	@Override
	public Edge edgeLabel(int vertex1, int vertex2) throws VertexNotAdjacentException {
		
		Edge e = matrixAdyacency[vertex1][vertex2]; 
		if(e==null)
			throw new VertexNotAdjacentException("Nanay");
		return e;
	}
    
    public void setElementsReference(ArrayList<VertexM<V>> elementsReference) {
    	this.elementsReference = elementsReference;
    }
    
    public Edge[][] getMatrixAdyacency() {
    	return matrixAdyacency;
    }
    
    public void setMatrixAdyacency(Edge[][] matrixAdyacency) {
    	this.matrixAdyacency = matrixAdyacency;
    }

    @Override
    public void insertVertex(V value) {
        elementsReference.add(new VertexM<V>(value));
        nVertex++;
        visitedG = new boolean[nVertex];
    }

    @Override
    public void insertEdge(int position1, int position2, double conection) {
        if(directed){
            matrixAdyacency[position1][position2] = new Edge(elementsReference.get(position1),elementsReference.get(position2),conection);
        }else{
            matrixAdyacency[position1][position2] = new Edge(elementsReference.get(position1),elementsReference.get(position2),conection);
            matrixAdyacency[position2][position1] = new Edge(elementsReference.get(position2),elementsReference.get(position1),conection);
        }

    }

    @Override
    public void deleteVertex(int positionVertex) throws IndexOutOfBoundsException {
        elementsReference.remove(positionVertex);
        nVertex--;


        for (int I = 0; I < positionVertex;I++ ){
            for(int J = positionVertex; J<matrixAdyacency.length ; J++){

                matrixAdyacency[I][J] = matrixAdyacency[I][J+1] ;

                if(matrixAdyacency[I][J+1] == null){
                    break;
                }

            }
        }

        for (int J = 0; J < positionVertex; J++){
            for( int I = positionVertex; I < matrixAdyacency.length;I++ ){

                matrixAdyacency[I][J] = matrixAdyacency[I+1][J] ;

                if(matrixAdyacency[I+1][J] == null){
                    break;
                }

            }
        }

        for (int I = positionVertex; I < matrixAdyacency.length ;I++ ){
            for(int J = positionVertex; J<matrixAdyacency.length ; J++){
                if(I+1 < matrixAdyacency.length && J+1 < matrixAdyacency.length) {
                    matrixAdyacency[I][J] = matrixAdyacency[I + 1][J + 1];
                }else{
                    matrixAdyacency[I][J] = null;
                }
            }
        }

    }

    @Override
    public void deleteEdge(int labelVertex1, int labelVertex2) throws VertexNotAdjacentException {
        if(directed){
            matrixAdyacency[labelVertex1][labelVertex2] = null;
        }else{
            matrixAdyacency[labelVertex1][labelVertex2] = null;
            matrixAdyacency[labelVertex1][labelVertex2] = null;
        }

    }

    public ArrayList<VertexM<V>> getVertexM() {
        return elementsReference;
    }

    
    @Override
    public ArrayList<Integer> BFS(int startPosition) {

        if(elementsReference.get(startPosition) == null){
            return null;
        }

        int s = startPosition;
        ArrayList<Integer> Solution = new ArrayList<>();

        boolean visited[] = new boolean[elementsReference.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while(queue.size() != 0){
            s = queue.poll();

            Solution.add(s);

            for (int I = 0; I< matrixAdyacency[s].length ; I++){
                if(matrixAdyacency[s][I] != null && !visited[I]){
                    visited[I] = true;
                    queue.add(I);
                }
            }
        }


        return Solution;
    }

    public ArrayList<int[]> BFS_freedomDegrees(int startPosition) {

        return null;
    }

    public ArrayList<Integer> DFS(int startPosition) {
    	if(elementsReference.get(startPosition) == null){
            return null;
        }

        int s = startPosition;
        ArrayList<Integer> Solution = new ArrayList<>();


        Stack<Integer> stack = new Stack<>();

        boolean visited[] = new boolean[elementsReference.size()];

        visited[s]=true;
        stack.add(s);
        visitedG[s] = true;

        while(stack.size() != 0){
            s = stack.pop();

            Solution.add(s);

            for (int I = 0; I< matrixAdyacency[s].length ; I++){
                if(matrixAdyacency[s][I] != null && !visited[I]){
                    visited[I] = true;
                    visitedG[I] = true;
                    stack.add(I);
                }
            }
        }


        return Solution;
    }

    @Override
    public ArrayList<ArrayList<Integer>> DFS() {
        ArrayList<ArrayList<Integer>> Solution = new ArrayList<>();
        visitedG = new boolean[elementsReference.size()];

        for(int I = 0; I< elementsReference.size() ; I++){
            if(visitedG[I] = false && elementsReference.get(I) != null){
                Solution.add(DFS(I));
            }
        }

        return Solution;
    }

    public static class pair implements Comparable<pair>{

        int objeto;
        double distancia;

        public pair(int objeto, double distancia) {
            this.objeto = objeto;
            this.distancia = distancia;
        }

        public int getObjeto() {
            return objeto;
        }

        public double getDistancia() {
            return distancia;
        }

        @Override
        public int compareTo(pair p) {
            if(this.distancia>p.distancia){
                return  1;
            }else if(this.distancia<p.distancia){
                return -1;
            }
            return 0;
        }
    }

    @Override
    public ArrayList<Integer> Prim(int startPosition) {
        ArrayList<Integer> Solution = new ArrayList<>();
        int V = nVertex;

        PriorityQueue<pair> minD = new PriorityQueue<>();

        TreeMap<Double, Integer> minDistance = new TreeMap<>();

        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        double key[] = new double [V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        ArrayList<pair> elements = new ArrayList<>();
        for (int i = 0; i < V; i++)
        {
            key[i] = Double.MAX_VALUE;
            elements.add(new pair(i,Double.MAX_VALUE));
            minD.offer(elements.get(i));
            mstSet[i] = false;
        }
        // Always include first 1st vertex in MST.
        //key[0] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        key[0] = 0;
        parent[0] = -1; // First node is always root of MST
        minD.offer(new pair(0,Double.MAX_VALUE));
        minD.remove(elements.get(0));

        // The MST will have V vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST

            pair ref = minD.poll();

            int u = ref.objeto;


            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v] l

                double c = -1;

                if (matrixAdyacency[u][v] != null){

                    c =matrixAdyacency[u][v].getWeight();
                }

                if (matrixAdyacency[u][v] != null && mstSet[v] == false &&
                        c < key[v]) {
                    parent[v] = u;
                    key[v] = c;
                    minD.offer(new pair(v,c));
                    minD.remove(elements.get(v));

                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();

        for(int I = 0; I< parent.length; I++){
            result.add(parent[I]);
        }

        return result;
    }

//    public int minKey(double key[], Boolean mstSet[])
//    {
//        // Initialize min value
//        double min = Integer.MAX_VALUE;
//        int min_index=-1;
//
//        for (int v = 0; v < mstSet.length; v++)
//            if (mstSet[v] == false && key[v] < min)
//            {
//                min = key[v];
//                min_index = v;
//            }
//
//        return min_index;
//    }

    @Override
    public Object[] Dijsktra(int startPosition) {
        Object[] arrays = new Object[2];
        int V = nVertex;
        double dist[] = new double[V];
        Boolean sptSet[] = new Boolean[V];
        int[] predecessors = new int[V];

        PriorityQueue<VertexM<V>> minDistance = new PriorityQueue<>();

        for (int i = 0; i < V; i++)
        {
            dist[i] = Double.MAX_VALUE;
            predecessors[i] = -1;
            minDistance.offer(elementsReference.get(i));
        }

        dist[startPosition] = 0;


        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = (int)minDistance.poll().getValue();

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++) {

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]

                double c = (matrixAdyacency[u][v].getWeight());

                if (!sptSet[v] && matrixAdyacency[u][v] != null &&
                        dist[u] != Double.MAX_VALUE &&
                        dist[u] + c < dist[v]) {
                    dist[v] = dist[u] + c;
                    predecessors[v] = u;
                }

            }
        }

        ArrayList<Double> result = new ArrayList<>();

        for(int I = 0; I< dist.length; I++){
            result.add(dist[I]);
        }

        arrays[0] = dist;
        arrays[1] = predecessors;

        return arrays;
    }
 
    @Override
    public double[][] Floyd_Warshal() {
        int v = elementsReference.size();
        double dist[][] = new double[v][v];
        int i, j, k;

        for (i = 0; i < v; i++) {
            for (j = 0; j < v; j++) {
            	
            	if(i==j)
            		dist[i][j] = 0;
            	else {
	                if(matrixAdyacency[i][j] != null){
	                    
	                	dist[i][j] = matrixAdyacency[i][j].getWeight();
	                    
	                }else{
	                    dist[i][j] = Double.MAX_VALUE;
	                }
            	}
            }
        }
        for (k = 0; k < v; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < v; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < v; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

	@Override
	public ArrayList<Edge> Kruskal() throws HeapUnderFlowException {
		
		double[][] cost = new double[nVertex][nVertex];
		for(int i = 0; i<cost.length;i++) {
			for( int j=0; j<cost[i].length;j++) {
				if(matrixAdyacency[i][j]!=null) {
					cost[i][j] = matrixAdyacency[i][j].getWeight();
				}
			}
		} kruskal(cost);
		return null; //HAY QUE CORREGIR¡¡¡¡¡
	}
 
//  // Finds MST using Kruskal's algorithm 
	public void  kruskal(double cost[][]) { 

		int parent[] = new int[nVertex];	
		int mincost = 0; 
		DisjointSet disjointSet = new DisjointSet(nVertex);

		for (int i = 0; i < nVertex; i++) 
			parent[i] = i; 
		  
// Include minimum weight edges one by one 
    int edge_count = 0; 
    while (edge_count < nVertex-1) {
    	double min = Double.MAX_VALUE;
    	int a = -1;
        int b = -1; 
          
        for (int i = 0; i < nVertex; i++){ 
            for (int j = 0; j < nVertex; j++) {
                if (!disjointSet.sameComponent(i, j)  && cost[i][j] < min) { 
                    min = cost[i][j]; 
                    a = i; 
                    b = j; 
                } 
            } 
        }
        disjointSet.unionByRank(a, b);  
    }
      
	}
	@Override
	public Vertex<V> searchVertex(V e) {
		
		Vertex<V> vertex = null;
		
		for(int i=0; i<elementsReference.size();i++) {
			if(elementsReference.get(i).equals(e))
				vertex = elementsReference.get(i);
		}
		return vertex;
	}

	@Override
	public boolean isAdjacent(int vertex1, int vertex2) throws VertexNotAdjacentException {
		
		boolean Adjacent = false;
		Edge e = null;
		if(directed) {
			e = matrixAdyacency[vertex1][vertex2];
			Adjacent = true;
		}else {
			e = matrixAdyacency[vertex1][vertex2];
			Adjacent = true;
		}
		if(e==null) {
			throw new VertexNotAdjacentException("No we...");
		}
		return Adjacent;
	}

	@Override
	public boolean isEmpty() {

		return (elementsReference.size()==0);
	}

}
