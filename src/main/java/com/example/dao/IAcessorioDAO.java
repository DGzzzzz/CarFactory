package com.example.dao;

import java.util.List;

import com.example.domain.Acessorio;

public interface IAcessorioDAO {
    void save(Acessorio acessorio);
    void update(Acessorio acessorio);
    void delete(Acessorio acessorio);
    Acessorio findById(Long id);
    List<Acessorio> findAll();
}
