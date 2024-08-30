package com.example;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.dao.CarroDAO;
import com.example.dao.AcessorioDAO;
import com.example.dao.MarcaDAO;
import com.example.domain.Carro;
import com.example.domain.Acessorio;
import com.example.domain.Marca;

public class CarroTest {

    private CarroDAO carroDAO;
    private AcessorioDAO acessorioDAO;
    private MarcaDAO marcaDAO;
    private List<Carro> carrosCriados;
    private List<Acessorio> acessoriosCriados;
    private List<Marca> marcasCriadas;

    @Before
    public void setUp() {
        carroDAO = new CarroDAO();
        acessorioDAO = new AcessorioDAO();
        marcaDAO = new MarcaDAO();
        carrosCriados = new ArrayList<>();
        acessoriosCriados = new ArrayList<>();
        marcasCriadas = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (Carro carro : carrosCriados) {
            if (carroDAO.findById(carro.getId()) != null) {
                carroDAO.delete(carro);
            }
        }
        for (Acessorio acessorio : acessoriosCriados) {
            if (acessorioDAO.findById(acessorio.getId()) != null) {
                acessorioDAO.delete(acessorio);
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

        Acessorio acessorio = new Acessorio();
        acessorio.setCodigo(generateCodigo());
        acessorio.setNome("Acessorio Teste");
        acessorio.setDescricao("Descricao Teste");

        acessorioDAO.save(acessorio);
        acessoriosCriados.add(acessorio);

        Carro carro = new Carro();
        carro.setCodigo(generateCodigo());
        carro.setModelo("Modelo Teste");
        carro.setAno(2022);
        carro.setMarca(marca);

        List<Acessorio> acessorios = new ArrayList<>();
        acessorios.add(acessorio);
        carro.setAcessorios(acessorios);

        carroDAO.save(carro);
        carrosCriados.add(carro);

        Carro savedCarro = carroDAO.findById(carro.getId());
        assertNotNull(savedCarro);
        assertEquals(carro.getCodigo(), savedCarro.getCodigo());
        assertEquals(1, savedCarro.getAcessorios().size());
    }

    @Test
    public void testUpdate() {
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

        carro.setModelo("Modelo Teste Atualizado");
        carroDAO.update(carro);

        Carro updatedCarro = carroDAO.findById(carro.getId());
        assertEquals("Modelo Teste Atualizado", updatedCarro.getModelo());
    }

    @Test
    public void testDelete() {
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

        Carro foundCarro = carroDAO.findById(carro.getId());
        assertNotNull(foundCarro);

        carroDAO.delete(carro);
        Carro deletedCarro = carroDAO.findById(carro.getId());
        assertNull(deletedCarro);
    }

    @Test
    public void testFindById() {
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

        Carro foundCarro = carroDAO.findById(carro.getId());
        assertNotNull(foundCarro);
        assertEquals(carro.getCodigo(), foundCarro.getCodigo());
    }

    @Test
    public void testFindAll() {
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

        List<Carro> carros = carroDAO.findAll();
        assertTrue(carros.size() > 0);
    }
}