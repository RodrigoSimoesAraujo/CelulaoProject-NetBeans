package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public interface Funcionario {
    public String getNome();
    public String getRG();
    public String getCPF();
    public String getEstado();
    public String getCidade();
    public String getEndereço();
    public String[] getTelefone();
    public TipoPessoa getTipo();
}
