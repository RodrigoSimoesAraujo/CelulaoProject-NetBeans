package br.com.celulao.bean;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public abstract class Pessoall {
    private Integer cod_pessoa;

    public Pessoall() { }

    public abstract int getTipo();

    public Integer getCod_pessoa() { return cod_pessoa; }

    public void setCod_pessoa(Integer cod_pessoa) { if(this.cod_pessoa==null)this.cod_pessoa = cod_pessoa; }
}
