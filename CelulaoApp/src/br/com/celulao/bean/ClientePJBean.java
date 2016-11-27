package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

import java.util.List;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class ClientePJBean extends PessoaJuridicaBean implements Cliente{
    private static TipoPessoa tipo = TipoPessoa.ClientePJ;
    private PessoaFisicaBean responsavel;
    private List<OrdemServicoBean> ordemServico= null;

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
    public TipoPessoa getTipo() { return tipo; }

    public List<OrdemServicoBean> getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(List<OrdemServicoBean> ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Integer getCodPessoaOS() {
        return responsavel.getCod_pessoa();
    }
}
