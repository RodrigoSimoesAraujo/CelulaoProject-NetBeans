package br.com.celulao.test;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.service.Authentication;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ServiceTest {
    @Test
    public void authenticateFuncionarioTecnico(){
        TipoPessoa authTipo = Authentication.LogOnCPF("136140");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.FuncionarioTecnico.getTipo());
    }

    @Test
    public void authenticateFuncionarioAtendente(){
        TipoPessoa authTipo = Authentication.LogOnCPF("197989");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.FuncionarioAtendente.getTipo());
    }

    @Test
    public void notAuthenticateCliente(){
        TipoPessoa authTipo = Authentication.LogOnCPF("955333");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.Undefined.getTipo());

        authTipo = Authentication.LogOnCPF("803470");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.Undefined.getTipo());
    }
}
