package domain;

import domain.exceptions.SolutionException;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<Integer> solutionList;

    public Solution() {
        solutionList = new LinkedList<Integer>();
    }

    public Solution(List<Integer> list) {
        if(list.size() > 8) throw new SolutionException();
        solutionList = list;
    }

    public List<Integer> getSolutionList() {
        if(solutionList.size() != 8) throw new SolutionException();
        return solutionList;
    }

    public void augment(int i) {
        if(solutionList.size() == 8) throw new SolutionException();
        solutionList.add(i);
    }

    public int getSize() {
        return solutionList.size();
    }

    public Integer get(int i) {
        return solutionList.get(i);
    }
}
