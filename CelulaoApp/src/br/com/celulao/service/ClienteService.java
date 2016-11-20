package br.com.celulao.service;

import br.com.celulao.bean.ClientePF;
import br.com.celulao.bean.ClientePJ;
import br.com.celulao.dao.ClientePFDAO;
import br.com.celulao.dao.ClientePJDAO;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ClienteService {
    public static ClientePF searchClientePFByCPF(String CPF){
        ClientePFDAO searchCliente = new ClientePFDAO();
        try {
            ClientePF clientePFFound = searchCliente.findByCPF(CPF);
            return clientePFFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ClientePJ searchClientePJByCNPJ(String CNPJ){
        ClientePJDAO searchCliente = new ClientePJDAO();
        try {
            ClientePJ clientePJFound = searchCliente.findByCNPJ(CNPJ);
            return clientePJFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
