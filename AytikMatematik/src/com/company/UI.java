package com.company;

import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI {

    Matrix matrix = null;
    private ControlPanel controlPanel = null;
    OutputAction outputAction = new OutputAction(this);
    MutlakBulma mutlakBulma = new MutlakBulma(this);
    Kapsama kapsama = new Kapsama(this);
    SezgiselRota sezgiselRota = new SezgiselRota(this);

    private JFrame jf = null;
    private JPanel jp = null;
    private JTextField txtRow = null;
    private JTextField txtCol = null;
    private JButton btnCreate = null;
    private JLabel[][] lblMatrix = null;
    private JScrollPane scrollPane;

    int row, col;

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame("Kapsama Bulucu");
            jf.setSize(300, 300);
            jf.setLayout(null);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //jf.setResizable(false);
            jf.getContentPane().add(getJp());
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            jf.setLocation(dim.width / 2 - jf.getSize().width / 2, dim.height / 2 - jf.getSize().height / 2);
            jf.setVisible(true);
            jf.setResizable(false);
        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(0, 0, 300, 300);
            //jp.setBackground(new Color(198, 207, 252));
            jp.setLayout(null);
            jp.add(getTxtRow());
            jp.add(getTxtCol());
            jp.add(getBtnCreate());
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JTextField getTxtRow() {
        if (txtRow == null) {
            txtRow = new JTextField("Satır");
            txtRow.setForeground(Color.gray);
            txtRow.setBounds(50, 50, 200, 30);
            txtRow.setFont(new Font("Tahoma", Font.BOLD, 12));
            txtRow.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent focusEvent) {
                    if (txtRow.getText().equals("Satır")) {
                        txtRow.setText("");
                        txtRow.setForeground(Color.black);
                    }
                }

                @Override
                public void focusLost(FocusEvent focusEvent) {
                    if (txtRow.getText().isEmpty()) {
                        txtRow.setText("Satır");
                        txtRow.setForeground(Color.gray);
                    }
                }
            });
            txtRow.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent keyEvent) {

                }

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    if (keyEvent.getKeyChar() >= '0' && keyEvent.getKeyChar() <= '9' || keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        getTxtRow().setEditable(true);
                    } else
                        getTxtRow().setEditable(false);
                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {

                }
            });
        }
        return txtRow;
    }

    public void setTxtRow(JTextField txtRow) {
        this.txtRow = txtRow;
    }

    public JTextField getTxtCol() {
        if (txtCol == null) {
            txtCol = new JTextField("Sütun");
            txtCol.setForeground(Color.gray);
            txtCol.setBounds(50, 100, 200, 30);
            txtCol.setFont(new Font("Tahoma", Font.BOLD, 12));
            txtCol.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent focusEvent) {
                    if (txtCol.getText().equals("Sütun")) {
                        txtCol.setText("");
                        txtCol.setForeground(Color.black);
                    }
                }

                @Override
                public void focusLost(FocusEvent focusEvent) {
                    if (txtCol.getText().isEmpty()) {
                        txtCol.setText("Sütun");
                        txtCol.setForeground(Color.gray);
                    }
                }
            });
            txtCol.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent keyEvent) {

                }

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    if (keyEvent.getKeyChar() >= '0' && keyEvent.getKeyChar() <= '9' || keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        getTxtCol().setEditable(true);
                    } else
                        getTxtCol().setEditable(false);
                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {

                }
            });
        }
        return txtCol;
    }

    public void setTxtCol(JTextField txtCol) {
        this.txtCol = txtCol;
    }

    public JButton getBtnCreate() {
        if (btnCreate == null) {
            btnCreate = new JButton("Matris Oluştur");
            btnCreate.setBounds(100, 150, 100, 50);
            btnCreate.setBackground(Color.black);
            btnCreate.setForeground(Color.white);
            btnCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnCreate.addActionListener(new Action());
        }
        return btnCreate;
    }

    public void setBtnCreate(JButton btnCreate) {
        this.btnCreate = btnCreate;
    }

    public Matrix getMatrix() {
        if (matrix == null)
            matrix = new Matrix(this);
        return matrix;
    }

    public JLabel[][] getLblMatrix() {
        return lblMatrix;
    }

    public void setLblMatrix(JLabel[][] lblMatrix) {
        this.lblMatrix = lblMatrix;
    }

    public ControlPanel getControlPanel() {
        if (controlPanel == null)
            controlPanel = new ControlPanel(this);
        return controlPanel;
    }


    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == getBtnCreate()) {
                row = Integer.parseInt(getTxtRow().getText());
                col = Integer.parseInt(getTxtCol().getText());
                getTxtRow().setVisible(false);
                getTxtCol().setVisible(false);
                getBtnCreate().setVisible(false);
                getJf().setSize(1000, 600);
                getJp().setSize(600, 600);
                getJf().getContentPane().add(getControlPanel().getJpInputAndOutput());
                setLblMatrix(getMatrix().createMatrix(row, col));
            }
        }
    }
}