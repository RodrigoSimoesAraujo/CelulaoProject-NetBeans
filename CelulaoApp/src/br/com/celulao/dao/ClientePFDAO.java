package br.com.celulao.dao;
import br.com.celulao.bean.ClientePFBean;
import br.com.celulao.bean.PessoaFisicaBean;
import br.com.celulao.constants.TipoPessoa;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class ClientePFDAO extends PessoaFisicaDAO implements DAO<ClientePFBean> {
        public void insert (ClientePFBean obj){}
        public void delete (ClientePFBean obj){}
        public void update (ClientePFBean obj){}

        public ClientePFBean findByID(Integer id) throws SQLException {
            try{
                PessoaFisicaBean pfFound = findPessoaFisicaByID(id);
                if(pfFound==null) return null;
                return bindPessoaFisicaToClientePF(pfFound);
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        public ClientePFBean findByCPF(String CPF) throws SQLException {
            try{
                PessoaFisicaBean pfFound = findPessoaFisicaByCPF(CPF);
                if(pfFound==null) return null;
                return bindPessoaFisicaToClientePF(pfFound);
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        private ClientePFBean bindPessoaFisicaToClientePF(PessoaFisicaBean PF){
            if(PF.getTipo()!= TipoPessoa.ClientePF.getTipoValue()) return null;
            ClientePFBean returnClientePFBean = new ClientePFBean(
                    PF.getEstado(),
                    PF.getCidade(),
                    PF.getEndereço(),
                    PF.getTelefone(),
                    PF.getNome(),
                    PF.getRG(),
                    PF.getCPF());
            returnClientePFBean.setCod_pessoa(PF.getCod_pessoa());
            return returnClientePFBean;
        }
}
