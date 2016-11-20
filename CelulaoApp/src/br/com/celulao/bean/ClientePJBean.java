package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class ClientePJBean extends PessoaJuridicaBean implements Cliente{
    private static int tipo = TipoPessoa.ClientePJ.getTipoValue();
    private PessoaFisicaBean responsavel;

    public ClientePJBean(String razaoSocial, String nomeFantasia, String insMunicipal, String insEstadual, String CNPJ, PessoaFisicaBean responsavel) {
        super(razaoSocial, nomeFantasia, insMunicipal, insEstadual, CNPJ);
        this.responsavel = responsavel;
    }

    public String getNomeReferencia(){
        return getNomeFantasia();
    }
    public PessoaFisicaBean getResponsavel() {
        return responsavel;
    }
    public int getTipo() { return tipo; }
}
