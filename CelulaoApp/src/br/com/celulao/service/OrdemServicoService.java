package br.com.celulao.service;

import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.dao.OrdemServicoDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class OrdemServicoService {
    public static void searchOrdemServicoByCliente(ClientePFBean cliente){
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        try{
            List<OrdemServicoBean> listaOrdemServico = ordemServicoDAO.findByCodCliente(cliente.getCod_pessoa());
            cliente.setOrdemServico(listaOrdemServico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchOrdemServicoByCliente(ClientePJBean cliente){
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        try{
            List<OrdemServicoBean> listaOrdemServico =
                    ordemServicoDAO.findByCodCliente(cliente.getResponsavel().getCod_pessoa());
            cliente.setOrdemServico(listaOrdemServico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
