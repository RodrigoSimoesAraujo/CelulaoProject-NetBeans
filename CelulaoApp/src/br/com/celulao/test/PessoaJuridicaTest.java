package br.com.celulao.test;

import br.com.celulao.bean.PessoaJuridica;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public class PessoaJuridicaTest {
    @Test
    public void creatingPessoaJuridica(){
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";
        PessoaJuridica pj1Test = new PessoaJuridica(3333,"São Paulo","São VIcente","Rua Jardim Guassú, 222",telefones,"Casa de Massas SA",
                "Massas e festas","12345678","33998877","1239893843984/0001-10");

        Assert.assertEquals(pj1Test.getCNPJ(),"1239893843984/0001-10");
    }
}
