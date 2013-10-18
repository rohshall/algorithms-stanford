package org.mcvly.algo.dynamic.csp;

import java.util.BitSet;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 17.10.13
 */
public class Clause {

    private int x,y;
    private boolean notX, notY;

    public Clause(int x, int y) {
        this.x = Math.abs(x);
        this.y = Math.abs(y);
        if (x < 0) {
            notX = true;
        }
        if (y < 0) {
            notY = true;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean notX() {
        return notX;
    }

    public boolean notY() {
        return notY;
    }

    public boolean evaluate(BitSet bs) {
        return (notX ? !bs.get(x) : bs.get(x)) || (notY ? !bs.get(y) : bs.get(y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Clause clause = (Clause) o;

        if (x != clause.x) {
            return false;
        }
        if (y != clause.y) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
