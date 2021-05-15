package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Matrix {
    public Matrix(UI ui) {
        setUi(ui);
    }

    private UI ui;
    public JLabel[][] lblMatrix = null;

    public JLabel[][] createMatrix(int n, int m) {
        int colName = 97;
        lblMatrix = new JLabel[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 && j == 0)
                    lblMatrix[i][j] = new JLabel();
                else if (i == 0) {
                    lblMatrix[i][j] = new JLabel("" + j, JLabel.CENTER);
                    lblMatrix[i][j].setBackground(new Color(128,128,128));
                } else if (j == 0) {
                    lblMatrix[i][j] = new JLabel(Character.toString((char) colName), JLabel.CENTER);
                    lblMatrix[i][j].setBackground(new Color(128,128,128));
                    colName += 1;

                } else {
                    lblMatrix[i][j] = new JLabel("0", JLabel.CENTER);
                    lblMatrix[i][j].addMouseListener(new Action());
                    lblMatrix[i][j].setBackground(new Color(202,202,202));
                }
                lblMatrix[i][j].setBounds(50 * j, 50 * i, 50, 50);
                lblMatrix[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                lblMatrix[i][j].setOpaque(true);
                lblMatrix[i][j].setFont(new Font("Tahoma", Font.BOLD, 12));
            }
        }
        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < m + 1; j++)
                ui.getJp().add(lblMatrix[i][j]);
         return lblMatrix;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    class Action implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                if (((JLabel) e.getSource()).getText().equals("0")){
                    ((JLabel) e.getSource()).setText("1");
                }
                else if (((JLabel) e.getSource()).getText().equals("1")){
                    ((JLabel) e.getSource()).setText("0");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}
