package domain;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<Integer> solutionList;

    public Solution() {
        solutionList = new LinkedList<Integer>();
    }

    public List<Integer> getSolutionList() {
        return solutionList;
    }

    public void augment(int i) {
        solutionList.add(i);
    }
}
