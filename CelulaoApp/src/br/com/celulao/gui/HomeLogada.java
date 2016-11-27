/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celulao.gui;

import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.Cliente;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.gui.utils.Alert;
import br.com.celulao.service.ClienteService;
import br.com.celulao.utils.General;
import br.com.celulao.utils.Validators;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

/**
 *
 * @author SYSTEM
 */
public class HomeLogada extends javax.swing.JDialog {
    private Cliente mainCliente;
    private String userCPF;
    private TipoPessoa tipoFuncionario;
        
    /**
     * Creates new form HomeLogada
     */
    public HomeLogada(String userCPF, TipoPessoa tipoPessoa) {
        this.tipoFuncionario = tipoPessoa;
        this.userCPF = userCPF;
        initComponents();
        setModal(true);
        prepFieldsPanelsOnStart();
    }
    
    //Lógica de apresentação dos componentes na tela.
    
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    
    private void onCadastrarCliente() {
        //TODO implementar o cadastro de cliente
        prepFieldsNovoCliente();
        mainCliente = null;
    }

    private void salvarOuCriarCliente(){
        if(!txtCPFCliente.getText().equals("") && !txtClienteCNPJ.getText().equals("")){
            try {
                ClientePJBean clientePJBeanCreated = ClienteService.createClientePJ(
                        txtRazaoSocialCliente.getText(),
                        txtNomeFantasiaCliente.getText(),
                        txtInscMunicipalCliente.getText(),
                        txtInscEstadualCliente.getText(),
                        txtClienteCNPJ.getText(),
                        txtEstadoCliente.getText(),
                        txtCidadeCliente.getText(),
                        txtEndereçoCliente.getText(),
                        new String[]{txtTelefone1Cliente.getText(), 
                            txtTelefone2Cliente.getText()},
                        txtNomeCliente.getText(),
                        txtRGCliente.getText(),
                        txtCPFCliente.getText());
                mainCliente = clientePJBeanCreated;
                showDadosCliente(clientePJBeanCreated);
                showListaOrdemServico(clientePJBeanCreated.getOrdemServico());
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert.showAlertOnField("Dados incorretos do cliente. \n Exception:" + ex.getMessage());
            }
        }else if(!txtCPFCliente.getText().equals("") && txtClienteCNPJ.getText().equals("")){
            try {
                ClientePFBean clientePFBeanCreated = ClienteService.createClientePF(
                        txtEstadoCliente.getText(),
                        txtCidadeCliente.getText(),
                        txtEndereçoCliente.getText(),
                        new String[]{txtTelefone1Cliente.getText(), 
                            txtTelefone2Cliente.getText()},
                        txtNomeCliente.getText(),
                        txtRGCliente.getText(),
                        txtCPFCliente.getText());
                mainCliente = clientePFBeanCreated;
                showDadosCliente(clientePFBeanCreated);
                showListaOrdemServico(clientePFBeanCreated.getOrdemServico());
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert.showAlertOnField("Dados incorretos do cliente. \n Exception:" + ex.getMessage());
            }
        }else{
            Alert.showAlertOnField("Preencha os dados corretos do cliente.");
        }
    }
    
    private void onCriarNovaOrdemServico(){
        HomeOrdemServico.run(mainCliente);
    }

    private void onSelectionOrdemServico(){
        HomeOrdemServico.run(mainCliente,
                (OrdemServicoBean) cmbOrdensServicoCliente.getSelectedItem() );
    }

