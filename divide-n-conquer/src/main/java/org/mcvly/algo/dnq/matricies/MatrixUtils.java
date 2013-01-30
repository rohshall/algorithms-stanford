package org.mcvly.algo.dnq.matricies;

/**
 * User: RMalyona
 * Date: 30.01.13
 */
public class MatrixUtils {

    public static long[][] add(long a[][], long b[][]) {
        long[][] resultingMatrix = new long[a.length][a.length];
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a.length; j++) {
                resultingMatrix[i][j] = a[i][j] + b[i][j];
            }
        }

        return resultingMatrix;
    }

    public static long[][] subtract(long a[][], long b[][]) {
        if (!isQuadratic(a) || !isQuadratic(b) || a.length != b.length) {
            throw new IllegalArgumentException("Matrices have not equal dimensions");
        }

        long[][] resultingMatrix = new long[a.length][a.length];
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a.length; j++) {
                resultingMatrix[i][j] = a[i][j] - b[i][j];
            }
        }

        return resultingMatrix;
    }

    public static void checkMatricesDimensions(long[][] a, long[][] b) throws IllegalAccessException {
        if (!isQuadratic(a) || !isQuadratic(b) || a.length != b.length) {
            throw new IllegalArgumentException("Matrices have not equal dimensions");
        }
    }

    public static boolean isQuadratic(long matrix[][]) {
        int rows = matrix.length;
        for (int i=0; i<rows; i++) {
            if (matrix[i].length != rows) {
                return false;
            }
        }
        return true;
    }

    public static String printMatrix(long[][] m) {
        StringBuilder b = new StringBuilder();
        for (long[] x : m) {
            b.append("[");
            for (int i=0; i<x.length; i++) {
                b.append(x[i]);
                if (i+1<x.length) {
                    b.append(",");
                }
            }
            b.append("]\n");
        }
        return b.toString();
    }
}
