package com.example.dao;

// import java.util.List;

import com.example.domain.Carro;

public interface ICarroDAO {
    void save(Carro carro);
    void update(Carro carro);
    void delete(Carro carro);
    Carro findById(Long id);
    // List<Carro> findAll();
}
