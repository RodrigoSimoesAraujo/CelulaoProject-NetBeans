package br.com.celulao.bean;

import br.com.celulao.constants.TipoPessoa;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class OrdemServicoBean {
    private Integer codOrdem;
    private Integer cod_pessoa;
    private TipoPessoa pessoaTipo;
    private String ceularMarca;
    private String celularModelo;
    private String celularPartesEntregues;

    public OrdemServicoBean(Integer codOrdem,
                            Integer cod_pessoa,
                            TipoPessoa pessoaTipo,
                            String ceularMarca,
                            String celularModelo,
                            String celularPartesEntregues) {
        this.codOrdem = codOrdem;
        this.cod_pessoa = cod_pessoa;
        this.pessoaTipo = pessoaTipo;
        this.ceularMarca = ceularMarca;
        this.celularModelo = celularModelo;
        this.celularPartesEntregues = celularPartesEntregues;
    }

    public Integer getCodOrdem() {
        return codOrdem;
    }

    public Integer getCod_pessoa() {
        return cod_pessoa;
    }

    public TipoPessoa getPessoaTipo() {
        return pessoaTipo;
    }

    public String getCeularMarca() {
        return ceularMarca;
    }

    public String getCelularModelo() {
        return celularModelo;
    }

    public String getCelularPartesEntregues() {
        return celularPartesEntregues;
    }

    public String toString(){
        return getCeularMarca() + " - " + getCelularModelo();
    }
}
