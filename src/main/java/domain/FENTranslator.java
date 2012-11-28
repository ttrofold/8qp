package domain;

import domain.exceptions.FENTranslatorException;

import java.util.List;

public class FENTranslator {
    private List<Integer> solutionList;

    public FENTranslator(List<Integer> solutionList) {
        if(solutionList.size() != 8) throw new FENTranslatorException();
        this.solutionList = solutionList;
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

    public Object getSolutionList() {
        return solutionList;
    }
}
