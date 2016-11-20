package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public class PessoaFisicaBean extends Pessoall{
    private String Nome;
    private String RG;
    private String CPF;
    private String Estado;
    private String Cidade;
    private String Endereço;
    private String[] Telefone;

    private int tipo = TipoPessoa.Undefined.getTipoValue();

    public PessoaFisicaBean(String estado,
                            String cidade,
                            String endereço,
                            String[] telefone,
                            String nome,
                            String RG,
                            String CPF) {
        super();
        Nome = nome;
        this.RG = RG;
        this.CPF = CPF;
        Estado = estado;
        Cidade = cidade;
        Endereço = endereço;
        Telefone = telefone;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return Nome;
    }

    public String getRG() {
        return RG;
    }

    public String getCPF() {
        return CPF;
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

    public int getTipo() { return tipo; }
}
