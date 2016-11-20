package br.com.celulao.test;
import br.com.celulao.bean.ClientePJBean;
import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.ClientePFDAO;
import br.com.celulao.dao.ClientePJDAO;
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

        PessoaFisicaBean responsavel = new PessoaFisicaBean(
                "São Paulo", "Santos", "Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X", "31928233805");

        ClientePJBean pj1Test = new ClientePJBean("Casa de Massas SA","Massas e festas", "12345678", "33998877",
                "1239893843984/0001-10", responsavel);

        Assert.assertEquals(pj1Test.getNomeReferencia(), "Massas e festas");
        Assert.assertEquals(pj1Test.getNomeFantasia(), "Massas e festas");
        Assert.assertEquals(pj1Test.getRazaoSocial(), "Casa de Massas SA");
        Assert.assertEquals(pj1Test.getCNPJ(), "1239893843984/0001-10");
        Assert.assertEquals(pj1Test.getInsEstadual(), "33998877");
        Assert.assertEquals(pj1Test.getInsMunicipal(), "12345678");
        Assert.assertEquals(pj1Test.getTipo(), TipoPessoa.ClientePJ.getTipoValue());

        Assert.assertEquals(pj1Test.getResponsavel().getNome(), "Rodrigo Simões");
        Assert.assertEquals(pj1Test.getResponsavel().getCidade(), "Santos");
        Assert.assertEquals(pj1Test.getResponsavel().getEstado(), "São Paulo");
        Assert.assertEquals(pj1Test.getResponsavel().getCPF(), "31928233805");
        Assert.assertEquals(pj1Test.getResponsavel().getRG(), "43852875X");
        Assert.assertEquals(pj1Test.getResponsavel().getEndereço(), "Julio conceicao, 282");
        Assert.assertArrayEquals(pj1Test.getResponsavel().getTelefone(), telefones);
        Assert.assertEquals(pj1Test.getResponsavel().getTipo(), TipoPessoa.Undefined.getTipoValue());
    }

    @Test
    public void creatingClientePF() {
        String[] telefones = new String[]{""};
        telefones[0] = "1322334455";
        ClientePFBean pf1Test = new ClientePFBean(
                "São Paulo", "Santos", "Julio conceicao, 282", telefones, "Rodrigo Simões", "43852875X", "31928233805");

        Assert.assertEquals(pf1Test.getNomeReferencia(), "Rodrigo Simões");
        Assert.assertEquals(pf1Test.getNome(), "Rodrigo Simões");
        Assert.assertEquals(pf1Test.getCidade(), "Santos");
        Assert.assertEquals(pf1Test.getEstado(), "São Paulo");
        Assert.assertEquals(pf1Test.getCPF(), "31928233805");
        Assert.assertEquals(pf1Test.getRG(), "43852875X");
        Assert.assertEquals(pf1Test.getEndereço(), "Julio conceicao, 282");
        Assert.assertArrayEquals(pf1Test.getTelefone(), telefones);
        Assert.assertEquals(pf1Test.getTipo(), TipoPessoa.ClientePF.getTipoValue());
    }

    @Test
    public void findClientePFByID() {
        ClientePFDAO clientePFDAO = new ClientePFDAO();
        try {
            ClientePFBean byID = clientePFDAO.findByID(15);
            Assert.assertTrue(byID != null);
            Assert.assertEquals(byID.getNome(),"Tad");
            Assert.assertEquals(byID.getCidade(),"Santos");
            Assert.assertEquals(byID.getEstado(),"São Paulo");
            Assert.assertEquals(byID.getTelefone()[0],"1-237-664-8994");
            Assert.assertEquals(byID.getTelefone()[1],"1-762-952-9803");
            Assert.assertEquals(byID.getEndereço(),"7898 Ornare, Road");
            Assert.assertEquals(byID.getCPF(),"239457");
            Assert.assertEquals(byID.getRG(),"24255331799");
            Assert.assertEquals(byID.getCod_pessoa().toString(),"15");
            Assert.assertEquals(byID.getTipo(), TipoPessoa.ClientePF.getTipoValue());
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void findClientePFByCPF() {
        ClientePFDAO clientePFDAO = new ClientePFDAO();
        try {
            ClientePFBean byID = clientePFDAO.findByCPF("239457");
            Assert.assertTrue(byID != null);
            Assert.assertEquals(byID.getNome(),"Tad");
            Assert.assertEquals(byID.getCidade(),"Santos");
            Assert.assertEquals(byID.getEstado(),"São Paulo");
            Assert.assertEquals(byID.getTelefone()[0],"1-237-664-8994");
            Assert.assertEquals(byID.getTelefone()[1],"1-762-952-9803");
            Assert.assertEquals(byID.getEndereço(),"7898 Ornare, Road");
            Assert.assertEquals(byID.getCPF(),"239457");
            Assert.assertEquals(byID.getRG(),"24255331799");
            Assert.assertEquals(byID.getCod_pessoa().toString(),"15");
            Assert.assertEquals(byID.getTipo(), TipoPessoa.ClientePF.getTipoValue());
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void findClientPFByIDNotExists() {
        ClientePFDAO clientePFDAO = new ClientePFDAO();
        try {
            ClientePFBean byID = clientePFDAO.findByID(1);
            Assert.assertTrue(byID == null);
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void findClientePJByCPF() {
        ClientePJDAO clientePJDAO = new ClientePJDAO();
        try{
            ClientePJBean clientePFByCNPJ = clientePJDAO.findByCNPJ("41721");
            Assert.assertEquals(clientePFByCNPJ.getNomeReferencia(), "ridiculus mus. Donec");
            Assert.assertEquals(clientePFByCNPJ.getNomeFantasia(), "ridiculus mus. Donec");
            Assert.assertEquals(clientePFByCNPJ.getRazaoSocial(), "dolor, nonummy");
            Assert.assertEquals(clientePFByCNPJ.getCNPJ(), "41721");
            Assert.assertEquals(clientePFByCNPJ.getInsEstadual(), "5793");
            Assert.assertEquals(clientePFByCNPJ.getInsMunicipal(), "7748");
            Assert.assertEquals(clientePFByCNPJ.getTipo(), TipoPessoa.ClientePJ.getTipoValue());

            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getNome(), "Travis");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getCidade(), "São Paulo");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getEstado(), "São Paulo");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getCPF(), "803470");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getRG(), "30455347999");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getEndereço(), "6379 Euismod Rd.");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getTelefone()[0], "1-100-182-5819");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getTelefone()[1], "151-7210");
            Assert.assertEquals(clientePFByCNPJ.getResponsavel().getTipo(), TipoPessoa.Undefined.getTipoValue());

        }catch (SQLException e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void findClientePJByCPFNotExists() {
        ClientePJDAO clientePJDAO = new ClientePJDAO();
        try{
            ClientePJBean clientePFByCNPJ = clientePJDAO.findByCNPJ("0000");
            Assert.assertTrue(clientePFByCNPJ == null);
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
