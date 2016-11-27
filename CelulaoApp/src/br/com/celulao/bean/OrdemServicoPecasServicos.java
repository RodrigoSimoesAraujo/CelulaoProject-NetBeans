/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celulao.bean;

import br.com.celulao.constants.TipoAndamentoOS;

/**
 *
 * @author SYSTEM
 */
public class OrdemServicoPecasServicos {
    Integer id;
    TipoAndamentoOS tipo;
    Double valor;
    Integer quantidade;
    String descricao;

    public OrdemServicoPecasServicos(
            Integer id, TipoAndamentoOS tipo, Double valor, Integer quantidade, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
