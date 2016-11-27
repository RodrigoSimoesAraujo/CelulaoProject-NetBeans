package br.com.celulao.service;

import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.bean.PessoaJuridicaBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.ClientePFDAO;
import br.com.celulao.dao.ClientePJDAO;
import br.com.celulao.dao.PessoaFisicaDAO;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ClienteService {
    public static ClientePFBean searchClientePFByCPF(String CPF){
        ClientePFDAO searchCliente = new ClientePFDAO();
        try {
            ClientePFBean clientePFBeanFound = searchCliente.findByCPF(CPF);
            if(clientePFBeanFound==null) return null;
            OrdemServicoService.searchOrdemServicoByCliente(clientePFBeanFound);
            return clientePFBeanFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ClientePJBean searchClientePJByCNPJ(String CNPJ){
        ClientePJDAO searchCliente = new ClientePJDAO();
        try {
            ClientePJBean clientePJBeanFound = searchCliente.findByCNPJ(CNPJ);
            if(clientePJBeanFound==null) return null;
            OrdemServicoService.searchOrdemServicoByCliente(clientePJBeanFound);
            return clientePJBeanFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ClientePJBean createClientePJ(  
                                                String razaoSocial, 
                                                String nomeFantasia, 
                                                String insMunicipal, 
                                                String insEstadual, 
                                                String CNPJ, 
                                                String estado,
                                                String cidade,
                                                String endereço,
                                                String[] telefone,
                                                String nome,
                                                String RG,
                                                String CPF) throws Exception{
        PessoaFisicaBean newRespPJ = new PessoaFisicaBean(
                estado, cidade, endereço, telefone, nome, RG, CPF);
        newRespPJ.setTipo(TipoPessoa.ClientePJ);
        PessoaFisicaDAO.salveOrUpdate(newRespPJ);
        newRespPJ.setTipo(TipoPessoa.Undefined);
        ClientePJBean newPJ = new ClientePJBean(
                razaoSocial, nomeFantasia, insMunicipal, insEstadual, CNPJ, newRespPJ);
        ClientePJDAO.salveOrUpdate(newPJ);
        OrdemServicoService.searchOrdemServicoByCliente(newPJ);
        return newPJ;
    }
    
    public static ClientePFBean createClientePF(
                                                String estado,
                                                String cidade,
                                                String endereço,
                                                String[] telefone,
                                                String nome,
                                                String RG,
                                                String CPF) throws Exception{
        ClientePFBean newPF = new ClientePFBean(
                estado, cidade, endereço, telefone, nome, RG, CPF);
        ClientePFDAO.salveOrUpdate(newPF);
        OrdemServicoService.searchOrdemServicoByCliente(newPF);
        return newPF;
    }
}
