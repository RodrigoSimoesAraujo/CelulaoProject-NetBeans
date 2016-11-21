package br.com.celulao.gui;

import br.com.celulao.bean.Cliente;
import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.gui.utils.Alert;
import br.com.celulao.service.ClienteService;
import br.com.celulao.utils.validators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HomeAtendente extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextField txtCPFouCNPJCliente;
    private JButton btnBuscarCliente;
    private JRadioButton CPFRadioButton;
    private JRadioButton CNPJRadioButton;
    private JPanel panelPrincipal;
    private JButton btnCadastrarCliente;
    private JPanel panelBuscaCliente;
    private JPanel panelFooter;
    private JPanel panelDadosCliente;
    private JTextField txtCodCliente;
    private JTextField txtNomeCliente;
    private JTextField txtTelefone1Cliente;
    private JTextField txtTelefone2Cliente;
    private JTextField txtEndereçoCliente;
    private JTextField txtCPFCliente;
    private JTextField txtRGCliente;
    private JTextField txtCidadeCliente;
    private JTextField txtEstadoCliente;
    private JTextField txtClienteCNPJ;
    private JTextField txtInscMunicipalCliente;
    private JTextField txtNomeFantasiaCliente;
    private JTextField txtRazaoSocialCliente;
    private JTextField txtInscEstadualCliente;
    private JButton btnSalvarDadosCliente;
    private JPanel panelOrdemServico;
    private JComboBox cmbOrdensServicoCliente;
    private JButton btnCriarNovaOS;
    private JButton btnConsultarOS;
    private JPanel panelCadastrarCliente;

    private Cliente mainCliente;

    public HomeAtendente() {
        setContentPane(contentPane);
        setModal(true);

        prepFieldsPanelsOnStart();

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

        //Eventos dos botões na tela
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        btnCadastrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCadastrarCliente();
            }
        });
        btnBuscarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSearchCliente();
            }
        });
        btnConsultarOS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSelectionOrdemServico();
            }
        });
        btnCriarNovaOS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCriarNovaOrdemServico();
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onCadastrarCliente() {
        //TODO implementar o cadastro de cliente
        prepFieldsNovoCliente();
        mainCliente = null;
    }

    private void onCriarNovaOrdemServico(){
        HomeOrdemServico.run(mainCliente, null);
    }

    private void onSelectionOrdemServico(){
        HomeOrdemServico.run(mainCliente,
                (OrdemServicoBean) cmbOrdensServicoCliente.getSelectedItem() );
    }

    private void onSearchCliente() {
        String clienteCPFouCNPF = txtCPFouCNPJCliente.getText();
        Boolean isCPFDigitado = CPFRadioButton.isSelected();
        Boolean isCNPJDigitado = CNPJRadioButton.isSelected();

        if(isCPFDigitado && validators.isCPFValid(clienteCPFouCNPF)){
            ClientePFBean clientePFBeanFound = ClienteService.searchClientePFByCPF(clienteCPFouCNPF);
            if(clientePFBeanFound ==null) Alert.showAlertOnField("Não encontramos o cliente.", txtCPFouCNPJCliente);
            else {
                mainCliente = clientePFBeanFound;
                showDadosCliente(clientePFBeanFound);
                showListaOrdemServico(clientePFBeanFound.getOrdemServico());
            }
        }
        else if(isCNPJDigitado && validators.isCNPJValid(clienteCPFouCNPF)){
            ClientePJBean clientePJBeanFound = ClienteService.searchClientePJByCNPJ(clienteCPFouCNPF);
            if(clientePJBeanFound ==null) Alert.showAlertOnField("Não encontramos o cliente.", txtCPFouCNPJCliente);
            else{
                mainCliente = clientePJBeanFound;
                showDadosCliente(clientePJBeanFound);
                showListaOrdemServico(clientePJBeanFound.getOrdemServico());
            }
        }else{
            Alert.showAlertOnField("Você digitou um CPF ou CNPJ inválido! Tente novamente.", txtCPFouCNPJCliente);
        }
    }

    private void prepFieldsPanelsOnStart(){
        setEnableFieldsCliente(false);
        setContentFieldsCliente("");
        cmbOrdensServicoCliente.removeAllItems();
        panelDadosCliente.setVisible(false);
        panelOrdemServico.setVisible(false);
    }

    private void prepFieldsClienteEncontrado(){
        setEnableFieldsCliente(false);
        setContentFieldsCliente("");
        btnSalvarDadosCliente.setVisible(false);
        panelDadosCliente.setVisible(true);
    }

    private void prepFieldsNovoCliente(){
        setEnableFieldsCliente(true);
        setContentFieldsCliente("");
        btnSalvarDadosCliente.setVisible(true);
        cmbOrdensServicoCliente.removeAllItems();
        panelOrdemServico.setVisible(false);
    }

    private void showListaOrdemServico(List<OrdemServicoBean> ordemServico){
        prepListBoxOrdemServico();
        for(int i=0;i<ordemServico.size();i++){
            cmbOrdensServicoCliente.addItem(ordemServico.get(i));
        }
    }

    private void prepListBoxOrdemServico(){
        cmbOrdensServicoCliente.removeAllItems();
        panelOrdemServico.setVisible(true);
    }

    private void showDadosCliente(ClientePFBean cliente){
        prepFieldsClienteEncontrado();

        txtCodCliente.setText(cliente.getCod_pessoa().toString());
        txtNomeCliente.setText(cliente.getNome());
        if(cliente.getTelefone().length>0)
            txtTelefone1Cliente.setText(cliente.getTelefone()[0]);
        else txtTelefone1Cliente.setText("");
        if(cliente.getTelefone().length>1)
            txtTelefone2Cliente.setText(cliente.getTelefone()[1]);
        else txtTelefone2Cliente.setText("");
        txtEndereçoCliente.setText(cliente.getEndereço());
        txtCPFCliente.setText(cliente.getCPF());
        txtRGCliente.setText(cliente.getRG());
        txtCidadeCliente.setText(cliente.getCidade());
        txtEstadoCliente.setText(cliente.getEstado());
    }

    private void showDadosCliente(ClientePJBean cliente){
        prepFieldsClienteEncontrado();

        txtCodCliente.setText(cliente.getCod_pessoa().toString());
        txtNomeCliente.setText(cliente.getResponsavel().getNome());
        if(cliente.getResponsavel().getTelefone().length>0)
            txtTelefone1Cliente.setText(cliente.getResponsavel().getTelefone()[0]);
        else txtTelefone1Cliente.setText("");
        if(cliente.getResponsavel().getTelefone().length>1)
            txtTelefone2Cliente.setText(cliente.getResponsavel().getTelefone()[1]);
        else txtTelefone2Cliente.setText("");
        txtEndereçoCliente.setText(cliente.getResponsavel().getEndereço());
        txtCPFCliente.setText(cliente.getResponsavel().getCPF());
        txtRGCliente.setText(cliente.getResponsavel().getRG());
        txtCidadeCliente.setText(cliente.getResponsavel().getCidade());
        txtEstadoCliente.setText(cliente.getResponsavel().getEstado());
        txtClienteCNPJ.setText(cliente.getCNPJ());
        txtInscEstadualCliente.setText(cliente.getInsEstadual());
        txtInscMunicipalCliente.setText(cliente.getInsMunicipal());
        txtNomeFantasiaCliente.setText(cliente.getNomeFantasia());
        txtRazaoSocialCliente.setText(cliente.getRazaoSocial());
    }

    private void setEnableFieldsCliente(boolean value){
        txtCodCliente.setEnabled(value);
        txtNomeCliente.setEnabled(value);
        txtTelefone1Cliente.setEnabled(value);
        txtTelefone2Cliente.setEnabled(value);
        txtEndereçoCliente.setEnabled(value);
        txtCPFCliente.setEnabled(value);
        txtRGCliente.setEnabled(value);
        txtCidadeCliente.setEnabled(value);
        txtEstadoCliente.setEnabled(value);
        txtClienteCNPJ.setEnabled(value);
        txtInscEstadualCliente.setEnabled(value);
        txtInscMunicipalCliente.setEnabled(value);
        txtNomeFantasiaCliente.setEnabled(value);
        txtRazaoSocialCliente.setEnabled(value);
    }

    private void setContentFieldsCliente(String value){
        txtCodCliente.setText(value);
        txtNomeCliente.setText(value);
        txtTelefone1Cliente.setText(value);
        txtTelefone2Cliente.setText(value);
        txtEndereçoCliente.setText(value);
        txtCPFCliente.setText(value);
        txtRGCliente.setText(value);
        txtCidadeCliente.setText(value);
        txtEstadoCliente.setText(value);
        txtClienteCNPJ.setText(value);
        txtInscEstadualCliente.setText(value);
        txtInscMunicipalCliente.setText(value);
        txtNomeFantasiaCliente.setText(value);
        txtRazaoSocialCliente.setText(value);
    }

    public static void run(String funcionarioCPF) {
        HomeAtendente dialog = new HomeAtendente();

        dialog.setTitle("Celulao App - Acesso do Atendente com CPF: " + funcionarioCPF);
        dialog.setSize(800,600);

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);

        dialog.setVisible(true);

        System.exit(0);
    }
}
