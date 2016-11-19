package br.com.celulao.test;

import br.com.celulao.bean.PessoaFisica;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by SYSTEM on 17/11/2016.
 */
public class PessoaFisicaTest {
    @Test
    public void creatingPessoaFisica(){
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";
        PessoaFisica pf1Test = new PessoaFisica(
                    123,"São Paulo","Santos","Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X","31928233805");

        Assert.assertEquals(pf1Test.getRG(),"43852875X");
    }
}
