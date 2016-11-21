package br.com.celulao.dao;

import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.DAO;
import br.com.celulao.dao.DBConnection.MySQLDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class OrdemServicoDAO implements DAO<OrdemServicoBean> {
    public void insert (OrdemServicoBean obj){}
    public void delete (OrdemServicoBean obj){}
    public void update (OrdemServicoBean obj){}

    public OrdemServicoBean findByID(Integer id){
        //TODO ordem de serviço por ID
        return null;
    }

    public List<OrdemServicoBean> findByCodCliente(Integer id) throws SQLException{
        Connection conn = MySQLDriverManager.getConnection();
        String query = "SELECT * FROM ordemservico o where CodCliente = ?";
        PreparedStatement selectByCodCliente = conn.prepareStatement(query);
        selectByCodCliente.setInt(1,id);
        ResultSet rs = selectByCodCliente.executeQuery();

        List<OrdemServicoBean> returnOdemServico = bindResultSetToListOrdemServico(rs);

        rs.close();

        return returnOdemServico;
    }

    private List<OrdemServicoBean> bindResultSetToListOrdemServico(ResultSet rs) throws SQLException{
        Integer codOrdem;
        Integer cod_pessoa;
        TipoPessoa pessoaTipo;
        String ceularMarca;
        String celularModelo;
        String celularPartesEntregues;

        List<OrdemServicoBean> ordemServicoBeanListReturn = new ArrayList<OrdemServicoBean>();

        while(rs!=null && rs.next()) {
            codOrdem = rs.getInt("codOrdem");
            cod_pessoa = rs.getInt("CodCliente");;
            pessoaTipo = TipoPessoa.get(rs.getInt("PessoaTipoCliente"));
            ceularMarca = rs.getString("CelularMarca");
            celularModelo = rs.getString("CelularModelo");
            celularPartesEntregues = rs.getString("CelularPartesEntregues");
            if(codOrdem!=null) {
                ordemServicoBeanListReturn.add(
                        new OrdemServicoBean(
                                codOrdem, cod_pessoa, pessoaTipo, ceularMarca, celularModelo, celularPartesEntregues
                        )
                );
            }
        }
        return ordemServicoBeanListReturn;
    }
}
