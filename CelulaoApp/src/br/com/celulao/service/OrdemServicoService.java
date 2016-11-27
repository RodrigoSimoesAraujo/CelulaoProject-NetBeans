package br.com.celulao.service;

import br.com.celulao.bean.Cliente;
import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.dao.OrdemServicoDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class OrdemServicoService {
    public static void searchOrdemServicoByCliente(Cliente cliente){
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        try{
            List<OrdemServicoBean> listaOrdemServico = ordemServicoDAO.findByCodCliente(cliente.getCodPessoaOS());
            cliente.setOrdemServico(listaOrdemServico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static OrdemServicoBean createOrdemServicoByCliente(
                                                Cliente cliente, 
                                                String celularMarca,
                                                String celularModelo,
                                                String celularPartesEntregues) throws SQLException{
        
        OrdemServicoBean newOS = new OrdemServicoBean(
                                                    cliente.getCod_pessoa(), 
                                                    cliente.getTipo(), 
                                                    celularMarca, 
                                                    celularModelo, 
                                                    celularPartesEntregues);
        OrdemServicoDAO.salveOrUpdate(newOS);
        cliente.getOrdemServico().add(newOS);
        return newOS;
    }
}

