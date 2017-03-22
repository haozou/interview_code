package com.hao.interview;

/**
 * Created by hzou on 9/8/16.
 */
public class TicTacToe {
    private int row;
    private int col;

    private int[][] rows;
    private int[][] cols;
    private int[] backDiagnose;
    private int[] diagnose;
    private int[][] matrix;
    public TicTacToe() {

    }

    public TicTacToe(int row, int col) {
        this.row = row;
        this.col = col;
        matrix = new int[row][col];
        rows = new int[2][row];
        cols = new int[2][col];
        backDiagnose = new int[]{0,0};
        diagnose = new int[]{0,0};
    }

    /*public int move(int n, int m, int player) throws Exception {

        if (n < 0 || n >= row || m < 0 || m >= col) {
            throw new Exception(String.format("move not valid: (%d, %d)", n, m));
        }
        if (matrix[n][m] != 0) {
            throw new Exception((String.format("position (%d, %d) already been taken", n, m)));
        }
        matrix[n][m] = player;


        // check row
        boolean win = true;
        for (int i = 0; i < col; i++) {
            if (matrix[n][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return player;

        // check column
        win = true;
        for (int i = 0; i < row; i++) {
            if (matrix[i][m] != player) {
                win = false;
                break;
            }
        }
        if (win) return player;

        // check back diagnose
        win = true;
        for (int i = 0; i < row; i++) {
            if (matrix[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return player;

        // check diagnose
        for (int i = 0; i < row; i++) {
            if (matrix[i][col - i - 1] != player) {
                win = false;
                break;
            }
        }
        if (win) return player;

        return 0;
    }

    public int move2(int n, int m, int player) throws Exception {

        if (n < 0 || n >= row || m < 0 || m >= col) {
            throw new Exception(String.format("move not valid: (%d, %d)", n, m));
        }

        if (matrix[n][m] != 0) {
            throw new Exception((String.format("position (%d, %d) already been taken", n, m)));
        }

        matrix[n][m] = player;

        rows[n]++;
        cols[m]++;

        if (n == m) {
            backDiagnose++;
        }
        if (n == row - 1 - m) {
            diagnose++;
        }

        if (rows[n] == row
                || cols[m] == row
                || backDiagnose == row
                || diagnose == row) {
            return player;
        }
        return 0;

    } */

    public int move (int n, int m, int player) throws Exception {
        if (n < 0 || n >= row || m < 0 || m >= col) {
            throw new Exception("");
        }
        if (matrix[n][m] != 0) {
            throw new Exception("");
        }
        matrix[n][m] = player;
        boolean win = true;
        for (int i = 0; i < row; i++) {
            if (matrix[n][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return player;

        win = true;
        for (int i = 0; i < col; i++) {
            if (matrix[i][m] != player) {
                win = false;
                break;
            }
        }
        if (win) return player;

        if (m == n) {
            win = true;
            for (int i = 0; i < row; i++) {
                if (matrix[i][i] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return player;
        }

        if ( m + n + 1 == row) {
            for (int i = 0; i < row; i++) {
                if (matrix[i][row - i - 1] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return player;
        }
        return 0;
    }

    public int move2 (int n, int m, int player) throws Exception {
        if (n < 0 || n >= row || m < 0 || m >= col) {
            throw new Exception("");
        }
        if (matrix[n][m] != 0) {
            throw new Exception("");
        }
        matrix[n][m] = player;

        rows[player - 1][n]++;
        cols[player - 1][m]++;

        if (n == m) {
            backDiagnose[player - 1]++;
        }

        if (n + m + 1 == row) {
            diagnose[player - 1]++;
        }
        if (rows[player - 1][n] == row
                || cols[player - 1][m] == col
                || diagnose[player - 1] == row
                || backDiagnose[player - 1] == row) {
            return player;
        }
        return 0;
    }

}
