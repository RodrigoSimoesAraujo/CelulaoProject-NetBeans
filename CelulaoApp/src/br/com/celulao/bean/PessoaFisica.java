package br.com.celulao.bean;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public final class PessoaFisica extends Pessoall{
    private String Nome;
    private String RG;
    private String CPF;

    public PessoaFisica(int cod_pessoa,
                        String estado,
                        String cidade,
                        String endereço,
                        String[] telefone,
                        String nome,
                        String RG,
                        String CPF) {
        super(2, cod_pessoa, estado, cidade, endereço, telefone);
        Nome = nome;
        this.RG = RG;
        this.CPF = CPF;
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

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
