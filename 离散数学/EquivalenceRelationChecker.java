package lssx;

import java.util.*;

public class EquivalenceRelationChecker {
    //自反性判断
    public static boolean isReflexive(int[][] relation) {
        for (int i = 0; i < relation.length; i++) {
            if (!containsPair(relation, i, i)) {
                return false;
            }
        }
        return true;
    }
    //反自反性判断
    public static boolean isAntiReflexive(int[][] relation) {
        for (int i = 0; i < relation.length; i++) {
            if (containsPair(relation, i, i)) {
                return false;
            }
        }
        return true;
    }
    //对称性判断
    public static boolean isSymmetric(int[][] relation) {
        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation[0].length; j++) {
                if (relation[i][j] != relation[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    //反对称性判断
    public static boolean isAntiSymmetric(int[][] relation) {
        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation[0].length; j++) {
                if (relation[i][j] == 1 && i != j && relation[j][i] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    //传递性判断
    public static boolean isTransitive(int[][] relation) {
        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation[0].length; j++) {
                if (relation[i][j] == 1) {
                    for (int k = 0; k < relation[0].length; k++) {
                        if (relation[j][k] == 1 && relation[i][k] != 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    //判断关系矩阵row行clo列是否为1
    private static boolean containsPair(int[][] relation, int row, int col) {
        return relation[row][col] == 1;
    }

    public static void main(String[] args) {
        // 示例关系矩阵
        int[][] relation = {
                {1, 0, 0},
                {0, 1, 1},
                {0, 1, 1}
        };

        if(isReflexive(relation)){
            System.out.println("该二元关系是自反的");
        }else{
            System.out.println("该二元关系不是自反的");
        }
        if(isAntiReflexive(relation)){
            System.out.println("该二元关系是反自反的");
        }else{
            System.out.println("该二元关系不是反自反的");
        }
        if(isSymmetric(relation)){
            System.out.println("该二元关系是对称的");
        }else{
            System.out.println("该二元关系不是对称的");
        }
        if(isAntiSymmetric(relation)){
            System.out.println("该二元关系是反对称的");
        }else{
            System.out.println("该二元关系不是反对称的");
        }
        if(isTransitive(relation)){
            System.out.println("该二元关系是传递的");
        }else {
            System.out.println("该二元关系不存在传递性");
        }
    }
}
