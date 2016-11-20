package br.com.celulao.dao;

import br.com.celulao.bean.ClientePJ;
import br.com.celulao.bean.PessoaFisica;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.DBConnection.MySQLDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class ClientePJDAO extends PessoaFisicaDAO implements DAO<ClientePJ>  {
    public void insert (ClientePJ obj){}
    public void delete (ClientePJ obj){}
    public void update (ClientePJ obj){}

    public ClientePJ findByID(Integer id) throws SQLException {
        // TODO implementar a busca por ID de cliente PJ
        return null;
    }

    public ClientePJ findByCNPJ(String CNPJ) throws SQLException {
        Connection conn = MySQLDriverManager.getConnection();
        String query = "SELECT pj.* FROM pessoajuridica pj inner join pessoa pf on pj.CodPessoaJud=pf.CodPessoa where pj.cnpj = ?";
        PreparedStatement selectByID = conn.prepareStatement(query);
        selectByID.setString(1,CNPJ);
        ResultSet rs = selectByID.executeQuery();

        ClientePJ returnPJ = bindResultSetToClientePJ(rs);

        rs.close();
        conn.close();

        return returnPJ;
    }

    private ClientePJ bindResultSetToClientePJ(ResultSet rs) throws SQLException{
        Integer cod_pessoa= null;
        String CNPJ= null;
        String InscEstadual= null;
        String InscMunicipal= null;
        String NomeFantasia= null;
        String RazaoSocial= null;
        Integer IDPessoaResponsavel= null;
        PessoaFisica PessoaResponsavel= null;
        while(rs!=null && rs.next()) {
            cod_pessoa = rs.getInt("CodPessoaJuridica");
            CNPJ = rs.getString("CNPJ");
            InscEstadual = rs.getString("InsEstadual");
            InscMunicipal = rs.getString("InsMunicipal");
            NomeFantasia = rs.getString("NomeFantasia");
            RazaoSocial = rs.getString("RazaoSocial");
            IDPessoaResponsavel = rs.getInt("CodPessoaJud");
            PessoaResponsavel = findPessoaFisicaByID(IDPessoaResponsavel);
            PessoaResponsavel.setTipo(TipoPessoa.Undefined.getTipo());
        }
        if(cod_pessoa==null || PessoaResponsavel==null) return null;
        ClientePJ returnPJ = new ClientePJ(
                RazaoSocial, NomeFantasia, InscMunicipal, InscEstadual, CNPJ, PessoaResponsavel);
        returnPJ.setCod_pessoa(cod_pessoa);
        return returnPJ;
    }
}
