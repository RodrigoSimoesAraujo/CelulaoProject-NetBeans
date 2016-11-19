package br.com.celulao.bean;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public abstract class PessoaJuridica extends Pessoall{
    private String RazaoSocial;
    private String NomeFantasia;
    private String InsMunicipal;
    private String InsEstadual;
    private String CNPJ;

    public PessoaJuridica(String razaoSocial,
                          String nomeFantasia,
                          String insMunicipal,
                          String insEstadual,
                          String CNPJ) {
        super();
        RazaoSocial = razaoSocial;
        NomeFantasia = nomeFantasia;
        InsMunicipal = insMunicipal;
        InsEstadual = insEstadual;
        this.CNPJ = CNPJ;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public String getInsMunicipal() {
        return InsMunicipal;
    }

    public String getInsEstadual() {
        return InsEstadual;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public void setNomeFantasia(String nomeFantasia) {
        NomeFantasia = nomeFantasia;
    }

    public void setInsMunicipal(String insMunicipal) {
        InsMunicipal = insMunicipal;
    }

    public void setInsEstadual(String insEstadual) {
        InsEstadual = insEstadual;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
}
