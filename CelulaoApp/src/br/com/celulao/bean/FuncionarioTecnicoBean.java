package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class FuncionarioTecnicoBean extends PessoaFisicaBean implements Funcionario{
    private static int tipo = TipoPessoa.FuncionarioTecnico.getTipoValue();

    public FuncionarioTecnicoBean(String estado, String cidade, String endereço, String[] telefone, String nome, String RG, String CPF) {
        super(estado, cidade, endereço, telefone, nome, RG, CPF);
    }

    public int getTipo() {
        return tipo;
    }
}
