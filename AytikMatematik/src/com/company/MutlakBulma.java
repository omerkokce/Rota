package com.company;

import java.awt.*;

public class MutlakBulma {

    int[][] matrix;
    private UI ui;

    public MutlakBulma(UI ui) {
        setUi(ui);
    }

    private int row;
    private int col;
    private int colTutucu = -1;
    private int rowTutucu = -1;
    private boolean okkay = false;

    public void takeMatris(int[][] matrix, int row, int col) {
        this.matrix = new int[row][col];
        this.row = row;
        this.col = col;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                this.matrix[i][j] = matrix[i][j];
    }

    public void weight() {
        okkay = false;
        int[] weight = new int[col];
        for (int i = 0; i < col; i++)
            for (int j = 0; j < row; j++) {
                weight[i] += matrix[j][i];
            }
        findCol(weight);
    }

    public void findCol(int[] weight) {
        for (int i = 0; i < weight.length; i++)
            if (weight[i] == 1) {
                colTutucu = i;
                break;
            }
    }

    public void findRow() {
        if (colTutucu > -1) {
            for (int i = 0; i < row; i++)
                if (matrix[i][colTutucu] == 1)
                    rowTutucu = i;
        }
    }

    public void deleteCol() {
        for (int i = 0; i < col; i++)
            if (matrix[rowTutucu][i] == 1) {
                for (int a = 0; a < row; a++) {
                    for (int b = i; b < col - 1; b++)
                        matrix[a][b] = matrix[a][b + 1];
                }
                for (int a = 0; a < row; a++) {
                    for (int b = i + 1; b < col; b++) {
                        getUI().getMatrix().lblMatrix[a][b] = getUI().getMatrix().lblMatrix[a][b + 1];
                    }
                }
                col--;
                i--;
            }
    }

    public void deleteRow() {
        for (int a = rowTutucu; a < row - 1; a++) {
            for (int b = 0; b < col; b++)
                matrix[a][b] = matrix[a + 1][b];
        }
        getUI().outputAction.kapsama += " " + getUI().getMatrix().lblMatrix[rowTutucu + 1][0].getText();
        for (int a = rowTutucu + 1; a < row; a++) {
            for (int b = 0; b < col; b++)
                getUI().getMatrix().lblMatrix[a][b] = getUI().getMatrix().lblMatrix[a + 1][b];

        }
        row--;
    }

    public void mutlakSatir(int[][] matrix, int row, int col) {
        takeMatris(matrix, row, col);
        weight();
        findRow();
        if (rowTutucu > -1 && colTutucu > -1) {
            deleteCol();
            deleteRow();
            colTutucu = -1;
            rowTutucu = -1;
            okkay = true;
        }
        send();
    }

    public boolean okay() {
        return okkay;
    }

    public void send() {
        getUI().outputAction.takeMatris(matrix, row, col);
    }

    public UI getUI() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
