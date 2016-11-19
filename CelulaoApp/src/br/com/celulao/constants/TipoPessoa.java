package br.com.celulao.constants;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public enum TipoPessoa {
    ClientePF(2), ClientePJ(1), FuncionarioAtendente(3), FuncionarioTecnico(4), Undefined(0);
    private int tipo;
    private TipoPessoa(int tipo){
        this.tipo = tipo;
    }
    public int getTipo(){
        return tipo;
    }
}
