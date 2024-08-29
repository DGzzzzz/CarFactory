package com.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.domain.Carro;
import com.example.dao.CarroDAO;

public class CarroTest {

    private CarroDAO carroDAO;

    @Before
    public void setUp() {
        carroDAO = new CarroDAO();
    }

    @After
    public void tearDown() {
        carroDAO.delete(carroDAO.findById(1));
    }
    
    @Test
    public void testSave() {
        Carro carro = new Carro();
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setPreco(20000.0);
        CarroDAO carroDAO = new CarroDAO();
        carroDAO.save(carro);

        Carro savedCarro = carroDAO.findById(1);
        assertNotNull(savedCarro);
        assertEquals("Fiat", savedCarro.getMarca());
        assertEquals("Uno", savedCarro.getModelo());
        assertEquals(2010, savedCarro.getAno());
        assertEquals(20000.0, savedCarro.getPreco(), 0.0);
    }

    @Test
    public void testUpdate() {
        Carro carro = new Carro();
        carro.setId(1);
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setPreco(20000.0);
        carroDAO.save(carro);

        carro.setMarca("Ford");
        carro.setModelo("Ka");
        carro.setAno(2015);
        carro.setPreco(30000.0);

        carroDAO.update(carro);

        Carro updatedCarro = carroDAO.findById(1);
        assertNotNull(updatedCarro);
        assertEquals("Ford", updatedCarro.getMarca());
        assertEquals("Ka", updatedCarro.getModelo());
        assertEquals(2015, updatedCarro.getAno());
        assertEquals(30000.0, updatedCarro.getPreco(), 0.0);
    }

    @Test
    public void testDelete() {
        Carro carro = new Carro();
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setPreco(20000.0);
        carroDAO.save(carro);

        carroDAO.delete(carro);

        Carro deletedCarro = carroDAO.findById(1);
        assertNull(deletedCarro);
    }

    @Test
    public void testFindById() {
        Carro carro = new Carro();
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setAno(2010);
        carro.setPreco(20000.0);
        carroDAO.save(carro);

        Carro foundCarro = carroDAO.findById(1);
        assertNotNull(foundCarro);
        assertEquals("Fiat", foundCarro.getMarca());
        assertEquals("Uno", foundCarro.getModelo());
        assertEquals(2010, foundCarro.getAno());
        assertEquals(20000.0, foundCarro.getPreco(), 0.0);
    }
}
