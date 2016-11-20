package br.com.celulao.service;

import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.dao.ClientePFDAO;
import br.com.celulao.dao.ClientePJDAO;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ClienteService {
    public static ClientePFBean searchClientePFByCPF(String CPF){
        ClientePFDAO searchCliente = new ClientePFDAO();
        try {
            ClientePFBean clientePFBeanFound = searchCliente.findByCPF(CPF);
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
            return clientePJBeanFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
