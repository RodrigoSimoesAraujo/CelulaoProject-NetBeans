package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class ClientePJ extends PessoaJuridica implements Cliente{
    private static int tipo = TipoPessoa.ClientePJ.getTipo();
    private PessoaFisica responsavel;

    public ClientePJ(String estado, String cidade, String endereço, String[] telefone, String razaoSocial, String nomeFantasia, String insMunicipal, String insEstadual, String CNPJ, PessoaFisica responsavel) {
        super(razaoSocial, nomeFantasia, insMunicipal, insEstadual, CNPJ);
        this.responsavel = responsavel;
    }

    public String getNomeReferencia(){
        return getNomeFantasia();
    }
    public PessoaFisica getResponsavel() {
        return responsavel;
    }
    public int getTipo() { return tipo; }
}
