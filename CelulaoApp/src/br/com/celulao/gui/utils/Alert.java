package br.com.celulao.gui.utils;

import javax.swing.*;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class Alert {
    public static void showAlertOnField(String message, JTextField field){
        JOptionPane optionPane = new JOptionPane(message,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        field.setText("");
        field.requestFocusInWindow();
    }
}
