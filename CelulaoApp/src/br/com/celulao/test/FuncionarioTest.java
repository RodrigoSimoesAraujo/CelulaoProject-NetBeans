package br.com.celulao.test;

import br.com.celulao.bean.Funcionario;
import br.com.celulao.bean.FuncionarioAtendenteBean;
import br.com.celulao.bean.FuncionarioTecnicoBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.FuncionarioDAO;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public class FuncionarioTest {
    @Test
    public void creatingFuncionarioAtendente(){
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";
        FuncionarioAtendenteBean pf1Test = new FuncionarioAtendenteBean(
                "São Paulo","Santos","Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X","31928233805");

        Assert.assertEquals(pf1Test.getNome(),"Rodrigo Simões");
        Assert.assertEquals(pf1Test.getCidade(),"Santos");
        Assert.assertEquals(pf1Test.getEstado(),"São Paulo");
        Assert.assertEquals(pf1Test.getCPF(),"31928233805");
        Assert.assertEquals(pf1Test.getRG(),"43852875X");
        Assert.assertEquals(pf1Test.getEndereço(),"Julio conceicao, 282");
        Assert.assertArrayEquals(pf1Test.getTelefone(),telefones);
        Assert.assertEquals(pf1Test.getTipo(), TipoPessoa.FuncionarioAtendente.getTipoValue());
    }

    @Test
    public void creatingFuncionarioTecnico(){
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";
        FuncionarioTecnicoBean pf1Test = new FuncionarioTecnicoBean(
                "São Paulo","Santos","Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X","31928233805");

        Assert.assertEquals(pf1Test.getNome(),"Rodrigo Simões");
        Assert.assertEquals(pf1Test.getCidade(),"Santos");
        Assert.assertEquals(pf1Test.getEstado(),"São Paulo");
        Assert.assertEquals(pf1Test.getCPF(),"31928233805");
        Assert.assertEquals(pf1Test.getRG(),"43852875X");
        Assert.assertEquals(pf1Test.getEndereço(),"Julio conceicao, 282");
        Assert.assertArrayEquals(pf1Test.getTelefone(),telefones);
        Assert.assertEquals(pf1Test.getTipo(), TipoPessoa.FuncionarioTecnico.getTipoValue());
    }

    @Test
    public void findFuncionarioByIDNotExists() {
        FuncionarioDAO funcByID = new FuncionarioDAO();
        try {
            Funcionario byID = funcByID.findByID(1);
            Assert.assertTrue(byID == null);
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void findFuncionarioTecnicoByID(){
        FuncionarioDAO funcByID = new FuncionarioDAO();
        try {
            Funcionario pf1Test = funcByID.findByID(10);
            Assert.assertEquals(pf1Test.getNome(),"Malik");
            Assert.assertEquals(pf1Test.getCidade(),"Santos");
            Assert.assertEquals(pf1Test.getEstado(),"São Paulo");
            Assert.assertEquals(pf1Test.getCPF(),"136140");
            Assert.assertEquals(pf1Test.getRG(),"25935377299");
            Assert.assertEquals(pf1Test.getEndereço(),"863-9462 Accumsan Rd.");
            Assert.assertArrayEquals(pf1Test.getTelefone(),new String[]{"693-0186","1-946-717-2407"});
            Assert.assertEquals(pf1Test.getTipo(), TipoPessoa.FuncionarioTecnico.getTipoValue());
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void findFuncionarioTecnicoByCPF(){
        FuncionarioDAO funcByCPF = new FuncionarioDAO();
        try {
            Funcionario pf1Test = funcByCPF.findByCPF("222534");
            Assert.assertEquals(pf1Test.getNome(),"Thor");
            Assert.assertEquals(pf1Test.getCidade(),"Santos");
            Assert.assertEquals(pf1Test.getEstado(),"São Paulo");
            Assert.assertEquals(pf1Test.getCPF(),"222534");
            Assert.assertEquals(pf1Test.getRG(),"49880321299");
            Assert.assertEquals(pf1Test.getEndereço(),"654-8611 Feugiat St.");
            Assert.assertArrayEquals(pf1Test.getTelefone(),new String[]{"1-816-370-6093","1-574-860-0116"});
            Assert.assertEquals(pf1Test.getTipo(), TipoPessoa.FuncionarioTecnico.getTipoValue());
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
