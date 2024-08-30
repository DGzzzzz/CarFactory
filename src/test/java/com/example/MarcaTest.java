package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.dao.MarcaDAO;
import com.example.domain.Marca;

public class MarcaTest {

    private MarcaDAO marcaDAO;
    private Marca marca;

    @Before
    public void setUp() {
        marcaDAO = new MarcaDAO();
        marca = new Marca();
        marca.setCodigo(generateCodigo());
        marca.setNome("Marca Teste");
    }

    @After
    public void tearDown() {
        if (marcaDAO.findById(marca.getId()) != null) {
            marcaDAO.delete(marca);
        }
    }

    private String generateCodigo() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    @Test
    public void testSave() {
        marcaDAO.save(marca);
        Marca savedMarca = marcaDAO.findById(marca.getId());
        assertNotNull(savedMarca);
        assertEquals(marca.getCodigo(), savedMarca.getCodigo());
    }

    @Test
    public void testUpdate() {
        marcaDAO.save(marca);
        marca.setNome("Marca Teste Atualizada");
        marcaDAO.update(marca);
        Marca updatedMarca = marcaDAO.findById(marca.getId());
        assertEquals("Marca Teste Atualizada", updatedMarca.getNome());
    }

    @Test
    public void testDelete() {
        marcaDAO.save(marca);
        marcaDAO.delete(marca);
        Marca deletedMarca = marcaDAO.findById(marca.getId());
        assertNull(deletedMarca);
    }

    @Test
    public void testFindById() {
        marcaDAO.save(marca);
        Marca foundMarca = marcaDAO.findById(marca.getId());
        assertNotNull(foundMarca);
        assertEquals(marca.getCodigo(), foundMarca.getCodigo());
    }

    @Test
    public void testFindAll() {
        marcaDAO.save(marca);
        List<Marca> marcas = marcaDAO.findAll();
        assertTrue(marcas.size() > 0);
    }
}
