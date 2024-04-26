package io.codeforall.hackaton.persistence.dao;
import io.codeforall.hackaton.persistence.model.Model;
import java.util.List;

public interface Dao<T extends Model> {

    List<T> findAll();


    T findById(Integer id);


    T saveOrUpdate(T modelObject);


    void delete(Integer id);
}
