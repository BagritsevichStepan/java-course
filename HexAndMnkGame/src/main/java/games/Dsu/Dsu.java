package games.Dsu;

import java.util.Arrays;

public class Dsu {
    private final Node[] sets;

    public Dsu(Node[] sets) {
        this.sets = Arrays.copyOf(sets, sets.length);
    }

    public void updateSetValue(int setNumber, Object newValue) {
        sets[setNumber].findParent().updateTreeValue(newValue);
    }

    public Object getSetValue(int setNumber) {
        return sets[setNumber].findParent().getTreeValue();
    }

    public void unionSets(int firstSetNumber, int secondSetNumber) {
        Node firstSet = sets[firstSetNumber].findParent();
        Node secondSet = sets[secondSetNumber].findParent();

        if (!firstSet.canMerge(secondSet)) {
            return;
        }

        if (firstSet.getTreeSize() > secondSet.getTreeSize()) {
            firstSet.addTree(secondSet);
        } else {
            secondSet.addTree(firstSet);
        }
    }
}
