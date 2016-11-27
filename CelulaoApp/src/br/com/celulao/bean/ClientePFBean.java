package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

import java.util.List;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class ClientePFBean extends PessoaFisicaBean implements Cliente{
    private static TipoPessoa tipo = TipoPessoa.ClientePF;
    private List<OrdemServicoBean> ordemServico= null;

    public ClientePFBean(String estado, String cidade, String endereço, String[] telefone, String nome, String RG, String CPF) {
        super(estado, cidade, endereço, telefone, nome, RG, CPF);
    }


    public String getNomeReferencia(){
        return getNome();
    }
    public TipoPessoa getTipo() { return tipo; }

    public List<OrdemServicoBean> getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(List<OrdemServicoBean> ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Integer getCodPessoaOS() {
        return getCod_pessoa();
    }
}
