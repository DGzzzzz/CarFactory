package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.dao.AcessorioDAO;
import com.example.dao.CarroDAO;
import com.example.dao.MarcaDAO;
import com.example.domain.Acessorio;
import com.example.domain.Carro;
import com.example.domain.Marca;

public class AcessorioTest {
    private AcessorioDAO acessorioDAO;
    private CarroDAO carroDAO;
    private MarcaDAO marcaDAO;
    private List<Acessorio> acessoriosCriados;
    private List<Carro> carrosCriados;
    private List<Marca> marcasCriadas;

    @Before
    public void setUp() {
        acessorioDAO = new AcessorioDAO();
        carroDAO = new CarroDAO();
        marcaDAO = new MarcaDAO();
        acessoriosCriados = acessorioDAO.findAll();
        carrosCriados = carroDAO.findAll();
        marcasCriadas = marcaDAO.findAll();
    }

    @After
    public void tearDown() {
        for (Acessorio acessorio : acessoriosCriados) {
            if (acessorioDAO.findById(acessorio.getId()) != null) {
                acessorioDAO.delete(acessorio);
            }
        }
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

        Acessorio acessorio = new Acessorio();
        acessorio.setCodigo(generateCodigo());
        acessorio.setNome("Acessorio Teste");
        acessorio.setDescricao("Descricao Teste");

        List<Carro> carros = new ArrayList<>();
        carros.add(carro);
        acessorio.setCarros(carros);

        List<Marca> marcas = new ArrayList<>();
        marcas.add(marca);
        acessorio.setMarcas(marcas);

        acessorioDAO.save(acessorio);
        acessoriosCriados.add(acessorio);

        Acessorio savedAcessorio = acessorioDAO.findById(acessorio.getId());
        assertNotNull(savedAcessorio);
        assertEquals(acessorio.getCodigo(), savedAcessorio.getCodigo());
    }
}
