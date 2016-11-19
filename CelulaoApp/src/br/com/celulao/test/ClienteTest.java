package br.com.celulao.test;

import br.com.celulao.bean.ClientePJ;
import br.com.celulao.bean.ClientePF;
import br.com.celulao.bean.PessoaFisica;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.ClientePFDAO;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 17/11/2016.
 */
public class ClienteTest {
    @Test
    public void creatingClientePJ() {
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";

        PessoaFisica responsavel = new PessoaFisica(
                "São Paulo", "Santos", "Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X", "31928233805");

        ClientePJ pj1Test = new ClientePJ("São Paulo", "São Vicente", "Rua Jardim Guassú, 222", telefones, "Casa de Massas SA",
                "Massas e festas", "12345678", "33998877", "1239893843984/0001-10", responsavel);

        Assert.assertEquals(pj1Test.getNomeReferencia(), "Massas e festas");
        Assert.assertEquals(pj1Test.getNomeFantasia(), "Massas e festas");
        Assert.assertEquals(pj1Test.getRazaoSocial(), "Casa de Massas SA");
        Assert.assertEquals(pj1Test.getCNPJ(), "1239893843984/0001-10");
        Assert.assertEquals(pj1Test.getInsEstadual(), "33998877");
        Assert.assertEquals(pj1Test.getInsMunicipal(), "12345678");
        Assert.assertEquals(pj1Test.getTipo(), TipoPessoa.ClientePJ.getTipo());

        Assert.assertEquals(pj1Test.getTipo(), 1);
        Assert.assertEquals(pj1Test.getResponsavel().getTipo(), 0);
        Assert.assertEquals(pj1Test.getResponsavel().getNome(), "Rodrigo Simões");
        Assert.assertEquals(pj1Test.getResponsavel().getCidade(), "Santos");
        Assert.assertEquals(pj1Test.getResponsavel().getEstado(), "São Paulo");
        Assert.assertEquals(pj1Test.getResponsavel().getCPF(), "31928233805");
        Assert.assertEquals(pj1Test.getResponsavel().getRG(), "43852875X");
        Assert.assertEquals(pj1Test.getResponsavel().getEndereço(), "Julio conceicao, 282");
        Assert.assertArrayEquals(pj1Test.getResponsavel().getTelefone(), telefones);
        Assert.assertEquals(pj1Test.getResponsavel().getTipo(), TipoPessoa.Undefined.getTipo());
    }

    @Test
    public void creatingClientePF() {
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";
        ClientePF pf1Test = new ClientePF(
                "São Paulo", "Santos", "Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X", "31928233805");

        Assert.assertEquals(pf1Test.getNomeReferencia(), "Rodrigo Simões");
        Assert.assertEquals(pf1Test.getNome(), "Rodrigo Simões");
        Assert.assertEquals(pf1Test.getCidade(), "Santos");
        Assert.assertEquals(pf1Test.getEstado(), "São Paulo");
        Assert.assertEquals(pf1Test.getCPF(), "31928233805");
        Assert.assertEquals(pf1Test.getRG(), "43852875X");
        Assert.assertEquals(pf1Test.getEndereço(), "Julio conceicao, 282");
        Assert.assertArrayEquals(pf1Test.getTelefone(), telefones);
        Assert.assertEquals(pf1Test.getTipo(), TipoPessoa.ClientePF.getTipo());
    }

    @Test
    public void findClientePFByID() {
        ClientePFDAO clientePFDAO = new ClientePFDAO();
        try {
            ClientePF byID = clientePFDAO.findByID(15);
            Assert.assertTrue(byID != null);
            Assert.assertEquals(byID.getNome(),"Tad");
            Assert.assertEquals(byID.getCidade(),"Santos");
            Assert.assertEquals(byID.getEstado(),"São Paulo");
            Assert.assertEquals(byID.getTelefone()[0],"1-237-664-8994");
            Assert.assertEquals(byID.getTelefone()[1],"1-762-952-9803");
            Assert.assertEquals(byID.getEndereço(),"7898 Ornare, Road");
            Assert.assertEquals(byID.getCPF(),"239457");
            Assert.assertEquals(byID.getRG(),"24255331799");
            Assert.assertEquals(byID.getTipo(), TipoPessoa.ClientePF.getTipo());
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }

    }
    @Test
    public void findClientPFByIDNotExists() {
        ClientePFDAO clientePFDAO = new ClientePFDAO();
        try {
            ClientePF byID = clientePFDAO.findByID(1);
            Assert.assertTrue(byID == null);
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
