package br.com.celulao.service;
import br.com.celulao.bean.Funcionario;
import br.com.celulao.bean.FuncionarioAtendente;
import br.com.celulao.bean.FuncionarioTecnico;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.FuncionarioDAO;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class Authentication {
    public static TipoPessoa LogOnCPF(String CPF){
        FuncionarioDAO funcByCPF = new FuncionarioDAO();
        try{
            Funcionario func = funcByCPF.findByCPF(CPF);
            if(func==null) return TipoPessoa.Undefined;
            else if(func.getClass().equals(FuncionarioAtendente.class)) return TipoPessoa.FuncionarioAtendente;
            else if(func.getClass().equals(FuncionarioTecnico.class)) return TipoPessoa.FuncionarioTecnico;
            else return TipoPessoa.Undefined;
        }catch (SQLException e){
            e.printStackTrace();
            return TipoPessoa.Undefined;
        }
    }
}
