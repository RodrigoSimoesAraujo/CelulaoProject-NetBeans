package br.com.celulao.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public enum TipoPessoa {
    Undefined(0),
    ClientePJ(1),
    ClientePF(2),
    FuncionarioAtendente(3),
    FuncionarioTecnico(4);

    private static final Map lookup = new HashMap();

    static {
        for(TipoPessoa t : TipoPessoa.values())
            lookup.put(t.getTipoValue(), t);
    }
    private int tipoValue;
    private TipoPessoa(int tipoValue){
        this.tipoValue = tipoValue;
    }

    public int getTipoValue(){
        return tipoValue;
    }
    public static TipoPessoa get(int tipoValue) {
        return (TipoPessoa)lookup.get(tipoValue);
    }
}
