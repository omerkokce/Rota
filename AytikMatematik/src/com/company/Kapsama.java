package com.company;

import java.awt.*;

public class Kapsama {

    private UI ui;

    int[][] matrix;
    int row;
    int col;
    boolean karsi;
    boolean okkay = false;

    public Kapsama(UI ui) {
        setUi(ui);
    }

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
        int[] weight = new int[row];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                weight[i] += matrix[i][j];
            }
        bul(weight);
    }

    public void bul(int[] weight) {
        outerloop:
        for (int i = 0; i < weight.length; i++)
            for (int j = 0; j < weight.length; j++)
                if (weight[i] <= weight[j] && i != j) {
                    karsi = Karsilastir(i, j);
                    if (karsi) {
                        deleteRow(i);
                        okkay = true;
                        yazdir();
                        break outerloop;
                    }
                }
    }

    public boolean Karsilastir(int weightCol, int karsilastirilan) {
        for (int a = 0; a < col; a++)
            if (matrix[weightCol][a] == 1 && matrix[weightCol][a] != matrix[karsilastirilan][a])
                return false;
        return true;
    }

    public void deleteRow(int rowTutucu) {
        for (int a = rowTutucu; a < row - 1; a++) {
            for (int b = 0; b < col; b++)
                matrix[a][b] = matrix[a + 1][b];
        }
        for (int a = rowTutucu + 1; a < row; a++) {
            for (int b = 0; b < col; b++)
                getUI().getMatrix().lblMatrix[a][b] = getUI().getMatrix().lblMatrix[a + 1][b];

        }
        row--;
    }

    public void kapsama(int[][] matrix, int row, int col) {
        takeMatris(matrix, row, col);
        weight();
    }


    public boolean okay() {
        return okkay;
    }

    public void yazdir() {
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