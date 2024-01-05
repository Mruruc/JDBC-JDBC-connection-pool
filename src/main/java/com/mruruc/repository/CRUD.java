package com.mruruc.repository;

import java.util.List;

public interface CRUD<T,ID>{
    boolean save(T t);
    List<T> getAll();
}
