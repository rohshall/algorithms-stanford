package org.mcvly.algo.dynamic.csp;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 17.10.13
 */
public class CSPAlgorithm {

    private TwoSatInstance problem;
    private Random random;
    private BitSet bitSet;
    private int n;

    public CSPAlgorithm(String fileName) {
        problem = TwoSatReader.readFromFile(fileName);
        random = new Random();
        n = problem.getN();
        bitSet = preprocessing(problem);
    }

    private BitSet preprocessing(TwoSatInstance originalProblem) {
        BitSet newBS = new BitSet(n);
        Set<Integer> excluded = new HashSet<>();
        for (int i = 0; i < n; i++) {
            List<Integer> clausesList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                Clause clause = originalProblem.getClause(j);
                if (i == clause.getX()) {
                    clausesList.add(clause.notX() ? -i : i);
                } else if (i == clause.getY()) {
                    clausesList.add(clause.notY() ? -i : i);
                }
            }

            if (clausesList.size() == 0) {
                n = n - 1;
            } else {
                boolean isSame = true;
                boolean less = clausesList.get(0) < 0;
                for (int j = 1; j < clausesList.size(); j++) {
                    if (clausesList.get(j) > 0 == less) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame && clausesList.size() > 1) {
                    if (!less) {
                        newBS.set(i);
                    }
                    excluded.addAll(clausesList);
                }
            }

        }

        List<Clause> newClauses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!excluded.contains(i)) {
                newClauses.add(problem.getClause(i));
            }
        }

        problem.setClauses(newClauses.toArray(new Clause[0]));
        return newBS;
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
        try {
            System.out.println(algorithm.runAlgorithm());
        } catch (NotSatisfiableSolutionException e) {
            System.out.println("no satisfiable solution");
        }
    }

}
