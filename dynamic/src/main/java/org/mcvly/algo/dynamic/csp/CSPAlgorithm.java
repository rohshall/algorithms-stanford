package org.mcvly.algo.dynamic.csp;

import java.util.*;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 17.10.13
 */
public class CSPAlgorithm {

    private TwoSatInstance problem;
    private Random random;
    private BitSet bitSet;
    private int n;
    private Set<Integer> redundantClauses = new HashSet<>();
    private Set<Integer> definedValues = new HashSet<>();
    private Set<Integer> existingNumbers;
    private Map<Integer, List<Integer>> numbersAndClauses;

    public CSPAlgorithm(String fileName) {
        problem = TwoSatReader.readFromFile(fileName);
        random = new Random();
        n = problem.getN();
        bitSet = new BitSet(n);
        existingNumbers = getExistingNumbers();
        numbersAndClauses = getNumbersAndClausesMap();

        preprocessing(problem);
    }

    private void preprocessing(TwoSatInstance originalProblem) {

        for (int i : existingNumbers) {
            if (numbersAndClauses.containsKey(i) && !numbersAndClauses.containsKey(-i) ||
                    !numbersAndClauses.containsKey(i) && numbersAndClauses.containsKey(-i)) {
                definedValues.add(i);
                redundantClauses.addAll(numbersAndClauses.get(i));
                if (numbersAndClauses.containsKey(i)) {
                    bitSet.set(i);
                }
            } else {  //both x and -x
                for (int j : numbersAndClauses.get(i)) {
                    for (int k : numbersAndClauses.get(-i)) {
                        int otherJ = getOtherVariable(j, i);
                        int otherK = getOtherVariable(k ,i);
                        if (otherJ == otherK) {
                            if (otherJ < 0) {
                                throw new RuntimeException("can't be false");
                            }
                            redundantClauses.add(j);
                            redundantClauses.add(k);
                            setValueTrue(otherJ); // they can't be false
                        } else if (otherJ == -otherK) {
                            redundantClauses.add(k);
                        }
                    }
                }
            }
        }
    }

    private void setValueTrue(int n) {
        definedValues.add(n);
        bitSet.set(n);

        if (numbersAndClauses.containsKey(-n)) {
            for (int i : numbersAndClauses.get(-n)) {
                int other = getOtherVariable(i, n);
                definedValues.add(other);
                redundantClauses.add(i);
                if (other > 0) {
                    bitSet.set(other);
                    setValueTrue(other);
                }
            }
        }

    }

    private int getOtherVariable(int clauseN, int varN) {
        Clause clause = problem.getClause(clauseN);
        if (clause.getX() == varN) {
            return clause.notY() ? -clause.getY() : clause.getY();
        }

        return clause.notX() ? -clause.getX() : clause.getX();
    }

    private Set<Integer> getExistingNumbers() {
        Set<Integer> existingNumbers = new HashSet<>();
        for (Clause clause : problem.getClauses()) {
            existingNumbers.add(clause.getX());
            existingNumbers.add(clause.getY());
        }

        return existingNumbers;
    }

    private Map<Integer, List<Integer>> getNumbersAndClausesMap() {
        Map<Integer, List<Integer>> numbersAndClauses = new HashMap<>();

        for (int i=1; i<=n; i++) {
            Clause clause = problem.getClause(i);
            int x, y;
            x = clause.notX() ? -clause.getX() : clause.getX();
            y = clause.notY() ? -clause.getY() : clause.getY();

            if (!numbersAndClauses.containsKey(x)) {
                numbersAndClauses.put(x, new ArrayList<Integer>());
            }

            if (!numbersAndClauses.containsKey(y)) {
                numbersAndClauses.put(y, new ArrayList<Integer>());
            }

            numbersAndClauses.get(x).add(i);
            numbersAndClauses.get(y).add(i);
        }

        return numbersAndClauses;
    }

    public BitSet runAlgorithm() throws NotSatisfiableSolutionException {
        for (int k = 0; k < (Math.log(n) / Math.log(2)); k++) {
            for (long i=0; i < 2 * n * n; i++) {
                Clause unsatisfied = problem.evaluate(bitSet);
                if (unsatisfied == null) {
                    return bitSet;
                }
                improve(bitSet, unsatisfied);
            }
        }

        throw new NotSatisfiableSolutionException();
    }

    private void improve(BitSet bs, Clause unsatisfied) {

        boolean first = random.nextBoolean();
        if (first) {
            bs.flip(unsatisfied.getX());
        } else {
            bs.flip(unsatisfied.getY());
        }
    }

    public static void main(String[] args) {
        //simple
        CSPAlgorithm algorithm = new CSPAlgorithm("2sat/simple.txt");
//        try {
//            System.out.println(algorithm.runAlgorithm());
//        } catch (NotSatisfiableSolutionException e) {
//            System.out.println("no satisfiable solution");
//        }
    }

}
