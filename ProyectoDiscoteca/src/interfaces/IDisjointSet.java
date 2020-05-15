package interfaces;

public interface IDisjointSet {

    int find(int x);
    void union(int x, int y);
    void union_by_rank(int x, int y);
    boolean same_component(int x, int y);
    int connected_components();
    int[] nodes_numbers_by_components();

}
