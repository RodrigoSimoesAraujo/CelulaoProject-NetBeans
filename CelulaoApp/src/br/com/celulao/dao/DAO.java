package br.com.celulao.dao;

import java.sql.SQLException;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public interface DAO<T> {
    void insert (T obj);
    void delete (T obj);
    void update (T obj);
    T findByID(Integer id) throws SQLException;
}
