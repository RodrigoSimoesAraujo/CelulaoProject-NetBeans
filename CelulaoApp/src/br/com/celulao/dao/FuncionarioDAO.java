package br.com.celulao.dao;
import br.com.celulao.bean.FuncionarioAtendenteBean;
import br.com.celulao.bean.Funcionario;
import br.com.celulao.bean.FuncionarioTecnicoBean;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.constants.TipoPessoa;
import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class FuncionarioDAO {
    public void insert (Funcionario obj){}
    public void delete (Funcionario obj){}
    public void update (Funcionario obj){}

    public Funcionario findByID(Integer id) throws SQLException {
        try{
            PessoaFisicaBean pfFound = PessoaFisicaDAO.findPessoaFisicaByID(id);
            if(pfFound==null) return null;
            return bindPessoaFisicaToFuncionario(pfFound);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Funcionario findByCPF(String CPF) throws SQLException {
        try{
            PessoaFisicaBean pfFound = PessoaFisicaDAO.findPessoaFisicaByCPF(CPF);
            if(pfFound==null) return null;
            return bindPessoaFisicaToFuncionario(pfFound);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Funcionario bindPessoaFisicaToFuncionario(PessoaFisicaBean PF){
        if(PF.getTipo().getTipoValue() == TipoPessoa.FuncionarioAtendente.getTipoValue()) {
            FuncionarioAtendenteBean returnFunc =
                    new FuncionarioAtendenteBean(PF.getEstado(),
                            PF.getCidade(),
                            PF.getEndereço(),
                            PF.getTelefone(),
                            PF.getNome(),
                            PF.getRG(),
                            PF.getCPF());
            returnFunc.setCod_pessoa(PF.getCod_pessoa());
            return returnFunc;
        }else if(PF.getTipo().getTipoValue() == TipoPessoa.FuncionarioTecnico.getTipoValue()) {
            FuncionarioTecnicoBean returnFunc =
                    new FuncionarioTecnicoBean(PF.getEstado(),
                            PF.getCidade(),
                            PF.getEndereço(),
                            PF.getTelefone(),
                            PF.getNome(),
                            PF.getRG(),
                            PF.getCPF());
            returnFunc.setCod_pessoa(PF.getCod_pessoa());
            return returnFunc;
        }else{
            return null;
        }
    }
    
    public void salveOrUpdate(Funcionario obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
