package graph;


import java.util.*;

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
        elementsReference.add(new VertexM<>(value));
        nVertex++;
        visitedG = new boolean[nVertex];
    }

    @Override
    public void insertEdge(int position1, int position2, E conection) {
        if(directed){
            matrixAdyacency[position1][position2] = new EdgeM(conection,elementsReference.get(position1),elementsReference.get(position2));
        }else{
            matrixAdyacency[position1][position2] = new EdgeM(conection,elementsReference.get(position1),elementsReference.get(position2));
            matrixAdyacency[position2][position1] = new EdgeM(conection,elementsReference.get(position2),elementsReference.get(position1));
        }

    }

    @Override
    public void deleteVertex(int positionVertex) {
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
    public void deleteEdge(int position1, int position2, E conection) {
        if(directed){
            matrixAdyacency[position1][position2] = null;
        }else{
            matrixAdyacency[position1][position2] = null;
            matrixAdyacency[position2][position1] = null;
        }

    }

    @Override
    public void deleteAllEdge(int position1, int position2) {
        if(directed){
            matrixAdyacency[position1][position2] = null;
        }else{
            matrixAdyacency[position1][position2] = null;
            matrixAdyacency[position2][position1] = null;
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

    @Override
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

                    c =(double)((int)matrixAdyacency[u][v].getValue());
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

    int minKey(double key[], Boolean mstSet[])
    {
        // Initialize min value
        double min = Integer.MAX_VALUE;
        int min_index=-1;

        for (int v = 0; v < mstSet.length; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

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

                double c = new Double((int)matrixAdyacency[u][v].getValue());

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
        int V = matrixAdyacency.length;
        double dist[][] = new double[matrixAdyacency.length][matrixAdyacency.length];
        int i, j, k;

        for (i = 0; i < matrixAdyacency.length; i++)
            for (j = 0; j < matrixAdyacency.length; j++)
                if(matrixAdyacency[i][j] != null){
                    if (!(matrixAdyacency[i][j].getValue() instanceof Double)){
                        dist[i][j] = (double)((int)matrixAdyacency[i][j].getValue());
                    }else{
                        dist[i][j] = (double)matrixAdyacency[i][j].getValue();
                    }
                }else{
                    dist[i][j] = Double.MAX_VALUE;
                }

        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] != Double.MAX_VALUE && dist[k][j] != Double.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }


        return dist;
    }


	@Override
	public ArrayList<EdgeL<V, E>> Kruskal() throws HeapUnderFlowException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VertexL<V> searchVertex(int e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdjacent(VertexL<V> vertex1, VertexL<V> vertex2) throws VertexNotAdjacentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Edge edgeLabel(VertexL<V> vertex1, VertexL<V> vertex2) throws VertexNotAdjacentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEdge(VertexL<V> origin, VertexL<V> destiny, double conection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEdge(int labelVertex1, int labelVertex2) throws VertexNotAdjacentException {
		// TODO Auto-generated method stub
		
	}



}
