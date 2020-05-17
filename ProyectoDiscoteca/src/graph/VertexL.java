package graph;

import java.util.ArrayList;

public class VertexL<V> extends Vertex<V> {

    private int key;
    private int vAdjs;
    private ArrayList<VertexL<V>> adjacencyList;
    private ArrayList<Edge> incidencyList;
    
    public VertexL(V value){
        super(value);
        key = 0;
        vAdjs = 0;
        adjacencyList = new ArrayList<>(); 
        incidencyList = new ArrayList<>();
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key){
        this.key = key;
    }

    public int getVadjs() {
        return vAdjs;
    }

    public void setVadjs(int vAdjs){
        this.vAdjs = vAdjs;
    }

    public ArrayList<VertexL<V>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(ArrayList<VertexL<V>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public ArrayList<Edge> getIncidencyList() {
		return incidencyList;
	}

	public void setIncidencyList(ArrayList<Edge> incidencyList) {
		this.incidencyList = incidencyList;
	}

	public VertexL<V> searchAdjacentVertex(int label){
    	
    	VertexL<V> v = null;
    	for(int i=0; i<vAdjs;i++) {
    		
    		if(adjacencyList.get(i).getLabel()==label) {
    			v = adjacencyList.get(i);
    		}
    	}
    	
        return v;
    }
    
    public int getIndexAdjacentVertex(int label) {
    	
    	int v = 0;
    	for(int i=0; i<vAdjs;i++) {
    		
    		if(adjacencyList.get(i).getLabel()==label) {
    			v = i;
    		}
    	}
    	
        return v;
    }

    public void addAdjacentVertexAndIncidency(VertexL<V> adjacent, Edge e){
        adjacencyList.add(adjacent);
        incidencyList.add(e);
    }

//    public void setAdjacencyList(ArrayList<VertexL<V>> adjacencyList){
//        this.adjacencyList = adjacencyList;
//        vAdjs = this.adjacencyList.size();
//    }

    public void deleteFromAdjacencyList(int index) throws IndexOutOfBoundsException{
        adjacencyList.remove(index);
        incidencyList.remove(index);
        vAdjs = vAdjs-1;
    }

//    public void deleteAdjacencyList(){
//        adjacencyList = null;
//        vAdjs = 0;
//    }



}
