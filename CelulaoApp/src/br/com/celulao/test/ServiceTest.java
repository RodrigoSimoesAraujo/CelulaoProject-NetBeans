package br.com.celulao.test;
import br.com.celulao.bean.ClientePF;
import br.com.celulao.bean.ClientePJ;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.service.AuthenticationService;
import br.com.celulao.service.ClienteService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ServiceTest {
    @Test
    public void authenticateFuncionarioTecnico(){
        TipoPessoa authTipo = AuthenticationService.LogOnCPF("136140");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.FuncionarioTecnico.getTipo());
    }

    @Test
    public void authenticateFuncionarioAtendente(){
        TipoPessoa authTipo = AuthenticationService.LogOnCPF("197989");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.FuncionarioAtendente.getTipo());
    }

    @Test
    public void notAuthenticateCliente(){
        TipoPessoa authTipo = AuthenticationService.LogOnCPF("955333");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.Undefined.getTipo());

        authTipo = AuthenticationService.LogOnCPF("803470");
        Assert.assertEquals(authTipo.getTipo(),TipoPessoa.Undefined.getTipo());
    }

    @Test
    public void searchClientePFByCPF(){
        ClientePF foundByCPF = ClienteService.searchClientePFByCPF("264719");
        Assert.assertEquals(foundByCPF.getCPF(),"264719");
    }

    @Test
    public void searchClientePJByCNPJ(){
        ClientePJ foundByCNPJ = ClienteService.searchClientePJByCNPJ("147012");
        Assert.assertEquals(foundByCNPJ.getCNPJ(),"147012");
    }
}
