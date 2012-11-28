package domain;

import domain.exceptions.FENTranslatorException;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class FENTranslatorTest {

    @Test(expected = FENTranslatorException.class)
    public void
    ifProvidedWithAListOfSizeLessThan8() {
        new FENTranslator(new LinkedList<Integer>(){{add(1);}});
    }

    @Test(expected = FENTranslatorException.class)
    public void
    ifProvidedWithAListOfSizeMoreThan8() {
        new FENTranslator(new LinkedList<Integer>(){{add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);}});
    }

    @Test
    public void
    encapsulatesAListRepresentingASolution() {
        List<Integer> solutionList =
                new LinkedList<Integer>(){{add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);}};

        assertEquals(solutionList, new FENTranslator(solutionList).getSolutionList());
    }

    @Test
    public void
    firstTranslationTest() {
        assertEquals("6Q1/Q7/3Q4/2Q5/4Q3/1Q6/7Q/5Q2", new FENTranslator(new LinkedList<Integer>() {{
            add(1); add(5); add(3); add(2); add(4); add(7); add(0); add(6);}}).fen());
    }

    @Test
    public void
    secondTranslationTest() {
        assertEquals("Q7/1Q6/2Q5/3Q4/4Q3/5Q2/6Q1/7Q", new FENTranslator(new LinkedList<Integer>() {{
            add(0); add(1); add(2); add(3); add(4); add(5); add(6); add(7);}}).fen());
    }


    @Test
    public void
    thirdTranslationTest() {
        assertEquals("7Q/6Q1/5Q2/4Q3/3Q4/2Q5/1Q6/Q7", new FENTranslator(new LinkedList<Integer>() {{
            add(7); add(6); add(5); add(4); add(3); add(2); add(1); add(0);}}).fen());
    }
}
