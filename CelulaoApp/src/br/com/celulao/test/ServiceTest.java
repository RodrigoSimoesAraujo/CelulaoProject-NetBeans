package br.com.celulao.test;
import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.service.AuthenticationService;
import br.com.celulao.service.ClienteService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static br.com.celulao.service.OrdemServicoService.searchOrdemServicoByCliente;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ServiceTest {
    @Test
    public void authenticateFuncionarioTecnico(){
        TipoPessoa authTipo = AuthenticationService.LogOnCPF("136140");
        Assert.assertEquals(authTipo.getTipoValue(),TipoPessoa.FuncionarioTecnico.getTipoValue());
    }

    @Test
    public void authenticateFuncionarioAtendente(){
        TipoPessoa authTipo = AuthenticationService.LogOnCPF("197989");
        Assert.assertEquals(authTipo.getTipoValue(),TipoPessoa.FuncionarioAtendente.getTipoValue());
    }

    @Test
    public void notAuthenticateCliente(){
        TipoPessoa authTipo = AuthenticationService.LogOnCPF("955333");
        Assert.assertEquals(authTipo.getTipoValue(),TipoPessoa.Undefined.getTipoValue());

        authTipo = AuthenticationService.LogOnCPF("803470");
        Assert.assertEquals(authTipo.getTipoValue(),TipoPessoa.Undefined.getTipoValue());
    }

    @Test
    public void searchClientePFByCPF(){
        ClientePFBean foundByCPF = ClienteService.searchClientePFByCPF("264719");
        Assert.assertEquals(foundByCPF.getCPF(),"264719");
    }

    @Test
    public void searchClientePJByCNPJ(){
        ClientePJBean foundByCNPJ = ClienteService.searchClientePJByCNPJ("147012");
        Assert.assertEquals(foundByCNPJ.getCNPJ(),"147012");
    }

    @Test
    public void searchOrdemServicoByClientePF(){
        ClientePFBean foundByCPF = ClienteService.searchClientePFByCPF("239457");
        Assert.assertEquals(foundByCPF.getCPF(),"239457");

        List<OrdemServicoBean> ordemServicoBean = searchOrdemServicoByCliente(foundByCPF);
        Assert.assertTrue(ordemServicoBean.size()>0);
        Integer indexOrdemServicoCliente= null;
        for(int i=0;i<ordemServicoBean.size();i++){
            if(ordemServicoBean.get(i).getCodOrdem()==5) indexOrdemServicoCliente=i;
        }
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCeularMarca(),"Nokia");
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCelularModelo(),"XPTO3456");
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCelularPartesEntregues(),"2 cabos usb");
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCod_pessoa(),foundByCPF.getCod_pessoa());
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getPessoaTipo(),TipoPessoa.ClientePF);
    }

    @Test
    public void searchOrdemServicoByClientePFWithoutOS(){
        ClientePFBean foundByCPF = ClienteService.searchClientePFByCPF("264719");
        Assert.assertEquals(foundByCPF.getCPF(),"264719");

        List<OrdemServicoBean> ordemServicoBean = searchOrdemServicoByCliente(foundByCPF);
        Assert.assertTrue(ordemServicoBean.size()==0);
    }

    @Test
    public void searchOrdemServicoByClientePJ(){
        ClientePJBean foundByCNPJ = ClienteService.searchClientePJByCNPJ("147012");
        Assert.assertEquals(foundByCNPJ.getCNPJ(),"147012");

        List<OrdemServicoBean> ordemServicoBean = searchOrdemServicoByCliente(foundByCNPJ);
        Assert.assertTrue(ordemServicoBean.size()>0);
        Integer indexOrdemServicoCliente= null;
        for(int i=0;i<ordemServicoBean.size();i++){
            if(ordemServicoBean.get(i).getCodOrdem()==6) indexOrdemServicoCliente=i;
        }
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCeularMarca(),"Lenovo");
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCelularModelo(),"RWX-7700");
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente)
                .getCelularPartesEntregues(),"6 cabos usb e carregador");
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getCod_pessoa(),
                foundByCNPJ.getResponsavel().getCod_pessoa());
        Assert.assertEquals(ordemServicoBean.get(indexOrdemServicoCliente).getPessoaTipo(),TipoPessoa.ClientePJ);
    }
}
