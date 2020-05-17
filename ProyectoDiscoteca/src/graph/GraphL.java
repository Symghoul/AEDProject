package graph;

import java.util.ArrayList;
import auxiliaryStructures.DisjointSet;
import auxiliaryStructures.MaxPriorityQueue;
import auxiliaryStructures.MinPriorityQueue;
import auxiliaryStructures.Queue;
import exception.*;
import interfaces.IGraph;

public class GraphL<V> implements IGraph<V> {

    private boolean directed;
    private boolean weighted;
    private int time;
    private int nVertices;
    private int nEdges;
    private ArrayList<VertexL<V>> vertex;
    private ArrayList<Edge> edges;

    public GraphL(boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
        vertex = new ArrayList<>();
        edges = new ArrayList<>();
        nVertices = vertex.size();
        nEdges = edges.size();
    }

    public GraphL(boolean directed, boolean weighted, VertexL<V> v){
        this.directed = directed;
        this.weighted = weighted;
        vertex = new ArrayList<>();
        edges = new ArrayList<>();
        vertex.add(v);
        nVertices = vertex.size();
        nEdges = edges.size();
    }

	@Override
	public VertexL<V> searchVertex(int e) {
		
		return vertex.get(e);
	}

	@Override
	public boolean isAdjacent(int labelO, int labelD) throws VertexNotAdjacentException {
		
		VertexL<V> vertex1 = searchVertex(labelO);
		VertexL<V> vertex2 = searchVertex(labelD);

		boolean adj = true;
		VertexL<V> vL1 = vertex1.searchAdjacentVertex(vertex2.getLabel());
		VertexL<V> vL2 = vertex2.searchAdjacentVertex(vertex1.getLabel());
		
    	if(vL1==null && vL2==null)
    		throw new VertexNotAdjacentException("No es un vertice adjacente");
    	return adj;  	
	}

	@Override
	public boolean isEmpty() {

		boolean empty = false;
		if(nVertices <=0) {
			empty = true;
		}return empty;
	}

	@Override
	public Edge edgeLabel(int origin, int destiny)  throws VertexNotAdjacentException  {
		
		Edge e = null;
		
		
		if(isAdjacent(origin, destiny)) {
							
			int n = searchVertex(origin).getIndexAdjacentVertex(destiny);
			e = searchVertex(origin).getIncidencyList().get(n);
		}
		return e;
	}

	@Override
	public void insertVertex(V value){
		VertexL<V> u = new VertexL<>(value);
		vertex.add(u);
		nVertices = vertex.size();
		u.setLabel(nVertices);
  }

	@Override
	public void insertEdge(int origin1, int destiny1, double conection) {
		
		VertexL<V> origin = searchVertex(origin1);
		VertexL<V> destiny = searchVertex(destiny1);
		
		Edge e = new Edge(origin, destiny, conection);
		if(directed) {
			origin.addAdjacentVertexAndIncidency(destiny, e);
		}else {
			Edge e2 = new Edge(destiny, origin, conection);
			origin.addAdjacentVertexAndIncidency(destiny, e);
			destiny.addAdjacentVertexAndIncidency(origin, e2);
		}
		
	}

	@Override
	public void deleteVertex(int labelVertex) {
		
		VertexL<V> forgotten = searchVertex(labelVertex);
		ArrayList<VertexL<V>> vertexAdj = getAllAsociations(forgotten);
		int n =0;
		for(int i=0; i<vertexAdj.size();i++) {
			vertexAdj.get(i).getIndexAdjacentVertex(labelVertex);
			vertexAdj.get(i).deleteFromAdjacencyList(labelVertex);
			vertexAdj.get(i).getIncidencyList().remove(n);
		}vertex.remove(labelVertex);
	}

	private ArrayList<VertexL<V>> getAllAsociations(VertexL<V> forgotten) {
			
		ArrayList<VertexL<V>> forgoters = null;
		if(directed) {
			for(int i=0; i<nVertices;i++) {
				if(vertex.get(i).searchAdjacentVertex(forgotten.getLabel())!=null) {
					forgoters.add(vertex.get(i));
				}
			}
		}else {
			forgoters = forgotten.getAdjacencyList();
		}
		return forgoters;	
	}

	@Override
	public void deleteEdge(int origin, int destiny) throws VertexNotAdjacentException {
		
		VertexL<V> v1 = searchVertex(origin);
		VertexL<V> v2 = searchVertex(destiny);
		
		if(directed) {
			v1.deleteFromAdjacencyList(v1.getIndexAdjacentVertex(destiny));
			v1.getIncidencyList().remove(v1.getIndexAdjacentVertex(destiny));
		}else {
			v1.deleteFromAdjacencyList(v1.getIndexAdjacentVertex(destiny));
			v1.getIncidencyList().remove(v1.getIndexAdjacentVertex(destiny));
			v2.deleteFromAdjacencyList(v2.getIndexAdjacentVertex(origin));
			v2.getIncidencyList().remove(v2.getIndexAdjacentVertex(origin));
		}
	}

