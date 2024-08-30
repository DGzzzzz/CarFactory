package com.example.dao;

import java.util.List;

import com.example.domain.Marca;

public interface IMarcaDAO {
void save(Marca marca);
    void update(Marca marca);
    void delete(Marca marca);
    Marca findById(Long id);
    List<Marca> findAll();
}
