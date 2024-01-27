package com.mruruc.repository;

import com.mruruc.model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUD <T,ID>{
    boolean save(Student student) throws SQLException;

    List<T> getAll();
    Optional<T> get(ID id);
    ID delete(ID id);
    T update(T t);
}
