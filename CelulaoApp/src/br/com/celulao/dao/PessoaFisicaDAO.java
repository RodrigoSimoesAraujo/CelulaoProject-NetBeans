package br.com.celulao.dao;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.DBConnection.MySQLDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class PessoaFisicaDAO{

    public static PessoaFisicaBean findPessoaFisicaByCPF(String CPF) throws SQLException {
        Connection conn = MySQLDriverManager.getConnection();
        String query = "Select * from pessoa where CPF = ?";
        PreparedStatement selectByID = conn.prepareStatement(query);
        selectByID.setString(1,CPF);
        ResultSet rs = selectByID.executeQuery();

        PessoaFisicaBean returnPF = bindResultSetToPessoaFisica(rs);

        rs.close();

        return returnPF;
    }

    public static PessoaFisicaBean findPessoaFisicaByID(Integer id) throws SQLException {
        Connection conn = MySQLDriverManager.getConnection();
        String query = "Select * from pessoa where codpessoa = ?";
        PreparedStatement selectByID = conn.prepareStatement(query);
        selectByID.setInt(1,id);
        ResultSet rs = selectByID.executeQuery();

        PessoaFisicaBean returnPF = bindResultSetToPessoaFisica(rs);

        rs.close();

        return returnPF;
    }

    private static PessoaFisicaBean bindResultSetToPessoaFisica(ResultSet rs) throws SQLException{
        Integer cod_pessoa= null;
        String nome= null;
        String telefone1= null;
        String telefone2= null;
        String cidade= null;
        String estado= null;
        String endereco= null;
        String CPF= null;
        String RG= null;
        Integer pessoaTipo= null;
        while(rs!=null && rs.next()) {
            cod_pessoa = rs.getInt("CodPessoa");
            nome = rs.getString("NomePessoa");
            telefone1 = rs.getString("Telefone1");
            telefone2 = rs.getString("Telefone2");
            endereco = rs.getString("Endereço");
            cidade = rs.getString("Cidade");
            estado = rs.getString("Estado");
            CPF = rs.getString("CPF");
            RG = rs.getString("RG");
            pessoaTipo = rs.getInt("PessoaTipo");
        }
        if(cod_pessoa==null) return null;
        PessoaFisicaBean returnPF = new PessoaFisicaBean(
                estado,cidade,endereco, new String[]{telefone1,telefone2}, nome, RG, CPF);
        returnPF.setCod_pessoa(cod_pessoa);
        returnPF.setTipo(TipoPessoa.get(pessoaTipo));
        return returnPF;
    }


    public static void salveOrUpdate(PessoaFisicaBean obj) throws SQLException {
        if(obj.getCod_pessoa()==null){
            insert(obj);
        }else{
            update(obj);
        }
    }
    
    private static void insert(PessoaFisicaBean obj) throws SQLException{
        Connection conn = MySQLDriverManager.getConnection();
        String query = 
                "insert into pessoa"
                + "(PessoaTipo, NomePessoa, Telefone1, Telefone2, Endereço, CPF, RG, Cidade, Estado)"
                + "values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement insert = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        insert.setInt(1,obj.getTipo().getTipoValue());
        insert.setString(2,obj.getNome());
        if(obj.getTelefone().length>0){
            insert.setString(3,obj.getTelefone()[0]);
            if(obj.getTelefone().length>1)
                insert.setString(4,obj.getTelefone()[1]);
            else
                insert.setString(4,"");
        }else{
            insert.setString(3,"");
            insert.setString(4,"");
        }
        insert.setString(5,obj.getEndereço());
        insert.setString(6,obj.getCPF());
        insert.setString(7,obj.getRG());
        insert.setString(8,obj.getCidade());
        insert.setString(9,obj.getEstado());
        
        insert.executeUpdate();
        ResultSet tableKeys = insert.getGeneratedKeys();
        tableKeys.next();
        Integer autoGenKey = tableKeys.getInt(1);
        
        if(autoGenKey>0)
            obj.setCod_pessoa(autoGenKey);
        else
            throw new SQLException("Não foi possível inserir este novo cliente.");
    }
    
    private static void update(PessoaFisicaBean obj) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public static void delete(PessoaFisicaBean obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public PessoaFisicaDAO findByID(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
