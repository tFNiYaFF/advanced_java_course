package main.java.edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */

public class MatrixMultiplication {
    public static void main(String... args) {
        int[][] matrixA = new int[Integer.parseInt(args[0])][ Integer.parseInt(args[1])];
        int[][] matrixB = new int[Integer.parseInt(args[2])][ Integer.parseInt(args[3])];
        int pos = 4;

        if (matrixA[0].length != matrixB.length) {
            System.out.println("cant multiply this matrix");
            return;
        }

        //init matrixA
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                matrixA[i][j] = Integer.parseInt(args[pos++]);
            }
        }

        // init matrixB
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                matrixB[i][j] = Integer.parseInt(args[pos++]);
            }
        }

        MatrixMultiplication mainClass = new MatrixMultiplication();
        int[][] res = mainClass.multipty(matrixA, matrixB);

        System.out.println(mainClass.showMatrix(res));
    }


    public int[][] multipty(int[][] matrixA, int[][] matrixB) {
        int[][] result = new int[matrixA.length][matrixB[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                int summ = 0;
                for (int k = 0; k < matrixA[0].length; k++) {
                    summ += matrixA[i][k] * matrixB[k][j];
                }
                result[i][j] = summ;
            }
        }
        return result;
    }

    public String showMatrix(int[][] matrix) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                build.append(String.format("%-5d ", matrix[i][j]));
            }
            build.append("\n");
        }
        return build.toString();
    }
}