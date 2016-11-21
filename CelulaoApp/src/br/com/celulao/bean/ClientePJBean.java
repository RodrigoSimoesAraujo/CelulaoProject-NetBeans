package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

import java.util.List;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public final class ClientePJBean extends PessoaJuridicaBean implements Cliente{
    private static int tipo = TipoPessoa.ClientePJ.getTipoValue();
    private PessoaFisicaBean responsavel;
    private List<OrdemServicoBean> ordemServico;

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

    public List<OrdemServicoBean> getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(List<OrdemServicoBean> ordemServico) {
        this.ordemServico = ordemServico;
    }
}
