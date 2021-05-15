package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputAction implements ActionListener {

    public OutputAction(UI ui) {
        setUi(ui);
    }

    private UI ui = null;

    private int[][] matrix;
    private int row;
    private int col;
    public String kapsama = "";


    @Override
    public void actionPerformed(ActionEvent e) {
        row = getUI().getMatrix().lblMatrix.length - 1;
        col = getUI().getMatrix().lblMatrix[0].length - 1;
        matrix = new int[row][col];
        if (e.getSource() == getUI().getControlPanel().getBtnComplete()) {
            getUI().getControlPanel().getBtnComplete().setEnabled(false);
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    matrix[i][j] = Integer.parseInt(getUI().getMatrix().lblMatrix[i + 1][j + 1].getText());
            while (col > 0) {
                getUI().mutlakBulma.mutlakSatir(matrix, row, col);
                if (getUI().mutlakBulma.okay()) {
                    continue;
                }

                getUI().kapsama.kapsama(matrix, row, col);
                if (getUI().kapsama.okay()) {
                    continue;
                }
                getUI().sezgiselRota.sezgiselSatir(matrix, row, col);
            }
            kapsama += " }";
            getUI().getControlPanel().getSonuc().setText(getUI().getControlPanel().getSonuc().getText() + kapsama);
        }
//        else if (e.getSource() == getUI().getControlPanel().getBtnStepwise()) {
//            getUI().getControlPanel().getBtnComplete().setEnabled(false);
//            System.out.println(col);
//            for (int i = 0; i < row; i++)
//                for (int j = 0; j < col; j++)
//                    matrix[i][j] = Integer.parseInt(getUI().getMatrix().lblMatrix[i + 1][j + 1].getText());
//
//                getUI().mutlakBulma.mutlakSatir(matrix, row, col);
//                if (getUI().mutlakBulma.okay()) {
//                    getUI().getControlPanel().getSonuc().setText(getUI().getControlPanel().getSonuc().getText() + kapsama);
//                    return;
//                }
//
//                getUI().kapsama.kapsama(matrix, row, col);
//                if (getUI().kapsama.okay()) {
//                    getUI().getControlPanel().getSonuc().setText(getUI().getControlPanel().getSonuc().getText() + kapsama);
//                    return;
//                }
//                getUI().sezgiselRota.sezgiselSatir(matrix, row, col);
//
//        }
    }

    public void takeMatris(int[][] matrix, int row, int col) {
        this.matrix = new int[row][col];
        this.row = row;
        this.col = col;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                this.matrix[i][j] = matrix[i][j];
    }

    public UI getUI() {
        if (ui == null)
            setUi(new UI());
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
