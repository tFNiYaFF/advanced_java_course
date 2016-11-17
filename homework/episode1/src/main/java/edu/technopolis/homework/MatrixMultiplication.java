package main.java.edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */

public class MatrixMultiplication {
    public static void main(String... args) {
        if (args.length < 6) {
            System.out.println("not enough arguments");
            return;
        }

        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int X = Integer.parseInt(args[2]);
        int Y = Integer.parseInt(args[3]);

        int[][] matrixA = new int[N][M];
        int[][] matrixB = new int[X][Y];
        int pos = 4;

        if (args.length != (N*M + X*Y + 4)) {
            System.out.println("not enough arguments");
            return;
        }

        if (matrixA[0].length != matrixB.length) {
            System.out.println("can't multiply this matrix");
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
                build.append(matrix[i][j]);
            }
            build.append("\n");
        }
        return build.toString();
    }
}