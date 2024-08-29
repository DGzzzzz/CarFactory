package com.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.Carro;
import com.example.domain.Marca;
import com.example.dao.CarroDAO;

public class CarroTest {

    private CarroDAO carroDAO;

    @Before
    public void setUp() {
        carroDAO = new CarroDAO();
    }

    @After
    public void tearDown() {
        carroDAO.delete(carroDAO.findById(1L));
    }
    
    @Test
    public void testSave() {
        Marca marca = new Marca();
        marca.setNome("Fiat");

        Carro carro = new Carro();
        carro.setCodigo("12345");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setMarca(marca);
        carroDAO.save(carro);

        Carro savedCarro = carroDAO.findById(1L);
        assertNotNull(savedCarro);
        assertEquals("12345", savedCarro.getCodigo());
        assertEquals("Uno", savedCarro.getModelo());
        assertEquals(2010, savedCarro.getAno());
        assertEquals("Fiat", savedCarro.getMarca().getNome());
    }

    @Test
    public void testUpdate() {
        Marca marca = new Marca();
        marca.setNome("Fiat");

        Carro carro = new Carro();
        carro.setId(1L);
        carro.setCodigo("12345");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setMarca(marca);
        carroDAO.save(carro);

        Marca novaMarca = new Marca();
        novaMarca.setNome("Ford");

        carro.setCodigo("67890");
        carro.setModelo("Ka");
        carro.setAno(2015);
        carro.setMarca(novaMarca);

        carroDAO.update(carro);

        Carro updatedCarro = carroDAO.findById(1L);
        assertNotNull(updatedCarro);
        assertEquals("67890", updatedCarro.getCodigo());
        assertEquals("Ka", updatedCarro.getModelo());
        assertEquals(2015, updatedCarro.getAno());
        assertEquals("Ford", updatedCarro.getMarca().getNome());
    }

    @Test
    public void testDelete() {
        Marca marca = new Marca();
        marca.setNome("Fiat");

        Carro carro = new Carro();
        carro.setCodigo("12345");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setMarca(marca);
        carroDAO.save(carro);

        carroDAO.delete(carro);

        Carro deletedCarro = carroDAO.findById(1L);
        assertNull(deletedCarro);
    }

    @Test
    public void testFindById() {
        Marca marca = new Marca();
        marca.setNome("Fiat");

        Carro carro = new Carro();
        carro.setCodigo("12345");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setMarca(marca);
        carroDAO.save(carro);

        Carro foundCarro = carroDAO.findById(1L);
        assertNotNull(foundCarro);
        assertEquals("12345", foundCarro.getCodigo());
        assertEquals("Uno", foundCarro.getModelo());
        assertEquals(2010, foundCarro.getAno());
        assertEquals("Fiat", foundCarro.getMarca().getNome());
    }
}