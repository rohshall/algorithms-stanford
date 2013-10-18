package org.mcvly.algo.dynamic.csp;

import java.util.BitSet;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 17.10.13
 */
public class TwoSatInstance {

    private int n;
    private Clause[] clauses;

    public TwoSatInstance(int n) {
        this.n = n;
        this.clauses = new Clause[n];
    }

    public void setClause(int n, int x, int y) {
        clauses[n] = new Clause(x, y);
    }

    public Clause[] getClauses() {
        return clauses;
    }

    public Clause getClause(int n) {
        return clauses[n-1];
    }

    public void setClauses(Clause[] clauses) {
        this.clauses = clauses;
    }

    public int getN() {
        return n;
    }

    public Clause evaluate(BitSet bs) {

        for (Clause clause : clauses) {
            if (!clause.evaluate(bs)) {
                return clause;
            }
        }

        return null;
    }

}
