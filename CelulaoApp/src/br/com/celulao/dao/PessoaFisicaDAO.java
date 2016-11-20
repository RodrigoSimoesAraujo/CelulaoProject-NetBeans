package br.com.celulao.dao;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.dao.DBConnection.MySQLDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public abstract class PessoaFisicaDAO {

    protected void insert (PessoaFisicaBean obj){}
    protected void delete (PessoaFisicaBean obj){}
    protected void update (PessoaFisicaBean obj){}

    protected PessoaFisicaBean findPessoaFisicaByCPF(String CPF) throws SQLException {

        Connection conn = MySQLDriverManager.getConnection();
        String query = "Select * from pessoa where CPF = ?";
        PreparedStatement selectByID = conn.prepareStatement(query);
        selectByID.setString(1,CPF);
        ResultSet rs = selectByID.executeQuery();

        PessoaFisicaBean returnPF = bindResultSetToPessoaFisica(rs);

        rs.close();
        conn.close();

        return returnPF;
    }

    protected PessoaFisicaBean findPessoaFisicaByID(Integer id) throws SQLException {
        Connection conn = MySQLDriverManager.getConnection();
        String query = "Select * from pessoa where codpessoa = ?";
        PreparedStatement selectByID = conn.prepareStatement(query);
        selectByID.setInt(1,id);
        ResultSet rs = selectByID.executeQuery();

        PessoaFisicaBean returnPF = bindResultSetToPessoaFisica(rs);

        rs.close();
        conn.close();

        return returnPF;
    }

    private PessoaFisicaBean bindResultSetToPessoaFisica(ResultSet rs) throws SQLException{
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
        returnPF.setTipo(pessoaTipo);
        return returnPF;
    }
}
