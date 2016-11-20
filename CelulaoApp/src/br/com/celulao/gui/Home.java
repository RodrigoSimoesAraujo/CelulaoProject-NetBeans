package br.com.celulao.gui;

import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.gui.utils.Alert;
import br.com.celulao.service.AuthenticationService;
import br.com.celulao.utils.validators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea fldWelcome;
    private JTextArea fldInfosAdicionais;
    private JTextField txtUserCPF;

    public Home() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String userCPF = txtUserCPF.getText();

        if(!validators.isCPFValid(userCPF)){
            Alert.showAlertOnField("Você digitou um CPF inválido! Tente novamente.", txtUserCPF);
        }else{
            TipoPessoa tipoPessoa = AuthenticationService.LogOnCPF(userCPF);

            if(tipoPessoa.getTipo()==TipoPessoa.FuncionarioAtendente.getTipo()) {
                Alert.showAlertOnField("Você foi reconhecido como um funcionário atendente!", txtUserCPF);
                dispose();
                HomeAtendente.run(userCPF);
            }
            else if(tipoPessoa.getTipo() == TipoPessoa.FuncionarioTecnico.getTipo()) {
                Alert.showAlertOnField("Você foi reconhecido como um funcionário técnico!", txtUserCPF);
            }
            else
                Alert.showAlertOnField("Este não é um CPF autorizado. Tente Novamente.", txtUserCPF);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        Home dialog = new Home();

        dialog.setTitle("Celulao App");
        dialog.fldWelcome.setBackground(dialog.contentPane.getBackground());
        dialog.fldInfosAdicionais.setBackground(dialog.contentPane.getBackground());
        dialog.setSize(600,300);

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.txtUserCPF.requestFocusInWindow();
        dialog.setVisible(true);
    }
}
