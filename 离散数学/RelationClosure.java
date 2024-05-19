package lssx;

import java.util.*;

public class RelationClosure {
    //自反闭包
    public static int[][] reflexiveClosure(int[][] relation) {
        int size = relation.length;
        int[][] closure = new int[size][size];

        for (int i = 0; i < size; i++) {
            closure[i] = Arrays.copyOf(relation[i], size);
            closure[i][i] = 1; // 将对角线元素置为 1
        }

        return closure;
    }
    //对称闭包
    public static int[][] symmetricClosure(int[][] relation) {
        int size = relation.length;
        int[][] closure = new int[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(relation[i], 0, closure[i], 0, size);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (relation[i][j] == 1) {
                    closure[j][i] = 1; // 对称闭包，将关系矩阵中的对称位置也设为 1
                }
            }
        }

        return closure;
    }
    //传递闭包
    public static int[][] transitiveClosure(int[][] relation) {
        int size = relation.length;
        int[][] closure = new int[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(relation[i], 0, closure[i], 0, size);
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (closure[i][k] == 1 && closure[k][j] == 1) {
                        closure[i][j] = 1; // 传递闭包，如果存在 (i, k) 和 (k, j)，则将 (i, j) 设为 1
                    }
                }
            }
        }

        return closure;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 示例关系矩阵P97 7
        int[][] relation = {
                {0, 1, 1, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
        };

        System.out.println("原关系矩阵：");
        printMatrix(relation);

        System.out.println("\n自反闭包：");
        printMatrix(reflexiveClosure(relation));

        System.out.println("\n对称闭包：");
        printMatrix(symmetricClosure(relation));

        System.out.println("\n传递闭包：");
        printMatrix(transitiveClosure(relation));
    }
}
