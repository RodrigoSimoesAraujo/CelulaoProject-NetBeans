package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class FuncionarioAtendenteBean extends PessoaFisicaBean implements Funcionario{
    private static TipoPessoa tipo = TipoPessoa.FuncionarioAtendente;

    public FuncionarioAtendenteBean(String estado, String cidade, String endereço, String[] telefone, String nome, String RG, String CPF) {
        super(estado, cidade, endereço, telefone, nome, RG, CPF);
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

}
