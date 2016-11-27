package br.com.celulao.dao;

import br.com.celulao.bean.OrdemServicoBean;
import br.com.celulao.bean.OrdemServicoDetalhes;
import br.com.celulao.constants.TipoAndamentoOS;
import br.com.celulao.constants.TipoPessoa;
import br.com.celulao.dao.DBConnection.MySQLDriverManager;
import java.sql.Blob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SYSTEM on 20/11/2016.
 */
public class OrdemServicoDAO {
    public static void delete (OrdemServicoBean obj){}

    public static OrdemServicoBean findByID(Integer id){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static List<OrdemServicoBean> findByCodCliente(Integer id) throws SQLException{
        Connection conn = MySQLDriverManager.getConnection();
        String query = "SELECT * FROM ordemservico o where CodCliente = ?";
        PreparedStatement selectByCodCliente = conn.prepareStatement(query);
        selectByCodCliente.setInt(1,id);
        ResultSet rs = selectByCodCliente.executeQuery();

        List<OrdemServicoBean> returnOdemServico = bindResultSetToListOrdemServico(rs);
        rs.close();

        return returnOdemServico;
    }

    private static List<OrdemServicoBean> bindResultSetToListOrdemServico(ResultSet rs) throws SQLException{
        Integer codOrdem;
        Integer cod_pessoa;
        TipoPessoa pessoaTipo;
        String ceularMarca;
        String celularModelo;
        String celularPartesEntregues;

        List<OrdemServicoBean> ordemServicoBeanListReturn = new ArrayList<OrdemServicoBean>();
        OrdemServicoBean newOS;

        while(rs!=null && rs.next()) {
            codOrdem = rs.getInt("codOrdem");
            cod_pessoa = rs.getInt("CodCliente");
            pessoaTipo = TipoPessoa.get(rs.getInt("PessoaTipoCliente"));
            ceularMarca = rs.getString("CelularMarca");
            celularModelo = rs.getString("CelularModelo");
            celularPartesEntregues = rs.getString("CelularPartesEntregues");
            if(codOrdem!=null) {
                newOS = new OrdemServicoBean(
                                codOrdem, 
                                cod_pessoa, 
                                pessoaTipo, 
                                ceularMarca, 
                                celularModelo, 
                                celularPartesEntregues);
                newOS.setOrdemServicoDetalhes(findDetalhesOS(newOS));
                ordemServicoBeanListReturn.add(newOS);
            }
        }
        return ordemServicoBeanListReturn;
    }
    
    private static OrdemServicoDetalhes findDetalhesOS(OrdemServicoBean ordemServico) throws SQLException{
        Connection conn = MySQLDriverManager.getConnection();
        String query = "SELECT * FROM ordemservicoitem o where codOrdemExt = ?";
        PreparedStatement selectByOrdemServico = conn.prepareStatement(query);
        selectByOrdemServico.setInt(1,ordemServico.getCodOrdem());
        ResultSet rs = selectByOrdemServico.executeQuery();

        OrdemServicoDetalhes returnDetalhesOS = new OrdemServicoDetalhes();
        
        String osAndamento;
        
        while(rs!=null && rs.next()) {
            osAndamento = rs.getString("Andamento");
            if(osAndamento.equals(TipoAndamentoOS.RelatoCliente.getTipoValue())){
                returnDetalhesOS.setRelatoCliente(
                        getBlobValue(rs.getBlob("DescricaoItem")), 
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.RelatoAtendente.getTipoValue())){
                returnDetalhesOS.setRelatoAtendente(
                        getBlobValue(rs.getBlob("DescricaoItem")), 
                        rs.getInt("CodOrdemItem"));
            
            }else if(osAndamento.equals(TipoAndamentoOS.AvaliacaoTecnico.getTipoValue())){
                returnDetalhesOS.setAnaliseTecnico(
                        getBlobValue(rs.getBlob("DescricaoItem")), 
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.OrcamentoPeca.getTipoValue())){
                returnDetalhesOS.addPecaOrcamento(
                        rs.getDouble("ValorItem"),
                        rs.getInt("QtdItem"),
                        getBlobValue(rs.getBlob("DescricaoItem")), 
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.OrcamentoServico.getTipoValue())){
                returnDetalhesOS.addServicoOrcamento(
                        rs.getDouble("ValorItem"),
                        rs.getInt("QtdItem"),
                        getBlobValue(rs.getBlob("DescricaoItem")), 
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.OrcamentoReprovado.getTipoValue())){
                returnDetalhesOS.setOrcamentoReprovado(
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.OrcamentoAprovado.getTipoValue())){
                returnDetalhesOS.setOrcamentoAprovado(
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.ServicoRealizado.getTipoValue())){
                returnDetalhesOS.setServicoExecutado(
                        rs.getInt("CodOrdemItem"));
                
            }else if(osAndamento.equals(TipoAndamentoOS.ServicoCobrado.getTipoValue())){
                returnDetalhesOS.setServicoExecutado(
                        rs.getInt("CodOrdemItem"));
            
            }else if(osAndamento.equals(TipoAndamentoOS.OrcamentoRealizado.getTipoValue())){
                returnDetalhesOS.setOrcamentoRealizado(
                        rs.getDouble("ValorItem"),
                        rs.getInt("CodOrdemItem"));
            }
        }
        
        rs.close();
        return returnDetalhesOS;
    }
    
    private static String getBlobValue(Blob value) throws SQLException{
        java.sql.Blob ablob = value;
        return (new String(ablob.getBytes(1l, (int) ablob.length())));
    }

    public static void salveOrUpdate(OrdemServicoBean obj) throws SQLException {
        if(obj.getCodOrdem()==null){
            insert(obj);
        }else{
            update(obj);
        }
    }
    
    private static void insert(OrdemServicoBean obj) throws SQLException{
        Connection conn = MySQLDriverManager.getConnection();
        String query = 
                "insert into ordemservico"
                + "(CodCliente, PessoaTipoCliente, CelularMarca, CelularModelo, CelularPartesEntregues)"
                + "values (?,?,?,?,?)";
        PreparedStatement insert = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        insert.setInt(1,obj.getCod_pessoa());
        insert.setInt(2,obj.getPessoaTipo().getTipoValue());
        insert.setString(3,obj.getCelularMarca());
        insert.setString(4,obj.getCelularModelo());
        insert.setString(5,obj.getCelularPartesEntregues());
        
        insert.executeUpdate();
        ResultSet tableKeys = insert.getGeneratedKeys();
        tableKeys.next();
        Integer autoGenKey = tableKeys.getInt(1);
        
        if(autoGenKey>0)
            obj.setCodOrdem(autoGenKey);
        else
            throw new SQLException("Não foi possível inserir este novo cliente.");
    }
    
    private static void update(OrdemServicoBean obj) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
