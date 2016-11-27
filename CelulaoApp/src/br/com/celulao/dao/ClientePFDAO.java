package br.com.celulao.dao;
import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.constants.TipoPessoa;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ClientePFDAO {
    public void delete (ClientePFBean obj){}

    public static ClientePFBean findByID(Integer id) throws SQLException {
        try{
            PessoaFisicaBean pfFound = PessoaFisicaDAO.findPessoaFisicaByID(id);
            if(pfFound==null) return null;
            return bindPessoaFisicaToClientePF(pfFound);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ClientePFBean findByCPF(String CPF) throws SQLException {
        try{
            PessoaFisicaBean pfFound = PessoaFisicaDAO.findPessoaFisicaByCPF(CPF);
            if(pfFound==null) return null;
            return bindPessoaFisicaToClientePF(pfFound);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private static ClientePFBean bindPessoaFisicaToClientePF(PessoaFisicaBean PF){
        if(PF.getTipo().getTipoValue() != TipoPessoa.ClientePF.getTipoValue()) return null;
        ClientePFBean returnClientePFBean = new ClientePFBean(
                PF.getEstado(),
                PF.getCidade(),
                PF.getEndereço(),
                PF.getTelefone(),
                PF.getNome(),
                PF.getRG(),
                PF.getCPF());
        returnClientePFBean.setCod_pessoa(PF.getCod_pessoa());
        return returnClientePFBean;
    }
    
    public static void salveOrUpdate(ClientePFBean obj) throws SQLException {
        if(obj.getCod_pessoa()==null){
            insert(obj);
        }else{
            update(obj);
        }
    }
    
    private static void insert(ClientePFBean obj) throws SQLException{
        PessoaFisicaBean newRespPJ = new PessoaFisicaBean(
                obj.getEstado(), 
                obj.getCidade(), 
                obj.getEndereço(), 
                obj.getTelefone(), 
                obj.getNome(), 
                obj.getRG(), 
                obj.getCPF());
        newRespPJ.setTipo(TipoPessoa.ClientePF);
        PessoaFisicaDAO.salveOrUpdate(newRespPJ);
        obj.setCod_pessoa(newRespPJ.getCod_pessoa());
    }
    
    private static void update(ClientePFBean obj) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
