package auxiliaryStructures;

import interfaces.IDisjointSet;

public class DisjointSet implements IDisjointSet {

    private int[] parent;
    private int[] rank;
    private int[] root;
    private int[] nodes;
    private int numberComponents;
    private int size;

    public DisjointSet(int set){
        parent = new int[set];
        rank = new int[set];
        setRoot(new int[set]);
        nodes = new int[set];
        for(int i = 0; i < set; i++){
            parent[i] = i;
            rank[i] = 0;
        }
        size = set;
        numberComponents = set;
    }

    public int[] getRoot() {
		return root;
	}

	public void setRoot(int[] root) {
		this.root = root;
	}

	@Override
    public int find(int x) {
        if(x == parent[x])
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    @Override
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        parent[xRoot] = yRoot;
        rank[yRoot] = rank[xRoot] + 1;
    }

    @Override
    public void unionByRank(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if(rank[xRoot] == rank[yRoot]){
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
        else if(rank[xRoot] > rank[yRoot]){
            parent[yRoot] = xRoot;
        }
        else{
            parent[xRoot] = yRoot;
        }
    }

    @Override
    public boolean sameComponent(int x, int y) {
        return find(x) == find(y);
    }

    @Override
    public int connectedComponents() {
        numberComponents = 0;
        for(int i = 0; i < size; i++){
            if(find(i) == i){
                numberComponents++;
            }
        }
        return numberComponents;
    }

    @Override
    public int[] nodesNumbersByComponents() {
        for(int i = 0; i < size; i++){
            nodes[find(i)]++;
        }
        return nodes;
    }
}
