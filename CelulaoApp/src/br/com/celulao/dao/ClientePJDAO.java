package br.com.celulao.dao;

import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.DBConnection.MySQLDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class ClientePJDAO {
    
    public static void delete(ClientePJBean obj){}

    public static ClientePJBean findByID(Integer id) throws SQLException {
        // TODO implementar a busca por ID de cliente PJ
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ClientePJBean findByCNPJ(String CNPJ) throws SQLException {
        Connection conn = MySQLDriverManager.getConnection();
        String query = "SELECT pj.* FROM pessoajuridica pj inner join pessoa pf on pj.CodPessoaJud=pf.CodPessoa "
                + "where pj.cnpj = ?";
        PreparedStatement selectByID = conn.prepareStatement(query);
        selectByID.setString(1,CNPJ);
        ResultSet rs = selectByID.executeQuery();

        ClientePJBean returnPJ = bindResultSetToClientePJ(rs);

        rs.close();

        return returnPJ;
    }

    private static ClientePJBean bindResultSetToClientePJ(ResultSet rs) throws SQLException{
        Integer cod_pessoa= null;
        String CNPJ= null;
        String InscEstadual= null;
        String InscMunicipal= null;
        String NomeFantasia= null;
        String RazaoSocial= null;
        Integer IDPessoaResponsavel= null;
        PessoaFisicaBean PessoaResponsavel= null;
        
        while(rs!=null && rs.next()) {
            cod_pessoa = rs.getInt("CodPessoaJuridica");
            CNPJ = rs.getString("CNPJ");
            InscEstadual = rs.getString("InsEstadual");
            InscMunicipal = rs.getString("InsMunicipal");
            NomeFantasia = rs.getString("NomeFantasia");
            RazaoSocial = rs.getString("RazaoSocial");
            IDPessoaResponsavel = rs.getInt("CodPessoaJud");
            PessoaResponsavel = PessoaFisicaDAO.findPessoaFisicaByID(IDPessoaResponsavel);
            PessoaResponsavel.setTipo(TipoPessoa.Undefined);
        }
        if(cod_pessoa==null || PessoaResponsavel==null) return null;
        ClientePJBean returnPJ = new ClientePJBean(
                RazaoSocial, NomeFantasia, InscMunicipal, InscEstadual, CNPJ, PessoaResponsavel);
        returnPJ.setCod_pessoa(cod_pessoa);
        return returnPJ;
    }
    
    public static void salveOrUpdate(ClientePJBean obj) throws SQLException{
        if(obj.getCod_pessoa()==null){
            insert(obj);
        }else{
            update(obj);
        }
    }
    
    private static void insert(ClientePJBean obj) throws SQLException{
        Connection conn = MySQLDriverManager.getConnection();
        String query = 
                "insert into pessoajuridica"
                + "(CodPessoaJud, PessoaTipoJud, CNPJ, InsEstadual, InsMunicipal, NomeFantasia, RazaoSocial)"
                + "values (?,?,?,?,?,?,?)";
        PreparedStatement insert = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        insert.setInt(1,obj.getResponsavel().getCod_pessoa());
        insert.setInt(2,1);
        insert.setString(3,obj.getCNPJ());
        insert.setString(4,obj.getInsEstadual());
        insert.setString(5,obj.getInsEstadual());
        insert.setString(6,obj.getNomeFantasia());
        insert.setString(7,obj.getRazaoSocial());
        
        insert.executeUpdate();
        ResultSet tableKeys = insert.getGeneratedKeys();
        tableKeys.next();
        Integer autoGenKey = tableKeys.getInt(1);
        
        if(autoGenKey>0)
            obj.setCod_pessoa(autoGenKey);
        else
            throw new SQLException("Não foi possível inserir este novo cliente.");
    }
    
    private static void update(ClientePJBean obj) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
