package domain.manager;

import domain.queen.IQueen;
import domain.queen.PhantomQueen;
import domain.queen.Queen;

import java.util.LinkedList;
import java.util.List;

public class QueenManager {

    public static List<List<Integer>> generateAllPermutations() {
        return innerGenerateAllPermutations(
                new LinkedList<Integer>()
                {{add(0); add(1); add(2); add(3); add(4); add(5); add(6); add(7);}},
                new LinkedList<Integer>(),
                new LinkedList<List<Integer>>(){{add(new LinkedList<Integer>());}});
    }

    private static List<List<Integer>> innerGenerateAllPermutations
            (List<Integer> unused,
             List<Integer> used,
             List<List<Integer>> partialPermutations) {
        if(unused.size() == 0) {
            return partialPermutations;
        }
        List<List<Integer>> cumulative = new LinkedList<List<Integer>>();
        for(Integer integer : unused) {
            List<List<Integer>> newPartialPermutations = new LinkedList<List<Integer>>();
            for(List<Integer> partialPermutation : partialPermutations) {
                LinkedList<Integer> newPartialPermutation = new LinkedList<Integer>(partialPermutation);
                newPartialPermutation.add(integer);
                newPartialPermutations.add(newPartialPermutation);
            }

            List<Integer> newUnused = new LinkedList<Integer>(unused);
            newUnused.remove(integer);
            List<Integer> newUsed = new LinkedList<Integer>(used);
            newUsed.add(integer);
            cumulative.addAll(innerGenerateAllPermutations(newUnused, newUsed, newPartialPermutations));
        }
        return cumulative;
    }

    public static List<Queen> generateAllPlacements() {
        List<List<Integer>> permutations = generateAllPermutations();
        List<IQueen> placements = new LinkedList<IQueen>();
        for(List<Integer> permutation : permutations) {
            IQueen tempReference = PhantomQueen.INSTANCE;
            for(Integer integer : permutation) {
                tempReference = new Queen(integer, permutation.lastIndexOf(integer), tempReference);
            }
            placements.add(tempReference.clone());
        }

        List<Queen> result = new LinkedList<Queen>();
        for(IQueen placement : placements) {
            result.add((Queen) placement);

        }

        return result;
    }
}
