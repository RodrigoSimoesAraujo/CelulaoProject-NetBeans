package br.com.celulao.dao;
import br.com.celulao.bean.ClientePF;
import br.com.celulao.bean.PessoaFisica;
import br.com.celulao.constants.TipoPessoa;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ClientePFDAO extends PessoaFisicaDAO implements DAO<ClientePF> {
        public void insert (ClientePF obj){}
        public void delete (ClientePF obj){}
        public void update (ClientePF obj){}

        public ClientePF findByID(Integer id) throws SQLException {
            try{
                PessoaFisica pfFound = findPessoaFisicaByID(id);
                if(pfFound==null) return null;
                return bindPessoaFisicaToClientePF(pfFound);
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        public ClientePF findByCPF(String CPF) throws SQLException {
            try{
                PessoaFisica pfFound = findPessoaFisicaByCPF(CPF);
                if(pfFound==null) return null;
                return bindPessoaFisicaToClientePF(pfFound);
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        private ClientePF bindPessoaFisicaToClientePF(PessoaFisica PF){
            if(PF.getTipo()!= TipoPessoa.ClientePF.getTipo()) return null;
            ClientePF returnClientePF = new ClientePF(
                    PF.getEstado(),
                    PF.getCidade(),
                    PF.getEndereço(),
                    PF.getTelefone(),
                    PF.getNome(),
                    PF.getRG(),
                    PF.getCPF());
            returnClientePF.setCod_pessoa(PF.getCod_pessoa());
            return returnClientePF;
        }
}
