package br.com.celulao.gui;

import br.com.celulao.bean.Cliente;
import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.OrdemServicoBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeOrdemServico extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextArea txtPartesEntregues;
    private JTextArea txtRelatoCliente;
    private JButton btnRegistrarEtapa1;
    private JTextArea txtRelatoAtendente;
    private JButton btnRegistrarEtapa2;
    private JPanel panelEtapa1;
    private JPanel panelEtapa2;
    private JPanel panelHeader;
    private JPanel panelEtapa3;
    private JTextArea txtAvaliacaoTecnico;
    private JButton btnRegistrarEtapa3;
    private JPanel panelEtapa4;
    private JTextField txtValorOrcamento;
    private JButton btnAprovarOrcamento;
    private JButton btnReprovarOrcamento;
    private JPanel panelEtapa5;
    private JButton executarServiçoDeManutençãoButton;
    private JPanel panelEtapa6;
    private JButton cobrarEEntregarAparelhoButton;
    private JTextField txtDescricaoItemPecasServico;
    private JTextField txtQuantidadeItemPecasServico;
    private JTextField txtValorItemPecasServico;
    private JButton btnIncluirItemPecasServicos;
    private JTable tblPecasServicosRegistrados;

    private Cliente mainCliente;
    private OrdemServicoBean mainSelectedCodOrdemServico;

    public HomeOrdemServico(Cliente cliente, OrdemServicoBean selectedCodOrdemServico) {

        this.mainCliente = cliente;
        this.mainSelectedCodOrdemServico = selectedCodOrdemServico;

        prepFieldsOnStart();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void prepFieldsOnStart() {
        if(mainSelectedCodOrdemServico!=null){
            txtMarca.setText(mainSelectedCodOrdemServico.getCeularMarca());
            txtModelo.setText(mainSelectedCodOrdemServico.getCelularModelo());
            txtPartesEntregues.setText(mainSelectedCodOrdemServico.getCelularPartesEntregues());
        }
    }

    public static void run(Cliente cliente, OrdemServicoBean selectedCodOrdemServico) {
        HomeOrdemServico dialog = new HomeOrdemServico(cliente, selectedCodOrdemServico);
        if(cliente!=null && selectedCodOrdemServico!=null)dialog.setTitle(
                "Ordem de Serviço: " + selectedCodOrdemServico.getCodOrdem() +
                        " - Cliente: " + cliente.getNomeReferencia() );


        dialog.setSize(800,600);
        dialog.pack();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);

        dialog.setVisible(true);
    }
}
