package interfaces;

public interface IDisjointSet {

    int find(int x);
    void union(int x, int y);
    void unionByRank(int x, int y);
    boolean sameComponent(int x, int y);
    int connectedComponents();
    int[] nodesNumbersByComponents();

}
