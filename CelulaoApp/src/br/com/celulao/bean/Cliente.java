package br.com.celulao.bean;

import java.util.List;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public interface Cliente {
    String getNomeReferencia();
    public List<OrdemServicoBean> getOrdemServico();
    public void setOrdemServico(List<OrdemServicoBean> ordemServico);
}
