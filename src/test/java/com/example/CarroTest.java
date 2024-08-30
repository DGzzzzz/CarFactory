package com.example;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.Carro;
import com.example.domain.Marca;
import com.example.dao.CarroDAO;
import com.example.dao.MarcaDAO;

public class CarroTest {

    private CarroDAO carroDAO;
    private MarcaDAO marcaDAO;
    private List<Carro> carrosCriados;
    private List<Marca> marcasCriadas;

    @Before
    public void setUp() {
        carroDAO = new CarroDAO();
        marcaDAO = new MarcaDAO();
        carrosCriados = new ArrayList<>();
        marcasCriadas = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (Carro carro : carrosCriados) {
            if (carroDAO.findById(carro.getId()) != null) {
                carroDAO.delete(carro);
            }
        }
        for (Marca marca : marcasCriadas) {
            if (marcaDAO.findById(marca.getId()) != null) {
                marcaDAO.delete(marca);
            }
        }
    }

    private String generateCodigo() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    @Test
    public void testSave() {
        Marca marca = new Marca();
        marca.setCodigo(generateCodigo());
        marca.setNome("Marca Teste");

        marcaDAO.save(marca);
        marcasCriadas.add(marca);

        Carro carro = new Carro();
        carro.setCodigo(generateCodigo());
        carro.setModelo("Modelo Teste");
        carro.setAno(2022);
        carro.setMarca(marca);

        carroDAO.save(carro);
        carrosCriados.add(carro);

        Carro savedCarro = carroDAO.findById(carro.getId());
        assertNotNull(savedCarro);
        assertEquals(carro.getCodigo(), savedCarro.getCodigo());
    }

    @Test
    public void testUpdate() {
        Marca marca = new Marca();
        marca.setCodigo(generateCodigo());
        marca.setNome("Marca Teste 4");

        marcaDAO.save(marca);
        marcasCriadas.add(marca);

        Carro carro = new Carro();
        carro.setCodigo(generateCodigo());
        carro.setModelo("Modelo Teste 4");
        carro.setAno(2025);
        carro.setMarca(marca);

        carroDAO.save(carro);
        carrosCriados.add(carro);

        carro.setModelo("Modelo Atualizado");
        carroDAO.update(carro);

        Carro updatedCarro = carroDAO.findById(carro.getId());
        assertNotNull(updatedCarro);
        assertEquals("Modelo Atualizado", updatedCarro.getModelo());
    }

    @Test
    public void testDelete() {
        Marca marca = new Marca();
        marca.setCodigo(generateCodigo());
        marca.setNome("Marca Teste 3");

        marcaDAO.save(marca);
        marcasCriadas.add(marca);

        Carro carro = new Carro();
        carro.setCodigo(generateCodigo());
        carro.setModelo("Modelo Teste 3");
        carro.setAno(2024);
        carro.setMarca(marca);

        carroDAO.save(carro);
        carrosCriados.add(carro);

        carroDAO.delete(carro);

        Carro deletedCarro = carroDAO.findById(carro.getId());
        assertNull(deletedCarro);
    }

    @Test
    public void testFindById() {
        Marca marca = new Marca();
        marca.setCodigo(generateCodigo());
        marca.setNome("Marca Teste 2");

        marcaDAO.save(marca);
        marcasCriadas.add(marca);

        Carro carro = new Carro();
        carro.setCodigo(generateCodigo());
        carro.setModelo("Modelo Teste 2");
        carro.setAno(2023);
        carro.setMarca(marca);

        carroDAO.save(carro);
        carrosCriados.add(carro);

        Carro foundCarro = carroDAO.findById(carro.getId());
        assertNotNull(foundCarro);
        assertEquals(carro.getCodigo(), foundCarro.getCodigo());
    }
}