	@Override
	public ArrayList<Integer> BFS(int labelVertexStart) throws UnderflowException {
        for(int i = 0; i < nVertices; i++){
            vertex.get(i).setColor((byte)0);
            vertex.get(i).setDistance(Double.MAX_VALUE);
            vertex.get(i).setPredecessor(-1);
        }
        return BFS_algorithm(labelVertexStart);
	}
	
    private ArrayList<Integer> BFS_algorithm(int initialVertexIndex) throws UnderflowException {
        VertexL<V> initialVertex = vertex.get(initialVertexIndex);
        initialVertex.setColor((byte)1);
        initialVertex.setDistance(0);
        Queue<VertexL<V>> nextToVisit = new Queue<>();
        ArrayList<Integer> visited = new ArrayList<>();
        nextToVisit.enqueue(initialVertex);
        visited.add(initialVertexIndex);
        while(!nextToVisit.isEmpty()){
            VertexL<V> u = nextToVisit.dequeue();
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                VertexL<V> v = u.getAdjacencyList().get(i);
                if(v.getColor() == 0){
                    v.setPredecessor(vertex.indexOf(u));
                    v.setDistance(u.getDistance() + 1);
                    v.setColor((byte)1);
                    visited.add(vertex.indexOf(v));
                    nextToVisit.enqueue(v);
                }
            }
            u.setColor((byte)2);
        }
        return visited;
    }

