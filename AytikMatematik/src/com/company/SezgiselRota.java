package com.company;

import java.awt.*;
import java.util.Arrays;

public class SezgiselRota {

    private UI ui;

    int[][] matrix;
    int[] weightCol;
    double[] weightRow;
    private int row;
    private int col;

    public SezgiselRota(UI ui) {
        setUi(ui);
    }


    public void takeMatris(int[][] matrix, int row, int col) {
        this.matrix = new int[row][col];
        this.weightCol = new int[col];
        this.weightRow = new double[row];
        this.row = row;
        this.col = col;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                this.matrix[i][j] = matrix[i][j];
    }

    public void weight() {
        for (int i = 0; i < col; i++)
            for (int j = 0; j < row; j++) {
                weightCol[i] += matrix[j][i];
            }
        minCol();
    }

    public void minCol() {
        int min = -1;
        if (weightCol.length > 0)
            min = Arrays.stream(weightCol).min().getAsInt();
        for (int i = 0; i < weightCol.length; i++)
            if (weightCol[i] == min)
                sutunaGit(i);
    }

    public void sutunaGit(int a) {
        for (int i = 0; i < row; i++)
            if (matrix[i][a] == 1)
                satirAgirligi(i);
    }

    public void satirAgirligi(int a) {
        if (weightRow[a] == 0)
            for (int i = 0; i < col; i++)
                if (matrix[a][i] == 1)
                    weightRow[a] += row * (1 / (double) weightCol[i]);
    }

    public void findRow() {
        double min = 999;
        int rowTutucu = -1;
        for (int i = 0; i < weightRow.length; i++)
            if (weightRow[i] != 0 && weightRow[i] < min) {
                rowTutucu = i;
                min = weightRow[i];
            }
        if (rowTutucu > -1)
            deleteRow(rowTutucu);
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

    public void sezgiselSatir(int[][] matrix, int row, int col) {
        takeMatris(matrix, row, col);
        weight();
        findRow();
        yaz();
    }

    public void yaz() {
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
