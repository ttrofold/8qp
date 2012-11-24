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

    public Solution rotateBy90() {
        List<Integer> result = new LinkedList<Integer>();
        for(int i = 0; i <  getSolutionList().size(); i ++) {
             result.add(7 - getSolutionList().lastIndexOf(i));
        }
        return new Solution(result);
    }

    public Solution rotateBy180() {
        List<Integer> result = new LinkedList<Integer>();
        for(int i = 0; i <  getSolutionList().size(); i ++) {
            result.add(7 - getSolutionList().get(7 - i));
        }
        return new Solution(result);
    }

    public Solution rotateBy270() {
        List<Integer> result = new LinkedList<Integer>();
        for(int i = 0; i <  getSolutionList().size(); i ++) {
            result.add(getSolutionList().lastIndexOf(7 - i));
        }
        return new Solution(result);
    }

    public Solution reflect() {
        List<Integer> result = new LinkedList<Integer>();
        for(int i = 0; i <  getSolutionList().size(); i ++) {
            result.add(getSolutionList().get(7 - i));
        }
        return new Solution(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        return !(solutionList != null ? !solutionList.equals(solution.solutionList) : solution.solutionList != null);

    }
}
