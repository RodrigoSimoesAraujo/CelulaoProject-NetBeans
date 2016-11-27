/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celulao.bean;

import br.com.celulao.constants.TipoAndamentoOS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SYSTEM
 */
public class OrdemServicoDetalhes {
    String relatoCliente;
    Integer relatoClienteId;
    String relatoAtendente;
    Integer relatoAtendenteId;
    String AnaliseTecnico;
    Integer AnaliseTecnicoId;
    boolean orcamentoRealizado;
    Integer orcamentoRealizadoId;
    Double orcamentoRealizadoValor;
    boolean orcamentoAprovado;
    Integer orcamentoAprovadoId;
    boolean servicoExecutado;
    Integer servicoExecutadoId;
    boolean servicoCobrado;
    Integer servicoCobradoId;
    List<OrdemServicoPecasServicos> ordemServicoPecasServicos = new ArrayList<OrdemServicoPecasServicos>();

    public void setRelatoCliente(String relatoCliente, int id) {
        this.relatoCliente = relatoCliente;
        this.relatoClienteId = id;
    }

    public void setRelatoAtendente(String relatoAtendente, int id) {
        this.relatoAtendente = relatoAtendente;
        this.relatoAtendenteId = id;
    }

    public void setAnaliseTecnico(String relatoTecnico, int id) {
        this.AnaliseTecnico = relatoTecnico;
        this.AnaliseTecnicoId = id;
    }

    public void setOrdemServicoPecasServicos(List<OrdemServicoPecasServicos> ordemServicoPecasServicos) {
        this.ordemServicoPecasServicos = ordemServicoPecasServicos;
    }
    
    public void addPecaOrcamento(Double valor, Integer quantidade, String descricao, Integer id) {
        OrdemServicoPecasServicos newPeca = 
                new OrdemServicoPecasServicos(
                        id, 
                        TipoAndamentoOS.OrcamentoPeca,
                        valor,
                        quantidade,
                        descricao);
        this.ordemServicoPecasServicos.add(newPeca);
    }
    
    public void addServicoOrcamento(Double valor, Integer quantidade, String descricao, Integer id) {
        OrdemServicoPecasServicos newServico = 
                new OrdemServicoPecasServicos(
                        id, 
                        TipoAndamentoOS.OrcamentoServico,
                        valor,
                        quantidade,
                        descricao);
        this.ordemServicoPecasServicos.add(newServico);
    }
    
    public String getRelatoCliente() {
        return relatoCliente;
    }

    public String getRelatoAtendente() {
        return relatoAtendente;
    }

    public String getAnaliseTecnico() {
        return AnaliseTecnico;
    }

    public List<OrdemServicoPecasServicos> getOrdemServicoPecasServicos() {
        return ordemServicoPecasServicos;
    }
    
    public Double getOrcamentoServicosPecas(){
        if(ordemServicoPecasServicos.size()>0){
            Double orcamento = 0.0;
            for(int i=0;i<ordemServicoPecasServicos.size();i++){
                orcamento=+ordemServicoPecasServicos.get(i).getValor();
            }
            return orcamento;
        }else{
            return 0.0;
        }
    }

    public void setOrcamentoAprovado(int id) {
        orcamentoAprovado = true;
        orcamentoAprovadoId = id;
    }
    
    public void setOrcamentoReprovado(int id) {
        orcamentoAprovado = false;
        orcamentoAprovadoId = id;
    }
    
    public boolean isOrcamentoAprovado() {
        return orcamentoAprovado;
    }

    public boolean isServicoExecutado() {
        return servicoExecutado;
    }
    
    public void setServicoExecutado(int id) {
        servicoExecutado = true;
        servicoExecutadoId = id;
    }

    public boolean isServicoCobrado() {
        return servicoCobrado;
    }
    
    public void setServicoCobrado(int id) {
        servicoCobrado = true;
        servicoCobradoId = id;
    }

    public boolean isOrcamentoRealizado() {
        return orcamentoRealizado;
    }
    
    public Double getOrcamentoRealizado() {
        return orcamentoRealizadoValor;
    }
    
    public void setOrcamentoRealizado(Double valor, int id) {
        orcamentoRealizado = true;
        orcamentoRealizadoId = id;
        orcamentoRealizadoValor = valor;
    }
}
