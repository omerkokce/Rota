package com.company;

import javax.swing.*;
import java.awt.*;

public class ControlPanel {

    private UI ui;

    private JPanel jpInputAndOutput = null;
    private JButton btnStepwise = null;
    private JButton btnComplete = null;
    private JLabel sonuc = null;
    SpringLayout springLayout = new SpringLayout();

    public ControlPanel(UI ui) {
        setUi(ui);
    }

    public JPanel getJpInputAndOutput() {
        if (jpInputAndOutput == null) {
            jpInputAndOutput = new JPanel();
            jpInputAndOutput.setSize(new Dimension(400, 600));
            jpInputAndOutput.setLocation(600, 0);
            //jpInputAndOutput.setBackground(new Color(223, 228, 234));
            jpInputAndOutput.setLayout(springLayout);
            jpInputAndOutput.add(getBtnStepwise());
            jpInputAndOutput.add(getBtnComplete());
            jpInputAndOutput.add(getSonuc());
        }
        return jpInputAndOutput;
    }

    public JButton getBtnStepwise() {
        if (btnStepwise == null) {
            btnStepwise = new JButton("Adım Adım Hesapla");
            btnStepwise.setBackground(new Color(87, 96, 111));
            btnStepwise.setForeground(Color.white);
            btnStepwise.setFocusPainted(false);
            btnStepwise.setBorder(null);
            btnStepwise.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnStepwise.setEnabled(false);
            //btnStepwise.addActionListener(getUi().outputAction);

            springLayout.putConstraint(SpringLayout.NORTH, btnStepwise, 20, SpringLayout.NORTH, getJpInputAndOutput());
            springLayout.putConstraint(SpringLayout.WEST, btnStepwise, 20, SpringLayout.WEST, getJpInputAndOutput());
            springLayout.getConstraints(btnStepwise).setHeight(Spring.constant(50));
            springLayout.getConstraints(btnStepwise).setWidth(Spring.constant(230));
        }
        return btnStepwise;
    }

    public JButton getBtnComplete() {
        if (btnComplete == null) {
            btnComplete = new JButton("Sonuç");
            btnComplete.setBackground(new Color(87, 96, 111));
            btnComplete.setForeground(Color.white);
            btnComplete.setFocusPainted(false);
            btnComplete.setBorder(null);
            btnComplete.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnComplete.setEnabled(true);
            btnComplete.addActionListener(getUi().outputAction);

            springLayout.putConstraint(SpringLayout.NORTH, btnComplete, 20, SpringLayout.NORTH, getJpInputAndOutput());
            springLayout.putConstraint(SpringLayout.WEST, btnComplete, 20, SpringLayout.EAST, getBtnStepwise());
            springLayout.getConstraints(btnComplete).setHeight(Spring.constant(50));
            springLayout.getConstraints(btnComplete).setWidth(Spring.constant(110));
        }
        return btnComplete;
    }

    public UI getUi() {
        if (ui == null)
            ui = new UI();
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public JLabel getSonuc() {
        if (sonuc == null) {
            sonuc = new JLabel("Kapsama = {");
            sonuc.setFont(new Font("Tahoma", Font.BOLD, 12));
            sonuc.setOpaque(true);
            sonuc.setSize(100,50);
            springLayout.putConstraint(SpringLayout.NORTH, sonuc, 20, SpringLayout.SOUTH, getBtnStepwise());
            springLayout.putConstraint(SpringLayout.WEST, sonuc, 20, SpringLayout.WEST, getJpInputAndOutput());
        }
        return sonuc;
    }

    public void setSonuc(JLabel sonuc) {
        this.sonuc = sonuc;
    }

}
