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
    private Set<Clause> redundantClauses = new HashSet<>();
    private Set<Integer> definedValues = new HashSet<>();
    private Set<Integer> existingNumbers;
    private Map<Integer, List<Clause>> numbersAndClauses;

    public CSPAlgorithm(String fileName) {
        problem = TwoSatReader.readFromFile(fileName);
        random = new Random();
        n = problem.getN();
        bitSet = new BitSet(n);
        existingNumbers = getExistingNumbers();
        numbersAndClauses = getNumbersAndClausesMap();
        preprocessing();
    }

    private void preprocessing() {
        while(true) {
            boolean hasChanged = false;

            for (int i : existingNumbers) {
                if (numbersAndClauses.containsKey(i) && !numbersAndClauses.containsKey(-i) && numbersAndClauses.get(i).size() > 0 ||
                        !numbersAndClauses.containsKey(i) && numbersAndClauses.containsKey(-i) && numbersAndClauses.get(-i).size() > 0) { // only x or -x
                    hasChanged = true;
                    definedValues.add(i);
                    if (numbersAndClauses.containsKey(i)) {
                        bitSet.set(i);
                        redundantClauses.addAll(numbersAndClauses.get(i));
                    } else {
                        redundantClauses.addAll(numbersAndClauses.get(-i));
                    }
                }
//                else if (numbersAndClauses.containsKey(i) && numbersAndClauses.containsKey(-i)) {  //both x and -x
//                    for (Clause j : numbersAndClauses.get(i)) {
//                        for (Clause k : numbersAndClauses.get(-i)) {
//                            int otherJ = getOtherVariable(j, i);
//                            int otherK = getOtherVariable(k ,i);
//                            if (otherJ == otherK) {
//                                hasChanged = true;
//                                if (otherJ < 0) {
//                                    throw new RuntimeException("can't be false");
//                                }
//                                redundantClauses.add(j);
//                                redundantClauses.add(k);
//                            }
//                        }
//                    }
//                }
            }

            makeNewProblem();

            if (!hasChanged) {
                break;
            }
        }
    }

    public BitSet runAlgorithm() throws NotSatisfiableSolutionException {
        if (problem.getClauses().length == 0 || problem.getN() == 0) {
            return bitSet;
        }
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

    private void makeNewProblem() {
        List<Clause> clauses = new ArrayList<>();
        for (Clause clause : problem.getClauses()) {
            if (!redundantClauses.contains(clause)) {
                clauses.add(clause);
            }
        }

        problem.setClauses(clauses.toArray(new Clause[clauses.size()]));
        existingNumbers = getExistingNumbers();
        n = existingNumbers.size();
        problem.setN(n);
        numbersAndClauses = getNumbersAndClausesMap();
    }

    private int getOtherVariable(Clause clause, int varN) {
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

    private Map<Integer, List<Clause>> getNumbersAndClausesMap() {
        Map<Integer, List<Clause>> numbersAndClauses = new HashMap<>();

        for (Clause clause : problem.getClauses()) {
            int x, y;
            x = clause.notX() ? -clause.getX() : clause.getX();
            y = clause.notY() ? -clause.getY() : clause.getY();

            if (!numbersAndClauses.containsKey(x)) {
                numbersAndClauses.put(x, new ArrayList<Clause>());
            }

            if (!numbersAndClauses.containsKey(y)) {
                numbersAndClauses.put(y, new ArrayList<Clause>());
            }

            numbersAndClauses.get(x).add(clause);
            numbersAndClauses.get(y).add(clause);
        }

        return numbersAndClauses;
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
        CSPAlgorithm algorithm = new CSPAlgorithm("2sat/test_case_10000.txt");
        try {
            System.out.println(algorithm.runAlgorithm());
        } catch (NotSatisfiableSolutionException e) {
            System.out.println("no satisfiable solution");
        }
    }

}
