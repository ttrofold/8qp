package domain;

import domain.exceptions.SolutionException;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<Integer> solutionList;
    private List<List<Integer>> metadata;

    {
        metadata = new LinkedList<List<Integer>>();

        for(int i = 0; i < 7; i++) {
            metadata.add(new LinkedList<Integer>());
        }
    }

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

    @Override
    public int hashCode() {
        return solutionList != null ? solutionList.hashCode() : 0;
    }

    public List<Integer> getMetadata(MetaKey key) {
        return metadata.get(key.getMetaIndex());
    }

    public List<List<Integer>> getMetadata() {
        return metadata;
    }

    public void addMetadata(MetaKey key, int i) {
        metadata.get(key.getMetaIndex()).add(i);
    }

    public String fen() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 8; i++) {
            int position = solutionList.lastIndexOf(i) + 1;
            int prepend = position - 1;
            int append = 8 - position;
            sb.append(i==0  ? "" : "/")
              .append(prepend == 0 ? "" : prepend)
              .append("Q")
              .append(append == 0 ? "" : append);
        }
        return sb.toString();
    }

    public enum MetaKey {
        T90 {
            @Override
            public int getMetaIndex() {
                return 0;
            }
        }, T180 {
            @Override
            public int getMetaIndex() {
                return 1;
            }
        }, T270 {
            @Override
            public int getMetaIndex() {
                return 2;
            }
        }, R0 {
            @Override
            public int getMetaIndex() {
                return 3;
            }
        }, R90 {
            @Override
            public int getMetaIndex() {
                return 4;
            }
        }, R180 {
            @Override
            public int getMetaIndex() {
                return 5;
            }
        }, R270 {
            @Override
            public int getMetaIndex() {
                return 6;
            }
        };
        public abstract int getMetaIndex();
    }
}