    private void onSearchCliente() {
        String clienteCPFouCNPF = txtCPFouCNPJCliente.getText();
        Boolean isCPFDigitado = CPFRadioButton.isSelected();
        Boolean isCNPJDigitado = CNPJRadioButton.isSelected();

        if(isCPFDigitado && Validators.isCPFValid(clienteCPFouCNPF)){
            ClientePFBean clientePFBeanFound = ClienteService.searchClientePFByCPF(clienteCPFouCNPF);
            if(clientePFBeanFound ==null) Alert.showAlertOnField("Não encontramos o cliente.", txtCPFouCNPJCliente);
            else {
                mainCliente = clientePFBeanFound;
                showDadosCliente(clientePFBeanFound);
                showListaOrdemServico(clientePFBeanFound.getOrdemServico());
            }
        }
        else if(isCNPJDigitado && Validators.isCNPJValid(clienteCPFouCNPF)){
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
        btnSalvarDadosCliente.setVisible(false);
        panelDadosCliente.setVisible(true);
        panelOrdemServico.setVisible(false);
        
        if(tipoFuncionario.equals(TipoPessoa.FuncionarioTecnico))
            btnCadastrarCliente.setEnabled(false);
    }

    private void prepFieldsClienteEncontrado(){
        setEnableFieldsCliente(false);
        setContentFieldsCliente("");
        btnSalvarDadosCliente.setVisible(false);
        panelDadosCliente.setVisible(true);
    }

    private void prepFieldsNovoCliente(){
        setEnableFieldsCliente(true);
        txtCodCliente.setEnabled(false);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelDadosCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCPFouCNPJCliente = new javax.swing.JTextField();
        CPFRadioButton = new javax.swing.JRadioButton();
        CNPJRadioButton = new javax.swing.JRadioButton();
        btnBuscarCliente = new javax.swing.JButton();
        btnCadastrarCliente = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefone1Cliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefone2Cliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEndereçoCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCPFCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRGCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCidadeCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEstadoCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtClienteCNPJ = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtInscEstadualCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtInscMunicipalCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNomeFantasiaCliente = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtRazaoSocialCliente = new javax.swing.JTextField();
        btnSalvarDadosCliente = new javax.swing.JButton();
        panelOrdemServico = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cmbOrdensServicoCliente = new javax.swing.JComboBox<>();
        btnConsultarOS = new javax.swing.JButton();
        btnCriarNovaOS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelDadosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar cliente"));
        panelDadosCliente.setName("panelBuscaCliente"); // NOI18N

        jLabel1.setText("Digite CPF ou CNPJ:");

        txtCPFouCNPJCliente.setName(""); // NOI18N

        buttonGroup1.add(CPFRadioButton);
        CPFRadioButton.setText("CPF");
        CPFRadioButton.setName(""); // NOI18N

        buttonGroup1.add(CNPJRadioButton);
        CNPJRadioButton.setText("CNPJ");
        CNPJRadioButton.setName(""); // NOI18N

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.setName(""); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnCadastrarCliente.setText("Novo cliente");
        btnCadastrarCliente.setName(""); // NOI18N
        btnCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDadosClienteLayout = new javax.swing.GroupLayout(panelDadosCliente);
        panelDadosCliente.setLayout(panelDadosClienteLayout);
        panelDadosClienteLayout.setHorizontalGroup(
            panelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtCPFouCNPJCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CPFRadioButton)
                .addGap(26, 26, 26)
                .addComponent(CNPJRadioButton)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarCliente)
                .addGap(18, 18, 18)
                .addComponent(btnCadastrarCliente)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelDadosClienteLayout.setVerticalGroup(
            panelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCPFouCNPJCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CPFRadioButton)
                    .addComponent(CNPJRadioButton)
                    .addComponent(btnBuscarCliente)
                    .addComponent(btnCadastrarCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do cliente"));

        jLabel2.setText("ID:");

        jLabel3.setText("Nome:");

        jLabel4.setText("Telefone1:");

        jLabel5.setText("Telefone 2:");

        jLabel6.setText("Endereço:");

        jLabel7.setText("CPF:");

        jLabel8.setText("RG:");

        jLabel9.setText("Cidade:");

        jLabel10.setText("Estado:");

        jLabel11.setText("CNPJ:");

        jLabel12.setText("Insc. Estadual:");

        jLabel13.setText("Insc. Municipal:");

        jLabel14.setText("Nome Fantasia:");

        jLabel15.setText("Razão Social:");

        btnSalvarDadosCliente.setText("Salvar dados");
        btnSalvarDadosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarDadosClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodCliente)
                    .addComponent(txtNomeCliente)
                    .addComponent(txtTelefone1Cliente)
                    .addComponent(txtTelefone2Cliente)
                    .addComponent(txtEndereçoCliente)
                    .addComponent(txtCPFCliente)
                    .addComponent(txtRGCliente)
                    .addComponent(txtCidadeCliente)
                    .addComponent(txtEstadoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtClienteCNPJ)
                        .addComponent(txtInscEstadualCliente)
                        .addComponent(txtInscMunicipalCliente)
                        .addComponent(txtNomeFantasiaCliente)
                        .addComponent(txtRazaoSocialCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .addComponent(btnSalvarDadosCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtClienteCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(txtInscEstadualCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefone1Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtInscMunicipalCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefone2Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtNomeFantasiaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEndereçoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtRazaoSocialCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRGCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarDadosCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelOrdemServico.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordem de serviço"));

        jLabel16.setText("OS deste cliente:");

        btnConsultarOS.setText("Consultar");
        btnConsultarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarOSActionPerformed(evt);
            }
        });

        btnCriarNovaOS.setText("Nova OS");
        btnCriarNovaOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarNovaOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOrdemServicoLayout = new javax.swing.GroupLayout(panelOrdemServico);
        panelOrdemServico.setLayout(panelOrdemServicoLayout);
        panelOrdemServicoLayout.setHorizontalGroup(
            panelOrdemServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrdemServicoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbOrdensServicoCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCriarNovaOS, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelOrdemServicoLayout.setVerticalGroup(
            panelOrdemServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrdemServicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOrdemServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbOrdensServicoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarOS)
                    .addComponent(btnCriarNovaOS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDadosCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelOrdemServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOrdemServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        onCancel();
    }//GEN-LAST:event_btnSairActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onCancel();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarClienteActionPerformed
        // TODO add your handling code here:
        onCadastrarCliente();
    }//GEN-LAST:event_btnCadastrarClienteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
        onSearchCliente();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnConsultarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarOSActionPerformed
        // TODO add your handling code here:
        onSelectionOrdemServico();
    }//GEN-LAST:event_btnConsultarOSActionPerformed

