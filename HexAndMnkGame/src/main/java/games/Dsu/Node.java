package games.Dsu;

public interface Node {
    int getTreeSize();
    Node findParent();
    boolean canMerge(Node anotherTree);
    void addTree(Node anotherTree);
    void updateTreeValue(Object newValue);
    Object getTreeValue();
}
