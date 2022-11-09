package games.Dsu;

import games.HexDsuNode;

public abstract class DsuNode implements Node {
    private DsuNode parent;
    protected int treeSize;

    protected DsuNode() {
        parent = this;
        this.treeSize = 1;
    }

    @Override
    public int getTreeSize() {
        return treeSize;
    }

    @Override
    public DsuNode findParent() {
        if (this == parent) {
            return this;
        }

        updateParent(parent.findParent());
        return parent;
    }

    @Override
    public boolean canMerge(Node anotherTree) {
        return this != anotherTree;
    }

    @Override
    public void addTree(Node anotherTree) {
        //if (anotherTree instanceof DsuNode) {
            treeSize += anotherTree.getTreeSize();
            DsuNode anotherTreeCopy = (DsuNode) anotherTree;
            anotherTreeCopy.updateParent(this);
        //}

        //throw new AssertionError("Unknown operation: union two different nodes");
    }

    protected void updateParent(Node newParent) {
        //if (newParent instanceof DsuNode) {
            parent = (DsuNode) newParent;
        //}

        //throw new AssertionError("Unknown operation: union two different nodes");
    }
}
