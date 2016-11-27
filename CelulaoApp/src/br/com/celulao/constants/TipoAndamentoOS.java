/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celulao.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SYSTEM
 */
public enum TipoAndamentoOS {
    RelatoCliente("01"),
    RelatoAtendente("02"),
    AvaliacaoTecnico("03"),
    OrcamentoRealizado("04"),
    OrcamentoReprovado("05"),
    OrcamentoAprovado("06"),
    OrcamentoPeca("7a"),
    OrcamentoServico("7b"),
    ServicoRealizado("07"),
    ServicoCobrado("08");

    private static final Map lookup = new HashMap();

    static {
        for(TipoPessoa t : TipoPessoa.values())
            lookup.put(t.getTipoValue(), t);
    }
    private String tipoValue;
    private TipoAndamentoOS(String tipoValue){
        this.tipoValue = tipoValue;
    }

    public String getTipoValue(){
        return tipoValue;
    }
    public static TipoAndamentoOS get(String tipoValue) {
        return (TipoAndamentoOS)lookup.get(tipoValue);
    }
}
