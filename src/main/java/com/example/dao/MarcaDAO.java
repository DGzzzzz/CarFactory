package com.example.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.domain.Marca;

public class MarcaDAO implements IMarcaDAO {

    @Override
    public void save(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(marca);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(marca);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(marca);
        transaction.commit();
        session.close();
    }

    @Override
    public Marca findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Marca marca = session.get(Marca.class, id);
        session.close();
        return marca;
    }
}