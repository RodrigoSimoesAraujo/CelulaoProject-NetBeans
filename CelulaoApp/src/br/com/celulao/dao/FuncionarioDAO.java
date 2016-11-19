package br.com.celulao.dao;
import br.com.celulao.bean.FuncionarioAtendente;
import br.com.celulao.bean.Funcionario;
import br.com.celulao.bean.FuncionarioTecnico;
import br.com.celulao.bean.PessoaFisica;
import br.com.celulao.constants.TipoPessoa;
import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class FuncionarioDAO extends PessoaFisicaDAO implements DAO<Funcionario> {
    public void insert (Funcionario obj){}
    public void delete (Funcionario obj){}
    public void update (Funcionario obj){}

    public Funcionario findByID(Integer id) throws SQLException {
        try{
            PessoaFisica pfFound = findPessoaFisicaByID(id);
            if(pfFound==null) return null;
            return bindPessoaFisicaToFuncionario(pfFound);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Funcionario findByCPF(String CPF) throws SQLException {
        try{
            PessoaFisica pfFound = findPessoaFisicaByCPF(CPF);
            if(pfFound==null) return null;
            return bindPessoaFisicaToFuncionario(pfFound);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Funcionario bindPessoaFisicaToFuncionario(PessoaFisica PF){
        if(PF.getTipo()== TipoPessoa.FuncionarioAtendente.getTipo()) {
            FuncionarioAtendente returnFunc =
                    new FuncionarioAtendente(PF.getEstado(),
                            PF.getCidade(),
                            PF.getEndereço(),
                            PF.getTelefone(),
                            PF.getNome(),
                            PF.getRG(),
                            PF.getCPF());
            returnFunc.setCod_pessoa(PF.getCod_pessoa());
            return returnFunc;
        }else if(PF.getTipo()== TipoPessoa.FuncionarioTecnico.getTipo()) {
            FuncionarioTecnico returnFunc =
                    new FuncionarioTecnico(PF.getEstado(),
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
}
