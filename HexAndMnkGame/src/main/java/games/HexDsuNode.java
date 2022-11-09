package games;

import games.Dsu.DsuNode;
import games.Dsu.Node;

public class HexDsuNode extends DsuNode {
    private Cell nodeType;
    private boolean[] onISide;

    HexDsuNode(boolean[] onISide) {
        super();
        this.onISide = onISide;
        nodeType = Cell.EMPTY;
    }

    @Override
    public boolean canMerge(Node anotherTree) {
        //if (anotherTree instanceof HexDsuNode) {
            HexDsuNode anotherTreeCopy = (HexDsuNode) anotherTree;
            return nodeType == anotherTreeCopy.getNodeType();
        //}

        //throw new AssertionError("Unknown operation: union two different nodes");
    }

    private Cell getNodeType() {
        return nodeType;
    }

    @Override
    public void addTree(Node anotherTree) {
        //if (anotherTree instanceof Node) {
            treeSize += anotherTree.getTreeSize();

            HexDsuNode anotherTreeCopy = (HexDsuNode) anotherTree;
            for (int i = 0; i < onISide.length; i++) {
                onISide[i] |= anotherTreeCopy.getOnISide(i);
            }
            anotherTreeCopy.updateParent(this);
       //}

        //throw new AssertionError("Unknown operation: union two different nodes");
    }

    private boolean getOnISide(int i) {
        return onISide[i];
    }


    @Override
    public void updateTreeValue(Object newValue){
       //if (newValue instanceof Enu) {
            nodeType = (Cell) newValue;
        //}

        //throw new AssertionError("Invalid data type of new value");
    }

    @Override
    public Object getTreeValue() {
        return onISide;
    }
}
