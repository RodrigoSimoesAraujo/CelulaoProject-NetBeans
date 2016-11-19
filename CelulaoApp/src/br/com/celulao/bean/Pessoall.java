package br.com.celulao.bean;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public abstract class Pessoall {
    private int tipo;
    private int cod_pessoa;
    private String Estado;
    private String Cidade;
    private String Endereço;
    private String[] Telefone;

    public Pessoall(int tipo,
                    int cod_pessoa,
                    String estado,
                    String cidade,
                    String endereço,
                    String[] telefone) {
        this.tipo = tipo;
        this.cod_pessoa = cod_pessoa;
        Estado = estado;
        Cidade = cidade;
        Endereço = endereço;
        Telefone = telefone;
    }

    public String getEstado() {
        return Estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public String getEndereço() {
        return Endereço;
    }

    public String[] getTelefone() {
        return Telefone;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public void setEndereço(String endereço) {
        Endereço = endereço;
    }

    public void setTelefone(String[] telefone) {
        Telefone = telefone;
    }

}
