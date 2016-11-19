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
                if(pfFound==null || pfFound.getTipo()!= TipoPessoa.ClientePF.getTipo()) return null;
                ClientePF returnClientePF = new ClientePF(
                        pfFound.getEstado(),
                        pfFound.getCidade(),
                        pfFound.getEndereço(),
                        pfFound.getTelefone(),
                        pfFound.getNome(),
                        pfFound.getRG(),
                        pfFound.getCPF());
                returnClientePF.setCod_pessoa(pfFound.getCod_pessoa());
                return returnClientePF;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
}