    private void btnCriarNovaOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarNovaOSActionPerformed
        // TODO add your handling code here:
        onCriarNovaOrdemServico();
    }//GEN-LAST:event_btnCriarNovaOSActionPerformed

    private void btnSalvarDadosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarDadosClienteActionPerformed
        // TODO add your handling code here:
        salvarOuCriarCliente();
    }//GEN-LAST:event_btnSalvarDadosClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void run(String userCPF, TipoPessoa tipoPessoa) {
        HomeLogada dialog = new HomeLogada(userCPF, tipoPessoa);
        
        dialog.setTitle("Celulao App - Acesso com CPF: " + userCPF + " - Tipo: " + tipoPessoa);

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        
        dialog.setVisible(true);
        General.exitSystem();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CNPJRadioButton;
    private javax.swing.JRadioButton CPFRadioButton;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCadastrarCliente;
    private javax.swing.JButton btnConsultarOS;
    private javax.swing.JButton btnCriarNovaOS;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvarDadosCliente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<OrdemServicoBean> cmbOrdensServicoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelDadosCliente;
    private javax.swing.JPanel panelOrdemServico;
    private javax.swing.JTextField txtCPFCliente;
    private javax.swing.JTextField txtCPFouCNPJCliente;
    private javax.swing.JTextField txtCidadeCliente;
    private javax.swing.JTextField txtClienteCNPJ;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtEndereçoCliente;
    private javax.swing.JTextField txtEstadoCliente;
    private javax.swing.JTextField txtInscEstadualCliente;
    private javax.swing.JTextField txtInscMunicipalCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeFantasiaCliente;
    private javax.swing.JTextField txtRGCliente;
    private javax.swing.JTextField txtRazaoSocialCliente;
    private javax.swing.JTextField txtTelefone1Cliente;
    private javax.swing.JTextField txtTelefone2Cliente;
    // End of variables declaration//GEN-END:variables
}