//	@Override
//	public ArrayList<ArrayList<Integer>> DFS(int initialVertexIndex) {
//      ArrayList<Integer> tree = new ArrayList<>();
//      DFS_restart();
//      tree.add(initialVertexIndex);
//      DFS_Visit(tree, initialVertexIndex);
//      return tree;
//	}

	public ArrayList<ArrayList<Integer>> DFS() {			//Para recorrer el bosque desde el principio
	    ArrayList<ArrayList<Integer>> forest = new ArrayList<>();
	    DFS_restart();
	    for(int i = 0; i < nVertices; i++){
	        if(vertex.get(i).getColor() == 0){
	            ArrayList<Integer> tree = new ArrayList<>();
	            tree.add(i);
	            DFS_Visit(tree, i);
	            forest.add(tree);
	        }
	    }
	    return forest;
	}
	
	private void DFS_restart(){
	    for(int i = 0; i < nVertices; i++){
	        vertex.get(i).setColor((byte)0);
	        vertex.get(i).setPredecessor(-1);
	    }
	    time = 0;
	  }
	
	private void DFS_Visit(ArrayList<Integer> tree, int i){
		time ++;
		VertexL<V> u = vertex.get(i);
		u.setInitialTime(time);
		u.setColor((byte)1);
		for(int j = 0; j < u.getAdjacencyList().size(); j++){
			VertexL<V> v = u.getAdjacencyList().get(j);
			if(v.getColor() == 0){
				tree.add(vertex.indexOf(v));
				v.setPredecessor(vertex.indexOf(u));
				DFS_Visit(tree, vertex.indexOf(v));
			}
		}
		u.setColor((byte)2);
		time ++;
		u.setFinalTime(time);
	}
	
	
	
	
	
	@Override
	public ArrayList<Integer> Prim(int initialVertexIndex) throws GreaterKeyException, HeapUnderFlowException, IndexOutOfBoundsException {
		VertexL<V> r = vertex.get(initialVertexIndex);
		ArrayList<Integer> tree = new ArrayList<>();
		for(int i = 0; i < nVertices; i++){
			vertex.get(i).setColor((byte)0);
			vertex.get(i).setDistance(Integer.MAX_VALUE);
			vertex.get(i).setPredecessor(-1);
		}
		r.setDistance(0);
		ArrayList<Integer> heap = new ArrayList<>();
		MinPriorityQueue<Integer> minQueueVertex = new MinPriorityQueue<Integer>(heap, 0);
		minQueueVertex.insert(r.getLabel());
		for(int i = 0; i < nVertices; i++){
			if(i != initialVertexIndex)
				minQueueVertex.insert(vertex.get(i).getLabel());
		}
		while(!minQueueVertex.isEmpty()){
			Integer f = minQueueVertex.extract_min();
			VertexL<V> u = searchVertex(f);
			ArrayList<Edge> incidentEdges;
			for(int i = 0; i < u.getAdjacencyList().size(); i++){
				VertexL<V> v = u.getAdjacencyList().get(i);
				if(v.getColor() == 0){
					int ifV = v.getIndexAdjacentVertex(u.getLabel());
					if(u.getIncidencyList().get(i)!=null)
						incidentEdges.add(u.getIncidencyList().get(i));
					if(v.getIncidencyList().get(ifV)!=null)
						incidentEdges.add(v.getIncidencyList().get(ifV));
					for(int j = 0; j < incidentEdges.size(); j++){
						double weight = Double.parseDouble(String.valueOf(incidentEdges.get(j).getWeight()));
						if(weight < v.getDistance()){
							v.setDistance(weight);
							v.setPredecessor(vertex.indexOf(u));
							minQueueVertex.decrease_key(v., v.getLabel()); //Hay error aqui, se supone que han de entrarle 2 integer pero 
						}													// no tengo idea de porque si se supone que v es un heap.
					}
				}
			}
			u.setColor((byte)1);
			tree.add(vertex.indexOf(u));
		}
		return tree;
	}

	@Override
	public ArrayList<Edge> Kruskal() throws HeapUnderFlowException{
		ArrayList<Edge> mst = new ArrayList<>();
		ArrayList<Integer> cloneOfEdgesArray = (ArrayList<Integer>)edges.clone(); //No esoy seguro que esta conversion funcione al 100%
																					//Se supone que es un clon del arreglo de aristas pero no recuerdo haber creado aque arreglo asi que seguramente de errores
		MaxPriorityQueue<Integer> maxPriorityQueueEdges = new MaxPriorityQueue<Integer>(cloneOfEdgesArray, 1000);
		maxPriorityQueueEdges.heapsort();
		DisjointSet disjointSet = new DisjointSet(nVertices);
		Integer labelEdge = null;			//Falta arreglar el error para que te busque la arista con la etiqueta y de ahi para alla terminar de acomodar el algoritmo
		Edge e = null;
		while(!maxPriorityQueueEdges.isEmpty()) {
			labelEdge = maxPriorityQueueEdges.extract_max();
			maxPriorityQueueEdges.heapsort();
			
			int indexOfOriginVertex = vertices.indexOf(minEdge.getOriginVertex());
			int indexOfDestinationVertex = vertices.indexOf(minEdge.getDestinationVertex());
			if (!disjointSet.same_component(indexOfOriginVertex, indexOfDestinationVertex)) {
				mst.add(minEdge);
			}
			disjointSet.union_by_rank(indexOfOriginVertex, indexOfDestinationVertex);
		}
		return mst;
	}

	@Override
	public Object[] Dijsktra(int startPosition) throws IndexOutOfBoundsException, VertexNotAdjacentException {
		Object[] arrays = new Object[2];
		double[] distances = new double[nVertices];
		int[] predecessors = new int[nVertices];
		boolean[] visited = new  boolean[nVertices];
		for(int i = 0; i < nVertices; i++){
			distances[i] = Integer.MAX_VALUE;
			predecessors[i] = -1;
			visited[i] = false;
		}
		distances[startPosition] = 0;
		int minimum = getMinimumVertex(distances, visited);
		while(minimum >= 0){
			VertexL<V> u = vertex.get(minimum);
			int indexOfU = vertex.indexOf(u);
			for(int i = 0; i < u.getAdjacencyList().size(); i++){
				VertexL<V> v = u.searchAdjacentVertex(i);
				int indexOfV = vertex.indexOf(v);
				ArrayList<Edge> incidentEdges = getEdges(vertex.indexOf(u), vertex.indexOf(v));
				for(int j = 0; j < incidentEdges.size(); j++){
					double weight = Double.parseDouble(String.valueOf(incidentEdges.get(j).getWeight()));
					double sum = (distances[indexOfU] + weight);
					if(!visited[indexOfV] && sum < distances[indexOfV]){
						distances[indexOfV] = sum;
						predecessors[indexOfV] = indexOfU;
					}
				}
			}
			visited[indexOfU] = true;
			minimum = getMinimumVertex(distances, visited);
		}
		arrays[0] = distances;
		arrays[1] = predecessors;
		return arrays;
	}

	private ArrayList<Edge> getEdges(int indexOf, int indexOf2) throws VertexNotAdjacentException {

		
		VertexL<V> iO1 = searchVertex(indexOf);
		VertexL<V> iO2 = searchVertex(indexOf2);
		ArrayList<Edge> edges = null;
		
		if(!isAdjacent(indexOf, indexOf2))
			throw new VertexNotAdjacentException("laj cagao we X.X");
		if(directed)
			edges.add(iO1.getIncidencyList().get(iO1.getIndexAdjacentVertex(indexOf2))); //Agrega la arista dirigida entre indexOf e IndexOf2
		else {
			edges.add(iO1.getIncidencyList().get(iO1.getIndexAdjacentVertex(indexOf2)));
			edges.add(iO2.getIncidencyList().get(iO2.getIndexAdjacentVertex(indexOf)));
		}
		return edges;
	}

	@Override
	public double[][] Floyd_Warshal() {
		// TODO Auto-generated method stub
		return null;
	}

//

//

//
	private int getMinimumVertex(double[] dist, boolean[] visited){
        int u = -1;
        double value = Integer.MAX_VALUE;
        for(int i = 0; i < dist.length; i++){
            if(!visited[i] && dist[i] < value){
                value = dist[i];
                u = i;
            }
        }
        return u;
    }

    public boolean isDirected() {
        return directed;
    }
	
    public int getNumberOfVertices(){
        return nVertices;
    }
//
    public int getNumberOfEdges(){
        return nEdges;
    }
}
