package com.conveniencestore.conveniencestore.services;

import java.util.List;

public interface ServiceInterface <T, Y>{
    T insert(Y data);
    List<T> getAll(String orderby, String order);
    T getById(int id);
    T delete(int id);
    T update(int id, Y data);

}
